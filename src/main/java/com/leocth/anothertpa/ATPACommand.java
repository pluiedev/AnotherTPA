package com.leocth.anothertpa;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * Do not criticize my code, cuz I'm a n00b /Slap-on-my-face/
 *  
 * File: AnotherTPA/com.leocth.anothertpa.ATPACommand
 * Desc.: /atpa command executor
 * ------------------------------------------------------
 * Don't steal PLZ!!!
 */
/**
 * /atpa command executor
 * @author LeoC200
 *
 */
public final class ATPACommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		System.out.println(sender);
		if (sender instanceof org.bukkit.entity.Player) {
			Player player = AnotherTPA.onlineplayers.get(((org.bukkit.entity.Player) sender).getUniqueId());

			if (args.length != 1) {
				sender.sendMessage(AnotherTPA.messages.get("anothertpa.lang.illegal-arguments"));
				return false;
			}
			if (Bukkit.getServer().getPlayer(args[0]) == null) {
				sender.sendMessage(AnotherTPA.messages.get("anothertpa.lang.not-online"));
				return false;
			}
			Player target = AnotherTPA.onlineplayers.get((Bukkit.getServer().getPlayer(args[0]).getUniqueId()));
				
			if (target.event != null) {
				sender.sendMessage(target.getName() + " " + AnotherTPA.messages.get("anothertpa.lang.request-full"));
			}
			player.sendRequest(target);
			return true;
		}
		else {
			sender.sendMessage(AnotherTPA.messages.get("anothertpa.lang.not-player"));
			return false;
		}
	}
}
