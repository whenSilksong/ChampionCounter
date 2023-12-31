/**
 * Only works for League of Legends!
 * @author Dominik Winter
 * special thanks @moritztucher
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

public class ChampionCounter extends JFrame {
    public static String mostPlayedChampions = "";

    public static void main(String[] args) {
        JFrame guiLayout = new GUIChampionCounter();
    }

    //Add champion input to hashmap
    public static void EnterChampionsToHashMap() {
        // Create or load HashMap
        HashMap<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("championCounterList.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                String[] arr = str[0].split(";");
                map.put(arr[0], Integer.parseInt(arr[1]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update champion counters based on user input
        for (int i = 0; i < 10; i++) {
            String championName = GUIChampionCounter.playedChampions[i];
            if (map.containsKey(championName)) {
                map.put(championName, map.get(championName) + 1);
            }
        }

        // Save updated data to CSV file
        System.out.println(map);
        try {
            HashMapToCSV(map, "championCounterList.CSV");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Convert HashMap to CSVfile
    public static void HashMapToCSV(Map<String, Integer> data, String csvFilePath) throws IOException {
        FileWriter writer = new FileWriter(csvFilePath);
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            writer.append(key);
            writer.append(";");
            writer.append(value.toString());
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    //Resets the list to 0
    public static void resetList(){
        //read default CSVfile
        HashMap<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("championCounterList_backup.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                String[] arr = str[0].split(";");
                map.put(arr[0], Integer.parseInt(arr[1]));
            }
        } catch (FileNotFoundException e) { //If file is not found
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //override CSVfile
        try {
            HashMapToCSV(map, "championCounterList.CSV");
            System.out.println("List has been reset.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // sorts and gives the top 10 champions
    public static void getTop10Champions() {
        mostPlayedChampions = ""; //Prevents the string from being regenerated
        HashMap<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("championCounterList.CSV"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                String[] arr = str[0].split(";");
                map.put(arr[0], Integer.parseInt(arr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        int count = 1;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            if (count > 10) {
                break;
            }
            mostPlayedChampions += count + ". " + entry.getKey() + " - " + entry.getValue() + " times played\n";
            count++;
        }
    }
}
