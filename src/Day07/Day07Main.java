package Day07;

import Commons.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day07Main {

    public static int dirMaxSize = 100000;
    public static int acumulation = 0;
    public static final int minDeletionSize = 30000000;
    public static ArrayList<Integer> dirSizes = new ArrayList<>();
    public static final int maxSpace = 70000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = CommonUtils.createBufferedReader("src/Day07/Day07Input.txt");
        Directory mainDirectory = new Directory("main");
        parseInput(br, mainDirectory);
        countData(mainDirectory);
        System.out.println(acumulation);
        getDirToDelete();
    }

    public static void parseInput(BufferedReader br, Directory mainDirectory) throws IOException {

        ArrayList<String> path = new ArrayList<>();
        boolean keepReading = true;

        while (keepReading) {
            String line = br.readLine();

            if (line == null) {
                keepReading = false;
            } else {
                if ((line.charAt(0)) == '$'){
                    String[] commandLine = line.split(" ");
                    if (commandLine[1].equals("cd")) {
                        switch (commandLine[2]) {
                            case ".." -> path.remove(path.size() - 1);
                            case "/" -> path = new ArrayList<>();
                            default -> path.add(commandLine[2]);
                        }
                    }
                }
                else {
                    String[] commandLine = line.split(" ");
                    if (commandLine[0].equals("dir")) {
                        mainDirectory.addDirectory(path, commandLine[1]);
                    } else {
                        mainDirectory.addFile(path, Integer.parseInt(commandLine[0]));
                    }
                }
            }
        }
    }

    public static int countData(Directory directory) {
        if(directory.directories.size() != 0) {
            int addedSizes = 0;
            for (Directory dir: directory.directories) {
                addedSizes += countData(dir);
            }
            addedSizes += directory.getFileSizes();
            if (addedSizes < dirMaxSize) {
                acumulation += addedSizes;
            }
            dirSizes.add(addedSizes);
            return addedSizes;
        } else {
            int fileSizes = directory.getFileSizes();
            if (fileSizes < dirMaxSize) {
                acumulation += fileSizes;
            }
            dirSizes.add(fileSizes);
            return fileSizes;
        }
    }

    public static void getDirToDelete() {
        Collections.sort(dirSizes);
        int unusedSpace = maxSpace - dirSizes.get(dirSizes.size()-1);
        for (Integer dir: dirSizes) {
            if (dir + unusedSpace >= minDeletionSize) {
                System.out.println(dir);
                break;
            }
        }
    }
}
