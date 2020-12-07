package org.puzzle;

import org.util.IO;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 implements PuzzleInterface{
    @Override
    public void start() {
        System.out.println("---Puzzle Day 7---");
        List<String> input = new CopyOnWriteArrayList<>(IO.readStringsFromRessourceAsList("input/day7_input.txt"));
        System.out.println("input = " + input);

        System.out.println("1)");
        int result1 = p1(input);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input);
        System.out.println("result2 = " + result2);
    }

    private int p1(List<String> rules) {
        int numOfPossibleBags = 0;

        List<String> bags = new CopyOnWriteArrayList<>();
        bags.add("shiny gold");
        int bagSize = bags.size();

        while(bags.size() != 0){
            for(String bag : bags){
                Pattern pattern = Pattern.compile("[0-9] " + bag);
                Matcher matcher;

                for(String rule : rules){
                    matcher = pattern.matcher(rule);
                    if(matcher.find()){
                        String[] split = rule.split(" ");
                        bags.add(split[0] + " " + split[1]);
                    }
                }
            }

            for(String bag : bags){
                Pattern pattern = Pattern.compile(bag + " bags contain");
                Matcher matcher;

                for(String rule : rules){
                    matcher = pattern.matcher(rule);
                    if(matcher.find()){
                        rules.remove(rule);
                    }
                }
            }

            bags = bags.subList(bagSize, bags.size());
            bags = new CopyOnWriteArrayList<>(new HashSet<>(bags));
            bagSize = bags.size();
            numOfPossibleBags += bags.size();
        }

        return numOfPossibleBags;
    }

    private int p2(List<String> rules) {
        return -1;
    }
}
