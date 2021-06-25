package ru.etysoft.townytech.gui;

import javafx.application.Platform;
import org.bukkit.entity.Player;

public class SlotRunnable implements Runnable {
    @Override
    public void run() {

    }
    private Player sender;
    public void setSender(Player sender)
    {
        this.sender = sender;
    }

    public Player getSender() {
        return sender;
    }


}
