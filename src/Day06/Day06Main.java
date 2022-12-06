package Day06;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Commons.CommonUtils;

public class Day06Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = CommonUtils.createBufferedReader("src/Day06/Day06Input.txt");
        processInput(br);
    }

    private static void processInput(BufferedReader br) throws IOException {
        String line = br.readLine();
        List<Character> listChar = line.chars().mapToObj(c -> (char) c).toList();
        process(new ArrayList<>(listChar), 0, 4);
        process(new ArrayList<>(listChar), 0, 14);
    }

    private static void process(ArrayList<Character> arrayList, int execution, int goal) {
        ArrayList<Character> subArrayList = new ArrayList<>();

        for (int i = 0; i < goal; i++) {
            subArrayList.add(arrayList.get(i));
        }
        if (testSubArrayList(subArrayList)) {
            System.out.println(goal + execution);
        } else {
            arrayList.remove(0);
            process(arrayList, execution + 1, goal);
        }
    }

    private static boolean testSubArrayList(ArrayList<Character> arrayList) {
        int arrayListSize = arrayList.size();

        if (arrayListSize == 1) {
            return true;
        }
        for (int i = 1; i < arrayListSize; i++) {
            if (arrayList.get(i).equals(arrayList.get(0))) {
                return false;
            }
        }
        arrayList.remove(0);
        return testSubArrayList(arrayList);
    }
}
