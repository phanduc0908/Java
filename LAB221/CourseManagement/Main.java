
import entity.Course;
import java.util.ArrayList;
import view.Menu;
import Dcontroller.Controller;
import view.AddCourse;
import view.DisplayCourses;
import view.SearchCourse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phan Van Duc
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Course> listCourse = new ArrayList<>();
//        listCourse.add(new Course("PRF311", "Java web Aplication", 5));
//        listCourse.add(new Course("PRF3131", "Java Lab Aplication", 4));
//        listCourse.add(new Course("PRF314", "Java web Aplication", 6));
        Menu menu = new Menu();
        AddCourse addCourse = new AddCourse(menu, true);
        DisplayCourses displayCourse = new DisplayCourses(menu, true);
        SearchCourse searchCourse = new SearchCourse(menu, true);
        
        Controller controll = new Controller();
        controll.showMenu(listCourse, menu, addCourse, displayCourse, searchCourse);

    }
}
