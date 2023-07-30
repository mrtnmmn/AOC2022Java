package Day09;

import Commons.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day09Stage2 {

    public static int ropeSize = 10;
    public static ArrayList<String[]> instructions = new ArrayList<>();
    public static ArrayList<int[]> tailPositionsRecord = new ArrayList<>();
    public static int[][] rope = new int[ropeSize][2];

    public static void main(String[] args) throws IOException {
        tailPositionsRecord.add(new int[]{0,0});
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
                movePosition(currentLine[0], 0);
                for (int j = 1; j < ropeSize; j++) {
                    followTail(j);
                }
                recordTailPosition();
            }
            instructions.remove(0);
        }
    }

    public static void followTail(int knot){
        if (!isInRange(knot)) {
            if (rope[knot][0] != rope[knot-1][0] && rope[knot][1] != rope[knot-1][1]) {
                handleDiagonalMove(knot);
            }
            else {
                if (rope[knot][0] + 1 == rope[knot-1][0] - 1) {
                    movePosition("R", knot);
                } else if (rope[knot][0] - 1 == rope[knot-1][0] + 1) {
                    movePosition("L", knot);
                } else if (rope[knot][1] + 1 == rope[knot-1][1] - 1) {
                    movePosition("U", knot);
                } else if (rope[knot][1] - 1 == rope[knot-1][0] + 1) {
                    movePosition("D", knot);
                }
            }
        }
    }

    public static void handleDiagonalMove(int knot) {
        if (rope[knot-1][1] > rope[knot][1]) {
            movePosition("U", knot);
        } else {
            movePosition("D", knot);
        }
        if (rope[knot-1][0] < rope[knot][0]) {
            movePosition("L", knot);
        } else  {
            movePosition("R", knot);
        }
    }

    public static void movePosition(String direction, int knotNumber) {
        switch (direction) {
            case "U" -> rope[knotNumber][1] += 1;
            case "R" -> rope[knotNumber][0] += 1;
            case "D" -> rope[knotNumber][1] -= 1;
            case "L" -> rope[knotNumber][0] -= 1;
        }
    }

    public static boolean isInRange(int knot) {
        return rope[knot-1][0] <= rope[knot][0] + 1 && rope[knot-1][0] >= rope[knot][0] - 1 &&
                rope[knot-1][1] <= rope[knot][1] + 1 && rope[knot-1][1] >= rope[knot][1] - 1;
    }

    public static void recordTailPosition() {
        boolean exist = false;
        for (int[] position: tailPositionsRecord) {
            if (position[0] == rope[ropeSize - 1][0] && position[1] == rope[ropeSize - 1][1]) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            tailPositionsRecord.add(rope[ropeSize - 1].clone());
        }
    }

    public static void getResult() {
        System.out.println(tailPositionsRecord.size());
    }
}
