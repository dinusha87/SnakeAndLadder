/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandladder;

import java.util.Scanner;

/**
 *
 * @author Dinusha
 */
public class Game {
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int roundNo = 0;
        Die d = new Die();
        GameBoard gb = new GameBoard();
        gb.initGameBoard(10, 10, 4, 4); // Board size, number of snakes and ladders, can customize the board by changing parameters
        
        System.out.print("Enter number of players :");
        int noOfPlayers = sc.nextInt();
        Player[] players = new Player[noOfPlayers];
        for(int i=0; i<noOfPlayers; i++)
        {
            System.out.print("Enter name of player no." + (i+1) + ":");
            String name = sc.next();
            players[i] = new Player(name);
        }
        
        gb.veiwBoard(10, 10); // View initial board
        gb.veiwSnakesAndLadders(); // view snake and ladder positions
        
        while(true)
        {
            if (roundNo == 0)
            {
               System.out.println("Game Started...!!!");
               sc.nextLine();
            }
            System.out.println("ROUND NO." + (roundNo + 1));
            System.out.println("-----------------------------------------------");
            for(int i=0; i<noOfPlayers; i++)
            {
                System.out.println(players[i].name + " is at cell " + players[i].currentCell.cellNumber  + ". Press enter to roll the die");
                sc.nextLine();
                int val = d.rollDie();
                int newPos = gb.move(players[i], val, 10*10);
                if(players[i].isFinished)
                {
                    System.out.println("CONGRATULATIONS....... " + players[i].name);
                    return;
                }
                else
                {
                    players[i].currentCell.cellNumber = newPos;
                    System.out.println(players[i].name + "'s position is " + players[i].currentCell.cellNumber);
                }
                System.out.println("-----------------------------------------------");
            }
            System.out.println("Press enter to Go to next round...");
            sc.nextLine();
            gb.veiwBoard(10, 10); // View board (give parameters according to x,y value given for initGameBoard method)
            gb.veiwSnakesAndLadders(); // view snake and ladder positions
            roundNo ++;
        }
        
   }
    
}
