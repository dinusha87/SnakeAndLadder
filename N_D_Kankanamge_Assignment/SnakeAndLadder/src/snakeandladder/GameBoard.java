/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandladder;

import java.util.Random;

/**
 *
 * @author Dinusha
 */
public class GameBoard {
    Cell[] cellArray;
    Snake[] Snakes;
    Ladder[] Ladders;
    
    // Generate the board with x*y number of cells
    public void initGameBoard(int x, int y, int snakes, int ladders)
    {
        cellArray = new Cell[x*y];
        for(int i=0; i< x*y; i++)
        {
           cellArray[i]= new Cell();
           cellArray[i].cellNumber = i+1;
        }
        cellArray[x*y-1].endCell = true;
        Snakes = new Snake[snakes];
        Ladders = new Ladder[ladders];
        generateSnakes(snakes, x*y);
        generateLadders(snakes, x*y);
        putSnakeAndLadersOnBoard(cellArray, Snakes, Ladders);
    }
    // This method will generate snake positions randomly
    private void generateSnakes(int noOfSnakes, int noOfCells)
    {
        Random r = new Random();
        for(int i=0; i<noOfSnakes; i++)
        {
            int Start = r.nextInt(noOfCells); // exclud 100th cell
            int End   = r.nextInt(Start);
            Snakes[i] = new Snake();
            Snakes[i].snakeNo = i+1;
            Snakes[i].startPos = Start;
            Snakes[i].endPos = End;
        }
    }
    
    // This method will generate ladder positions randomly
    private void generateLadders(int noOfLadders, int noOfCells)
    {
        Random r = new Random();
        for(int i=0; i<noOfLadders; i++)
        {
            int Start = r.nextInt(noOfCells); // exclud 100th cell
            int End   = Start + (int)(Math.random() * ((noOfCells - Start) + 1));// Should be greater than Start
            Ladders[i] = new Ladder();
            Ladders[i].ladderNo = i+1;
            Ladders[i].startPos = Start;
            Ladders[i].endPos = End;
        }
    }
    
    //Assign positions of snake and ladders in the board
    private void putSnakeAndLadersOnBoard(Cell[] cells, Snake[] snakes, Ladder[] ladders)
    {
        for(int i=0; i<cells.length; i++)
        {
           for(int j=0; j<snakes.length; j++)
           {
               if(snakes[j].startPos == cells[i].cellNumber)
               {
                   cells[i].isSnake = true;
                   cells[i].snakeNo = snakes[j].snakeNo;
                   cells[i].snakeLaderEnd = snakes[j].endPos;
               }
           } 
           for(int k=0; k<ladders.length; k++)
           {
               if(ladders[k].startPos == cells[i].cellNumber && cells[i].isSnake == false)
               {
                   cells[i].isLadder = true;
                   cells[i].ladderNo = ladders[k].ladderNo;
                   cells[i].snakeLaderEnd = ladders[k].endPos;
               }
           } 
        }
    }
    // Print positions of snakes and ladders
    public void veiwSnakesAndLadders()
    {
        System.out.println("-----------------------------------SNAKES AND LADDER POSITIONS--------------------------------------------------------");
        for(int i=0; i<Snakes.length; i++)
        {
            System.out.print("Snake " + Snakes[i].snakeNo +": [" + Snakes[i].startPos + " " + Snakes[i].endPos +"]\t");
        }
        System.out.println();
        for(int i=0; i<Ladders.length; i++)
        {
            System.out.print("Ladder " + Ladders[i].ladderNo +": [" + Ladders[i].startPos + " " + Ladders[i].endPos +"]\t");
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }
    
    // Print the board
    public void veiwBoard(int x, int y)
    {
        for(int i=cellArray.length; i>0; i--)
        {
            System.out.print("| " + cellArray[i-1].cellNumber + " ");
            if(cellArray[i-1].isSnake)
            {
                System.out.print("Snake:" + cellArray[i-1].snakeNo + " Start ");
            }
            else if(cellArray[i-1].isLadder)
            {
                System.out.print("Ladder:" + cellArray[i-1].ladderNo + " Start");
            }
            else if(cellArray[i-1].endCell)
            {
                System.out.print("END         ");
            }
            else
            {
                System.out.print("              ");
            }
            System.out.print("|");
            if(cellArray[i-1].cellNumber % x == 0)// Go to next line after x no of cells
            {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    // Move the player to next position
    public int move(Player p, int dieVal, int BoardSize)
    {
        int currPos = p.currentCell.cellNumber;
        int nextPos = currPos + dieVal;
        System.out.println("Moving from " + currPos + " to " + nextPos);
        if(nextPos < BoardSize) // if this condition is false, player has reached the top cell
        {
            if(cellArray[nextPos-1].isSnake || cellArray[nextPos-1].isLadder)
            {            
                if(cellArray[nextPos-1].isSnake)
                {
                    System.out.println("Found a Snake. going from " + nextPos + " to " + cellArray[nextPos-1].snakeLaderEnd);
                }
                else if(cellArray[nextPos-1].isLadder)
                {
                    System.out.println("Found a Ladder. going from " + nextPos + " to " + cellArray[nextPos-1].snakeLaderEnd);
                }
                nextPos = cellArray[nextPos-1].snakeLaderEnd;
            }
            else if(cellArray[nextPos-1].endCell || nextPos > BoardSize)
            {
                System.out.println("PLAYER : " + p.name + " WON !!!!!");
                p.isFinished = true;
            }
        }
        else
        {
             System.out.println("PLAYER : " + p.name + " WON !!!!!");
             p.isFinished = true;
        }
        return nextPos;
    }
}
