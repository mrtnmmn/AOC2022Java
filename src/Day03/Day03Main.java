package Day03;

import java.io.*;

public class Day03Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = createBufferedReader("src/Day03/Day03InputExample.txt");
        BufferedReader br = createBufferedReader("src/Day03/Day03Input.txt");
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
                totalPoints += processLine(line);
            }
        }

        return totalPoints;

    }

    private static int processLine(String line) {

        String[] rucksackCompartiments = splitRucksack(line);
        char repeatedChar = returnRepeatedLetter(rucksackCompartiments);
        return characterToValue(repeatedChar);
    }

    private static String[] splitRucksack(String line) {

        String[] rucksackCompartiments = new String[2];
        int lineLenght = line.length();

        rucksackCompartiments[0] = line.substring(0, lineLenght / 2);
        rucksackCompartiments[1] = line.substring(lineLenght / 2);

        return rucksackCompartiments;
    }

    private static char returnRepeatedLetter(String[] array) {

        char[] charArrayCompartiment1 = array[0].toCharArray();
        char[] charArrayCompartiment2 = array[1].toCharArray();

        for (char c1: charArrayCompartiment1) {
            for (char c2: charArrayCompartiment2) {
                if (c1 == c2) {
                    return c1;
                }
            }
        }

        return ' ';
    }

    private static int characterToValue(char character) {

        int charInAscii = (int) character;

        if (charInAscii <= 90 && charInAscii >= 65) {
            return charInAscii - 38;
        } else {
            return charInAscii - 96;
        }

    }

}
