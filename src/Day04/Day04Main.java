package Day04;

import java.io.*;

public class Day04Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = createBufferedReader("src/Day04/Day04InputExample.txt");
        BufferedReader br = createBufferedReader("src/Day04/Day04Input.txt");
        System.out.println(processInput(br));


    }

    private static BufferedReader createBufferedReader(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);

        return new BufferedReader(fileReader);

    }

    private static int processInput(BufferedReader br) throws IOException {

        boolean continueReading = true;
        int totalPoints = 0;

        while (continueReading) {
            String line = br.readLine();

            if (line == null) {
                continueReading = false;
            } else {
                // totalPoints += processLine(line);
                totalPoints += processLineStage2(line);
            }
        }

        return totalPoints;

    }

    private static int processLine(String line) {

        String[] lineSplittedInCommas = line.split(",");

        int[] firstElf = stringArrayToIntArray(lineSplittedInCommas[0].split("-"));
        int[] secondElf = stringArrayToIntArray(lineSplittedInCommas[1].split("-"));

        if (firstElf[0] <= secondElf[0] && firstElf[1] >= secondElf[1]) {
            return 1;
        }
        if (secondElf[0] <= firstElf[0] && secondElf[1] >= firstElf[1]) {
            return 1;
        }

        return 0;
    }

    private static int processLineStage2(String line) {

        String[] lineSplittedInCommas = line.split(",");

        int[] firstElf = stringArrayToIntArray(lineSplittedInCommas[0].split("-"));
        int[] secondElf = stringArrayToIntArray(lineSplittedInCommas[1].split("-"));

        if (firstElf[0] <= secondElf[0] && firstElf[1] >= secondElf[0]) {
            return 1;
        }
        if (secondElf[0] <= firstElf[0] && secondElf[1] >= firstElf[0]) {
            return 1;
        }

        return 0;
    }

    private static int[] stringArrayToIntArray(String[] stringArray) {

        int stringArrayLength = stringArray.length;
        int[] intArray = new int[stringArrayLength];

        for (int i = 0; i < stringArrayLength; i++ ) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        return intArray;

    }
}
