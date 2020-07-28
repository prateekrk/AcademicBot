package com.academicbot.academicbot;

public class Attendance_Model {
    String USN;
    public Attendance_Model(){}

    public  Attendance_Model(String USN){
        this.USN=USN;
    }

    public String getUsn() {
        return USN;
    }
}
