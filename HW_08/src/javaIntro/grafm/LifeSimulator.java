package javaIntro.grafm;

import java.util.ArrayList;

public class LifeSimulator {

    public int ROW_NUM = 0;
    private char [] [] BOARD;
    private int [] [] LIVING_NEIGHBORS;
    private char alive = 'X';
    private char dead = '_';
    LifeSimulator(int rowNum) {
        this.ROW_NUM = rowNum;
        this.BOARD = new char[rowNum][rowNum];
        this.LIVING_NEIGHBORS = new int[rowNum][rowNum];
    }

    public void defineRow(int rowNum, String line) {
        if (rowNum > ROW_NUM || line.strip().length() > ROW_NUM) {
            return;
        }

        char charArray [] = line.strip().toCharArray();

        for (int i = 0; i < line.strip().length() ; ++i) {
            BOARD[rowNum][i] = charArray[i];
        }
    }

    public void printOutBoard(char [][] futureGen) {
        for (int i = 0; i < ROW_NUM; ++i) {
            for (int j = 0; j < ROW_NUM; ++j) {
                System.out.print(BOARD[i][j]);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public void printOutBoard() {
        for (int i = 0; i < ROW_NUM; ++i) {
            for (int j = 0; j < ROW_NUM; ++j) {
                System.out.print(BOARD[i][j]);
            }
            System.out.print('\n');
        }
    }

    public void performNSteps(int steps) throws InterruptedException {
        for (int i = 0; i < steps; ++i) {
            char [] [] temp = new char [ROW_NUM] [ROW_NUM];
            for (int j = 0; j < ROW_NUM; ++j)
                temp[j] = BOARD[j].clone();
            temp = performStep(temp);
            for (int j = 0; j < ROW_NUM; ++j)
                BOARD[j] = temp[j].clone();
        }
    }

    public char[][] performStep(char [][] futureGen) throws InterruptedException
    {
        ArrayList<Thread> threads = new ArrayList<>();

        final int NUM_OF_THREADS = 8; //Or can be passed as an argument
        for(int thread = 0; thread < NUM_OF_THREADS; thread++) {
            int rowStart = thread * ROW_NUM / NUM_OF_THREADS;
            int rowEnd = (thread + 1) * ROW_NUM / NUM_OF_THREADS;
            Thread t = new Thread(() -> {
                for (int row = rowStart; row < rowEnd; row++) {
                    for (int column = 0; column < ROW_NUM; column++) {
                        futureGen[row][column] = evaluateNeighbors(row, column);
                    }
                }
            });
            t.start();
            threads.add(t);
        }
        for(Thread t : threads)
            t.join();
        return futureGen;
    }
    
    private char evaluateNeighbors(int rowOne, int columnOne) {
        int liveNeighbors = countLivingNeighbors(rowOne, columnOne);
        if (BOARD[rowOne][columnOne] == alive) {
            if (liveNeighbors == 2 || liveNeighbors == 3) return alive;
            else return dead;
        }
        else if(BOARD[rowOne][columnOne] == dead && liveNeighbors == 3)
            return alive;
        else
            return dead;
    }

    private int countLivingNeighbors(int row, int column) {
        int count = 0;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                int resultingRow = row + i;
                int resultingColumn = column + j;
                if(i == 0 && j == 0) continue; //don't check self
                if (resultingColumn == -1) resultingColumn += ROW_NUM;
                if (resultingColumn == ROW_NUM) resultingColumn -= ROW_NUM;
                if (resultingRow == -1) resultingRow += ROW_NUM;
                if (resultingRow == ROW_NUM) resultingRow -= ROW_NUM;
                if (BOARD[resultingRow][resultingColumn] == alive) count += 1;
            }
        }
        return count;
    }
}
