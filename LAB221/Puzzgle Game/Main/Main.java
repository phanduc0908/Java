/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.GameForm;
import controller.Controller;
import java.util.HashMap;
import javax.swing.JButton;

/**
 *
 * @author Phan Van Duc
 */
public class Main {
    public static void main(String[] args) {
        GameForm game = new GameForm();
        game.setVisible(true);        
        HashMap<Integer, JButton> listButton = new HashMap<>();
        Controller control = new Controller();
//        Thread elapse = control.initThreadElapse(game);
//        elapse.start();
        control.addAction(listButton, game);
        control.createGameArea(listButton, game);
    }
}
