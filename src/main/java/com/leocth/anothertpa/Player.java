package com.leocth.anothertpa;

/*
 * Do not treat my code as trash, because I'm a n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.Player
 * Desc.: ATPA's player. Used for processing TP requests
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * ATPA's Custom player.
 * @author LeoC200
 *
 */
public class Player {
	private org.bukkit.entity.Player nestedPlayer;
	public RequestEvent event = null;
	public int cooldown = 0;//TODO Not fully implemented
	
	public Player(org.bukkit.entity.Player player) {
		nestedPlayer = player;
	}
	
	public void sendRequest(Player target) {
		RequestEvent event = new RequestEvent(this, target);
		if (AnotherTPA.onlineplayers.containsKey(target.getNestedPlayer().getUniqueId())) {
			target.gotRequest(event);
		}
	}
    public void sendRequestTpHere(Player target) {
        RequestEvent event = new TphereRequestEvent(this, target);
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
        this.event = event;
        this.sendMessage(I18n.g("request", event.sender.getName()));
    }
	public void gotRequestTpHere(RequestEvent event) {
		this.event = event;
		this.sendMessage(I18n.g("request-tphere", event.sender.getName()));
	}
}
