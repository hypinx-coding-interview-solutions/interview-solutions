package com.hypinx.coding.capitalone.powerday.simplestorage.treesolution;

import java.util.*;

/**
 * Ok so for this solution since we have 2 operations, we will need a way to distinguish which operation to perform. So I will use an interface to
 * define the operations possible. I'm going to implement the interface using another class like SimplifiedInMemoryStorage. For every input operation
 * I will use a for loop and create a method called processOperation to perform the actions for that operation.
 *
 * In terms of storage I am thinking to create a tree structure where the root node will be the root directory. In the 4th test case we have
 * the ability to add to a sub directory so this will be an efficient way to traverse the tree into the proper directories. I am thinking for
 * each node we will need things like currentDirectory to indicate what is the current directory, a set of Strings for the files, and then
 * a map with String as key and Node as the value. This will hold all the subdirectories.
 *
 * So I will start by creating the FileNode definition
 */

class FileNode {
    // Current directory is the name of the current directory
    String currentDirectory;
    // Files is a hashset since we can't have duplicate file names within a given directory
    Set<String> files = new HashSet<>();
    // Parent will hold reference to the direct parent of the subdirectory
    FileNode parent;
    Map<String, FileNode> subDirectories = new HashMap<>();

    // Everytime we have a FileNode we will have currentDirectory as mandatory field I'll make a constructor
    public FileNode(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    // Then we need some methods to access the fields of this class. So we will need to get the currentDirectory, subDirectories,
    // get all the files, and be able to set and get the parent element for when we do delete operations
    public String getCurrentDirectory() { return this.currentDirectory; }

    public Map<String, FileNode> getSubDirectories() { return this.subDirectories; }
    public Set<String> getFiles() { return this.files; }

    public void setParentNode(FileNode parent) {
        this.parent = parent;
    }

    public FileNode getParentNode() { return this.parent; }

}
public class Solution {

    // So in the main method I will use the format of 2D array and write in the input test cases
    public static void main (String[] args) {
        String operations[][] = new String[][]{
            // Level 1 ----------------------------------------------------
            {"ADD_FILE", "file_1"},             // true
            {"ADD_FILE", "file_1"},             // false
            {"ADD_FILE", "file_2"},             // true
            {"ADD_FILE", "dir_1/file_2"},       // true
            {"DELETE_FILE", "file_2"},          // true
            {"DELETE_FILE", "file_2"},          // false
            // Level 2 ----------------------------------------------------
            {"COPY", "/dir_1/file_2", "/"},     // true
            {"COPY", "/file_2", "/"},           // false
            {"COPY", "/file_3", "/dir_1/"},     // false
            {"COPY", "/file_3", "/dir_3"},      // false
            // Level 3 -----------------------------------------------------
            {"GET_MOST_FILES", "0"},            // []
            {"GET_MOST_FILES", "1"},            // ["/ (3)"]
            {"GET_MOST_FILES", "2"},            // ["/ (3)", "/dir_1/ (1)"]
            {"GET_MOST_FILES", "3"}             // ["/ (3)", "/dir_1/ (1)"]
        };

        // I'm going to create a object of simplifiedInMemory - I still have to define the interface which I'll do after
        SimplifiedInMemoryStorage simplifiedInMemoryStorage = new SimplifiedInMemoryStorage();
        // Then ill run a for loop for every operation and invoke a processOperation method
        for (String[] operation : operations) {
            simplifiedInMemoryStorage.processOperation(operation);
        }

        // So I'm thinking of storing the results of processOperation into a list of Strings which I will print at the end so I'll have a
        // utility method to do that called printResults.
        simplifiedInMemoryStorage.printResults();
    }
}

// So I'll define the interface
interface SimplifiedStorage {

    // For both addFile and deleteFile we are returning true or false whether the operation was success of not, so I'll use boolean
    // return type here. Each of these methods will take in the entire path with file name and we will extract the path and fileName
    // using a helper method
    // Level  ----------------------------------------------------
    boolean addFile(String file);

    boolean deleteFile(String file);
    // ------------------------------------------------------------
    // Level 2 ----------------------------------------------------

    // I will introduce a new method in the interface. It will take a fileFrom and fileTo. This will be the full path + fileName for the
    // from and to, we will split it up inside the copy method for the path and fileName
    boolean copy(String fileFrom, String fileTo);
    // ------------------------------------------------------------
    // Level 3 ----------------------------------------------------
    // For getMostFiles I will define another method which will take an int n. We will return a String as output.
    String getMostFiles(int n);
    // ------------------------------------------------------------

}

// So the SimplifiedInMemoryStorage will implement our interface
class SimplifiedInMemoryStorage implements SimplifiedStorage {

    // I'll create a root node by default and set it to the root directory
    private FileNode rootNode = new FileNode("/");
    // List of Strings to store results of every operation
    private List<String> results = new ArrayList<>();

    // Level 3 ------------------------------------------------------------
    // I will introduce a new map called mostFilesMap in the SimplifiedInMemoryStorage. This map will have String as key and Integer as value.
    // I am thinking of using a tree map. Basically this map will contain the directory as string and total number of files + sub directories
    // as the value. Since we are using a tree map it will keep the entries with higher value up top so we can iterate over the tree map and
    // the directories with most files will be on top
    private Map<String, Integer> mostFilesMap = new TreeMap<>();
    // --------------------------------------------------------------------

    // The process operation method will take an entire operation array
    public void processOperation(String[] operation) {

        // Extract operation type and lowercase to avoid case insensitivity issues
        String op = operation[0].toLowerCase();
        // Switch statement will have different cases as we will execute accordingly
        switch(op) {
            case "add_file":
                // I'm going to also create another method to convert the output to a string and then store. I'll use a generic method to take
                // an input of any kind and return a String.
                // STOP HERE AND IMPLEMENT convertToString METHOD DOWN BELOW (LAST METHOD IN FILE)
                // So now we can do results dot add and pass the output of addFile method into convertToString before storing. And for
                // addToFile I will pass in the entire path which is index 1 in operation array
                results.add(convertToString(addFile(operation[1])));
                break;
            // I will have a similar case setup for deleteFile also
            case "delete_file":
                results.add(convertToString(deleteFile(operation[1])));
                break;
            // Skip to default
            // Level 2 ------------------------------------------------------------------------------------------------------------------------
            // I will add in a new case in the switch statement for copy. Index 1 from operation array will have the from value and index 2 will have
            // to value
            case "copy":
                results.add(convertToString(copy(operation[1], operation[2])));
                break;
            // ------------------------------------------------------------------------------------------------------------------------
            // Level 3 ------------------------------------------------------------------------------------------------------------------------
            // I'm going add another case called get_most_files. The argument I will parse into an int since we are getting as String.
            case "get_most_files":
                results.add(convertToString(getMostFiles(Integer.parseInt(operation[1]))));
                break;
            // ------------------------------------------------------------------------------------------------------------------------
            // We should also use a default case that says something like operation not valid incase we get a bad operation type
            default:
                results.add("System operation not valid.");
        }
    }

    /**
     * So for addFile method we will have the entire path and file name come in. We will have 2 different utility methods to extract the path
     * and the fileName. Then starting from the root node I will use an empty string as the current path and traverse the path String to go down
     * the hierarchy. If the node at any point is null it means we don't have the path defined, so I'll use an overlaoded method called addFile
     * which will take the rootNode, path, and fileName and I'll build out that tree path.
     */
    @Override
    public boolean addFile(String file) {
        String path = extractPath(file);
        String fileName = extractFileName(file);
        // Before I go further I'll implement the utility methods
        // STOP HERE AND IMPLEMENT BOTH EXTRACT PATH AND EXTRACT FILE NAME METHOD THEN RETURN

        // Now we want to traverse the path from the root node until we can find the node in which to add the file. If the node is null
        // it means we don't have it and need to add it.
        FileNode node = findFileNode(this.rootNode, path);
        // So I'll implement the logic for findFileNode now
        // STOP HERE AND IMPLEMENT findFileNode METHOD
        if (node == null) {
            // If a node is null it means we haven't created the path in our tree yet
            addFile(this.rootNode, path, fileName);
            // Once we are done making all the sub nodes and adding the file we can return true
            return true;
        }

        // Otherwise if we found the node now we need to check if the file is already present. If so we return false, otherwise we can add
        // the file and return true
        if (!node.getFiles().contains(fileName)) {
            node.getFiles().add(fileName);
        } else {
            // File already present
            return false;
        }
        return true;
    }

    /**
     *
     * Now for the delete method we will have similar logic where we extract the path and fileName and find the node by using findFileNode method
     * If the node is null we can return false because it means that directory does not exist so we have no file to delete. If the node is not
     * false we can check if the file is present in the node's set. If not we can return false because we have no file to delete.
     */
    @Override
    public boolean deleteFile(String file) {
        String fileName = extractFileName(file);
        String path = extractPath(file);

        FileNode node = findFileNode(this.rootNode, path);
        if (node == null) return false;
        if (!node.getFiles().contains(fileName)) return false;

        // Now if the node was not null and we passed both checks, we know the file is present so we can remove the file and return true
        node.getFiles().remove(fileName);

        return true;
    }

    // Level 2 ------------------------------------------------------------------------------------------------------------------------

    /**
     * For the copy method we will have 2 arguments. Inside here we will extract the pathFrom, pathTo, and fileFrom name. Then we will call
     * findFileNode for the pathFrom and pathTo to find the nodes for both. If either of the nodes are null it means either of the directories
     * does not exist so we can't copy. Then we have to check if the pathTo ends with a backslash and finally if both nodes exist we need to check
     * if the file is present in fromNode or if the file is present in the toNode. If so we can return false.
     */
    public boolean copy(String from, String to) {
        String pathFrom = extractPath(from);
        String pathTo = extractPath(to);
        String fileFrom = extractFileName(from);

        FileNode fromNode = findFileNode(this.rootNode, pathFrom);
        FileNode toNode = findFileNode(this.rootNode, pathTo);

        // Check if toDirectory ends in backslash
        if (!pathTo.endsWith("/")) return false;
        // Either of the paths don't exist
        if (fromNode == null || toNode == null) return false;
        // Ensure file exists in the from directory or file already exists in destination return false
        if (!fromNode.getFiles().contains(fileFrom) || toNode.getFiles().contains(fileFrom)) return false;

        // Copy the file if all checks pass
        toNode.getFiles().add(fileFrom);
        return true;
    }
    // ------------------------------------------------------------------------------------------------------------------------

    // Level 3 ------------------------------------------------------------------------------------------------------------------------

    /**
     * Now for the getMostFiles we have an edge case, that is if n is 0 we can return an empty array. Otherwise I will use an overlaoded
     * method to recursively go down the tree starting from the root node. For every node we will append to a directoryPath that we will be building
     * and we will take the sum of all files and subDirectories present in that node and add to our tree map.
     *
     */
    public String getMostFiles(int n) {
        // Edge case
        if (n == 0) return "[]";
        return getMostFiles(n, this.rootNode, "");
    }
    // SCROLL DOWN AND IMPLEMENT OVERLOADED METHOD FOR getMostFiles
    // ------------------------------------------------------------------------------------------------------------------------

    // So this will be the overloaded method. We will take a currentNode since we will recursively call this method to build the path, the
    // path String which will be all the directory path leading up to the file, and the file name to add
    private void addFile(FileNode currentNode, String path, String fileName) {
        // Our exit case will be when the path is equal to backslash. Basically everytime we recusrively call this method we will shave off the
        // starting directory. So in the end we should only be left with a backslash
        if (path.equals("/")) {
            // If we are left with only backslash in the path we can get the files in the current node and add in the fileName and return
            currentNode.getFiles().add(fileName);
            return;
        }

        // Now to shave the directory from the beginning, I'll use string builder. Then I will loop from index 1 until I find the first occurrence
        // of a backslash to indicate this is the end of the current directory
        StringBuilder nextDirectory = new StringBuilder();
        int i = 1;
        while(i < path.length() && path.charAt(i) != '/') {
            nextDirectory.append(path.charAt(i++));
        }
        // Now that we have nextDirectory we can make a new FileNode and add it to the subDirectories of the current node.
        FileNode nextNode = new FileNode(nextDirectory.toString());
        currentNode.getSubDirectories().put(nextDirectory.toString(), nextNode);
        // We want to update the parent of the nextNode and set it equal to currentNode
        nextNode.setParentNode(currentNode);
        // Now we can recursively call addFile again and we take the substring starting from 1 + nextDirectory length because we just processed
        // that directory
        addFile(nextNode, path.substring(1 + nextDirectory.toString().length()), fileName);
    }

    // Level 3 ------------------------------------------------------------------------------------------------------------------------

    /**
     * So here I will implement the overloaded method which will take int n and a FileNode starting with root node
     */
    private String getMostFiles(int n, FileNode current, String currentPath) {
        currentPath += current.getCurrentDirectory();
        // First we find out the count of all files and sub directories
        int fileCount = current.getFiles().size() + current.getSubDirectories().size();
        // Now we can add to the tree map
        mostFilesMap.put(currentPath, fileCount);

        // For every subdirectory we can recursively call the getMostFiles method
        for (FileNode subNode : current.getSubDirectories().values()) {
            getMostFiles(n, subNode, currentPath);
        }

        // Now the tree map will be built. We can loop over the treemap and get the top n directories. However if n is very large then we could
        // get an out of bounds exception. So we will lower bound n to be itself or the size of the treemap
        n = Math.min(n, mostFilesMap.size());
        // I will use string builder to build out the string with the results
        StringBuilder result = new StringBuilder();
        // Looping over the tree map entries, for every entry I append to the string builder I will decrement n by 1. Once n is less than or equal
        // to 0 we can break out of the loop indicating we found the top n directories
        for (Map.Entry<String, Integer> entry : mostFilesMap.entrySet()) {
            if (n-- <= 0) break;
            result.append(entry.getKey() + " (" + entry.getValue() + ") ");
        }
        return result.toString();
    }
    // END OF CODING - So I think that is the solution for this.
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * For the findFileNode we will take a current FileNode, the path. Everytime we traverse down the subdirectories, we will remove the
     * directory at the start of the path. So in the end when path is equal to a backslash it will mean we traversed all the directories
     * and have reached our node of interest and then return
     */
    private FileNode findFileNode(FileNode currentNode, String path) {
        // If the path generated matches the path we are searching for, return the current node
        if (path.equals("/")) {
            return currentNode;
        }

        StringBuilder nextDirectory = new StringBuilder();
        int i = 1;
        while(i < path.length() && path.charAt(i) != '/') {
            nextDirectory.append(path.charAt(i++));
        }
        // Node we can search the subDirectories to see if the nextDirectory exists. If we get back null then it's not present and we can
        // return null. Otherwise we can recursively call findFileNode with the sub directory node and join back the path with a backslash
        // delimiter. Since we set the first index to an empty string we will automatically shave this off the path
        FileNode nextNode = currentNode.getSubDirectories().get(nextDirectory.toString());
        if (nextNode != null) {
            return findFileNode(nextNode, path.substring(1 + nextDirectory.toString().length()));
        }

        return null;
    }

    // So the extract file name we can take the substring of the entire file from the last index of the backslash to the end. This will be the
    // file name
    private String extractFileName(String file) {

        // If backslashes are present then it means the file name occurs after the last backslash
        return file.substring(file.lastIndexOf("/") + 1);
    }

    // For the file path we have a few edge cases. If the file is already at the root level we will not have a backslash so we need to check
    // if the file String contains a backslash, or if the size is 1 meaning we only have the root directory and no files. In this case we return
    // a backslash to indicate it's at the root.
    // The other scenario is if the first character is not a backslash, we want to append it to be consistent with how we will parse the strings.
    // And finally we can return the substring from index 0 to 1 plus the last index of the backslash which will contain the entire directory path
    private String extractPath(String file) {
        // If no backslashes exist then the path is the root path
        if (!file.contains("/") || file.length() == 1) return "/";

        // Append / to front to indicate root path
        if (file.charAt(0) != '/') file = "/" + file;

        // path will from the beginning of the string to the last backslash
        return file.substring(0, file.lastIndexOf("/") + 1);
    }

    public void printResults() {
        for (String result: this.results) {
            System.out.println(result);
        }
    }

    // So the convertToString method will take any generic object and we will just call toString() method to return it's contents
    private <T> String convertToString(T object) {
        return object.toString();
    }
}