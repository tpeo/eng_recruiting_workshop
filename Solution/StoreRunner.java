package Solution;
import java.util.*;
import java.io.*;

public class StoreRunner {
    public static void main(String[] args) {
        Store store = new Store();
        // create, write to file, and read from file
        try {
            //create file
            File output = new File("output.txt");
            output.createNewFile();

            // create writer
            FileWriter writer = new FileWriter("output.txt");

            // read from input
            File input = new File("input.txt");
            Scanner scan = new Scanner(input);

            while (scan.hasNextLine()) {
                String data = scan.nextLine();

                // i == 0: query type
                // i == 1: key
                // i == 2: version or value
                String[] parsedData = data.split(" "); 
                
                if (parsedData[0].equals("PUT")) {
                    writer.write(store.put(parsedData[1], Integer.parseInt(parsedData[2])) + "\n");
                } else if (parsedData.length == 2) {
                    writer.write(store.get(parsedData[1]) + "\n");
                } else {
                    writer.write(store.get(parsedData[1], Integer.parseInt(parsedData[2]))  + "\n");
                }
            }

            writer.close();
            scan.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
