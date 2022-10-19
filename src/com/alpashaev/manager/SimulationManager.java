package com.alpashaev.manager;

import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;

import java.util.Scanner;

public class SimulationManager {
    public static void start(){
        try {
            int rats, lynx;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Number of rats and lynxes");
            rats = scanner.nextInt();
            lynx = scanner.nextInt();
            System.out.println("Hunger time max for lynxes");
            Lynx.hungerTimeMax = scanner.nextInt();

            Rat rat1 = new Rat();
            Lynx lynx1 = new Lynx();
            System.out.println("Rat: row " + rat1.rowPosition + "  col " + rat1.colPosition + " breeding point " + rat1.breedingPoint + " max amount " + rats);
            System.out.println("Lynx: row " + lynx1.rowPosition + "  col " + lynx1.colPosition + " breeding point " + lynx1.breedingPoint
                    + " hungerTimeMax " + Lynx.hungerTimeMax + " timeSinceEat " + lynx1.timeSinceEat + " max amount " + lynx);
        } catch (Exception e){
            System.out.println("Error SimulationManager");
        }
    }
}
