package com.am.mohamedraslan.hossamexams.JsonModel;

public class Questions_Form  {


    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String questionID;
    private String correctAnswer;


    public Questions_Form(String question, String answerOne, String answerTwo, String answerThree, String answerFour, String questionID, String correctAnswer) {
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.questionID = questionID;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public Questions_Form(){}


    public String getAnswerOne() {
        return answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }
}
