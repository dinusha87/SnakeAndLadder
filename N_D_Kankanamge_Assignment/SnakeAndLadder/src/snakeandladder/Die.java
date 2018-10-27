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
public class Die {
    public int rollDie()
    {
        Random r = new Random();
        return r.nextInt(6) + 1;// generate random number between 1 and 6
    }
}
