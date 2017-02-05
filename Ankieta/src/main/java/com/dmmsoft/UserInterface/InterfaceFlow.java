package com.dmmsoft.UserInterface;

import com.dmmsoft.Survey.Survey;
import java.io.IOException;

/**
 * Created by Milo4321 on 2017-02-03.
 */
public class InterfaceFlow {

    public static void startFlow(Survey survey) {
        UI ui = new UI();
        try {
            ui.initializeMenu("Testowa Ankieta");
            // TODO exception handling
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.initializeSurvey(survey);
        ui.printSurveyResults(survey);


    }


}
