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


public class GoobNames {
    public static String deWeirbify(String stringe, List<String> weirbs){
        for(int i=0; i<weirbs.size() ; i++){
            stringe = stringe.replace(weirbs.get(i).split("\\|")[0], weirbs.get(i).split("\\|")[1]);
        }
        return stringe;
    }

    public static List<String> getWeirbs() throws IOException{
        Path path = Paths.get("weirbs.txt");
        List<String> weirbs = Files.readAllLines(path);
        return weirbs;
    }

    public static void addWeirb (char weirb, char unweirb) throws IOException { // adds a weirb duh
            System.out.println("Added " + weirb + " as a weirb of " + unweirb);
            FileOutputStream fos = new FileOutputStream(("weirbs.txt"), true); // get file named after the server
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(outputStreamWriter);      
            out.write((weirb + "|" + unweirb + "\r\n").toCharArray()); //write channel & message ids
            out.close(); // to prevent memory leaks and more importantly java from yelling at me
    }
}