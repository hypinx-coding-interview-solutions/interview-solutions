package com.hypinx.coding.capitalone.powerday.simplestorage.solutionclass;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Initial Questions:
     * 1. The first input for ADD_FILE, we only have a file name. So we can assume this will be stored in a root directory correct? And for the 4th
     * input we have directory 1 followed by the file. So this means from the root directory, go into directory 1 and add the file there?
     * 2. In terms of input validation, can we assume that the input will be in the correct format already and there is no need to do any further
     * data validation (e.g what if an unsupported operation is provided, or check for null values?)
     * 3. For the output are we just printing to the console directly?
     *
     * Approach to solving:
     * Ok i think I am clear on the problem statement. Basically we are adding files to directories and then deleting. The approach I am thinking to this
     * is that for each directory we will need to maintain a record of what files are present inside. So we can create a model class called FileObject
     * which will hold a directory as a String and a set of files.
     *
     * Then we can have another class called SimpleFileStorage which will use a map for a in memory storage, the key can be the directory and the value
     * can be the corresponding FileObject. We can have a method called processFileOperation and then from the main method we can pass one operation
     * at a time to process for the FileSystem. And within this process method we can use a switch statement to check whether we want to do a ADD or DELETE
     * operation and then invoke a helper method accordingly to perform the action.
     *
     * LEVEL 2 - DO NOT SAY UNTIL I HOVER OVER --------------------------------------------
     *
     * Ok so I read over the requirements for copy. We are given a fromFilePath which contains the
     * directory and the file itself and a destination directory. We want to copy the file over while
     * verifying that the file already does not exist in the destination and the toDirectory ends with
     * a forward slash indicating its a directory.
     *
     * So I can update the switch statement with another case and then have another helper method for
     * copy. So let me start by doing that
     *
     * LEVEL 3 - DO NOT SAY UNTIL I HOVER OVER --------------------------------------------
     *
     * Ok so I get the problem statement for GET_MOST_FILES. Essentially we want to list the top N
     * directories which contain the most subfolders plus the files in it. So nested directories within
     * the subFolder do not count.
     *
     * So I am thinking for this what we can do is introduce a new variable in the FileObject class
     * called subFolders. And we do one loop over all the FileObjects and we populate this subFolder
     * value. This will be the sum of the current files in the directories plus all the immediate
     * directories present. So for this we will need another helper function and we will have to do
     * another loop to count all the subdirectories we have.
     *
     * And then once we populate the subFolders value we want to another traversal and use a comparator
     * function to sort based on the total count, if theres a tie we can sort alphabetically by the
     * directory name.
     *
     * And then finally loop over the first N sorted objects. Also we need to check the value for N to
     * see if its greater than the total directories we have, otherwise we could run into an index out
     * of bounds issue when we are printing the output. So this is the approach I am thinking.
     *
     * Let me start by updating the input in the main method and then updating the switch statement to
     * add another case.
     *
     */
    public static void main(String[] args) {
        String[][] inputs = new String[][]{
            // Level 1 ----------------------------
            {"ADD_FILE", "file_1"},                     // true
            {"ADD_FILE", "file_1"},                     // false
            {"ADD_FILE", "file_2"},                     // true
            {"ADD_FILE", "dir_1/file_2"},               // true
            {"DELETE_FILE", "file_2"},                  // true
            {"DELETE_FILE", "file_2"},                  // false
            // Level 2 ----------------------------
            {"COPY", "/dir_1/file_2", "/"},             // true
            {"COPY", "/file_2", "/"},                   // false
            {"COPY", "/file_3", "/dir_1/"},             // false
            {"COPY", "/file_3", "/dir_3"},              // false
            // Level 3 ----------------------------
            {"GET_MOST_FILES", "0"},                    // []
            {"GET_MOST_FILES", "1"},                    // ["/ (3)"]
            {"GET_MOST_FILES", "2"},                    // ["/ (3)", "/dir_1/ (1)"]
            {"GET_MOST_FILES", "3"}                     // ["/ (3)", "/dir_1/ (1)"]
        };

        // DO NOT SAY UNTIL I HOVER OVER THIS
        // Ok so first we create an instance of simple file storage and then with a for each loop go over every input
        // and call the processFileOperation method
        SimpleFileStorage simpleFileStorage = new SimpleFileStorage();
        for (String[] input : inputs) {
            simpleFileStorage.processFileOperation(input);
        }
    }
}

/**
 * So I'll create the FileObject model class now, this will have a String for directory
 * and a Set of Strings for all the files it currently contains.
 */
class FileObject {
    // Level 1 --------------------------------------------------
    private String directory;
    private Set<String> files;
    // Level 3 --------------------------------------------------
    // Ok so in FileObject I'll add variables subFolders and totalFilesAndFolders and then add
    // getter and setter method for both of these
    private int subFolders;
    private int totalFilesAndFolders;

    /**
     * We'll have a constructor with a required argument for directory and inside here
     * we can initialize an empty set
     */
    public FileObject(String directory) {
        this.directory = directory;
        this.files = new HashSet<>();
    }

    public Set<String> getFiles() { return this.files; }

    // Level 3 ---------------------------------------------------
    public void setSubFolders(int subFolders) {
        this.subFolders = subFolders;
    }

    public int getSubFolders() { return this.subFolders; }

    public void setTotalFilesAndFolders(int totalFilesAndFolders) {
        this.totalFilesAndFolders = totalFilesAndFolders;
    }

    public int getTotalFilesAndFolders() {
        return this.totalFilesAndFolders;
    }
}

/**
 * Now I'll make the SimpleFileStorage class. I'll have a map with directory string as the key and the corresponding
 * FileObject as the value. In the constructor we can initialize the map
 */
class SimpleFileStorage {

    private Map<String, FileObject> fileStorage;

    public SimpleFileStorage() {
        this.fileStorage = new HashMap<>();
    }

    /**
     * In the processFileOperation method we will take the operation to process.
     * We can extract the operation type which is the first argument and switch
     * based on that. I am going to force this string to uppercase just to avoid
     * any case insensitivity issues
     *
     * WAIT TILL I HOVER OVER THE BOTTOM PIECE BEFORE READING IT
     *
     * Ok so now that we setup our cases, let me add the helper methods for add
     * file and delete file and then we can invoke the methods.
     */
    public void processFileOperation(String[] operation) {
        String action = operation[0].toUpperCase();
        switch (action) {
            case "ADD_FILE":
                System.out.println(this.addFile(operation[1]));
                break;
            case "DELETE_FILE":
                System.out.println(this.deleteFile(operation[1]));
                break;
                // Ok so the switch is updated. I need to go in the main method and call processFileOperation for the inputs
            // Level 2 --------------------------------------------------------------
            case "COPY":
                // Ok so the copy method will take 2 arguments, the from and to arguments
                System.out.println(this.copy(operation[1], operation[2]));
                // Now let me add the copy helper method
                break;
            // Level 3 --------------------------------------------------------------
            case "GET_MOST_FILES":
                this.topNDirectories(Integer.parseInt(operation[1]));
                break;
                // Ok so now let me add the helper method to get topNDirectories
        }
    }

    /**
     * So for the add method we want to first verify if the file already exists or not.
     * If it exists then we return false and if the file is not there we can add it then return true.
     *
     * We will need two other utility methods to extract the directory from the file and
     * the fileName since we have the absolute path of the file given to us. So I will first
     * add 2 methods which we can call getDirectory and getFileName.
     */
    public boolean addFile(String file) {
        // First we split the directory and file name from each other
        String directory = getDirectory(file);
        String fileName = getFileName(file);

        // We want to check if we have this directory in our storage
        FileObject fileObject = fileStorage.getOrDefault(directory, null);

        // If fileObject is null then this is the first time we are adding this directory
        if (fileObject == null) {
            fileObject = new FileObject(directory);
            fileStorage.put(directory, fileObject);
        }

        // Otherwise the directory exists, we check if the file is present. If so return false
        Set<String> filesInDirectory = fileObject.getFiles();
        if (filesInDirectory.contains(fileName)) {
            return false;
        }

        // At this point the directory is present and the file does not exist, so we add it in and return true
        filesInDirectory.add(fileName);
        return true;
    }

    /**
     * For deleteFile we have a similar approach to addFile. We split the directory and fileName,
     * then check the storage. If fileObject is null it means the directory doesn't exist so we
     * have nothing to delete and can return false. Otherwise we check the set to
     * see if the file exists. If it doesn't then again we have nothing to delete so return false.
     */
    public boolean deleteFile(String file) {
        String directory = getDirectory(file);
        String fileName = getFileName(file);

        FileObject fileObject = fileStorage.getOrDefault(directory, null);

        if (fileObject == null) return false;

        Set<String> filesInDirectory = fileObject.getFiles();
        if (!filesInDirectory.contains(fileName)) {
            return false;
        }

        // And finally the directory exists and the file is present, so we can remove and return true
        filesInDirectory.remove(fileName);
        return true;
        // So now let me go back to the switch case and invoke addFile and deleteFile and print the output
    }

    public boolean copy(String fromFilePath, String toDirectory) {
        String fromDirectory = getDirectory(fromFilePath);
        String fileName = getFileName(fromFilePath);

        // So for copy method we were to verify 2 cases: first being the toDirectory ends with a forward
        // slash and the second that the file does not already exist in the destination. I think in
        // addition to these 2, we also want to verify if the from directory exists and if the file
        // we are trying to copy exists as well. And lastly the toDirectory exists. I think all 5 of
        // these scenarios is needed to safely copy the file over.
        // Edge Cases:
        // 1. Verify if the fromDirectory exists
        // 2. Verify file exists in fromDirectory
        // 3. Verify if toDirectory does not end with /
        // 4. Verify if toDirectory exists
        // 5. Verify if file already exists in toDirectory
        if (!fileStorage.containsKey(fromDirectory) ||
            !fileStorage.get(fromDirectory).getFiles().contains(fileName) ||
            !toDirectory.endsWith("/") ||
            !fileStorage.containsKey(toDirectory) ||
            fileStorage.get(toDirectory).getFiles().contains(fileName)) {
                return false;
        }

        // Once we get past our verifications we can now safely copy the file over
        Set<String> toDirectoryFileSet = fileStorage.get(toDirectory).getFiles();
        toDirectoryFileSet.add(fileName);
        return true;
    }

    public void topNDirectories(int n) {
        // Ok so first we have an edge case, where if we are asking for 0 then we show an empty array
        if (n == 0) {
            System.out.println("[]");
            return;
        }

        // Lower bound n based on itself or the total directories in the file storage to prevent index out of bound exception
        n = Math.min(n, fileStorage.size());

        // First loop through over all the file objects and calculate the number of subfolders
        for (Map.Entry<String, FileObject> entry : fileStorage.entrySet()) {
            FileObject current = entry.getValue();
            int subFolderCount = findSubfolderCount(entry.getKey());
            // So here I will use a helper method to find all the subfolders based on the given directory
            // Let me implement that after
            current.setSubFolders(subFolderCount);
            current.setTotalFilesAndFolders(subFolderCount + current.getFiles().size());
        }

        // Sort all the key-value pairs based on file count + subfolder first and return a new linked hash map to maintain order
        LinkedHashMap<String, FileObject> sortedMapByItemSize = fileStorage.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // Compare by set size in descending order
                    FileObject entryOneFileObject = entry1.getValue();
                    FileObject entryTwoFileObject = entry2.getValue();
                    int sizeComparison = Integer.compare(entryOneFileObject.getTotalFilesAndFolders(),
                            entryTwoFileObject.getTotalFilesAndFolders());
                    // If there is no tie, return the result of the compare
                    if (sizeComparison != 0) {
                        return sizeComparison;
                    }
                    return entry1.getKey().compareTo(entry2.getKey());
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        // Incase there are duplicate keys, return the first. This scenario shouldn't happen in our case
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        int counter = 0;
        for (Map.Entry<String, FileObject> entry : sortedMapByItemSize.entrySet()) {
            if (counter == n) break;
            FileObject current = entry.getValue();
            int totalFileCount = current.getFiles().size() + current.getSubFolders();
            System.out.print(entry.getKey() + " (" + totalFileCount + ")," + " ");
            counter++;
        }
        System.out.println();

    }

    /**
     * So the getDirectory method will take the entire file path. Basically everything up until
     * the last forward slash will be the directory. So we can find the lastIndex of the forward
     * slash and make a substring from index 0 to index. In the event we are adding to the root
     * directory we will not have a forward slash so this will return -1. So we can check if it's -1
     * then we simply return a forward slash which tells us this is going in the root directory.
     */
    private String getDirectory(String file) {
        int lastSlashIndex = file.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            return "/";
        }

        String directory = file.substring(0, lastSlashIndex);

        if (!directory.startsWith("/")) directory = "/" + directory;
        // So here I added a check to ensure the directory starts with a forward slash, for
        // the 4th input we are missing it and I'm doing this for consistency since every
        // directory should start from the root.

        return directory;
    }

    /**
     * The getFileName method will be similar to getDirectory. Basically we want to find the last forward slash index and take the
     * substring from there till the end of the string, this will be the file name. We have a situation where if there is no forward
     * slash then that means the current string we have is the file name
     */
    private String getFileName(String file) {
        int lastSlashIndex = file.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            return file;
        }

        String fileName = file.substring(lastSlashIndex + 1);
        return fileName;
        // Ok so now I'll go back to our addFile method and we can use these 2 utility methods there
    }

    private int findSubfolderCount(String directory) {
        int subfolders = 0;

        for (Map.Entry<String, FileObject> entry : fileStorage.entrySet()) {
            String currentDirectory = entry.getKey();

            // To find the immediate subfolders, we want to check if the current directory starts with
            // the entry and there are no further slash's occur after
            // it starts with the prefix. If so, this is a nested directory this is not an
            // immediate subfolder. So we can skip over it. And we also want to verify the current directory
            // is not the same one
            if (currentDirectory.startsWith(directory) &&
                    currentDirectory.lastIndexOf('/') < directory.length() &&
                    !currentDirectory.equals(directory)) {
                subfolders++;
            }
        }

        return subfolders;
    }
}
