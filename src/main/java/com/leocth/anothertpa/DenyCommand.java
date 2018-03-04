package com.leocth.anothertpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * Do not criticize my code, I'm a TOTAL n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.DenyCommand
 * Desc.: /atpad (deny) command executor
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * /atpad command executor
 * @author LeoC200
 *
 */
public final class DenyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof org.bukkit.entity.Player) {
			Player player = AnotherTPA.onlineplayers.get(((org.bukkit.entity.Player) sender).getUniqueId());
			if (player.event == null) {
				sender.sendMessage(I18n.g("no-pending-request"));
				return false;
			}
			player.event.deny();
			return true;
		}
		else {
			sender.sendMessage(I18n.g("not-player"));
			return false;
		}
	}

}
