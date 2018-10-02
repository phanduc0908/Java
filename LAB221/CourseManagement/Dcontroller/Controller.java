/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dcontroller;

import entity.Course;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import view.AddCourse;
import view.DisplayCourses;
import view.Menu;
import view.SearchCourse;

/**
 *
 * @author Phan Van Duc
 */
public class Controller extends Validate {

    public void showMenu(ArrayList<Course> listCourse, Menu menu, AddCourse addCourse, DisplayCourses displayCourse, SearchCourse searchCourse) {
        menu.setVisible(true);
        addActionButton(listCourse, menu, addCourse, displayCourse, searchCourse);
    }

    public void addActionButton(ArrayList<Course> listCourse, Menu menu, AddCourse addCourse, DisplayCourses displayCourse, SearchCourse searchCourse) {

        // Action of Add Course
        menu.getBtnAddCourse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse.setVisible(true);
            }
        });
        // Action of search button
        menu.getBtnSearchCourse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listCourse.isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "No course to search");
                } else {
                    searchCourse.setVisible(true);
                    searchCourse.getTxtCourseName().setEditable(false);
                    searchCourse.getTxtCredit().setEditable(false);
                }
            }
        });
        // Action of display button
        menu.getBtnDisplayCourse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listCourse.isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "No course to display");
                } else {
                    showCourse(listCourse, displayCourse);
                    displayCourse.setVisible(true);
                }
            }
        });
        // Action of Exit Application
        menu.getBtnExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultDialog = JOptionPane.showConfirmDialog(menu, "Do you want to exit", "Exit", JOptionPane.YES_NO_OPTION);
                if (resultDialog == 0) {
                    System.exit(0);
                }
            }
        });
        
        // Handle with Add Course Jdialog
        HandleAddCourse(listCourse, addCourse);
        // Handle with Search Course Jdialog       
        HandleSearchCourse(listCourse, searchCourse);            
    }
    
    public void HandleAddCourse(ArrayList<Course> listCourse, AddCourse addCourse) {

        // Action when user click on Add Course button
        addCourse.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = addCourse.getTxtCode().getText();
                String name = addCourse.getTxtName().getText();
                String txtCredit = addCourse.getTxtCredit().getText();
                int credit = 0;
                // Check all field is not empty
                if (isEmpty(code, name, txtCredit)) {
                    JOptionPane.showMessageDialog(addCourse, "All is required");
                    addCourse.getTxtCode().requestFocus();
                } else {
                    // Check code is exist ?
                    if (isExistedCode(listCourse, code)) {
                        JOptionPane.showMessageDialog(addCourse, "Code is Exist, try again");
                        addCourse.getTxtCode().requestFocus();
                    } else {
                        // check credit is a number
                        if (!isCreditNumber(txtCredit)) {
                            JOptionPane.showMessageDialog(addCourse, "Credit must be an integer");
                            addCourse.getTxtCredit().requestFocus();
                        } else {
                            credit = Integer.parseInt(txtCredit);
                            if (!isCreditRange(credit)) {
                                JOptionPane.showMessageDialog(addCourse, "Credit must be in range [1-33]");
                                addCourse.getTxtCredit().requestFocus();
                            } else {
                                String nameValidate = getValidateName(name);
                                Course course = new Course(code, nameValidate, credit);
                                listCourse.add(course);
                                removeText(addCourse);
                            }
                        }
                    }
                }

            }
        });
        
        // Action when user click on Clear Course button
        addCourse.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeText(addCourse);
            }
        });
    }
    
    public void HandleSearchCourse(ArrayList<Course> listCourse, SearchCourse searchCourse){
        searchCourse.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = searchCourse.getTxtCode().getText();
                if(!isExistedCode(listCourse, code)){
                    JOptionPane.showMessageDialog(searchCourse, "Node course found with code: \"" + code+"\"");
                }else{
                    Course course = getCourseByCode(listCourse, code);
                    searchCourse.getTxtCourseName().setText(course.getCourseName());
                    searchCourse.getTxtCredit().setText(course.getCredit()+"");
                }
            }
        });
    }
    
    public void HandleDisplayCourse(ArrayList<Course> listCourse,DisplayCourses displayCourse){
        displayCourse.getTxtAreaCourse().setText("dddd");
        Collections.sort(listCourse);
        System.out.println("Text");
    }
    // delete all truong after add
    public void removeText(AddCourse addCourse) {
        addCourse.getTxtCode().setText("");
        addCourse.getTxtName().setText("");
        addCourse.getTxtCredit().setText("");
    }
    // get Course by code
    public Course getCourseByCode(ArrayList<Course> listCourse, String code){
        int size = listCourse.size();
        Course course = new Course();
        for (int i = 0; i <size; i++) {
            if(listCourse.get(i).getCode().equalsIgnoreCase(code)){
                course.setCode(code);
                course.setCourseName(listCourse.get(i).getCourseName());
                course.setCredit(listCourse.get(i).getCredit());
            }
        }
        return course;
    }
    // show Course 
    public void showCourse(ArrayList<Course> listCourse, DisplayCourses displayCourse){
        displayCourse.getTxtAreaCourse().setText("");
        for(Course course : listCourse){
            displayCourse.getTxtAreaCourse().append(course.toString() + "\n");
        }
    }
}
