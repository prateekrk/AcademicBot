package com.academicbot.academicbot;

public class enrollment_model {
    String sub,code,credits;
    public enrollment_model(String s,String c,String c1){
        this.sub=s;
        this.code=c;
        this.credits=c1;
    }

    public String getCode() {
        return code;
    }

    public String getCredits() {
        return credits;
    }

    public String getSub() {
        return sub;
    }
    public enrollment_model(){}
}
