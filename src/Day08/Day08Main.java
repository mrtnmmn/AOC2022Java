package Day08;

import Commons.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day08Main {

    public static ArrayList<ArrayList<Integer>> processedInput = new ArrayList<>();
    public static int height = 0;
    public static int width = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = CommonUtils.createBufferedReader("src/Day08/Day08Input.txt");
        parseInput(br);
        System.out.println(getArrayVisibility());
        System.out.println(getSolutionStage2());
    }

    public static void parseInput(BufferedReader br) throws IOException {
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            } else {
                processedInput.add(stringToArraylistInteger(line));
            }
        }
        height = processedInput.size();
        width = processedInput.get(0).size();
    }

    public static ArrayList<Integer> stringToArraylistInteger(String line) {
        int lineLength = line.length();
        ArrayList<Integer> lineIntegers = new ArrayList<>();
        for (int i = 0; i < lineLength; i++) {
            if (i == lineLength - 1) {
                lineIntegers.add(Integer.parseInt(line.substring(i)));
            } else {
                lineIntegers.add(Integer.parseInt(line.substring(i, i+1)));
            }
        }
        return lineIntegers;
    }

    public static int getGridPerimeter() {
        return ((height-2) * 2 + (width - 2) * 2 + 4);
    }

    public static int getArrayVisibility() {
        int totalVisibility = getGridPerimeter();
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                totalVisibility += checkCellVisibility(j,i);
            }
        }
        return totalVisibility;
    }

    public static int checkCellVisibility(int x, int y) {
        int visibility = 0;
        visibility += checkHorizontal(x, y);
        visibility += checkVertical(x, y);
        if (visibility == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int checkHorizontal(int x, int y) {
        ArrayList<Integer> row = processedInput.get(y);
        ArrayList<Integer> rowBefore = new ArrayList<>();
        ArrayList<Integer> rowAfter = new ArrayList<>();
        int size = processedInput.size();
        for (int i = 0; i < size; i++) {
            if (i < x) {
                rowBefore.add(row.get(i));
            } else if( i > x){
                rowAfter.add(row.get(i));
            }
        }
        rowBefore.sort(Collections.reverseOrder());
        rowAfter.sort(Collections.reverseOrder());
        int cellValue = row.get(x);
        if (cellValue > rowBefore.get(0) || cellValue > rowAfter.get(0)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int checkVertical(int x, int y) {
        ArrayList<Integer> columnBefore = new ArrayList<>();
        ArrayList<Integer> columnAfter = new ArrayList<>();
        int size = processedInput.size();
        for (int i = 0; i < size; i++) {
            if (i < y) {
                columnBefore.add(processedInput.get(i).get(x));
            } else if( i > y){
                columnAfter.add(processedInput.get(i).get(x));
            }
        }
        columnBefore.sort(Collections.reverseOrder());
        columnAfter.sort(Collections.reverseOrder());
        int cellValue = processedInput.get(y).get(x);
        if (cellValue > columnBefore.get(0) || cellValue > columnAfter.get(0)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getSolutionStage2() {
        int maxValue = 0;
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int currentCellVisibility = processCellStage2(j,i);
                if (currentCellVisibility > maxValue) {
                    maxValue = currentCellVisibility;
                }
            }
        }
        return maxValue;
    }

    public static int processCellStage2(int x, int y) {

        int[] viewingDistance = new int[4];
        boolean keepProcessing = true;

        int currentCellValue = processedInput.get(y).get(x);

        ArrayList<Integer> row = processedInput.get(y);
        ArrayList<Integer> rowBefore = new ArrayList<>();
        ArrayList<Integer> rowAfter = new ArrayList<>();
        int sizeRow = processedInput.size();
        for (int i = 0; i < sizeRow; i++) {
            if (i < x) {
                rowBefore.add(row.get(i));
            } else if( i > x){
                rowAfter.add(row.get(i));
            }
        }

        ArrayList<Integer> columnBefore = new ArrayList<>();
        ArrayList<Integer> columnAfter = new ArrayList<>();
        int sizeColumn = processedInput.size();
        for (int i = 0; i < sizeColumn; i++) {
            if (i < y) {
                columnBefore.add(processedInput.get(i).get(x));
            } else if( i > y){
                columnAfter.add(processedInput.get(i).get(x));
            }
        }

        while (true) {
            viewingDistance[0] += 1;
            if (columnBefore.get(columnBefore.size()-1) >= currentCellValue || columnBefore.size() == 1) {
                break;
            } else {
                columnBefore.remove(columnBefore.size()-1);
            }
        }

        while (true) {
            viewingDistance[1] += 1;
            if (rowAfter.get(0) >= currentCellValue || rowAfter.size() == 1) {
                break;
            } else {
                rowAfter.remove(0);
            }
        }

        while (true) {
            viewingDistance[2] += 1;
            if (columnAfter.get(0) >= currentCellValue || columnAfter.size() == 1) {
                break;
            } else {
                columnAfter.remove(0);
            }
        }

        while (true) {
            viewingDistance[3] += 1;
            if (rowBefore.get(rowBefore.size()-1) >= currentCellValue || rowBefore.size() == 1) {
                break;
            } else {
                rowBefore.remove(rowBefore.size()-1);
            }
        }

        int result = 1;
        for (int number: viewingDistance) {
            result *= number;
        }

        return result;
    }
}
