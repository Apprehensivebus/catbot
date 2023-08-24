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
    static Long homechannel = 1142829953562464267L; // temp solution until I add commands to add channels and file storage for persistence

    static final int bit_size = 2;

    static BitSet homeperms = new BitSet(bit_size);
    static BitSet permSet = new BitSet();

    public static HashMap<Long, BitSet> parsePerms(JSONObject perms) {
        HashMap<Long, BitSet> parsedPerms =  new HashMap<Long, BitSet>();

        for (String key : perms.keySet()) {
            permSet.clear();
            int value = perms.getInt(key);
            for (int i = 0, k = 1; i <= 2; i++, k <<= 1) 
                if ((value & k) != 0) 
                    permSet.set(i);
                

            parsedPerms.put(Long.parseLong(key), (BitSet) permSet.clone());
        }
        return parsedPerms;
    }

    public static JSONObject initPerms() { //creates the permission bitset for each channel, fallback.

        JSONObject perms = new JSONObject(); // JSONObject with channel id as key and perms 

        //homeperms.set(0);// enable first perm by default for now
        homeperms.set(0);// enable second perm by default for now
        perms.put(String.valueOf(homechannel), homeperms); // woo bitset
        return perms;
    }

    public static JSONObject loadPerms() { //creates the permission bitset for each channel
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
    
    public static void savePerms(JSONObject perms) {
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
