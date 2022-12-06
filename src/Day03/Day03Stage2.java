package Day03;

import java.io.*;
import java.util.ArrayList;

public class Day03Stage2 {

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

                String[] elfGroup = new String[3];
                elfGroup[0] = line;
                elfGroup[1] = br.readLine();
                elfGroup[2] = br.readLine();

                totalPoints += processElfGroup(elfGroup);
            }
        }

        return totalPoints;

    }

    private static int processElfGroup(String[] group) {

        ArrayList<char[]> groupInCharArrays = stringToCharArrays(group);
        char groupBadge = returnGroupBadge(groupInCharArrays);
        return characterToValue(groupBadge);

    }

    private static ArrayList<char[]> stringToCharArrays(String[] group) {

        ArrayList<char[]> groupInCharArrays = new ArrayList<>();

        for (String array: group) {
            groupInCharArrays.add(array.toCharArray());
        }

        return groupInCharArrays;

    }

    private static char returnGroupBadge(ArrayList<char[]> group) {

        boolean isInRack2 = false;
        boolean isInRack3 = false;

        for (char characterInRacksack1: group.get(0)) {

            isInRack2 = false;
            isInRack3 = false;

            for (char characterInRacksack2: group.get(1)) {
                if (characterInRacksack1 == characterInRacksack2) {
                    isInRack2 = true;
                    break;
                }
            }

            for (char characterInRacksack2: group.get(2)) {
                if (characterInRacksack1 == characterInRacksack2) {
                    isInRack3 = true;
                    break;
                }
            }

            if (isInRack2 && isInRack3) {
                return characterInRacksack1;
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
