package catgirlbot;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.JSONWriter;

public class Perms {
    static final int bit_size = 8;
    static BitSet permSet = new BitSet();

    public static HashMap<Long, BitSet> parsePerms(JSONObject perms) {
        HashMap<Long, BitSet> parsedPerms =  new HashMap<Long, BitSet>();

        for (String key : perms.keySet()) {
            permSet.clear();
            int value = perms.getInt(key);
            for (int i = 0, k = 1; i < bit_size; i++, k <<= 1) { // Yippee something with bit magic
                if ((value & k) != 0) 
                    permSet.set(i); // whoa mia made this fancy and stuff
            }
            parsedPerms.put(Long.parseLong(key), (BitSet) permSet.clone());
        }
        return parsedPerms;
    }

    public static JSONObject initPerms() { //creates the permission bitset for each channel, fallback.

        JSONObject perms = new JSONObject(); // JSONObject with channel id as key and perms 

        perms.put(String.valueOf(1142469992617627713L), 0); // fallback
        return perms;
    }

    public static JSONObject loadPerms() { // ngl this is all either copied from somewhere or made by Mia idfk how json works
        InputStream is;
        try {
            is = new FileInputStream("perms.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            JSONObject perms = new JSONObject(jsonTxt); 
            System.out.println("Successfully loaded settings");
            return perms;
        } catch (Exception e) {
            JSONObject perms = initPerms();
            e.printStackTrace();
            System.out.println("Unsuccessfully loaded settings");
            Perms.savePerms(perms);
            perms = Perms.loadPerms();
            return perms;
        }
    }
    
    public static void savePerms(JSONObject perms) { // ngl this is all either copied from somewhere or made by Mia idfk how json works
        try {
            FileWriter myWriter = new FileWriter("perms.json");
            JSONWriter writer = new JSONWriter(myWriter);
            writer
                .object();
            for (String key : perms.keySet()) {
                writer
                    .key(key)
                    .value(perms.getInt(key));
            }
            writer
                .endObject();
            myWriter.close();
            System.out.println("Successfully saved settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
