package com.academicbot.academicbot;

public class assignmentModel {
    String subject,description,due_date;

   public  assignmentModel(String subject1,String description1,String due_date1){
       this.description=description1;
       this.subject=subject1;
       this.due_date=due_date1;

   }
   public  assignmentModel(){}

    public String getSubject() { return subject; }

    public String getDescription() {
        return description;
    }

    public String getDue_date() {
        return due_date;
    }
}
