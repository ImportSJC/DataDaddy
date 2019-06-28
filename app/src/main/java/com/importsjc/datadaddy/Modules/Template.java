package com.importsjc.datadaddy.Modules;

import java.util.ArrayList;
import java.util.List;

public class Template implements IListItem{
    private String name;
    List<IListItem> dataPointList;

    public Template(String name){
        this.name = name;
        dataPointList = new ArrayList<>();
    }

    public Template(String name, List<IListItem> dataPointList){
        this.name = name;
        this.dataPointList = dataPointList;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<IListItem> getDataPointList(){
        return dataPointList;
    }

    public void addDataPoint(DataPoint myDataPoint){
        dataPointList.add(myDataPoint);
    }

    public void removeDataPoint(int index){
        dataPointList.remove(index);
    }
}
