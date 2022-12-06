package Day02;

import java.io.*;

public class Day02Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = createBufferedReader("src/Day02/Day02Input.txt");
        System.out.println(processAllLines(br));

    }

    private static BufferedReader createBufferedReader(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);

        return new BufferedReader(fileReader);

    }

    private static int processAllLines(BufferedReader br) throws IOException {
        boolean continueReading = true;
        int totalPoints = 0;

        while (continueReading) {
            String line = br.readLine();

            if (line == null) {
                continueReading = false;
            } else if (line.length() > 0) {
                totalPoints += calculateOutputCase2(line.split(" "));
            }
        }

        return totalPoints;

    }

    private static int calculateOutput(String[] array) {

        String elf = array[0];
        String user = array[1];
        int output = 0;

        switch (elf) {
            case "A":
                switch (user) {
                    case "X":
                        output = 3;
                        break;
                    case "Y":
                        output = 6;
                        break;
                    case "Z":
                        break;
                }
                break;
            case "B":
                switch (user) {
                    case "X":
                        break;
                    case "Y":
                        output = 3;
                        break;
                    case "Z":
                        output = 6;
                        break;
                }
                break;
            case "C":
                switch (user) {
                    case "X":
                        output = 6;
                        break;
                    case "Y":
                        break;
                    case "Z":
                        output = 3;
                        break;
                }
                break;
        }

        switch (user) {
            case "X":
                output += 1;
                break;
            case "Y":
                output += 2;
                break;
            case "Z":
                output += 3;
                break;
        }

        return output;
    }

    private static int calculateOutputCase2(String[] array) {
        String elf = array[0];
        String user = array[1];
        int output = 0;

        switch (elf) {
            case "A":
                switch (user) {
                    case "X":
                        output = 3;
                        break;
                    case "Y":
                        output = 4;
                        break;
                    case "Z":
                        output = 8;
                        break;
                }
                break;
            case "B":
                switch (user) {
                    case "X":
                        output = 1;
                        break;
                    case "Y":
                        output = 5;
                        break;
                    case "Z":
                        output = 9;
                        break;
                }
                break;
            case "C":
                switch (user) {
                    case "X":
                        output = 2;
                        break;
                    case "Y":
                        output = 6;
                        break;
                    case "Z":
                        output = 7;
                        break;
                }
                break;
        }

        return output;
    }

}
