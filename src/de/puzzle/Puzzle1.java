package de.puzzle;

import de.util.IO;
import de.util.RBT;

public class Puzzle1 implements Puzzle {
    @Override
    public void start() {
        System.out.println("---Puzzle Day 1---");
        RBT<Integer> input = IO.readIntsFromRessourceAsRBT("day1_input.txt");
        System.out.println(input.getKeysAsList());

        System.out.println("1)");
        int result1 = day1_1(input, 2020);
        System.out.println("result = " + result1);

        System.out.println("2)");
        int result2 = day1_2(input, 2020);
        System.out.println("result = " + result2);
    }

    private static int day1_1(RBT<Integer> inputRBT, int target) {
        for(int num : inputRBT.getKeysAsList()){
            int remainder = target - num;
            if(inputRBT.search(remainder) != null){
                return num * remainder;
            }
        }

        return -1;
    }

    private static int day1_2(RBT<Integer> inputRBT, int target) {
        for(int num : inputRBT.getKeysAsList()){
            int remainder = target - num;
            int temp = day1_1(inputRBT, remainder);

            if(temp > 0){
                return num * temp;
            }
        }

        return -1;
    }
}
