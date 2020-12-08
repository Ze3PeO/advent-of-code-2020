package org.puzzle;

import org.util.IO;

import java.util.ArrayList;
import java.util.List;

public class Day8 implements PuzzleInterface{
    @Override
    public void start() {
        System.out.println("---Puzzle Day 8---");
        List<String> input = IO.readStringsFromRessourceAsList("input/day8_input.txt");
        System.out.println("input = " + input);

        System.out.println("1)");
        int result1 = p1(input);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(input);
        System.out.println("result2 = " + result2);
    }

    private int p1(List<String> lines) {
        boolean[] visited = new boolean[lines.size()];
        int acc = 0;
        for(int i = 0; i < lines.size();){
            if(!visited[i]){
                visited[i] = true;
                String[] split = lines.get(i).split(" ");
                if(split[0].equals("nop")){
                    i++;
                }
                if(split[0].equals("acc")) {
                    acc += Integer.parseInt(split[1]);
                    i++;
                }
                if(split[0].equals("jmp")) {
                    i += Integer.parseInt(split[1]);
                }
            }else{
                i = lines.size();
            }
        }
        return acc;
    }

    private int p2(List<String> lines) {
        List<Integer> indices = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            String[] split = lines.get(i).split(" ");

            if(split[0].equals("jmp"))
                indices.add(i);
            if(split[0].equals("nop"))
                indices.add(i);
        }

        for(int index : indices){
            String[] editSplit = lines.get(index).split(" ");
            if(editSplit[0].equals("jmp"))
                lines.set(index, "nop " + editSplit[1]);
            if(editSplit[0].equals("nop"))
                lines.set(index, "jmp " + editSplit[1]);

            int acc = 0;
            boolean exited = true;
            boolean[] visited = new boolean[lines.size()];

            for(int i = 0; i < lines.size();){
                if(!visited[i]){
                    visited[i] = true;
                    String[] split = lines.get(i).split(" ");
                    if(split[0].equals("nop")){
                        i++;
                    }
                    if(split[0].equals("acc")) {
                        acc += Integer.parseInt(split[1]);
                        i++;
                    }
                    if(split[0].equals("jmp")) {
                        i += Integer.parseInt(split[1]);
                    }
                }else{
                    i = lines.size();
                    exited = false;
                }
            }

            if(editSplit[0].equals("jmp"))
                lines.set(index, "jmp " + editSplit[1]);
            if(editSplit[0].equals("nop"))
                lines.set(index, "nop " + editSplit[1]);

            if(exited)
                return acc;
        }

        return -1;
    }
}
