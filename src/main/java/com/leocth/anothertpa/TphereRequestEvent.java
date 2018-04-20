package com.leocth.anothertpa;

import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TphereRequestEvent extends RequestEvent {
    public Player sender, target;
    public Date createdDate;

    public TphereRequestEvent(Player p1, Player p2, Date createdDate) {
        super(p1, p2, createdDate);
        sender = p1;
        target = p2;
        this.createdDate = createdDate;

    }
    public void accept() {
        sender.sendMessage(I18n.g("accepted", target.getName()));
        target.sendMessage(I18n.g("accept-after-info"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                target.getNestedPlayer().teleport(sender.getNestedPlayer(), PlayerTeleportEvent.TeleportCause.COMMAND);
                target.cooldown = AnotherTPA.cooldown;//TODO Not fully implemented
                target.requests.remove(target.mostRecent());
                this.cancel();
            }
        }, 3000);
    }
    public void deny() {
        sender.sendMessage(I18n.g("denied", target.getName()));
        target.sendMessage(I18n.g("deny-after-info"));
        target.requests.remove(target.mostRecent());
    }
}
