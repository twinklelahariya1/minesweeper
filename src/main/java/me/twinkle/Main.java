package me.twinkle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new Main().run();
    }

    private void run() {

        Scanner scanner = new Scanner(System.in);

        String next = "Yes";

        char[][] mineField = createMineField(scanner);
        char[][] mineFieldInitial = new char[mineField.length][];
        copyArrayElement(mineField, mineFieldInitial);

        System.out.println("Lets Play!");

        while (next.equals("Yes")) {

            System.out.println("Enter x-coordinate");
            int xCoordinate = getCoordinate(scanner);
            System.out.println("Enter y-coordinate");
            int yCoordinate = getCoordinate(scanner);
            System.out.println("Enter flag(F) or open(O)");
            String flagChoice = getFlagChoice(scanner);

            if (!flagChoice.isEmpty()) {
                if (!playMove(xCoordinate, yCoordinate, flagChoice, mineField)) {
                    System.out.println(Arrays.deepToString(mineFieldInitial));
                }
            }

            System.out.println("Continue? (Yes/No)");
            next = scanner.next();
        }
    }

    private void copyArrayElement(char[][] mineField, char[][] mineFieldInitial) {
        for (int i = 0; i < mineField.length; i++) {
            mineFieldInitial[i] = new char[mineField[i].length];
            System.arraycopy(mineField[i], 0, mineFieldInitial[i], 0, mineFieldInitial[i].length);
        }
    }

    private boolean playMove(int xCoordinate, int yCoordinate, String flagChoice, char[][] mineField) {
        if (flagChoice.equals("O")) {
            if (mineField[xCoordinate][yCoordinate] == 'm')
                return false;
            else mineField[xCoordinate][yCoordinate] = 'O';
        } else
            mineField[xCoordinate][yCoordinate] = 'F';
        return true;
    }

    private int getCoordinate(Scanner scanner) {
        return scanner.nextInt();
    }

    private String getFlagChoice(Scanner scanner) {
        return scanner.next();
    }

    private char[][] createMineField(Scanner scanner) {

        System.out.println("Enter the minefield layout");

        String mineFieldString = scanner.next();

        String[] mineRows = mineFieldString.trim().split(",");

        char[][] mineFieldLayout = new char[mineRows.length][];

        int i = 0;

        for (String s : mineRows) {
            mineFieldLayout[i] = s.toCharArray();
            i++;
        }

        return mineFieldLayout;
    }
}
