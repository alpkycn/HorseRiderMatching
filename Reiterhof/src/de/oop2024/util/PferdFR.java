package de.oop2024.util;


public class PferdFR {

private String name;
private int schwierigkeit;

public PferdFR (String name, int schwierigkeit) {
this.name = name;
this.schwierigkeit = schwierigkeit;
}

public String getName(){
return name;
}

public void setName(String nam){
        name= nam;
}

public int getSchwierigkeit(){
return schwierigkeit;
}

public void setSchwierigkeit(int schwer){
schwierigkeit = schwer;
}

}