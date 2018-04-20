package com.leocth.anothertpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

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
            if (player.requests.isEmpty()) {
				sender.sendMessage(I18n.g("no-pending-request"));
				return false;
			}
            if (args.length == 1) {
                Iterator i = player.requests.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry<Date, RequestEvent> current = (Map.Entry<Date, RequestEvent>) i.next();
                    if (current.getValue().sender.getName().equals(args[0])) {
                        if (player.requests.get(current.getKey()).isProcessed) {
                            sender.sendMessage(I18n.g("already-processed"));
                        }
                        player.requests.get(current.getKey()).accept();
                        return true;
                    }
                }
                AnotherTPA.logger.warning("ERROR: AcceptCommand, line 45 have uncaught situation, report this to the Github Issue Tracker!");
                return false;
            } else {
                player.mostRecent().accept();
                return true;
            }
		}
		else {
			sender.sendMessage(I18n.g("not-player"));
			return false;
		}
	}
}
