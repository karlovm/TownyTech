package ru.etysoft.townytech.tech;

import ru.etysoft.townytech.utils.TextManager;

public class MinePerks {
    public static int getMaxLevel()
    {
        return  TextManager.getIntFromConfig("miners.maxLevel");
    }

    public static int getCoalChance(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".coalChance");
    }

    public static int getFastDiggingEffectAmplifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".fastDiggingEffectAmplifier");
    }

    public static int getFastDiggingEffectDuration(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".fastDiggingEffectDuration");
    }

    public static boolean hasFastDiggingEffect(int mineLevel) {
        return TextManager.getBooleanFromConfig("miners.levels." + mineLevel + ".fastDigging");
    }

    public static int getCoalModifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".coalModifier");
    }

    public static int getGoldChance(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".goldChance");
    }

    public static int getGoldModifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".goldModifier");
    }

    public static int getDiamondChance(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".diamondChance");
    }

    public static int getDiamondModifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".diamondModifier");
    }

    public static   int getIronChance(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".ironChance");
    }

    public static int getIronModifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".ironModifier");
    }

    public static boolean isAutoIron(int mineLevel)
    {
        return TextManager.getBooleanFromConfig("miners.levels." + mineLevel + ".auto-iron");
    }

    public static boolean isAutoGold(int mineLevel)
    {
        return TextManager.getBooleanFromConfig("miners.levels." + mineLevel + ".auto-gold");
    }

    public static int getDiamondGoldBonusChance(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".diamondGoldBonusChance");
    }

    public static int getDiamondGoldBonusModifier(int mineLevel) {
        return TextManager.getIntFromConfig("miners.levels." + mineLevel + ".diamondGoldBonusModifier");
    }
}
