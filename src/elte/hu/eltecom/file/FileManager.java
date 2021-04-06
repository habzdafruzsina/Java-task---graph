package elte.hu.eltecom.file;

import elte.hu.eltecom.Manager;
import elte.hu.eltecom.graph.GraphFactory;
import elte.hu.eltecom.graph.LimitedGraph;
import elte.hu.eltecom.graph.UnlimitedGraph;
import elte.hu.eltecom.user.Language;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void writeGraph(Manager manager, String folderName){
        try {

            File outFile = new File(folderName + "/output.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(outFile));

            writer.print(manager.getGraph().getNodeNumber());
            writer.print(System.lineSeparator());
            if (manager.getGraph() instanceof LimitedGraph) {
                writer.print(((LimitedGraph) manager.getGraph()).print());
            } else if (manager.getGraph() instanceof UnlimitedGraph){
                writer.print(manager.getGraph().toString());
            }

            writer.close();
        }catch (IOException e){
            System.err.println("Fajlírási hiba");
        }
    }

    public static Manager readGraph(String folderName){
        try(final Scanner scanner = new Scanner(new File(folderName + "/input.txt"))){

            int size = scanner.nextByte();
            Manager manager = new Manager(GraphFactory.initGraph(size));
            scanner.nextLine();

            for(int i=0; i<size; i++){
                String[] strings = scanner.nextLine().split(" ");

                Language language = Language.HUN;
                for (Language l : Language.values()){
                    if (l.toString().equals(strings[0])){
                        language = l;
                        break;
                    }
                }


                String userName = "";
                for(int j=1; j<strings.length; j++){
                    userName = userName + strings[j];
                }

                if(userName.charAt(0) == '#'){
                    userName = userName.substring(1,userName.length());
                    manager.createAdmin(userName, language);
                } else {
                    manager.createUser(userName, language);
                }

            }

            int i=0;
            while (scanner.hasNextLine()){
                String[] strings = scanner.nextLine().split(" ");
                for(int j=0; j<manager.getGraph().getNodeNumber(); j++){
                    if(strings[j].equals("true")){
                        manager.linkUsers(manager.getUserById(i), manager.getUserById(j));
                    }
                }
                i++;
            }
            return manager;
        }catch (FileNotFoundException e){
            System.err.println("A fajl nem talalhato a " + folderName + "/input.txt" + " utvonalon");
            return null;
        }
    }
}
