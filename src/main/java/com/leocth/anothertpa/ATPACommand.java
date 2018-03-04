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
		if (sender instanceof org.bukkit.entity.Player) {
			Player player = AnotherTPA.onlineplayers.get(((org.bukkit.entity.Player) sender).getUniqueId());

			if (args.length != 1) {
				sender.sendMessage(I18n.g("illegal-arguments"));
				return false;
			}
			if (Bukkit.getServer().getPlayer(args[0]) == null) {
				sender.sendMessage(I18n.g("not-online", args[0]));
				return false;
			}
			Player target = AnotherTPA.onlineplayers.get((Bukkit.getServer().getPlayer(args[0]).getUniqueId()));
			if (target.getNestedPlayer().getUniqueId().equals(player.getNestedPlayer().getUniqueId())) {
				sender.sendMessage(I18n.g("self-tp"));
				return false;
			}
			if (target.event != null) {
				sender.sendMessage(I18n.g("request-full", target.getName()));
				return false;
			}
			sender.sendMessage(I18n.g("request-success", target.getName()));
			player.sendRequest(target);
			return true;
		}
		else {
			sender.sendMessage(I18n.g("not-player"));
			return false;
		}
	}
}
