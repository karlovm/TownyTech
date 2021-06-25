package ru.etysoft.townytech.tech;

import ru.etysoft.townytech.utils.TextManager;

public class FarmPerks {

    public static int getMaxLevel()
    {
        return  TextManager.getIntFromConfig("farmers.maxLevel");
    }

    public static int getWoolChance(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".woolChance");
    }

    public static int getWoolModifier(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".woolModifier");
    }


    public static int getWheatChance(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".wheatChance");
    }

    public static int getWheatModifier(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".wheatModifier");
    }

    public static int getGoldenAppleChance(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".goldenAppleChance");
    }

    public static int getGoldenAppleModifier(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".goldenAppleModifier");
    }

    public static int getCarrotChance(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".carrotChance");
    }

    public static int getCarrotModifier(int farmLevel) {
        return TextManager.getIntFromConfig("farmers.levels." + farmLevel + ".carrotModifier");
    }


}
