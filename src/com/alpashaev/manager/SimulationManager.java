package com.alpashaev.manager;

import com.alpashaev.entity.animal.Creature;
import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;

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
}
