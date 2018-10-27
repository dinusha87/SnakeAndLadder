/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandladder;

/**
 *
 * @author Dinusha
 */
public class Player {
    String name;
    int payerId;
    static int count = 0;
    boolean isFinished = false;
    Cell currentCell;
    
    public Player(String name)
    {
        this();
        this.name = name;
        this.payerId = count;
        currentCell = new Cell();

    }
    public Player()
    {
        count++;
    }
}
