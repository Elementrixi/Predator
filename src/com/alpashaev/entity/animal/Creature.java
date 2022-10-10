package com.alpashaev.entity.animal;

public class Creature {

    public int breedingPoint; // when to breed
    public int timeSinceBreed;
    public char symbol; // what symbol display at a console
    public int rowPosition;
    public int colPosition;

    // for position validation in the edges of map
    public boolean topFlag;
    public boolean rightFlag;
    public boolean bottomFlag;
    public boolean leftFlag;


    public Creature() // default empty constructor
    {
        this.rowPosition = -1;
        this.colPosition = -1;
        this.timeSinceBreed = 0;
        this.topFlag = true;
        this.bottomFlag = true;
        this.leftFlag = true;
        this.rightFlag = true;

    }

    public Creature(int row, int col) // constructor with row and col positions
    {
        this.rowPosition = row;
        this.colPosition = col;
        this.timeSinceBreed = 0;
    }

    public Creature(Creature o) // constructor of inheritancing
    {
        this.breedingPoint = o.breedingPoint;
        this.timeSinceBreed = o.timeSinceBreed;
        this.symbol = o.symbol;
        this.rowPosition = o.getRowPosition();
        this.colPosition = o.getColPosition();
    }

    public char getSymbol() {
        return symbol;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }
}
