package com.alpashaev.entity.animal;

public class Rat extends Creature {

    public Rat()
    {
        super(); // ref to parent class method
        defaultData();
    }


    public Rat(int row, int col)
    {
        super(row, col); // ref to parent class method
        defaultData();
    }

    public Rat(Rat o)
    {
        super(o);
    }

    @Override
    protected void defaultData() {
        this.symbol = 'R'; //Rat's symbol is R
        this.breedingPoint = 5; // at what point an instance of Rat breeds
    }
}
