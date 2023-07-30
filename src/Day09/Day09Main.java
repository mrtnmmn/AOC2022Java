package Day09;

import Commons.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day09Main {

    public static ArrayList<String[]> instructions = new ArrayList<>();
    public static int[] positionHead = new int[2];
    public static int[] positionTail = new int[2];
    public static ArrayList<int[]> tailPositionsRecord = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        tailPositionsRecord.add(positionTail.clone());
        BufferedReader br = CommonUtils.createBufferedReader("src/Day09/Day09Input.txt");
        parseInput(br);
        processInput();
        getResult();
    }

    public static void parseInput(BufferedReader br) throws IOException {
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            } else {
                instructions.add(line.split(" "));
            }
        }
    }

    public static void processInput() {
        while (instructions.size() > 0) {
            String[] currentLine = instructions.get(0);
            for (int i = 0; i < Integer.parseInt(currentLine[1]); i++) {
                movePosition(currentLine[0], positionHead);
                followTail(currentLine[0]);
            }
            instructions.remove(0);
        }
    }

    public static void followTail(String direction){
        if (!isInRange()) {
            if (positionTail[0] != positionHead[0] && positionTail[1] != positionHead[1]) {
                handleDiagonalMove();
            }
            else {
                movePosition(direction, positionTail);
            }
            recordTailPosition();
        }
    }

    public static void handleDiagonalMove() {
        if (positionHead[1] > positionTail[1]) {
            movePosition("U", positionTail);
        } else {
            movePosition("D", positionTail);
        }
        if (positionHead[0] < positionTail[0]) {
            movePosition("L", positionTail);
        } else  {
            movePosition("R", positionTail);
        }
    }

    public static void movePosition(String direction, int[] array) {
        switch (direction) {
            case "U":
                array[1] += 1;
                break;
            case "R":
                array[0] += 1;
                break;
            case "D":
                array[1] -= 1;
                break;
            case "L":
                array[0] -= 1;
                break;
        }
    }

    public static boolean isInRange() {
        return positionHead[0] <= positionTail[0] + 1 && positionHead[0] >= positionTail[0] - 1 &&
                positionHead[1] <= positionTail[1] + 1 && positionHead[1] >= positionTail[1] - 1;
    }

    public static void recordTailPosition() {
        boolean exist = false;
        for (int[] position: tailPositionsRecord) {
            if (position[0] == positionTail[0] && position[1] == positionTail[1]) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            tailPositionsRecord.add(positionTail.clone());
        }
    }

    public static void getResult() {
        System.out.println(tailPositionsRecord.size());
    }
}
