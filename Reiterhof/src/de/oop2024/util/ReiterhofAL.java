package de.oop2024.util;

import java.io.*;
import java.util.*;


public class ReiterhofAL {

	private List<PferdFR> pferde;
    private List<RiderAL> riders;
    private boolean error ;
    private String riderPath ;
    private String pferdPath ;
    public String[] attributes ;


    public ReiterhofAL() {
        pferde = new ArrayList<>();
        riders = new ArrayList<>();
    }



    public void Pfadeingabe() {
        error = true;
        while (error) {
            try {            
                riderPath = "rider.txt" ;
      
                pferdPath = "pferde.txt";

                ladeData(riderPath, pferdPath);
                error = false;
            } catch (IOException e) {
                System.out.println("Fehler beim Lesen der Konfigurationsdateien: " + e.getMessage());
            }
        }

        System.out.println("Beste Übereinstimmung finden...");
        ErgebnisFR ergebnis = match(riders, pferde);
        System.out.println(ergebnis);
    }


    public void ladeData(String riderPath, String pferdPath) throws IOException {
    	//Lade Pferde
        try (BufferedReader br = new BufferedReader(new FileReader(pferdPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length != 2) {
                    throw new IOException("Fehlerhafte Pferd-Konfigurationsdatei");
                }
                PferdFR pferd = new PferdFR(attributes[0], Integer.parseInt(attributes[1]));
                pferde.add(pferd);
            }
        }

     
        try (BufferedReader br = new BufferedReader(new FileReader(riderPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length < 2) {
                    throw new IOException("Fehlerhafte Rider-Konfigurationsdatei");
                }
                
                // Erstelle die Wunschpferde-Liste
                List<PferdFR> wunschPferde = new ArrayList<>();
                for (int i = 2; i < attributes.length; i++) {
                    PferdFR pferd = findePferd(attributes[i]);
                    if (pferd != null) {
                        wunschPferde.add(pferd);
                    } else {
                        throw new IOException("Fehlerhafte Rider-Konfigurationsdatei: Pferdename nicht gefunden (" + attributes[i] + ")");
                    }
                }

                RiderAL rider = new RiderAL(attributes[0], Integer.parseInt(attributes[1]), wunschPferde);
                riders.add(rider);
            }
        }
    }

   
    private PferdFR findePferd(String name) {
        for (PferdFR pferd : pferde) {
            if (pferd.getName().equals(name)) {
                return pferd;
            }
        }
        return null;
    }

  
    public ErgebnisFR match(List<RiderAL> riderList, List<PferdFR> pferdList) {
        ErgebnisFR bestErgebnis = new ErgebnisFR();

        for (RiderAL rider : riderList) {
            for (PferdFR pferd : pferdList) {
                if (rider.getEigenschaft() >= pferd.getSchwierigkeit()) {
                    // Erstelle Kopien der Listen ohne den aktuellen Rider und das aktuelle Pferd
                    List<RiderAL> remainingRiders = new ArrayList<>(riderList);
                    remainingRiders.remove(rider);
                    List<PferdFR> remainingPferde = new ArrayList<>(pferdList);
                    remainingPferde.remove(pferd);

                    // Rekursiver Aufruf fuer die verbleibenden Rider und Pferde
                    ErgebnisFR currentErgebnis = match(remainingRiders, remainingPferde);

                    // Fuege die aktuelle Uebereinstimmung hinzu
                    currentErgebnis.getMap().put(rider, pferd);
                    currentErgebnis.erhoheZahl();

                    // Zusaetzlicher Punkt für gewuenschtes Pferd
                    if (rider.getWunschPferde().contains(pferd)) {
                        currentErgebnis.erhoheZahl();
                    }

                    // Aktualisiere das beste Ergebnis
                    if (currentErgebnis.getZahl() > bestErgebnis.getZahl()) {
                        bestErgebnis = currentErgebnis;
                    }
                }
            }
        }

        return bestErgebnis;
    }}