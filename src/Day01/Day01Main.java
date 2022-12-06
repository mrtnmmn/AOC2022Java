package Day01;

import Commons.CommonUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = CommonUtils.createBufferedReader("src/Day01/Day01Input.txt");
        ArrayList caloriesArrayList = inputToIntegerArrays(bufferedReader);
        finalResultPartOne(caloriesArrayList);
        finalResultPartTwo(caloriesArrayList);

    }

    private static ArrayList inputToIntegerArrays(BufferedReader br) throws IOException {

        ArrayList<Integer> arrayListCalories = new ArrayList<Integer>();
        boolean continueReading = true;
        int currentElfCalories = 0;

        while (continueReading) {
            String line = br.readLine();

            if (line == null) {
                continueReading = false;
            } else {
                if (line.length() > 0) {
                    currentElfCalories += Integer.parseInt(line);
                } else {
                    arrayListCalories.add(currentElfCalories);
                    currentElfCalories = 0;
                }
            }
        }

        arrayListCalories.add(currentElfCalories);

        return arrayListCalories;

    }

    private static void finalResultPartOne(ArrayList<Integer> arrayList) {

        arrayList.sort(Collections.reverseOrder());

        System.out.println(arrayList.get(0));
    }

    private static void finalResultPartTwo(ArrayList<Integer> arrayList) {
        arrayList.sort(Collections.reverseOrder());
        int result = arrayList.get(0) + arrayList.get(1) + arrayList.get(2);
        System.out.println(result);
    }

}