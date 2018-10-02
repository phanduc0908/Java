/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dcontroller;

import entity.Course;
import java.util.ArrayList;

/**
 *
 * @author Phan Van Duc
 */
public class Validate {

    public String getValidateName(String name){
        name = name.trim();
        name = name.replaceAll("\\s+", " ");
        name = name.toLowerCase();        
        return name;
    }
    public boolean  isEmpty(String code, String name, String txtCredit){
        if(code.isEmpty() || name.isEmpty() || txtCredit.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean isCreditNumber(String txtCredit) {
        String regex = "\\d+";
        if (txtCredit.matches(regex)) {
            return true;
        }
        return false;
    }
    
    public boolean isCreditRange(int credit){
        if(credit > 0 && credit <= 33){
            return true;
        }
        return false;
    }

    public boolean isExistedCode(ArrayList<Course> listCourse, String Code) {
        int size = listCourse.size();
        for (int i = 0; i < size; i++) {
            String existedCode = listCourse.get(i).getCode();
            if (Code.equalsIgnoreCase(existedCode)) {
                return true;
            }
        }
        return false;
    }
}
