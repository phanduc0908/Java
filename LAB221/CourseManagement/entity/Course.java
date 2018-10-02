/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Kafka
 */
public class Course implements Comparable<Course>{
    
    private String code;
    private String courseName;
    private int credit;

    public Course() {
    }
    
    public Course(String code, String courseName, int credit) {
        this.code = code;
        this.courseName = courseName;
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return code + "  |  " + courseName + "  |  " + credit;
    }

    @Override
    public int compareTo(Course other) {
        return this.getCredit() - other.getCredit();
    }
       
}
