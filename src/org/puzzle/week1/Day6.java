package org.puzzle.week1;

import org.puzzle.PuzzleInterface;
import org.util.IO;

public class Day6 implements PuzzleInterface {
    @Override
    public void start() {
        System.out.println("---Puzzle Day 6---");
        String[] input = IO.readStringArrayFromRessource("input/week1/day6_input.txt");
        for(String str : input)
            System.out.println(str);

        System.out.println("1)");
        int result1 = p1(input);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input);
        System.out.println("result2 = " + result2);
    }

    private int p1(String[] groups) {
        int yesAnswersSum = 0;
        for(String group : groups) {
            int[] answers = new int[26];
            String[] persons = group.split(" ");

            for (String person : persons)
                for (int i = 0; i < person.length(); i++)
                    answers[person.charAt(i) - 97] += 1;

            for (int answer : answers)
                if (answer > 0)
                    yesAnswersSum++;
        }

        return yesAnswersSum;
    }

    private int p2(String[] groups) {
        int yesAnswersSum = 0;
        for(String group : groups) {
            int[] answers = new int[26];
            String[] persons = group.split(" ");

            for (String person : persons)
                for(int i = 0; i < person.length(); i++)
                    answers[person.charAt(i) - 97] += 1;

            for (int answer : answers)
                if (answer == persons.length)
                    yesAnswersSum++;
        }

        return yesAnswersSum;
    }
}
