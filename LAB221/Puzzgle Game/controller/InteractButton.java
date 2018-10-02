/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import View.GameForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Phan Van Duc
 */
public class InteractButton {
    
    public void addButtonAction(JButton btn, HashMap<Integer, JButton> listButton, GameForm game){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setFlag(true);
                String currentValue = btn.getText();
                if(swapable(currentValue, listButton, game)){
                    swapValue(listButton, game);
                    updateCount(game);
                    solveWin(listButton, game);
                    System.out.println(game.getMoveCount());
                }
            }
        });
    }
    /**
     * When player win,player can not move any button 
     */
    public void removeAction(HashMap<Integer, JButton> listButton){
        for(Map.Entry<Integer,JButton> entry : listButton.entrySet()){
            JButton value = entry.getValue();
            for(ActionListener e : value.getActionListeners()){
                value.removeActionListener(e);
            }
        }
    }
    public void updateCount(GameForm game){
        int currentCount = game.getMoveCount();
        game.setMoveCount(++currentCount);
        game.getLbMove().setText(currentCount+"");
    }
    public void swapValue(HashMap<Integer, JButton> listButton, GameForm game){
        int current = game.getCurrentPosi();
        int empty = game.getEmptyPosi();
        String currentValue = listButton.get(current).getText();
        listButton.get(empty).setText(currentValue);
        listButton.get(current).setText("");
        game.setEmptyPosi(current);
    }
    public boolean swapable(String currentValue,HashMap<Integer,JButton> listButton, GameForm game){
        return isBotton(currentValue, listButton, game)|| isLeft(currentValue, listButton, game)
                ||isRight(currentValue, listButton, game)||isTop(currentValue, listButton, game);
    }
    public boolean isTop(String currentValue, HashMap<Integer, JButton> listButton, GameForm game){
        int currentPosition = game.getEmptyPosi()- game.getEdge();
        if(currentPosition >= 0 && listButton.get(currentPosition).getText().equals(currentValue)){
            game.setCurrentPosi(currentPosition);
            return true;
        }
        return false;
    }
    public boolean isBotton(String currentValue, HashMap<Integer, JButton> listButton, GameForm game){
        int size = game.getEdge();
        int empty = game.getEmptyPosi();
        int currentPosition = empty+ size;
        if(currentPosition < size*size && listButton.get(currentPosition).getText().equals(currentValue)){
            game.setCurrentPosi(currentPosition);
            return true;
        }
        return false;
    }
    public boolean isRight(String currentValue, HashMap<Integer, JButton> listButton, GameForm game){
        int size = game.getEdge();
        int currentPosition = game.getEmptyPosi()+ 1;
        if(currentPosition % size !=0 && listButton.get(currentPosition).getText().equals(currentValue)){
            game.setCurrentPosi(currentPosition);
            return true;
        }
        return false;
    }
    public boolean isLeft(String currentValue, HashMap<Integer, JButton> listButton, GameForm game){
        int size = game.getEdge();
        int currentPosition = game.getEmptyPosi()- 1;
        int empty = game.getEmptyPosi();
        if(currentPosition >= 0 && empty % size != 0 && listButton.get(currentPosition).getText().equals(currentValue)){
            game.setCurrentPosi(currentPosition);
            return true;
        }
        return false;
    }
    public void solveWin(HashMap<Integer, JButton> listButton, GameForm game) {
        if (checkWin(listButton)) {
            game.setFlag(false);
            JOptionPane.showMessageDialog(game, "You Win !!!", "Congratulate", 1);
            game.setTime(0);
            removeAction(listButton);
        }
    }

    public boolean checkWin(HashMap<Integer, JButton> listButton) {
        for (Map.Entry<Integer, JButton> entry : listButton.entrySet()) {
            Integer key = entry.getKey();
            JButton value = entry.getValue();
            String txt = value.getText();
            
            if (!txt.equals("")) {
                int num = 0;
                try {
                    num = Integer.parseInt(txt);
                } catch (NumberFormatException numf) {
                    System.err.println("Convert number checkWin error");
                }
                if (num - 1 != key) {
                    return false;
                }
            }
        }
        return true;
    }
}
