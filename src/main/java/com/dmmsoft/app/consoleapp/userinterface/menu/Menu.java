package com.dmmsoft.app.consoleapp.userinterface.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Menu {

    protected static final String MENU_ITEM_SEPARATOR = " / ";

    protected List<MenuItem> menuItems = new ArrayList<>();
    protected String promptQuestion = "";

    protected void createMenuItems() {
    }

    public List<String> getAccessKeys() {
        return menuItems.stream()
            .map(MenuItem::getAccessKey)
            .collect(Collectors.toList());
    }

    public String getPrintableMenu() {
        return menuItems.stream()
            .map(menuItem -> menuItem.getName() + " [" + menuItem.getAccessKey() + "]")
            .collect(Collectors.joining(MENU_ITEM_SEPARATOR));
    }

    public Optional<MenuItem> getItemByAccessKey(String accessKey) {
        return menuItems.stream()
            .filter(menuItem -> menuItem.getAccessKey().matches("^(?i)" + accessKey))
            .findFirst();
    }

    public String getPromptMessage() {
        return promptQuestion;
    }
}
