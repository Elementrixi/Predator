package com.alpashaev.map;

import com.alpashaev.entity.animal.Creature;
import com.alpashaev.entity.animal.Lynx;
import com.alpashaev.entity.animal.Rat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Forest {
    public static Creature[][] forest = new Creature[20][20];
    public static int numberOfRats;
    public static int numberOfLynx;
    public Random randomNumGen = new Random();
    public static int rowScan = 0;
    public static int colScan = 0;


    public Forest()
    {
        numberOfRats = 100;
        numberOfLynx = 5;
        initializeBoard(numberOfRats, numberOfLynx);
    }

    public Forest(int rats, int lynx)
    {
        numberOfRats = rats;
        numberOfLynx = lynx;
        initializeBoard(numberOfRats, numberOfLynx);

    }

    public void initializeBoard(int numRat, int numLynx)
    {
        for (int i = 0; i < numRat; i++)
        {
            placeCreature(new Rat());
        }

        for (int i = 0; i < numLynx; i++)
        {
            placeCreature(new Lynx());
        }
    }

    public boolean isTaken(int row, int col)
    {
        return Forest.forest[row][col] != null;

    }

    public static void rowColScanClear(){
        rowScan = 0;
        colScan = 0;
    }

    public void placeCreature(Creature creature)
    {
        int row = randomNumGen.nextInt(20);
        int col = randomNumGen.nextInt(20);

        while (this.isTaken(row, col))
        {
            row = randomNumGen.nextInt(20);
            col = randomNumGen.nextInt(20);
        }

        if(creature instanceof Rat)
        {
            forest[row][col] = new Rat(row, col);
        }

        else if(creature instanceof Lynx)
        {
            forest[row][col]= new Lynx(row, col);
        }

        else
        {
            System.out.println("PlaceCreature error!");
            System.exit(-1);
        }
    }


    public static Creature getCreature(int row, int col)
    {
        if (row < 0 ) return forest[row + 1][col];
        if (col < 0) return forest[row][col+1];
        return forest[row][col];
    }

    public static Creature getCreature(int[] position)
    {
        return forest[position[0]][position[1]];
    }

    public boolean preyNear(Lynx lynx)
    {
        //Top left corner
        if (lynx.rightFlag && lynx.leftFlag)
        {
            if (	(getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom right corner
        else if (lynx.bottomFlag && lynx.rightFlag)
        {
            if (	(getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Top right corner
        else if (lynx.topFlag && lynx.rightFlag)
        {
            if (	(getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom left corner
        else if (lynx.bottomFlag && lynx.leftFlag)
        {
            if (	(getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        //Top row
        else if (lynx.topFlag)
        {
            if (	(getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Right column
        else if (lynx.rightFlag)
        {
            if (	(getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom row
        else if (lynx.bottomFlag)
        {
            if (	(getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Left column
        else if (lynx.leftFlag)
        {
            if (	(getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Check positions for Rats
        else return (getCreature(lynx.getRowPosition() - 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() + 1) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition() + 1, lynx.getColPosition()) instanceof Rat) ||
                    (getCreature(lynx.getRowPosition(), lynx.getColPosition() - 1) instanceof Rat);
    }

    public boolean emptyCellNear(Creature creature)
    {
        //Top left corner
        if (creature.topFlag && creature.leftFlag)
        {
            if (	(getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) ||
                    (getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom right corner
        else if (creature.bottomFlag && creature.rightFlag)
        {
            if ((getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                    (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Top right corner
        else if (creature.topFlag && creature.rightFlag)
        {
            if ((getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) ||
                    (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom left corner
        else if (creature.bottomFlag && creature.leftFlag)
        {
            if ((getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                    (getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        //Top row
        else if (creature.topFlag)
        {
            if (
                    (getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) ||
                            (getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) ||
                            (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Right column
        else if (creature.rightFlag)
        {
            if (
                    (getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                            (getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) ||
                            (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Bottom row
        else if (creature.bottomFlag)
        {
            if (
                    (getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                            (getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) ||
                            (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Left column
        else if (creature.leftFlag)
        {
            if (
                    (getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                            (getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) ||
                            (getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        //Check positions for Rat
        else return (getCreature(creature.getRowPosition() - 1, creature.getColPosition()) == null) ||
                    (getCreature(creature.getRowPosition(), creature.getColPosition() + 1) == null) ||
                    (getCreature(creature.getRowPosition() + 1, creature.getColPosition()) == null) ||
                    (getCreature(creature.getRowPosition(), creature.getColPosition() - 1) == null);
    }


    public void printField()
    {
        //Top row border
        System.out.println("  &&&&&&&&&&&&&&&&&&&&&& ");

        for(int i = 0; i < forest.length; i++)
        {
            //Left edge
            System.out.printf("%2d|", i);

            for(int j = 0; j < forest[i].length; j++)
            {
                //Print whitespace if empty cell
                if (forest[i][j] == null)
                {
                    System.out.print("-");
                }

                //Print symbol
                else
                {
                    System.out.print(forest[i][j].symbol);
                }
            }
            //Right edge border
            System.out.print("|");

            //Prevent printing last empty line
            if (i < forest.length - 1)
            {
                System.out.println();
            }
        }
        //Bottom row border
        System.out.println("\n&&&&&&&&&&&&&&&&&&&&&& ");

        System.out.println("Number of Rats: " + numberOfRats);
        System.out.println("Number of Lynxes: " + numberOfLynx);

    }


    public void removeCreature(int row, int col)
    {
        if (forest[row][col] == null)
        {
            System.out.println("Error: tried to remove a creature");
            System.exit(0);
        }

        if(forest[row][col] instanceof Rat)
        {
            numberOfRats--;
        }

        if(forest[row][col] instanceof Lynx)
        {
            numberOfLynx--;
        }

        forest[row][col] = null;
    }

    public static Integer[] randomDirections()
    {
        Integer[] directionArray = new Integer[] {1, 2, 3, 4};

        Collections.shuffle(Arrays.asList(directionArray));

        return directionArray;
    }


    public static int[] ratScanner()
    {
        //System.out.println("DEBUG rowScan colScan: " + rowScan + " " + colScan);
        for (int j = colScan; j < 20; j++)
        {
            //Finish partially completed row
            if (forest[rowScan][j] instanceof Rat)
            {
                //Check last column
                if (colScan >= 19)
                {
                    colScan = 0;
                    rowScan++;
                    return new int[] {rowScan - 1, 19};
                }

                else
                {
                    colScan = j + 1;
                    return new int[] {rowScan, j};
                }
            }
        }

        rowScan++;
        colScan = 0;

        for (int i = rowScan; i < 20; i++)
        {
            for (int j = colScan; j < 20; j++)
            {
                if (forest[i][j] instanceof Rat)
                {
                    if (j >= 19)
                    {
                        colScan = 0;
                        rowScan++;
                        return new int[] {i, 19};
                    }
                    else
                    {
                        colScan = j + 1;
                        return new int[] {i, j};
                    }

                }

                else if (j >= 19)
                {
                    colScan = 0;
                    rowScan++;
                }
            }
        }

        rowScan = 0;
        colScan = 0;
        return new int[] {-1, -1};
    }

    public static int[] lynxScanner()
    {
        for (int j = colScan; j < 20; j++)
        {
            //Finish partially completed row
            if (forest[rowScan][j] instanceof Lynx)
            {
                //Check last column
                if (colScan >= 19)
                {
                    colScan = 0;
                    rowScan++;
                    return new int[] {rowScan - 1, 19};
                }
                else
                {
                    colScan = j + 1;
                    return new int[] {rowScan, j};
                }
            }
        }

        rowScan++;
        colScan = 0;

        for (int i = rowScan; i < 20; i++)
        {
            for (int j = colScan; j < 20; j++)
            {
                if (forest[i][j] instanceof Lynx)
                {
                    if (j >= 19)
                    {
                        colScan = 0;
                        rowScan++;
                        return new int[] {i, 19};
                    }

                    else
                    {
                        colScan = j + 1;
                        return new int[] {i, j};
                    }
                }
            }

            colScan = 0;
            rowScan++;
        }

        rowScan = 0;
        colScan = 0;
        return new int[] {-1, -1};
    }

    public static void movement(Creature creature, int direction)
    {
        try {
            int rowPos = creature.getRowPosition();
            int colPos = creature.getColPosition();

            if ('R' == creature.getSymbol()) {
                switch (direction) {
                    case 1:
                        forest[rowPos - 1][colPos] = new Rat((Rat) creature);
                        getCreature(rowPos - 1, colPos).setRowPosition(rowPos - 1);
                        forest[rowPos][colPos] = null;
//				rowPos--;
                        break;
                    case 2:
                        forest[rowPos][colPos + 1] = new Rat((Rat) creature);
                        getCreature(rowPos, colPos + 1).setColPosition(colPos + 1);
                        forest[rowPos][colPos] = null;
//				colPos++;
                        break;
                    case 3:
                        forest[rowPos + 1][colPos] = new Rat((Rat) creature);
                        getCreature(rowPos + 1, colPos).setRowPosition(rowPos + 1);
                        forest[rowPos][colPos] = null;
//				rowPos++;
                        break;
                    case 4:
                        forest[rowPos][colPos - 1] = new Rat((Rat) creature);
                        getCreature(rowPos, colPos - 1).setColPosition(colPos - 1);
                        forest[rowPos][colPos] = null;
//				colPos--;
                        break;
                }
            } else if (creature.getSymbol() == 'L') {
                switch (direction) {
                    case 1:
                        forest[rowPos - 1][colPos] = new Lynx((Lynx) creature);
                        getCreature(rowPos - 1, colPos).setRowPosition(rowPos - 1);
                        forest[rowPos][colPos] = null;
//				rowPos--;
                        break;
                    case 2:
                        forest[rowPos][colPos + 1] = new Lynx((Lynx) creature);
                        getCreature(rowPos, colPos + 1).setColPosition(colPos + 1);
                        forest[rowPos][colPos] = null;
//				colPos++;
                        break;
                    case 3:
                        forest[rowPos + 1][colPos] = new Lynx((Lynx) creature);
                        getCreature(rowPos + 1, colPos).setRowPosition(rowPos + 1);
                        forest[rowPos][colPos] = null;
//				rowPos++;
                        break;
                    case 4:
                        forest[rowPos][colPos - 1] = new Lynx((Lynx) creature);
                        getCreature(rowPos, colPos - 1).setColPosition(colPos - 1);
                        forest[rowPos][colPos] = null;
//				colPos--;
                        break;
                }

                creature.timeSinceBreed++; //Increment counter for breeding
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void breed(Creature creature)
    {
        //Check breeding point and open cell
        if ((creature.timeSinceBreed == creature.breedingPoint) && this.emptyCellNear(creature))
        {
            int row = creature.getRowPosition();
            int col = creature.getColPosition();

            Integer[] randomDirections = Forest.randomDirections();
            boolean breedComplete = false;
            try {
                if (creature instanceof Rat) {
                    Rat rat = (Rat) Forest.getCreature(row, col);

                    for (int i = 0; i < 4 && (breedComplete == false); i++) {
                        switch (randomDirections[i]) {
                            case 1:
                                if (rat.topFlag)
                                    break;

                                if (Forest.getCreature(row - 1, col) == null) {
                                    forest[row - 1][col] = new Rat(row - 1, col);
                                    breedComplete = true;
                                }
                                break;
                            case 2:
                                if (rat.rightFlag)
                                    break;

                                if (Forest.getCreature(row, col + 1) == null) {
                                    forest[row][col + 1] = new Rat(row, col + 1);
                                    breedComplete = true;
                                }
                                break;
                            case 3:
                                if (rat.bottomFlag)
                                    break;

                                if (Forest.getCreature(row + 1, col) == null) {
                                    forest[row + 1][col] = new Rat(row + 1, col);
                                    breedComplete = true;
                                }
                                break;
                            case 4:
                                if (rat.leftFlag)
                                    break;

                                if (Forest.getCreature(row, col - 1) == null) {
                                    forest[row][col - 1] = new Rat(row, col - 1);
                                    breedComplete = true;
                                }
                                break;
                        }
                    }
                    creature.timeSinceBreed = 0; //Reset breeding counter
                    numberOfRats++;
                } else if (creature instanceof Lynx) {
                    Lynx lynx = (Lynx) Forest.getCreature(row, col);

                    for (int i = 0; i < 4 && (breedComplete == false); i++) {
                        switch (randomDirections[i]) {
                            case 1:
                                if (lynx.topFlag)
                                    break;

                                if (Forest.getCreature(row - 1, col) == null) {
                                    forest[row - 1][col] = new Lynx(row - 1, col);
                                    breedComplete = true;
                                }
                                break;
                            case 2:
                                if (lynx.rightFlag)
                                    break;

                                if (Forest.getCreature(row, col + 1) == null) {
                                    forest[row][col + 1] = new Lynx(row, col + 1);
                                    breedComplete = true;
                                }
                                break;
                            case 3:
                                if (lynx.bottomFlag)
                                    break;

                                if (Forest.getCreature(row + 1, col) == null) {
                                    forest[row + 1][col] = new Lynx(row + 1, col);
                                    breedComplete = true;
                                }
                                break;
                            case 4:
                                if (lynx.leftFlag)
                                    break;

                                if (Forest.getCreature(row, col - 1) == null) {
                                    forest[row][col - 1] = new Lynx(row, col - 1);
                                    breedComplete = true;
                                }
                                break;
                        }
                    }
                    creature.timeSinceBreed = 0;
                    numberOfLynx++;
                }
            } catch (Exception e){
                System.out.println("Error in breed!");
                System.exit(-1);
            }
        }
    }


    public void starving(Lynx lynx)
    {
        if (lynx.timeSinceEat >= Lynx.hungerTimeMax)
        {
            this.removeCreature(lynx.getRowPosition(), lynx.getColPosition());
        }
    }
}

