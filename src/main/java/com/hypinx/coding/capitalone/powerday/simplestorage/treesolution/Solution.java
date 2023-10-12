package com.hypinx.coding.capitalone.powerday.simplestorage.treesolution;

import java.util.*;
public class Solution {

    public static void main (String[] args) {
        String operations[][] = new String[][]{
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

        SimplifiedInMemoryStorage simplifiedInMemoryStorage = new SimplifiedInMemoryStorage();
        for (String[] operation : operations) {
            System.out.println(operation[0] + " | " + operation[1]);
            simplifiedInMemoryStorage.processOperation(operation);
        }

        simplifiedInMemoryStorage.printResults();
    }
}

interface SimplifiedStorage {

    boolean addFile(String file);

    boolean deleteFile(String file);

    boolean copy(String fileFrom, String fileTo);

    String getMostFiles(int n);

}

class SimplifiedInMemoryStorage implements SimplifiedStorage {

    // Create root node using root directory
    private FileNode rootNode = new FileNode("/");
    // Create list to store results from each operation
    private List<String> results = new ArrayList<>();

    private Map<String, Integer> mostFilesMap = new TreeMap<>();

    public void processOperation(String[] operation) {
        // Array used to hold the output for processing each operation. Will convert output to a string and store

        // Extract operation type and lowercase to avoid case insensitivity issues
        String op = operation[0].toLowerCase();
        switch(op) {
            case "add_file":
                results.add(convertToString(addFile(operation[1])));
                break;
            case "delete_file":
                results.add(convertToString(deleteFile(operation[1])));
                break;
            case "copy":
                results.add(convertToString(copy(operation[1], operation[2])));
                break;
            case "get_most_files":
                results.add(convertToString(getMostFiles(Integer.parseInt(operation[1]))));
                break;
            default:
                results.add("System operation not valid.");
        }
    }

    @Override
    public boolean addFile(String file) {
        // Fail if file already exists in given path
        String path = extractPath(file);
        String fileName = extractFileName(file);

        // Traverse from root node to the end of the path
        FileNode node = findFileNode(this.rootNode, path, "");
        if (node == null) {
            // If a node is null it means we haven't created the path in our tree yet
            addFile(this.rootNode, path, fileName);
            return true;
        }

        if (!node.getFiles().contains(fileName)) {
            node.getFiles().add(fileName);
        } else {
            // File already present
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(String file) {
        String fileName = extractFileName(file);
        String path = extractPath(file);

        FileNode node = findFileNode(this.rootNode, path, "");
        if (node == null) return false;
        if (!node.getFiles().contains(fileName)) return false;

        node.getFiles().remove(fileName);
        // All files were removed from this directory, we can remove the reference from parent node
        if (node.getFiles().size() == 0) {
            node.getParentNode().subDirectories.remove(node.getCurrentDirectory());
        }

        return true;
    }

    public boolean copy(String from, String to) {
        String pathFrom = extractPath(from);
        String pathTo = extractPath(to);
        String fileFrom = extractFileName(from);

        FileNode fromNode = findFileNode(this.rootNode, pathFrom, "");
        FileNode toNode = findFileNode(this.rootNode, pathTo, "");

        // Either of the paths don't exist
        if (fromNode == null || toNode == null) return false;
        // Ensure file exists in the from directory or file already exists in destination return false
        if (!fromNode.getFiles().contains(fileFrom) || toNode.getFiles().contains(fileFrom)) return false;

        // Copy the file
        toNode.getFiles().add(fileFrom);
        return true;
    }

    public String getMostFiles(int n) {
        if (n == 0) return "[]";
        return getMostFiles(n, this.rootNode);
    }

    private void addFile(FileNode currentNode, String path, String fileName) {
        if (path.equals("/")) {
            currentNode.getFiles().add(fileName);
            return;
        }
        // Start with the root node, from the path we want to find the first directory name
        StringBuilder nextDirectory = new StringBuilder();
        int i = 1;
        while(i < path.length() && path.charAt(i) != '/') {
            nextDirectory.append(path.charAt(i++));
        }
        FileNode nextNode = new FileNode(nextDirectory.toString());
        currentNode.getSubDirectories().put(nextDirectory.toString(), nextNode);
        nextNode.setParentNode(currentNode);
        addFile(nextNode, path.substring(1 + nextDirectory.toString().length()), fileName);
    }

    private String getMostFiles(int n, FileNode current) {

        int fileCount = current.getFiles().size() + current.getSubDirectories().size();
        mostFilesMap.put(current.getCurrentDirectory(), fileCount);

        for (FileNode subNode : current.getSubDirectories().values()) {
            getMostFiles(n, subNode);
        }

        n = Math.min(n, mostFilesMap.size());
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : mostFilesMap.entrySet()) {
            if (n <= 0) break;
            result.append(entry.getKey() + " (" + entry.getValue() + ") ");
        }
        return result.toString();
    }

    private FileNode findFileNode(FileNode currentNode, String path, String currentPath) {
        currentPath += currentNode.getCurrentDirectory();
        // If the path generated matches the path we are searching for, return the current node
        if (currentPath.equals(path)) {
            return currentNode;
        }

        String[] directories = path.split("/");
        directories[0] = "";
        String nextDirectory = directories[1];
        FileNode nextNode = currentNode.getSubDirectories().get(nextDirectory);
        if (nextNode != null) {
            return findFileNode(nextNode, String.join("/", directories), currentPath);
        }

        return null;
    }

    private String extractFileName(String file) {

        // If backslashes are present then it means the file name occurs after the last backslash
        return file.substring(file.lastIndexOf("/") + 1);
    }

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

    private <T> String convertToString(T object) {
        return object.toString();
    }
}

class FileNode {
    String currentDirectory;
    Set<String> files = new HashSet<>();
    FileNode parent;
    Map<String, FileNode> subDirectories = new HashMap<>();

    public FileNode(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getCurrentDirectory() { return this.currentDirectory; }

    public Map<String, FileNode> getSubDirectories() { return this.subDirectories; }
    public Set<String> getFiles() { return this.files; }

    public void setParentNode(FileNode parent) {
        this.parent = parent;
    }

    public FileNode getParentNode() { return this.parent; }

}