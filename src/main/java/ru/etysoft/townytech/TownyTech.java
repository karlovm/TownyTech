package ru.etysoft.townytech;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.etysoft.townytech.commands.MainExecutor;
import ru.etysoft.townytech.tech.TechListener;
import ru.etysoft.townytech.tech.TechTown;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public final class TownyTech extends JavaPlugin {

    private static TownyTech instance;
    public static boolean debugMode = true;
    private static final String path = "data.json";

    private static HashMap<String, TechTown> techList = new HashMap<>();

    public static boolean hasTechByUUID(String uuid)
    {
        return techList.containsKey(uuid);
    }

    public static TechTown createTechTown(Town town)
    {
        TechTown techTown = new TechTown();
        techTown.setTownName(town.getName());
        System.out.println("Created new TechTown " + town.getUUID().toString());
        techList.put(town.getUUID().toString(), techTown);
        return techTown;
    }

    public void save()
    {
        System.out.println("Saving data...");
        try {
            String toSave;

            Set<String> strings = techList.keySet();

            JSONArray jsonObject = new JSONArray();


            for (String uuid : strings) {
                TechTown techTown = techList.get(uuid);
                JSONObject techTownJSON = new JSONObject();
                techTownJSON.put("uuid", uuid);
                techTownJSON.put("farm", techTown.getFarmLevel());
                techTownJSON.put("mine", techTown.getMineLevel());
                techTownJSON.put("hunt", techTown.getHunterLevel());
                techTownJSON.put("level", techTown.getLevel());

                jsonObject.put(techTownJSON);
            }

            toSave = jsonObject.toString();

            try (PrintWriter out = new PrintWriter(path)) {
                out.println(toSave);
            }
            System.out.println("Saved successfully!");
        }
        catch (Exception e)
        {
            Bukkit.getConsoleSender().getServer().getLogger().warning("TownyTech Data Save Error! Check the error stack trace(bad config?):");
            e.printStackTrace();
        }
    }

    private String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public void load()
    {
        try {
            System.out.println("Loading data...");
            String data = readFileAsString(path);



            JSONArray jsonArray = new JSONArray(data);


            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                TechTown techTown = new TechTown();
                techTown.setFarmLevel(jsonObject.getInt("farm"));
                techTown.setHunterLevel(jsonObject.getInt("hunt"));
                techTown.setMineLevel(jsonObject.getInt("mine"));
                techTown.setLevel(jsonObject.getInt("level"));
                String uuid = jsonObject.getString("uuid");

                techList.put(uuid, techTown);


            }

            System.out.println("Loaded successfully!");
        }
        catch (Exception e)
        {
            Bukkit.getConsoleSender().getServer().getLogger().warning("TownyTech Data Load Error! Check the error stack trace(bad config?):");
            e.printStackTrace();
        }
    }


    public static HashMap<String, TechTown> getTechList() {
        return techList;
    }

    public static TechTown getTechByUUID(String uuid)
    {
        return techList.get(uuid);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        load();
        getCommand("tech").setExecutor(new MainExecutor());

        Bukkit.getPluginManager().registerEvents(new TechListener(), TownyTech.getInstance());
    }

    public static TownyTech getInstance()
    {
        return instance;
    }

    @Override
    public void onDisable() {
        save();

        // Plugin shutdown logic
    }
}
