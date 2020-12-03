package org.puzzle;

import org.util.IO;

public class Day3 implements PuzzleInterface{
    @Override
    public void start() {
        System.out.println("---Puzzle Day 3---");
        int height = 323;
        int width = 31;
        char[][] input = IO.read2DCharArrayWithKnownDimsFromRessource("input/day3_input.txt", width, height);
        for (int row = 0; row < height; row++){
            for (int col = 0; col < width; col++)
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
        int height = map.length;
        int width = map[0].length;

        int col = 0;
        for(int row = 0; row < height; row += down){
            if(map[row][col] == '#'){
                treeCount++;
            }

            col += right;
            if(col >= width){
                col = col % width;
            }
        }

        return treeCount;
    }

    private static int p2(char[][] map) {
        int r1 = p1(map, 1, 1);
        int r2 = p1(map, 3, 1);
        int r3 = p1(map, 5, 1);
        int r4 = p1(map, 7, 1);
        int r5 = p1(map, 1, 2);

        return r1 * r2 * r3 * r4 * r5;
    }
}
