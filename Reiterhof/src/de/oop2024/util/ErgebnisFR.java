package de.oop2024.util;

import java.util.HashMap;
import java.util.Map;

public class ErgebnisFR {
    
      private int zahl;
      private Map<RiderAL, PferdFR> map;

      public ErgebnisFR (){
        map = new HashMap<>();
        zahl= 0;
      }

    public int getZahl(){
        return zahl;
    }

    public void setZahl(int z){
        zahl = z;
    } 

    public void erhoheZahl(){
        zahl+=1;
    }

    public Map<RiderAL, PferdFR> getMap(){
        return map;
    }

    public void setMap(Map<RiderAL, PferdFR> m){
           map = m;
    }

    public String toString(){
          
        StringBuilder builder = new StringBuilder("\n+++ Beste Übereinstimmung +++\n");
        for (Map.Entry<RiderAL, PferdFR> entry : map.entrySet()) {
            RiderAL rider = entry.getKey();
            PferdFR pferd = entry.getValue();
            
            builder.append(String.format("Rider: %-15s | Pferd: %-15s\n", rider.getName(), pferd.getName()));
        }
        builder.append("----------------------------\n");
        
        return builder.toString();

    }
}