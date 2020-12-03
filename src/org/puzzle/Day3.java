package org.puzzle;

import org.util.IO;

public class Day3 implements PuzzleInterface{
    private static final int HEIGHT = 323;
    private static final int WIDTH = 31;

    @Override
    public void start() {
        System.out.println("---Puzzle Day 3---");
        char[][] input = IO.read2DCharArrayWithKnownDimsFromRessource("input/day3_input.txt", WIDTH, HEIGHT);

        for (int row = 0; row < HEIGHT; row++){
            for (int col = 0; col < WIDTH; col++)
                System.out.print(input[row][col]);
            System.out.println();
        }

        System.out.println("1)");
        int result1 = p1(input, 3, 1);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input);
        System.out.println("result2 = " + result2);
    }

    private static int p1(char[][] map, int right, int down) {
        int treeCount = 0;

        int col = 0;
        for(int row = 0; row < HEIGHT; row += down){
            if(map[row][col % WIDTH] == '#'){
                treeCount++;
            }
            col += right;
        }

        return treeCount;
    }

    private static int p2(char[][] map) {
        return p1(map, 1, 1) * p1(map, 3, 1) * p1(map, 5, 1) *
                p1(map, 7, 1) * p1(map, 1, 2);
    }
}
