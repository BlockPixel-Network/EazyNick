package net.dev.nickplugin.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.nickplugin.api.PlayerNickEvent;
import net.dev.nickplugin.api.PlayerUnnickEvent;
import net.dev.nickplugin.sql.MySQLNickManager;
import net.dev.nickplugin.sql.MySQLPlayerDataManager;
import net.dev.nickplugin.utils.FileUtils;
import net.dev.nickplugin.utils.LanguageFileUtils;
import net.dev.nickplugin.utils.StringUtils;
import net.dev.nickplugin.utils.Utils;

public class ReNickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(MySQLNickManager.isPlayerNicked(p.getUniqueId()) || Utils.joinNicking.contains(p.getUniqueId())) {
				if(Utils.joinNicking.contains(p.getUniqueId()))
					Utils.joinNicking.remove(p.getUniqueId());
				
				if(Utils.nickedPlayers.contains(p.getUniqueId())) {
					Bukkit.getPluginManager().callEvent(new PlayerUnnickEvent(p));
				} else {
					if(args.length == 0) {
						String name = Utils.nickNames.get((new Random().nextInt(Utils.nickNames.size())));
						boolean nickNameIsInUse = false;
						
						for (String nickName : Utils.playerNicknames.values())
							if(nickName.toUpperCase().equalsIgnoreCase(name.toUpperCase()))
								nickNameIsInUse = true;
						
						while (nickNameIsInUse ) {
							nickNameIsInUse = false;
							name = Utils.nickNames.get((new Random().nextInt(Utils.nickNames.size())));
							
							for (String nickName : Utils.playerNicknames.values())
								if(nickName.toUpperCase().equalsIgnoreCase(name.toUpperCase()))
									nickNameIsInUse = true;
						}

						if(MySQLPlayerDataManager.isRegistered(p.getUniqueId())) {
							Bukkit.getPluginManager().callEvent(new PlayerNickEvent(p, name, name,
									MySQLPlayerDataManager.getChatPrefix(p.getUniqueId()),
									MySQLPlayerDataManager.getChatSuffix(p.getUniqueId()),
									MySQLPlayerDataManager.getTabPrefix(p.getUniqueId()),
									MySQLPlayerDataManager.getTabSuffix(p.getUniqueId()),
									MySQLPlayerDataManager.getTagPrefix(p.getUniqueId()),
									MySQLPlayerDataManager.getTagSuffix(p.getUniqueId()),
									true, "NONE"));
						} else {
							boolean serverFull = Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers();
							String prefix = ChatColor.translateAlternateColorCodes('&', (serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.NameTag.prefix") : FileUtils.cfg.getString("Settings.NickFormat.NameTag.prefix")));
							String suffix = ChatColor.translateAlternateColorCodes('&', (serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.NameTag.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.NameTag.Suffix")));
							
							Bukkit.getPluginManager().callEvent(new PlayerNickEvent(p, name, name,
									ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.Chat.prefix") : FileUtils.cfg.getString("Settings.NickFormat.Chat.prefix")),
									ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.Chat.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.Chat.Suffix")),
									ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PlayerList.prefix") : FileUtils.cfg.getString("Settings.NickFormat.PlayerList.prefix")),
									ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PlayerList.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.PlayerList.Suffix")),
									prefix,
									suffix,
									true, (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()) ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PermissionsEx.GroupName") : FileUtils.cfg.getString("Settings.NickFormat.PermissionsEx.GroupName")));
						}
					} else {
						String name = args[0].replace("\"", "");
						boolean isCancelled = false;
						
						if(new StringUtils(name).removeColorCodes().getString().length() <= 16) {
							if(!(Utils.blackList.contains(args[0].toUpperCase()))) {
								boolean nickNameIsInUse = false;
								
								for (String nickName : Utils.playerNicknames.values())
									if(nickName.toUpperCase().equalsIgnoreCase(name.toUpperCase()))
										nickNameIsInUse = true;

								if(!(nickNameIsInUse)) {
									boolean playerWithNameIsKnown = false;
									
									for (Player all : Bukkit.getOnlinePlayers())
										if(all.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))
											playerWithNameIsKnown = true;
									
									for (OfflinePlayer all : Bukkit.getOfflinePlayers())
										if((all != null) && (all.getName() != null) && all.getName().toUpperCase().equalsIgnoreCase(name.toUpperCase()))
											playerWithNameIsKnown = true;
									
									if(!(FileUtils.cfg.getBoolean("AllowPlayersToNickAsKnownPlayers")) && playerWithNameIsKnown)
										isCancelled = true;
									
									if(!(isCancelled)) {
										if(!(name.equalsIgnoreCase(p.getName()))) {
											name = ChatColor.translateAlternateColorCodes('&', name);
											
											if(MySQLPlayerDataManager.isRegistered(p.getUniqueId())) {
												Bukkit.getPluginManager().callEvent(new PlayerNickEvent(p, name, name,
														MySQLPlayerDataManager.getChatPrefix(p.getUniqueId()),
														MySQLPlayerDataManager.getChatSuffix(p.getUniqueId()),
														MySQLPlayerDataManager.getTabPrefix(p.getUniqueId()),
														MySQLPlayerDataManager.getTabSuffix(p.getUniqueId()),
														MySQLPlayerDataManager.getTagPrefix(p.getUniqueId()),
														MySQLPlayerDataManager.getTagSuffix(p.getUniqueId()),
														true, MySQLPlayerDataManager.getOldPermissionsExRank(p.getUniqueId())));
											} else {
												boolean serverFull = Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers();
												String prefix = ChatColor.translateAlternateColorCodes('&', (serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.NameTag.prefix") : FileUtils.cfg.getString("Settings.NickFormat.NameTag.prefix")));
												String suffix = ChatColor.translateAlternateColorCodes('&', (serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.NameTag.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.NameTag.Suffix")));
												
												Bukkit.getPluginManager().callEvent(new PlayerNickEvent(p, name, name,
														ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.Chat.prefix") : FileUtils.cfg.getString("Settings.NickFormat.Chat.prefix")),
														ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.Chat.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.Chat.Suffix")),
														ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PlayerList.prefix") : FileUtils.cfg.getString("Settings.NickFormat.PlayerList.prefix")),
														ChatColor.translateAlternateColorCodes('&', serverFull ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PlayerList.Suffix") : FileUtils.cfg.getString("Settings.NickFormat.PlayerList.Suffix")),
														prefix,
														suffix,
														true, (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()) ? FileUtils.cfg.getString("Settings.NickFormat.ServerFullRank.PermissionsEx.GroupName") : FileUtils.cfg.getString("Settings.NickFormat.PermissionsEx.GroupName")));
											}
										} else {
											p.sendMessage(Utils.prefix + ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("Messages.CanNotNickAsSelf")));
										}
									} else {
										p.sendMessage(Utils.prefix + ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("Messages.PlayerWithThisNameIsKnown")));
									}
								} else {
									p.sendMessage(Utils.prefix + ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("Messages.NickNameAlreadyInUse")));
								}
							} else {
								p.sendMessage(Utils.prefix + ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("Messages.NameNotAllowed")));
							}
						} else {
							p.sendMessage(Utils.prefix + ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("Messages.NickTooLong")));
						}
					}
				}
			}
		} else {
			Utils.sendConsole(Utils.notPlayer);
		}
		
		return true;
	}
	
}
