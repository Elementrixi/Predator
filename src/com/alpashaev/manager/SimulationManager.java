package com.alpashaev.manager;

import com.alpashaev.entity.animal.Creature;
import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;
import com.alpashaev.map.Forest;

import java.util.Scanner;

public class SimulationManager {
    public static void start(){
        simulationStarter();
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
        Forest.rowColScanClear();
        return pos;
    }

    private static int[][] lynxPositions(){
        int[][] pos = new int[Forest.numberOfLynx][2];
        for (int i = 0; i < Forest.numberOfLynx; i++) {
            pos[i] = Forest.lynxScanner();
        }
        Forest.rowColScanClear();
        return pos;
    }
    public static void moveLynx(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        Lynx lynx = (Lynx) Forest.getCreature(row, col);

        Integer[] randomDirections = Forest.randomDirections();
        boolean moveComplete = false;
        for (int i = 0; i < 4 && (moveComplete == false); i++) {
            switch (randomDirections[i]) {
                case 1:
                    if (lynx.topFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] - 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 1);
                        moveComplete = true;
                    }
                    break;
                case 2:
                    if (lynx.rightFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] + 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 2);
                        moveComplete = true;
                    }
                    break;
                case 3:
                    if (lynx.bottomFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] + 1, coordinates[1]) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 3);
                        moveComplete = true;
                    }
                    break;
                case 4:
                    if (lynx.leftFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] - 1) == null) {
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 4);
                        moveComplete = true;
                    }
                    break;
            }
        }
    }

    public static void moveRat(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        Rat rat = (Rat) Forest.getCreature(row, col);

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

    public static void simulation(Forest forest) throws Exception {
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
                moveLynx(position);
            }
        }
        int[][] ratPositions = new int[Forest.numberOfRats][2];
        ratPositions = ratPositions();

        for (int i = 0; i < Forest.numberOfRats; i++) {
            position = ratPositions[i];

            Rat rat = (Rat) Forest.getCreature(position);
            rat.timeSinceBreed++;
            moveRat(position);
        }


        Forest.rowColScanClear();


//Breeding for Lynx
        lynxPositions = lynxPositions();
        int numberOfLynx = Forest.numberOfLynx;
        for (int i = 0; i < numberOfLynx; i++) {
            //Get lynx Coordinates
            position = lynxPositions[i];
            int row = position[0];
            int col = position[1];

            if (row < 0 || col < 0) {
                break;
            }
            forest.breed(Forest.getCreature(position));
        }

//Breeding for Rat
        ratPositions = ratPositions();
        int tempNumberOfRats = Forest.numberOfRats;

        for (int i = 0; i < tempNumberOfRats; i++) {
            //Get rat Coordinates
            position = ratPositions[i];
            int row = position[0];
            int col = position[1];

            if (row < 0 || col < 0) {
                break;
            }

            forest.breed(Forest.getCreature(row, col));
        }

//Hunger
        lynxPositions = lynxPositions();
        numberOfLynx = Forest.numberOfLynx;

        for (int i = 0; i < numberOfLynx; i++) {
            forest.starving((Lynx) Forest.getCreature(lynxPositions[i][0], lynxPositions[i][1]));
        }

    }
    public static void simulationStarter() {
        {
            int rats, lynx, maxStep;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 0 if you want to use default data if no any other number");
            if (scanner.nextInt() == 0) {
                rats = 50;
                lynx = 50;
                Lynx.hungerTimeMax = 6;
                maxStep = 40;

            } else {
                System.out.println("Number of rats, lynxes, max step amount");
                rats = scanner.nextInt();
                lynx = scanner.nextInt();
                maxStep = scanner.nextInt();

                System.out.println("Hunger time max for lynxes");
                Lynx.hungerTimeMax = scanner.nextInt();
            }
            Forest forest = new Forest(rats, lynx);

            forest.printField();
            int i = 0;
            try {
                while (Forest.numberOfRats != 0 || Forest.numberOfLynx != 0 || i != maxStep - 1) {

                    simulation(forest);

                    forest.printField();
                    i++;

                    fieldPause();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Simulation ended up at step " + i);
            }
        }
    }
}
