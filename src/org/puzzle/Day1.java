package org.puzzle;

import org.util.IO;
import org.util.RBT;

public class Day1 implements PuzzleInterface {
    @Override
    public void start() {
        System.out.println("---Puzzle Day 1---");
        RBT<Integer> input = IO.readIntsFromRessourceAsRBT("input/day1_input.txt");
        System.out.println(input.getKeysAsList());

        System.out.println("1)");
        int result1 = p1(input, 2020);
        System.out.println("result = " + result1);

        System.out.println("2)");
        int result2 = p2(input, 2020);
        System.out.println("result = " + result2);
    }

    private int p1(RBT<Integer> inputRBT, int target) {
        for(int num : inputRBT.getKeysAsList()){
            int remainder = target - num;
            if(inputRBT.search(remainder) != null){
                return num * remainder;
            }
        }

        return -1;
    }

    private int p2(RBT<Integer> inputRBT, int target) {
        for(int num : inputRBT.getKeysAsList()){
            int remainder = target - num;
            int temp = p1(inputRBT, remainder);

            if(temp > 0){
                return num * temp;
            }
        }

        return -1;
    }
}
