package com.hypinx.coding.capitalone.powerday.simplestorage.solutionclass;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

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
            {"GET_MOST_FILES", "0"},
            {"GET_MOST_FILES", "1"},
            {"GET_MOST_FILES", "2"},
            {"GET_MOST_FILES", "3"}
        };

        SimpleFileStorage simpleFileStorage = new SimpleFileStorage();
        for (String[] input : inputs) {
            simpleFileStorage.processFileOperation(input);
        }
    }
}

class FileObject {
    private String directory;
    private Set<String> files;
    // Level 3 --------------------------------------------------
    private int subFolders;

    public FileObject(String directory) {
        this.directory = directory;
        this.files = new HashSet<>();
    }

    public Set<String> getFiles() { return this.files; }

    public void addFile(String file) {
        this.files.add(file);
    }

    // Level 3 ---------------------------------------------------
    public void setSubFolders(int subFolders) {
        this.subFolders = subFolders;
    }

    public int getSubFolders() { return this.subFolders; }
}

class SimpleFileStorage {

    private Map<String, FileObject> fileStorage;

    public SimpleFileStorage() {
        this.fileStorage = new HashMap<>();
    }

    public void processFileOperation(String[] operation) {
        String action = operation[0].toUpperCase();
        switch (action) {
            case "ADD_FILE":
                System.out.println(this.addFile(operation[1]));
                break;
            case "DELETE_FILE":
                System.out.println(this.deleteFile(operation[1]));
                break;
            case "COPY":
                System.out.println(this.copy(operation[1], operation[2]));
                break;
            case "GET_MOST_FILES":
                this.topNDirectories(Integer.parseInt(operation[1]));
                break;
        }
    }

    public boolean addFile(String file) {
        String directory = getDirectory(file);
        String fileName = getFileName(file);

        FileObject fileObject = fileStorage.getOrDefault(directory, null);

        // First time we are adding the directory to the storage
        if (fileObject == null) {
            fileObject = new FileObject(directory);
            fileStorage.put(directory, fileObject);
        }

        // If the directory exists, check if the file is present. If so return false
        Set<String> filesInDirectory = fileObject.getFiles();
        if (filesInDirectory.contains(fileName)) {
            return false;
        }

        // Add the file to the file set and return true
        filesInDirectory.add(fileName);
        return true;
    }

    public boolean deleteFile(String file) {
        String directory = getDirectory(file);
        String fileName = getFileName(file);

        FileObject fileObject = fileStorage.getOrDefault(directory, null);

        // Directory does not exist, no file to delete
        if (fileObject == null) return false;

        // Directory exists but file not present. Nothing to delete
        Set<String> filesInDirectory = fileObject.getFiles();
        if (!filesInDirectory.contains(fileName)) {
            return false;
        }

        filesInDirectory.remove(fileName);
        return true;
    }

    public boolean copy(String fromFilePath, String toDirectory) {
        String fromDirectory = getDirectory(fromFilePath);
        String fileName = getFileName(fromFilePath);

        // TODO: Verify if these additional edge cases are really needed? The prompt only gave us 2
        // Edge Cases:
        // 1. Verify if the fromDirectory exists
        // 2. Verify file exists in fromDirectory
        // 3. Verify if file already exists in toDirectory
        // 4. Verify if toDirectory does not end with /
        // 5. Verify if toDirectory exists
        if (!fileStorage.containsKey(fromDirectory) ||
            !fileStorage.get(fromDirectory).getFiles().contains(fileName) ||
            !toDirectory.endsWith("/") ||
            !fileStorage.containsKey(toDirectory) ||
            fileStorage.get(toDirectory).getFiles().contains(fileName)) {
                return false;
        }

        // Make the copy by adding the file in the toDirectory
        Set<String> toDirectoryFileSet = fileStorage.get(toDirectory).getFiles();
        toDirectoryFileSet.add(fileName);
        return true;
    }

    public void topNDirectories(int n) {
        if (n == 0) {
            System.out.println("[]");
            return;
        }
        // Lower bound n based on itself or the total directories in the file storage to prevent index out of bound exception
        n = Math.min(n, fileStorage.size());

        // First loop through over all the file objects and calculate the number of subfolders
        for (Map.Entry<String, FileObject> entry : fileStorage.entrySet()) {
            int subFolderCount = findSubfolderCount(entry.getKey());
            entry.getValue().setSubFolders(subFolderCount);
        }

        // Sort all the key-value pairs based on file count + subfolder first and return a new linked hash map to maintain order
        LinkedHashMap<String, FileObject> sortedMapByItemSize = fileStorage.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // Compare by set size in descending order
                    FileObject entryOneFileObject = entry1.getValue();
                    FileObject entryTwoFileObject = entry2.getValue();
                    int sizeComparison = Integer.compare(entryTwoFileObject.getFiles().size() + entryTwoFileObject.getSubFolders(),
                            entryOneFileObject.getFiles().size() + entryOneFileObject.getSubFolders());
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

    private String getDirectory(String file) {
        int lastSlashIndex = file.lastIndexOf('/');
        // If no slashes exist, this means we are adding the file to the root directory
        if (lastSlashIndex == -1) {
            return "/";
        }

        // Get the substring from index 0 till the last slash
        String directory = file.substring(0, lastSlashIndex);

        // Check if the directory is leading with a /, otherwise prepend to the beginning
        if (!directory.startsWith("/")) directory = "/" + directory;

        return directory;
    }

    private String getFileName(String file) {
        int lastSlashIndex = file.lastIndexOf('/');
        // If no slashes exist, this means we have no preceeding directory hence the current string is the file name
        if (lastSlashIndex == -1) {
            return file;
        }

        // Get the substring from the last slash index + 1 till the end, that is the filename
        String fileName = file.substring(lastSlashIndex + 1);
        return fileName;
    }

    private int findSubfolderCount(String directory) {
        int subfolders = 0;

        for (Map.Entry<String, FileObject> entry : fileStorage.entrySet()) {
            String currentDirectory = entry.getKey();

            // To find the immediate subfolders, we want to check if the current directory starts with the entry and there are no further slash's that occur after
            // it starts with the prefix. If so, this is a nested directory this is not an immediate subfolder. So we can skip over it.
            if (currentDirectory.startsWith(directory) && currentDirectory.lastIndexOf('/') < directory.length() && !currentDirectory.equals(directory)) {
                subfolders++;
            }
        }

        return subfolders;
    }
}
