package com.alpashaev.entity.animal;

public class Rat extends Creature {

    public Rat()
    {
        super(); // ref to parent class method
        this.symbol = 'R'; //Rat symbol will be R
        this.breedingPoint = 3; // at what point an instance of Lynx being will breed
    }


    public Rat(int row, int col)
    {
        super(row, col); // ref to parent class method
        this.symbol = 'R'; //Rat symbol will be R
        this.breedingPoint = 3; // at what point an instance of Lynx being will breed
    }

    public Rat(Rat o)
    {
        super(o);
    }
}
