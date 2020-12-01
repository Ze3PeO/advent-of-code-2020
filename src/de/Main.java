package de;

import de.puzzle.PuzzleInterface;
import de.puzzle.Day1;
import de.puzzle.Day2;
import de.util.IO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<PuzzleInterface> puzzleList = new ArrayList<>();

        puzzleList.add(new Day1());
        puzzleList.add(new Day2());

        int puzzleNum = IO.readInt("Choose a Day: ");
        puzzleList.get(puzzleNum - 1).start();
    }
}
