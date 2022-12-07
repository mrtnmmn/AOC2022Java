package Day07;

import java.util.ArrayList;

public class Directory {

    public ArrayList<Integer> files;
    public ArrayList<Directory> directories;
    public String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
    }

    public void addDirectory(ArrayList<String> path, String name) {
        if (path.size() == 0) {
            /*
            boolean exists = false;
            for (Directory d: directories) {
                if (d.getName().equals(path.get(0))) {
                    exists = true;
                }
            }
            if (!exists) {
                directories.add(new Directory(name));
            }
             */
            directories.add(new Directory(name));
        } else {
            for (Directory d: directories) {
                if (d.getName().equals(path.get(0))) {
                    ArrayList<String> auxPath = (ArrayList<String>) path.clone();
                    auxPath.remove(0);
                    d.addDirectory(auxPath, name);
                }
            }
        }
    }

    public void addFile(ArrayList<String> path, int size) {
        if (path.size() == 0) {
            files.add(size);
        } else {
            for (Directory d: directories) {
                if (d.getName().equals(path.get(0))) {
                    ArrayList<String> auxPath = (ArrayList<String>) path.clone();
                    auxPath.remove(0);
                    d.addFile(auxPath, size);
                }
            }
        }
    }

    public int getFileSizes() {
        int result = 0;
        for (int file: files) {
            result += file;
        }
        return result;
    }
}