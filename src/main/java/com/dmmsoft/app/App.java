package com.dmmsoft.app;

import com.dmmsoft.app.Controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("DMMFinance application started");
        MainController mainController = new MainController();
        mainController.init();
    }
}
