package ru.etysoft.townytech.utils;

import org.bukkit.ChatColor;
import ru.etysoft.townytech.TownyTech;

import java.util.List;

public class TextManager {
    public static String toColor(String text) {
        try {
            return  ChatColor.translateAlternateColorCodes('&', text);
        } catch (Exception e) {
            return text;
        }
    }

    public static List<String> toColor(List<String> text) {
      for(int i = 0; i < text.size(); i++)
      {
          String updated = toColor(text.get(i));
          text.set(i, updated);
      }
      return text;
    }

    public static int getIntFromConfig(String key)
    {
        return TownyTech.getInstance().getConfig().getInt(key);
    }

    public static boolean getBooleanFromConfig(String key)
    {
        return TownyTech.getInstance().getConfig().getBoolean(key);
    }

    public static String[] getStringsFromConfigAsArray(String key)
    {
        List<String> list = getStringsFromConfig(key);
        String[] arr = list.toArray(new String[list.size()]);
        return arr;
    }

    public static List<String> getStringsFromConfig(String key)
    {
        return toColor(TownyTech.getInstance().getConfig().getStringList(key));
    }

    public static String getStringFromConfig(String key)
    {
        return toColor(TownyTech.getInstance().getConfig().getString(key));
    }

    public static String getPrefixedStringFromConfig(String key)
    {
        return toColor(TownyTech.getInstance().getConfig().getString("prefix") + TownyTech.getInstance().getConfig().getString(key));
    }
}
