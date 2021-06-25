package ru.etysoft.townytech.tech;

import ru.etysoft.townytech.utils.TextManager;

public class HunterPerks {

    public static int getMaxLevel()
    {
        return  TextManager.getIntFromConfig("hunters.maxLevel");
    }

    public static int getMeatChance(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".meatChance");
    }

    public static int getMeatModifier(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".meatModifier");
    }

    public static int getLeatherChance(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".leatherChance");
    }

    public static int getLeatherModifier(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".leatherModifier");
    }

    public static int getForceEffectAmplifier(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".forceEffectAmplifier");
    }

    public static int getForceEffectDuration(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".forceEffectDuration");
    }

    public static boolean hasForceEffect(int huntLevel) {
        return TextManager.getBooleanFromConfig("hunters.levels." + huntLevel + ".forceEffect");
    }

    public static int getRegenEffectAmplifier(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".regenEffectAmplifier");
    }

    public static int getRegenEffectDuration(int huntLevel) {
        return TextManager.getIntFromConfig("hunters.levels." + huntLevel + ".regenEffectDuration");
    }

    public static boolean hasRegenEffect(int huntLevel) {
        return TextManager.getBooleanFromConfig("hunters.levels." + huntLevel + ".regenEffect");
    }
}
