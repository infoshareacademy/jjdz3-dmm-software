package com.dmmsoft.UserInterface;

import java.io.IOException;

import com.dmmsoft.Survey.*;

/**
 * Created by Milo4321 on 2017-02-01.
 */
public class UI {

    public String initializeMenu(String title) throws IOException {

        this.printMenu(title);
        UserInput ui = new UserInput();
        String s = ui.ReadUserInput();
        MenuCommandScanner.ScanForMenuCommand(s);
        return (s);

        // TODO menu processing by SWITCH statement
    }

    private void printMenu(String title) {
        System.out.println("");
        System.out.println("***************************************");
        System.out.println("Ankieta: " + title);
        System.out.println("***************************************");
        System.out.println("  MENU: ");
        System.out.println("  w       - Powrot do glownego menu ");
        System.out.println("  q       - Zakoncz program");
        System.out.println();
        System.out.println("  <enter> - Rozpocznij ankiete! ");
    }

    public void initializeSurvey(Survey survey) {

        Question[] questions = survey.getQuestions();
        for (Question q : questions) {
            System.out.println();
            System.out.println(q.getId() + " " + q.getQuestion());

            Answer[] answers = q.getAnswers();
            for (Answer an : answers) {
                an.setIsCorrect(false);         // by default set all answers to false

                System.out.print("  ");
                System.out.print(AnswerMapper.convertIdToChar(an.getId()));
                System.out.print(an.getAnswer() + " ");
                System.out.println();
                //System.out.println(an.getIsCorrect());
            }
            System.out.print("Podaj literę <enter>:");
            UserInput ui = new UserInput();
            String s = ui.ReadUserInput();
            answers[AnswerMapper.convertInputCharToId(s)].setIsCorrect(true);
        }
    }

    public void printSurveyResults(Survey survey) {
        System.out.println();
        System.out.println("Koniec - wciśnij <enter> aby zobaczyć wynik ankiety");
        UserInput pressAnyKey = new UserInput();
        pressAnyKey.ReadUserInput();

        System.out.println("*** WYNIKI ANKIETY ***");
        Question[] questions = survey.getQuestions();
        for (Question q : questions) {
            System.out.println();
            System.out.println(q.getId() + " " + q.getQuestion());

            Answer[] answers = q.getAnswers();
            for (Answer an : answers) {
                if (an.getIsCorrect() == true) {
                    System.out.print("X ");
                } else {
                    System.out.print("  ");
                }
                System.out.print(AnswerMapper.convertIdToChar(an.getId()));
                System.out.print(an.getAnswer());
                System.out.println();
            }
        }
    }

}














