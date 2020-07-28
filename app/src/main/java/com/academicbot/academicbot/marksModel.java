package com.academicbot.academicbot;

public class marksModel {
  private   String Cie,See;
    public  marksModel(String cie,String see){
        this.Cie=cie;
        this.See=see;
    }
    public marksModel(){}

    public String getCie() {
        return Cie;
    }

    public String getSee() {
        return See;
    }
}
