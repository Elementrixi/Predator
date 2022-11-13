package com.alpashaev.entity.animal;

public class Creature {

    public int breedingPoint; // when to breed
    public int timeSinceBreed;
    public char symbol; // what symbol display at a console
    public int rowPosition;
    public int colPosition;
    public int timeSinceEat;

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
        this.setRowPosition(row);
        this.setColPosition(col);
        this.timeSinceBreed = 0;
    }

    public Creature(Creature o) // constructor for inheritance
    {
        this.breedingPoint = o.breedingPoint;
        this.timeSinceBreed = o.timeSinceBreed;
        this.symbol = o.symbol;
        this.setRowPosition(o.getRowPosition());
        this.setColPosition(o.getColPosition());
    }

    public void eat(int[] coordinates){
        System.out.println("Meal");
    }

    protected void defaultData(){
        this.symbol = '1';
        this.breedingPoint = -1;
        this.timeSinceEat = -1;
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
        this.topFlag = false;
        this.bottomFlag = false;

        this.rowPosition = rowPosition;

        if (rowPosition <= 0)
        {
            this.topFlag = true;
        }

        else if(rowPosition >= 19)
        {
            this.bottomFlag = true;
        }
    }

    public void setColPosition(int colPosition) {
        this.leftFlag = false;
        this.rightFlag = false;

        this.colPosition = colPosition;

        if (colPosition <= 0)
        {
            this.leftFlag = true;
        }

        else if(colPosition >= 19)
        {
            this.rightFlag = true;
        }
    }
}
