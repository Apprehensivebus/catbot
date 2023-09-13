package catgirlbot;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.javacord.api.entity.server.Server;


public class GoobNames {
    public static String deWeirbify(String stringe, List<String> weirbs){
        for(int i=0; i<weirbs.size() ; i++){
            stringe = stringe.replace(weirbs.get(i).split("\\|")[0], weirbs.get(i).split("\\|")[1]);
        }
        return stringe;
    }

    public static List<String> getWeirbs(Server server) throws IOException{
        Path path = Paths.get("weirbs/" + server.getId() + ".txt");
        List<String> weirbs = Files.readAllLines(path);
        return weirbs;
    }

    public static void addWeirb (Server server, char weirb, char unweirb) throws IOException { // adds a weirb duh
            System.out.println("Added " + weirb + " as a weirb of " + unweirb);
            FileOutputStream fos = new FileOutputStream(("weirbs/" + server.getIdAsString() + ".txt"), true); // get file named after the server
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(outputStreamWriter);      
            out.write((weirb + "|" + unweirb + "\r\n").toCharArray()); //write channel & message ids
            out.close(); // to prevent memory leaks and more importantly java from yelling at me
    }




    public static String deBadify(String stringe, List<String> weirbs){
        for(int i=0; i<weirbs.size() ; i++){
            stringe = stringe.replaceAll(weirbs.get(i).split("\\|")[0], weirbs.get(i).split("\\|")[1]);
        }
        return stringe;
    }

    public static List<String> getBads(Server server) throws IOException{
        Path path = Paths.get("bads/" + server.getId() + ".txt");
        List<String> weirbs = Files.readAllLines(path);
        return weirbs;
    }

    public static void addBad (Server server, String bad, String goob) throws IOException { // adds a weirb duh
            System.out.println("Added " + bad + " as a bad of " + goob);
            FileOutputStream fos = new FileOutputStream(("bads/" + server.getIdAsString() + ".txt"), true); // get file named after the server
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(outputStreamWriter);      
            out.write((bad + "|" + goob + "\r\n").toCharArray()); //write channel & message ids
            out.close(); // to prevent memory leaks and more importantly java from yelling at me
    }


}

