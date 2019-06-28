package com.importsjc.datadaddy.Modules;

public class DataPoint implements IListItem{
    private String name;
    private CustomValue value1;
    private CustomValue value2;
    private CustomValue value3;

    public DataPoint(String name, CustomValue value1, CustomValue value2, CustomValue value3){
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomValue getValue1() {
        return value1;
    }

    public void setValue1(CustomValue value1) {
        this.value1 = value1;
    }

    public CustomValue getValue2() {
        return value2;
    }

    public void setValue2(CustomValue value2) {
        this.value2 = value2;
    }

    public CustomValue getValue3() {
        return value3;
    }

    public void setValue3(CustomValue value3) {
        this.value3 = value3;
    }

//    @androidx.annotation.NonNull
    @Override
    public String toString() {
        return "["+value1.customToString(1)+value2.customToString(2)+value3.customToString(3)+"]";
    }
}
