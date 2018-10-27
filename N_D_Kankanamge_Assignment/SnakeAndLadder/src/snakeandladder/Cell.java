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
// Represent one cell in the game board
public class Cell {
    int cellNumber;
    boolean isLadder = false;
    boolean isSnake  = false;
    boolean endCell  = false;
    int snakeNo;
    int ladderNo;
    int snakeLaderEnd;
}
