package com.leocth.anothertpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * Do not criticize my code, I'm a n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.AcceptCommand
 * Desc.: /atpac (accept) command executor
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * /atpac command executor
 * @author LeoC200
 *
 */
public final class AcceptCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof org.bukkit.entity.Player) {
			Player player = AnotherTPA.onlineplayers.get(((org.bukkit.entity.Player) sender).getUniqueId());
			if (player.event == null) {
				sender.sendMessage(AnotherTPA.messages.get("anothertpa.lang.no-pending-request"));
				return false;
			}
			player.event.accept();
			return true;
		}
		else {
			sender.sendMessage(AnotherTPA.messages.get("anothertpa.lang.not-player"));
			return false;
		}
	}
}
