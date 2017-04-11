package com.dmmsoft.app.UserInterface.Menu;

public class SelectTypeOfAnalysisMenu extends Menu {

    private final static String PROMPT_QUESTION = "Wybierz rodzaj analizy, jaki chcesz przeprowadzić:";
    private final static String LABEL_LOCAL_EXTREMES = "Ekstrema lokalne";
    private final static String LABEL_GLOBAL_EXTREMES = "Ekstrema globalne";
    private final static String ACCESS_KEY_GLOBAL_EXTREMES = "G";
    private final static String ACCESS_KEY_LOCAL_EXTREMES = "L";

    public SelectTypeOfAnalysisMenu() {
        createMenuItems();
        promptQuestion = PROMPT_QUESTION;
    }

    protected void createMenuItems() {
        menuItems.add(new MenuItem(ItemId.LOCAL_EXTREMES, LABEL_LOCAL_EXTREMES, ACCESS_KEY_LOCAL_EXTREMES));
        menuItems.add(new MenuItem(ItemId.GLOBAL_EXTREMES, LABEL_GLOBAL_EXTREMES, ACCESS_KEY_GLOBAL_EXTREMES));
    }
}
