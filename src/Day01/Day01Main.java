package Day01;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = createBufferedReader("src/Day01/Day01Input.txt");
        ArrayList caloriesArrayList = inputToIntegerArrays(bufferedReader);
        finalResultPartOne(caloriesArrayList);
        finalResultPartTwo(caloriesArrayList);

    }

    private static BufferedReader createBufferedReader(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);

        return new BufferedReader(fileReader);

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
        int biggestCalories = 0;
        int index = 0;
        int arraySize = arrayList.size();

        for (int i = 0; i < arrayList.size(); i++ ) {
            int calories = arrayList.get(i);
            if (calories > biggestCalories) {
                biggestCalories = calories;
                index = i + 1;
            }
        }

        System.out.println("The little cocaine smuggler with most calories is number: " + index + ", with " + biggestCalories + " cal");
    }

    private static void finalResultPartTwo(ArrayList<Integer> arrayList) {

        int numberOfElvesToFind = 3;
        
        int arraySize = arrayList.size();
        ArrayList<Integer> topThreeCalories = new ArrayList<Integer>();

        for (int i = 0; i < arraySize; i++ ) {
            int calories = arrayList.get(i);
            boolean add = false;

            for(int caloriesInTop: topThreeCalories) {
                if (calories > caloriesInTop) {
                    add = true;
                }
            }

            if (add && topThreeCalories.size() == 3) {
                topThreeCalories.add(calories);
                Collections.sort(topThreeCalories, Collections.reverseOrder());
                topThreeCalories.remove(topThreeCalories.size() - 1);
            } else if(topThreeCalories.size() < 3) {
                topThreeCalories.add(calories);
            }

        }

        System.out.println(returnStringResult(topThreeCalories));
    }

    private static String returnStringResult(ArrayList<Integer> arrayCalorias) {
        String result = "El resultado final es: 1-> ";
        result += Integer.toString(arrayCalorias.get(0)) + " 2-> ";
        result += Integer.toString(arrayCalorias.get(1)) + " 3-> ";
        result += Integer.toString(arrayCalorias.get(2)) + " COMBINED: "
                + (Integer.toString(arrayCalorias.get(0) + arrayCalorias.get(1) + arrayCalorias.get(2)));

        return result;
    }
}