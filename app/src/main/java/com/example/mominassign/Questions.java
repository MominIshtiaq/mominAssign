package com.example.mominassign;

public class Questions {
    public int id;
    public String question;
    public String correct_answer;
    public String user_answer;

    public Questions(int id, String question, String correct_answer, String user_answer){
        this.id = id;
        this.question = question;
        this.correct_answer = correct_answer;
        this.user_answer = user_answer;
    }

    public void setId(int ia){
        id = ia;
    }
    public int getId(){
        return id;
    }

    public void setQuestion(String ques){
        question = ques;
    }
    public String getQuestion(){
        return question;
    }

    public void setCorrectAnswer(String ans){
        correct_answer = ans;
    }
    public String getCorrectAnswer(){
        return correct_answer;
    }

    public void setUserAnswer(String ans){
        user_answer = ans;
    }
    public String getUserAnswer(){
        return user_answer;
    }
}
