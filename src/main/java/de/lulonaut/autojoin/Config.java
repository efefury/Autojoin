package de.lulonaut.autojoin;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
    private static final String category = "general";
    public static Configuration config;

    public static String getServer() {
        return config.getString("server", category, "hypixel.net", "");
    }

    public static void setServer(String newServer) {
        Property server = config.get(category, "server", "hypixel.net");
        server.set(newServer);
        config.save();
    }

//    public static boolean getFirstRun() {
//        return config.getBoolean("firstRun", category, false, "");
//    }
//
//    public static void setFirstRun() {
//        Property firstRun = config.get(category, "firstRun", false);
//        firstRun.set(true);
//        config.save();
//    }

    public static boolean getToggle() {
        return config.getBoolean("toggle", category, true, "");
    }

    public static void setToggle(boolean newToggle) {
        Property toggle = config.get(category, "toggle", true, "");
        toggle.set(newToggle);
        config.save();
    }

}
