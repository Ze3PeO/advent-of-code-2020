package org.puzzle;

import org.util.IO;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Day5 implements PuzzleInterface{
    @Override
    public void start() {
        System.out.println("---Puzzle Day 5---");
        List<String> input = IO.readStringsFromRessourceAsList("input/day5_input.txt");
        System.out.println(input);
        List<Integer> IDs = getSeatIDs(input);

        System.out.println("1)");
        int result1 = p1(IDs);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(IDs);
        System.out.println("result2 = " + result2);
    }

    private int p1(List<Integer> IDs) {
        return IDs.get(IDs.size() - 1);
    }

    private int p2(List<Integer> IDs) {
        // Finde die eine fehlende ID in einer sortierten Liste von IDs
        int currSeatID = IDs.get(0);
        for(int i = 0; i < IDs.get(IDs.size() - 1); i++){
            if(IDs.get(i) != currSeatID)
                return currSeatID;
            currSeatID++;
        }
        return -1;
    }

    private List<Integer> getSeatIDs(List<String> boardingPasses){
        List<Integer> IDs = new ArrayList<>();

        for(String pass : boardingPasses){
            int row = 127;
            int col = 7;
            int rowD = 64;
            int colD = 4;

            for(int i = 0; i < 10; i++){
                if(pass.charAt(i) == 'F')
                    row -= rowD;
                if(i < 7)
                    rowD /= 2;
                if(pass.charAt(i) == 'L')
                    col -= colD;
                if(i > 6)
                    colD /= 2;
            }
            int seatID = row * 8 + col;
            IDs.add(seatID);
        }

        Collections.sort(IDs);
        return IDs;
    }
}
