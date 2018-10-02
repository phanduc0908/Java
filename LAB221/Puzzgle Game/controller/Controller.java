/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import View.GameForm;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author MTC
 */
public class Controller extends InteractButton {

    final int SPACE_BUTTON = 10;
    final int SIZE_BUTTON = 50;
    final int MARGIN_BUTTON = 5;
    Timer time;
    

    public void createGameArea(HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getEdge();
        ArrayList<Integer> listNumber = (ArrayList) listShuffle(size);
        game.getPnlGameArea().removeAll();
        game.getPnlGameArea().setLayout(new GridLayout(size, size, SPACE_BUTTON, SPACE_BUTTON));
        game.getPnlGameArea().setSize(size, size);
        for (int i = 0; i < size * size; i++) {
            int num = listNumber.get(i);
            String txt = num % (size * size) != 0 ? num + "" : "";
            if (txt.equals("")) {
                game.setEmptyPosi(i);
            }
            JButton btn = new JButton(txt);
            btn.setPreferredSize(new Dimension(SIZE_BUTTON, SIZE_BUTTON));
            btn.setMargin(new Insets(MARGIN_BUTTON, MARGIN_BUTTON, MARGIN_BUTTON, MARGIN_BUTTON));
            addButtonAction(btn, listButton, game);
            listButton.put(i, btn);
            game.getPnlGameArea().add(btn);
        }
        game.pack();
    }
        // get list shuffle number
    public ArrayList listShuffle(int size) {
        ArrayList<Integer> listSuffle = new ArrayList<>();
        for (int i = 1; i < size * size; i++) {
            listSuffle.add(i);
        }
        listSuffle.add(0);
        Random ran = new Random();
        for (int i = 0; i < Math.pow(size, 3); i++) {
            switch (ran.nextInt(4)) {
                case 0:
                    swapListShuffle(listSuffle, size, size);
                    break;
                case 1:
                    swapListShuffle(listSuffle, size, -size);
                    break;
                case 2:
                    swapListShuffle(listSuffle, size, 1);
                    break;
                case 3:
                    swapListShuffle(listSuffle, size, -1);
                    break;
            }
        }
        return listSuffle;
    }

    // swap list shuffle
    public ArrayList swapListShuffle(ArrayList<Integer> listShuffle, int size, int valueChange) {
        int emptyPosition = -1;
        for (Integer num : listShuffle) {
            if (num == 0) {
                emptyPosition = listShuffle.indexOf(num);
                break;
            }
        }
        int checkPosition = emptyPosition + valueChange;
        if (checkPosition >= 0 && checkPosition < size * size) {
            listShuffle.set(emptyPosition, listShuffle.get(checkPosition));
            listShuffle.set(checkPosition, 0);
        }
        return listShuffle;
    }
    public void addAction(HashMap<Integer, JButton> listButton, GameForm game) {
         time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isFlag()) {
                    int countTime = game.getTime();
                    game.getLbTime().setText(++countTime + " sec");
                    game.setTime(countTime);
                }
            }
        });
        game.getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isFlag()) {
                    game.setFlag(false);
                    int n = JOptionPane.showConfirmDialog(game, "Do you want to start a new game",
                            "New Game", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        game.setFlag(true);
                        game.setTime(0);
                        game.getLbTime().setText(game.getTime() + " sec");
                        game.setMoveCount(0);
                        game.getLbMove().setText(game.getMoveCount() + "");
                            createGameArea(listButton, game);

                        time.start();
                    }
                    else{
                        game.setFlag(true);
                    }
                } else{
                        game.setFlag(true);
                        game.setTime(0);
                        game.getLbTime().setText(game.getTime() + " sec");
                        game.setMoveCount(0);
                        game.getLbMove().setText(game.getMoveCount() + "");

                            createGameArea(listButton, game);
 
                        time.start();
                }
                
            }
        });
        game.getCcbSize().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSizeGameArea(game);
            }
        });
    }

    public void setSizeGameArea(GameForm game) {
        String sizeString = game.getCcbSize().getSelectedItem().toString();
        String[] sizeSquare = sizeString.split("x");
        try {
            int num = Integer.parseInt(sizeSquare[0]);
            game.setEdge(num);
        } catch (NumberFormatException nfe) {
            System.err.println("Size illegal");
        }
    }

    public int getTimeFromText(String txt) {
        String[] splitString = txt.split(" ");
        int num = 0;
        try {
            num = Integer.parseInt(splitString[0]);
        } catch (NumberFormatException nfe) {
            System.err.println("Get time error");
        }
        return num;
    }

    public Thread initThreadElapse(GameForm game) {
        Thread elapse = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.print("");
                    if (game.isFlag()) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            System.err.println("Thread elapse error");
                        }
                        int time = game.getTime();
                        game.getLbTime().setText(++time + " sec");
                        game.setTime(time);
                    }
                }
            }
        };
        return elapse;
    }

}
