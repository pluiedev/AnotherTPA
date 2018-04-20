package com.leocth.anothertpa;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

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
		getCommand("atpah").setExecutor(new TpHereCommand());
	}
	@Override
	public void onDisable() {
	}
	private void loadConfig() {
		cooldown = getConfig().getInt("anothertpa.cooldown", 5); //TODO Not fully implemented
	}
}
