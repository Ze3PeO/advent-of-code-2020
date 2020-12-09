package org;

import org.puzzle.PuzzleInterface;
import org.puzzle.week1.*;
import org.puzzle.week2.*;
import org.util.IO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<PuzzleInterface> puzzleList = new ArrayList<>();

        puzzleList.add(new Day1());
        puzzleList.add(new Day2());
        puzzleList.add(new Day3());
        puzzleList.add(new Day4());
        puzzleList.add(new Day5());
        puzzleList.add(new Day6());
        puzzleList.add(new Day7());

        puzzleList.add(new Day8());
        puzzleList.add(new Day9());

        int puzzleNum = IO.readInt("Choose a Day: ");
        puzzleList.get(puzzleNum - 1).start();
    }
}
