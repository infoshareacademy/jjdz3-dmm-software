package com.dmmsoft.app.consoleapp.userinterface.menu;

public class SelectTypeOfInvestmentMenu extends Menu {

    private final static String PROMPT_QUESTION = "Wybierz inwestycje, z którymi chcesz pracować:";
    private final static String LABEL_FUNDS = "Fundusze";
    private final static String LABEL_CURRENCIES = "Waluty";
    private final static String ACCESS_KEY_FUNDS = "F";
    private final static String ACCESS_KEY_CURRENCIES = "W";

    public SelectTypeOfInvestmentMenu() {
        createMenuItems();
        promptQuestion = PROMPT_QUESTION;
    }

    protected void createMenuItems() {
        menuItems.add(new MenuItem(ItemId.FUNDS, LABEL_FUNDS, ACCESS_KEY_FUNDS));
        menuItems.add(new MenuItem(ItemId.CURRENCIES, LABEL_CURRENCIES, ACCESS_KEY_CURRENCIES));
    }
}
