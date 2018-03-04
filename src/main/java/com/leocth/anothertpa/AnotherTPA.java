package com.leocth.anothertpa;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Do not criticize my code, It sucks and I'm a n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.ATPACommand
 * Desc.: Main class
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * Main class.
 * @author LeoC200
 *
 */
public final class AnotherTPA extends JavaPlugin implements Listener {
	public static HashMap<UUID, Player> onlineplayers = new HashMap<>();
	public static int cooldown; //TODO Not fully implemented
	public static HashMap<String, String> messages = new HashMap<>();
	public static Logger logger;
	
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		onlineplayers.put(event.getPlayer().getUniqueId(), (new Player(event.getPlayer())));
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		onlineplayers.remove(event.getPlayer().getUniqueId());
	}
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		logger = getLogger();
		new I18n(getConfig().getString("anothertpa.lang"));
		loadConfig();
		saveDefaultConfig();
		getCommand("atpa").setExecutor(new ATPACommand());
        getCommand("atpac").setExecutor(new AcceptCommand());
        getCommand("atpad").setExecutor(new DenyCommand());
	}
	@Override
	public void onDisable() {
	}
	private void loadConfig() {
		cooldown = getConfig().getInt("anothertpa.cooldown", 50); //TODO Not fully implemented
		/*
		 * BLIND ALARM!!!!
		 * Is there someone can help me about this?!?!
		 */
		messages.put("anothertpa.lang.no-pending-request", getConfig().getString("anothertpa.lang.no-pending-request", "You don't have any pending requests!"));
		messages.put("anothertpa.lang.not-player", getConfig().getString("anothertpa.lang.not-player", "You must be a player!"));
		messages.put("anothertpa.lang.accepted", getConfig().getString("anothertpa.lang.accepted", "accepted your request. Please wait for a few seconds"));
		messages.put("anothertpa.lang.denied", getConfig().getString("anothertpa.lang.denied", "denied your request."));
		messages.put("anothertpa.lang.illegal-arguments", getConfig().getString("anothertpa.lang.illegal-arguments", "Illegal arguments."));
		messages.put("anothertpa.lang.not-online", getConfig().getString("anothertpa.lang.not-online", "The player is not online!"));
		messages.put("anothertpa.lang.request-full", getConfig().getString("anothertpa.lang.request-full", "already have a pending request. Please try again later."));
		messages.put("anothertpa.lang.request-1", getConfig().getString("anothertpa.lang.request-1", "wanted to teleport to you."));
		messages.put("anothertpa.lang.request-2", getConfig().getString("anothertpa.lang.request-2", "Type /atpac to agree, /atpad to deny"));
		// Added v1.1.0
		messages.put("anothertpa.lang.request-success", getConfig().getString("anothertpa.lang.request-success", "Request sended to"));
		messages.put("anothertpa.lang.accept-after-info", getConfig().getString("anothertpa.lang.accept-after-info", "You accepted the teleport request. Please wait for a few seconds..."));
		messages.put("anothertpa.lang.deny-after-info", getConfig().getString("anothertpa.lang.deny-after-info","You denied the teleport request"));
		messages.put("anothertpa.lang.self-tp", getConfig().getString("anothertpa.lang.self-tp","You cannot teleport to yourself!"));
		
	}
}
