package fr.treemanager.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class CSVReader {
    /**
     * Méthode pour lire les données à partir d'un fichier CSV.
     * Cette méthode lit le fichier CSV ligne par ligne, divise chaque ligne en
     * utilisant le point-virgule comme séparateur et ajoute le tableau de chaînes
     * résultant à une liste.
     * La première ligne du fichier CSV est ignorée.
     * Si une exception se produit lors de la lecture du fichier, la trace de la
     * pile d'exception est imprimée.
     * 
     * @param filepath Le chemin d'accès au fichier CSV à lire.
     * @return Une liste de tableaux de chaînes, chaque tableau représentant une
     *         ligne du fichier CSV.
     */
    public static List<String[]> read(String filepath) {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
            String line;
            csvReader.readLine(); // on saute la première ligne
            while ((line = csvReader.readLine()) != null) {
                data.add(line.split(";"));
            }
            csvReader.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}