package com.dmmsoft.app.consoleapp.userinterface.menu;

public class MenuItem {

    private ItemId itemId;
    private String name;
    private String accessKey;

    public MenuItem(ItemId itemId, String name, String accessKey) {
        this.name = name;
        this.accessKey = accessKey;
        this.itemId = itemId;
    }

    public String getName() {
        return this.name;
    }

    public ItemId getItemId() {
        return this.itemId;
    }

    public String getAccessKey() {
        return this.accessKey;
    }
}
