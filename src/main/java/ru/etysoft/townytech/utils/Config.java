package ru.etysoft.townytech.utils;

public class Config {

    public static String denyConsole()
    {
        return TextManager.getPrefixedStringFromConfig("cant-from-console");
    }

    public static String noTown()
    {
        return TextManager.getPrefixedStringFromConfig("no-town");
    }

    public static String noTownMoney()
    {
        return TextManager.getPrefixedStringFromConfig("not-enough-money");
    }

    public static String cantUpgrade()
    {
        return TextManager.getPrefixedStringFromConfig("err-upgrade");
    }

    public static String noPerm()
    {
        return TextManager.getPrefixedStringFromConfig("no-perm");
    }

    public static String args()
    {
        return TextManager.getPrefixedStringFromConfig("args");
    }

}
