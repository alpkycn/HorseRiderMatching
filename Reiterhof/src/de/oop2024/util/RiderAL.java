package de.oop2024.util;

import java.util.List;


public class RiderAL {
    private String name;
    private int eigenschaft;
    private List<PferdFR> wunschPferde;


    public RiderAL(String name, int eigenschaft, List<PferdFR> wunschPferde) {
        this.name = name;
        this.eigenschaft = eigenschaft;
        this.wunschPferde = wunschPferde;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getEigenschaft() {
        return eigenschaft;
    }


    public void setEigenschaft(int eigenschaft) {
        this.eigenschaft = eigenschaft;
    }

 
    public List<PferdFR> getWunschPferde() {
        return wunschPferde;
    }

   
    public void setWuschPferde(List<PferdFR> wunschPferde) {
        this.wunschPferde = wunschPferde;
    }
}