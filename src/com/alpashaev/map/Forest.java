package com.alpashaev.map;

import com.alpashaev.entity.animal.Creature;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Forest {
    public static Creature[][] forest = new Creature[20][20];
    public static int numberOfRats;
    public static int numberOfLynx;
    public Random randomNumGen = new Random();
    public Scanner scanner = new Scanner(System.in);

    public static int rowScan = 0;
    public static int colScan = 0;

    public static Creature getCreature(int row, int col)
    {
        return forest[row][col];
    }

    public static Integer[] randomDirections()
    {
        Integer[] directionArray = new Integer[] {1, 2, 3, 4};

        Collections.shuffle(Arrays.asList(directionArray));

        return directionArray;
    }
}
