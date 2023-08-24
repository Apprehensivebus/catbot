package catgirlbot;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Perms {
    static Long homechannel = 1142829953562464267L; // temp solution until I add commands to add channels and file storage for persistence
    static Long homechannel2 = 1142496848125370448L;
    static Long homechannel3 = 1142829787409305680L;
    static BitSet homeperms= new BitSet(2);
    static BitSet homeperms2= new BitSet(2);
    static BitSet homeperms3= new BitSet(2);
    static BitSet permSet = new BitSet();

    public static HashMap<Long, BitSet> parsePerms(JSONObject perms){
        HashMap<Long, BitSet> parsedPerms =  new HashMap<Long, BitSet>();

        for (String key : perms.keySet()){
            permSet.clear();
            String[] p = perms.getString(key).replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\s", "").split(",");
            for (String k : p){
                permSet.set(Integer.parseInt(k));
            }
            System.out.println("Putting " + permSet + " into " + Long.parseLong(key));
            parsedPerms.put(Long.parseLong(key),(BitSet) permSet.clone());
        }
        return parsedPerms;
    }

    public static JSONObject initPerms(){ //creates the permission bitset for each channel, fallback.
        JSONObject perms= new JSONObject(); // JSONobject with channel id as key and perms 
        //homeperms.set(0);// enable first perm by default for now
        homeperms.set(0);// enable second perm by default for now
        perms.put(String.valueOf(homechannel),homeperms); // woo bitset

        homeperms2.set(1);// enable second perm by default for now
        perms.put(String.valueOf(homechannel2),homeperms2); // woo bitset

        homeperms3.set(0);//
        homeperms3.set(1);// enable second perm by default for now
        perms.put(String.valueOf(homechannel3),homeperms3); // woo bitset


        return perms;
    }

    public static JSONObject loadPerms(){ //creates the permission bitset for each channel
        InputStream is;
        try {
            is = new FileInputStream("perms.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            JSONObject perms = new JSONObject(jsonTxt); 
            System.out.println("Successfully loaded settings");
            return perms;
        } catch (Exception e) {
            JSONObject perms=initPerms();
            e.printStackTrace();
            System.out.println("Unsuccessfully loaded settings");
            Perms.savePerms(perms);
            return perms;
        }
    }
    
    public static void savePerms(JSONObject perms){
        try {
            FileWriter myWriter = new FileWriter("perms.json");
            perms.write(myWriter);
            myWriter.close();
            System.out.println("Successfully saved settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
