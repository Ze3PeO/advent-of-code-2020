package de;

import de.puzzle.Puzzle;
import de.puzzle.Puzzle1;
import de.util.IO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Puzzle> puzzleList = new ArrayList<>();

        puzzleList.add(new Puzzle1());

        int puzzleNum = IO.readInt("Choose a Day: ");
        puzzleList.get(puzzleNum).start();
    }
}
