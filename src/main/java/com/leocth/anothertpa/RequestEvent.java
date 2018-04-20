package com.leocth.anothertpa;

import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/*
 * Do not criticize my code, I'm a newcomer /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.RequestEvent
 * Desc.: The TP Request. Hooray!
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * TP Request. The class name is not important
 * @author LeoC200
 *
 */
public class RequestEvent {
	public Player sender, target;
    public boolean isProcessed = false;
    public Date createdDate;

    public RequestEvent(Player p1, Player p2, Date createdDate) {
		sender = p1;
		target = p2;
        this.createdDate = createdDate;

	}
	public void accept() {
        isProcessed = true;
		sender.sendMessage(I18n.g("accepted", target.getName()));
		target.sendMessage(I18n.g("accept-after-info"));
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
                sender.getNestedPlayer().teleport(target.getNestedPlayer(), PlayerTeleportEvent.TeleportCause.COMMAND);
                target.cooldown = AnotherTPA.cooldown;//TODO Not fully implemented
                target.requests.remove(target.mostRecent().createdDate);
                this.cancel();
			}
		}, 3000);
	}
	public void deny() {
        isProcessed = true;
		sender.sendMessage(I18n.g("denied", target.getName()));
		target.sendMessage(I18n.g("deny-after-info"));
        target.requests.remove(target.mostRecent().createdDate);
	}
}
