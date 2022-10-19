package com.alpashaev.map;

import com.alpashaev.entity.animal.Creature;

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
}
