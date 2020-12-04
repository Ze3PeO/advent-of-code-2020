package org.puzzle;

import org.util.IO;

import java.util.List;

public class Day2 implements PuzzleInterface {
    @Override
    public void start() {
        System.out.println("---Puzzle Day 2---");
        List<String> input = IO.readStringsFromRessourceAsList("input/day2_input.txt");
        System.out.println(input);

        System.out.println("1)");
        int result1 = p1(input);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input);
        System.out.println("result2 = " + result2);
    }


    private int p1(List<String> input) {
        int validPasswords = 0;

        for(String line : input){
            int count = 0;

            String[] lineSplit = line.split(":");
            String[] policySplit = lineSplit[0].split(" ");
            String[] countPolicySplit = policySplit[0].split("-");

            String password = lineSplit[1].substring(1);
            int low = Integer.parseInt(countPolicySplit[0]);
            int high = Integer.parseInt(countPolicySplit[1]);
            char letter = policySplit[1].charAt(0);

            for (int i = 0; i < password.length() && count <= high; i++){
                if(password.charAt(i) == letter){
                    count++;
                }
            }

            if(count >= low && count <= high){
                validPasswords++;
            }
        }

        return validPasswords;
    }

    private int p2(List<String> input) {
        int validPasswords = 0;

        for(String line : input){
            String[] lineSplit = line.split(":");
            String[] policySplit = lineSplit[0].split(" ");
            String[] countPolicySplit = policySplit[0].split("-");

            String password = lineSplit[1].substring(1);
            int pos1 = Integer.parseInt(countPolicySplit[0]);
            int pos2 = Integer.parseInt(countPolicySplit[1]);
            char letter = policySplit[1].charAt(0);

            if(password.charAt(pos1 - 1) == letter ^ password.charAt(pos2 - 1) == letter){
                validPasswords++;
            }
        }

        return validPasswords;
    }
}
