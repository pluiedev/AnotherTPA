package com.leocth.anothertpa;

/*
 * Do not treat my code as trash, because I'm a n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.Player
 * Desc.: ATPA's player. Used for processing TP requests
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ATPA's Custom player.
 * @author LeoC200
 *
 */
public class Player {
	private org.bukkit.entity.Player nestedPlayer;
	public int cooldown = 0;//TODO Not fully implemented
    public HashMap<Date, RequestEvent> requests = new HashMap<>();
	
	public Player(org.bukkit.entity.Player player) {
		nestedPlayer = player;
	}
	
	public void sendRequest(Player target) {
        RequestEvent event = new RequestEvent(this, target, GregorianCalendar.getInstance().getTime());
		if (AnotherTPA.onlineplayers.containsKey(target.getNestedPlayer().getUniqueId())) {
			target.gotRequest(event);
		}
	}
    public void sendRequestTpHere(Player target) {
        TphereRequestEvent event = new TphereRequestEvent(this, target, GregorianCalendar.getInstance().getTime());
        if (AnotherTPA.onlineplayers.containsKey(target.getNestedPlayer().getUniqueId())) {
            target.gotRequestTpHere(event);
        }
    }
	
	public org.bukkit.entity.Player getNestedPlayer() {
		return nestedPlayer;
		
	}
	
	public String getName() {
		return nestedPlayer.getName();
	}
	
	public void sendMessage(String msg) {
		nestedPlayer.sendMessage(msg);
	}
	
    public void gotRequest(RequestEvent event) {
        requests.put(event.createdDate, event);
        this.sendMessage(I18n.g("request", event.sender.getName()));
    }

    public void gotRequestTpHere(TphereRequestEvent event) {
        requests.put(event.createdDate, event);
		this.sendMessage(I18n.g("request-tphere", event.sender.getName()));
	}

    //Helper method
    public RequestEvent mostRecent() {
        boolean first = true; // used to identify the first element
        RequestEvent curMostRecent = null; // this will, theoretically, NEVER causes a NPE.
        for (Map.Entry<Date, RequestEvent> entry : requests.entrySet()) {
            if (first) {
                curMostRecent = entry.getValue();
                first = false;
            }
            if (entry.getKey().before(curMostRecent.createdDate)) {
                curMostRecent = entry.getValue();
            }
        }
        System.out.println(curMostRecent.sender.getName() + curMostRecent.target.getName());
        return curMostRecent;
    }
}
