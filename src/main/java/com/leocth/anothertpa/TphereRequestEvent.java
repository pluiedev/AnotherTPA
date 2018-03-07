package com.leocth.anothertpa;

import java.util.Timer;
import java.util.TimerTask;

public class TphereRequestEvent extends RequestEvent {
    public Player sender, target;

    public TphereRequestEvent(Player p1, Player p2) {
        super(p1, p2);
        sender = p1;
        target = p2;

    }
    public void accept() {
        sender.sendMessage(I18n.g("accepted", target.getName()));
        target.sendMessage(I18n.g("accept-after-info"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                target.getNestedPlayer().teleport(sender.getNestedPlayer());
                target.event = null;
                target.cooldown = AnotherTPA.cooldown;//TODO Not fully implemented
                this.cancel();
            }
        }, 3000);
    }
    public void deny() {
        sender.sendMessage(I18n.g("denied", target.getName()));
        target.sendMessage(I18n.g("deny-after-info"));
        target.event = null;
    }
}
