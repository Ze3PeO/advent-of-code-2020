package org.puzzle.week2;

import org.puzzle.PuzzleInterface;
import org.util.IO;

import java.util.ArrayList;
import java.util.List;

public class Day9 implements PuzzleInterface {
    @Override
    public void start() {
        System.out.println("---Puzzle Day 9---");
        List<Integer> input = IO.readIntsFromRessourceAsList("input/week2/day9_input.txt");

        System.out.println("1)");
        int result1 = p1(input, 25);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input, p1(input, 25));
        System.out.println("result2 = " + result2);
    }

    private int p1(List<Integer> numbers, int preambleLength) {
        List<Integer> toSearch = new ArrayList<>();

        for(int i = 0; i < preambleLength; i++){
            toSearch.add(numbers.get(i));
        }
        for(int i = preambleLength; i < numbers.size(); i++){
            int num = numbers.get(i);
            boolean possible = false;

            for(int j : toSearch){
                int remainder = num - j;
                if(toSearch.contains(remainder)){
                    possible = true;
                }
            }

            if(!possible){
                return num;
            }

            toSearch.remove(0);
            toSearch.add(num);
        }
        return -1;
    }

    private int p2(List<Integer> numbers, int num) {
        numbers.removeIf(n -> (n >= num));

        for(int start = 0; start < numbers.size(); start++){
            int sum = numbers.get(start);

            for(int end = start + 1; end < numbers.size() && sum < num; end++){
                sum += numbers.get(end);

                if(sum == num){
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;
                    for(int i = start; i < end; i++){
                        int n = numbers.get(i);
                        if(n > max){
                            max = n;
                        }
                        if(n < min){
                            min = n;
                        }
                    }
                    return min + max;
                }
            }
        }
        return -1;
    }
}
