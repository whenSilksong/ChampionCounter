/**
 * Only works for League of Legends!
 * @author Dominik Winter
 * special thanks @moritztucher
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

public class ChampionCounter extends JFrame {

    public static void main(String[] args) {
        JFrame guiLayout = new GUIChampionCounter();
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Press following key for actions:");
            System.out.println("Enter champion: E");
            System.out.println("Get full List: CL");
            System.out.println("Most played champions: MP");
            System.out.println("Reset list counter: R");
            System.out.println("To exit, type 'EXIT'");

            // enter choice
            choice = sc.next();
            if (choice.equalsIgnoreCase("E")) {
                EnterChampionsToHashMap();
            } else if (choice.equalsIgnoreCase("CL")) {
                getFullChampionList();
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.println("Are you sure to reset the stats? \nPress: yes/no");
                String yesNoCheck = sc.next();
                if (yesNoCheck.equalsIgnoreCase("YES")) {
                    resetList();
                }
            } else if (choice.equalsIgnoreCase("MP")) {
                getTop10Champions();
            }
        } while (!choice.equalsIgnoreCase("EXIT"));
        System.out.println("Program has ended.");
        sc.close();
    }

    //Saves the entered champions in a HashMap
    public static void EnterChampionsToHashMap() {
        //Create HashMap
        HashMap<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("championCounterList.CSV"));
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
        //Enter Champions
        Scanner enterchamps = new Scanner(System.in);
        System.out.println("Which champions are played?");
        String[] playedChampions = new String[10];
        for (int i = 0; i < 10; i++) {
            playedChampions[i] = enterchamps.next();
            if (map.containsKey(playedChampions[i])){
                map.put(playedChampions[i], map.get(playedChampions[i]) + 1);
            }
        }
        //Saves and overwrite CSVfile
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
        for (Map.Entry<String, Integer> entry : data.entrySet()){
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

    //Returns the complete list
    public static void getFullChampionList(){
        String pathChampionList = "championCounterList.CSV";
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathChampionList));
            String line;
            while ((line = br.readLine()) != null) { //reading all to the empty(null) line
                System.out.print(line + "\n");
            }
        } catch (FileNotFoundException e) { //If file is not found
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        HashMap<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("championCounterList.CSV"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                String[] arr = str[0].split(";");
                map.put(arr[0], Integer.parseInt(arr[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
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
            System.out.println(count + ". " + entry.getKey() + " - " + entry.getValue() + " times played");
            count++;
        }
    }
}