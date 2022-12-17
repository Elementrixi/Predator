package com.alpashaev.manager;

import com.alpashaev.entity.animal.Creature;
import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;
import com.alpashaev.map.Forest;

public class SimulationManager {
    public static void start(){
      test();
    }

    public static void test(){
        try {
            int rats = 10, lynx = 10, rowL = 10, colL = 10, rowR = 20, colR = 20;
            Lynx.hungerTimeMax = 7;

            Rat rat1 = new Rat();
            Rat rat2 = new Rat(rowR, colR);
            Rat rat3 = new Rat(rat2);
            Lynx lynx1 = new Lynx();
            Lynx lynx2 = new Lynx(rowL, colL);
            Lynx lynx3 = new Lynx(lynx2);

            System.out.println("Rat1: row " + rat1.rowPosition + "  col " + rat1.colPosition + " breedingPoint " + rat1.breedingPoint + "timeSinceBreed" + rat1.timeSinceBreed + " imputed amount " + rats);
            System.out.println("Lynx1: row " + lynx1.rowPosition + "  col " + lynx1.colPosition + " breeding point " + lynx1.breedingPoint + "timeSinceBreed" + lynx1.timeSinceBreed
                    + " hungerTimeMax " + Lynx.hungerTimeMax + " timeSinceEat " + lynx1.timeSinceEat + " imputed amount " + lynx + "\n");

            System.out.println("Rat2: row " + rat2.rowPosition + "  col " + rat2.colPosition + " breeding point " + rat2.breedingPoint + "timeSinceBreed" + rat2.timeSinceBreed + " imputed amount " + rats);
            System.out.println("Lynx2: row " + lynx2.rowPosition + "  col " + lynx2.colPosition + " breeding point " + lynx2.breedingPoint + "timeSinceBreed" + lynx2.timeSinceBreed
                    + " hungerTimeMax " + Lynx.hungerTimeMax + " timeSinceEat " + lynx2.timeSinceEat + " imputed amount " + lynx + "\n");

            if (propertiesEqual(rat2,rat3)) System.out.println("Rat2 and Rat3 have equal data");
            else System.out.println("False");
            if (propertiesEqual(lynx2,lynx3)) System.out.println("Lynx2 and Lynx3 have equal data");
            else System.out.println("False");

        } catch (Exception e){
            System.out.println("Error SimulationManager");
        }
    }

    private static boolean propertiesEqual(Creature creature1, Creature creature2){
        return creature1.rowPosition == creature2.rowPosition && creature1.colPosition == creature2.colPosition
                && creature1.timeSinceBreed == creature2.timeSinceBreed;
    }

    private static void fieldPause(){
        try {
            Thread.sleep(500);
            System.out.println("Next step");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }


    private static int[][] ratPositions(){
        int[][] pos = new int[Forest.numberOfRats][2];
        for (int i = 0; i < Forest.numberOfRats; i++) {
            pos[i] = Forest.ratScanner();
        }
        Forest.colScan=0;
        Forest.rowScan=0;
        return pos;
    }

    private static int[][] lynxPositions(){
        int[][] pos = new int[Forest.numberOfLynx][2];
        for (int i = 0; i < Forest.numberOfLynx; i++) {
            pos[i] = Forest.lynxScanner();
        }
        Forest.colScan=0;
        Forest.rowScan=0;
        return pos;
    }

    public static void move(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        Creature rat = Forest.getCreature(row, col);
        char letter = rat.getSymbol();
        Integer[] randomDirections = Forest.randomDirections();
        boolean moveComplete = false;

        for (int i = 0; i < 4 && (moveComplete == false); i++) {
            switch (randomDirections[i]) {
                case 1:
                    if (rat.topFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] - 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 1);
                        moveComplete = true;
                    }
                    break;
                case 2:
                    if (rat.rightFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] + 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 2);
                        moveComplete = true;
                    }
                    break;
                case 3:
                    if (rat.bottomFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] + 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 3);
                        moveComplete = true;
                    }
                    break;
                case 4:
                    if (rat.leftFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] - 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 4);
                        moveComplete = true;
                    }
                    break;
            }
        }
    }

    public static void simulation(Forest forest) {
        int[][] lynxPositions = new int[Forest.numberOfLynx][2];
        lynxPositions = lynxPositions();
        int[] position = new int[2];

        //Lynx movement or eat Rat if it's possible
        for (int i = 0; i < Forest.numberOfLynx; i++) {
            position = lynxPositions[i];

            //Error catching
            if (position[0] < 0 || position[1] < 0 || position[0] > 19 || position[1] > 19) System.exit(-1);

            //Eating
            if (forest.preyNear((Lynx) Forest.getCreature(position))) {
                Lynx lynx = (Lynx) Forest.getCreature(position);
                lynx.timeSinceEat = 0;
                lynx.timeSinceBreed++;
                lynx.eat(position);
            } else { //Moving
                Lynx lynx = (Lynx) Forest.getCreature(position);
                lynx.timeSinceEat++;
                lynx.timeSinceBreed++;
                move(position);
            }
        }
    }

}
