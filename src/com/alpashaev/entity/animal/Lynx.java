package com.alpashaev.entity.animal;

import com.alpashaev.map.Forest;

public class Lynx extends Creature {
    public static int hungerTimeMax; //max hunger time before death, input by user

    public Lynx()
    {
        super(); // ref to parent class method
        defaultData();
    }

    public Lynx(int row, int col)
    {
        super(row, col); // ref to parent class method
        defaultData();
    }

    public Lynx(Lynx o)
    {
        super(o); // ref to parent class method
        this.symbol = o.symbol; // coping data for an ancestor
        this.breedingPoint = o.breedingPoint;
        this.timeSinceEat = o.timeSinceEat;
    }

    @Override
    protected void defaultData() {
        this.symbol = 'L'; //Lynx symbol will be L
        this.breedingPoint = 9; // at what point an instance of Lynx being will breed
        this.timeSinceEat = 0;
    }

    @Override
    public void eat(int[] coordinates) {
        Lynx lynx = (Lynx) Forest.getCreature(coordinates[0], coordinates[1]);

        Integer[] randomDirections = Forest.randomDirections(); //Random direction list

        boolean moveComplete = false;

        for (int i = 0; i < 4 && (!moveComplete); i++) {
            switch (randomDirections[i]) {
                case 1:
                    if (lynx.topFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] - 1, coordinates[1]) instanceof Rat) {
                        Forest.forest[coordinates[0] - 1][coordinates[1]] = null;
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 1);
                        moveComplete = true;
                        Forest.numberOfRats--;
                    }
                    break;
                case 2:
                    if (lynx.rightFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] + 1) instanceof Rat) {
                        Forest.forest[coordinates[0]][coordinates[1] + 1] = null;
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 2);
                        moveComplete = true;
                        Forest.numberOfRats--;
                    }
                    break;
                case 3:
                    if (lynx.bottomFlag)
                        break;

                    if (Forest.getCreature(coordinates[0] + 1, coordinates[1]) instanceof Rat) {
                        Forest.forest[coordinates[0] + 1][coordinates[1]] = null;
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 3);
                        moveComplete = true;
                        Forest.numberOfRats--;
                    }
                    break;
                case 4:
                    if (lynx.leftFlag)
                        break;

                    if (Forest.getCreature(coordinates[0], coordinates[1] - 1) instanceof Rat) {
                        Forest.forest[coordinates[0]][coordinates[1] - 1] = null;
                        Forest.movement(Forest.getCreature(coordinates[0], coordinates[1]), 4);
                        moveComplete = true;
                        Forest.numberOfRats--;
                    }
                    break;
            }
        }
    }
}
