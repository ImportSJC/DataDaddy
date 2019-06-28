package com.importsjc.datadaddy.Modules;

public class CustomValue implements IListItem{
    private boolean active = true;
    private String name;
    private String value = null;

    public CustomValue(boolean active){
        this.active = active;
    }

    public CustomValue(String name){
        this.name = name;
    }

    public CustomValue(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActive(){ return active; }

    public String customToString(int index) {
        if(active) {
            if (index == 1) {
                return value != null ? name + ": " + value : "";
            } else {
                return value != null ? ", " + name + ": " + value : "";
            }
        }

        return "";
    }
}
