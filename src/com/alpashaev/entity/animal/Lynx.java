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
        this.breedingPoint = 8; // at what point an instance of Lynx being will breed
        this.timeSinceEat = 0;
    }


}
