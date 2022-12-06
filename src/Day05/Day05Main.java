package Day05;

import Commons.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day05Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = CommonUtils.createBufferedReader("src/Day05/Day05Input.txt");
        processInput(br);

    }

    private static void processInput(BufferedReader br) throws IOException {

        ArrayList<ArrayList<String>> cargo = parseCargo(br);
        cargo = moveCargo(cargo, br);
        System.out.println(getFinalResult(cargo));

    }

    private static ArrayList<ArrayList<String>> parseCargo(BufferedReader br) throws IOException {

        boolean continueReading = true;
        int numberOfPiles = 0;
        // the unprocessed string data, the rows
        ArrayList<String> unprocessedCargo = new ArrayList<>();

        while (continueReading) {

            String line = br.readLine();

            if (line.charAt(1) == '1') {
                numberOfPiles = line.replaceAll("\\s+","").length();
                continueReading = false;
            }else {
                unprocessedCargo.add(line);
            }

        }

        int numberOfRows = unprocessedCargo.size();
        ArrayList<ArrayList<String>> processedCargo = mockArrayList(numberOfPiles);

        for  (int i = numberOfRows - 1; i >= 0; i--) {
            String unprocessedRow = unprocessedCargo.get(i);
            processedCargo = processColumn(processedCargo, unprocessedRow, 1, 0);

            for (int j = 1; j < numberOfPiles; j++) {
                int substringIndex = 1 + j * 4;
                processedCargo = processColumn(processedCargo, unprocessedRow, substringIndex, j);
            }
        }

        return processedCargo;
    }

    private static ArrayList<ArrayList<String>> processColumn(
            ArrayList<ArrayList<String>> processedCargo,
            String unprocessedRow,
            int index, int column) {

        try {
            ArrayList<String> processedColumn = processedCargo.get(column);
            String letter = unprocessedRow.substring(index, index + 1);
            if (!letter.equals(" ")) {
                processedColumn.add(letter);
            }
            processedCargo.set(column, processedColumn);
        } catch (Exception e) {
            // do nothing
        }

        return processedCargo;
    }

    /**
     * given the number of columns of the input, creates an arraylist of empty arraylist of strings
     * @param numberOfColumns
     * @return mock
     */
    private static ArrayList<ArrayList<String>> mockArrayList(int numberOfColumns) {
        ArrayList<ArrayList<String>> mock = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            mock.add(new ArrayList<String>());
        }
        return mock;
    }

    private static ArrayList<ArrayList<String>> moveCargo(
            ArrayList<ArrayList<String>> cargo,
            BufferedReader br) throws IOException {

        boolean continueReading = true;

        while (continueReading) {

            String line = br.readLine();

            if (line == null) {
                continueReading = false;
            } else if (line.length() > 0) {
                //cargo = processInstructionsLine(cargo, line);
                cargo = processInstructionsLineStage2(cargo, line);
            }
        }

        return cargo;
    }

    private static ArrayList<ArrayList<String>> processInstructionsLine(ArrayList<ArrayList<String>> cargo, String line) {

        String[] processedLine = line.split(" ");

        int quantity = Integer.parseInt(processedLine[1]);
        int originNumber = Integer.parseInt(processedLine[3])-1;
        int destinyNumber = Integer.parseInt(processedLine[5])-1;

        ArrayList<String> origin = cargo.get(originNumber);
        ArrayList<String> destiny = cargo.get(destinyNumber);

        for (int i = 0; i < quantity; i++) {
            int lastPosition = origin.size() - 1;
            String boxToMove = origin.get(lastPosition);
            destiny.add(boxToMove);
            origin.remove(lastPosition);
        }

        cargo.set(originNumber, origin);
        cargo.set(destinyNumber, destiny);

        return cargo;
    }

    private static ArrayList<ArrayList<String>> processInstructionsLineStage2(ArrayList<ArrayList<String>> cargo, String line) {

        String[] processedLine = line.split(" ");

        int quantity = Integer.parseInt(processedLine[1]);
        int originNumber = Integer.parseInt(processedLine[3])-1;
        int destinyNumber = Integer.parseInt(processedLine[5])-1;

        ArrayList<String> origin = cargo.get(originNumber);
        ArrayList<String> destiny = cargo.get(destinyNumber);

        for (int i = quantity; i > 0; i--) {
            int movePosition = origin.size() - i;
            String boxToMove = origin.get(movePosition);
            destiny.add(boxToMove);
            origin.remove(movePosition);
        }

        cargo.set(originNumber, origin);
        cargo.set(destinyNumber, destiny);

        return cargo;
    }



    public static String getFinalResult(ArrayList<ArrayList<String>> cargo) {

        StringBuilder result = new StringBuilder();

        for (ArrayList<String> column: cargo) {
            result.append(column.get(column.size() - 1));
        }

        return result.toString();
    }

}