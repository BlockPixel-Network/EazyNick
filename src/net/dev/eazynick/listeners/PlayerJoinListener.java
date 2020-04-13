package net.dev.eazynick.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.eazynick.EazyNick;
import net.dev.eazynick.api.NickManager;
import net.dev.eazynick.api.PlayerNickEvent;
import net.dev.eazynick.sql.MySQLNickManager;
import net.dev.eazynick.sql.MySQLPlayerDataManager;
import net.dev.eazynick.utils.FileUtils;
import net.dev.eazynick.utils.LanguageFileUtils;
import net.dev.eazynick.utils.PacketInjector;
import net.dev.eazynick.utils.PacketInjector_1_7;
import net.dev.eazynick.utils.Utils;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		EazyNick eazyNick = EazyNick.getInstance();
		Utils utils = eazyNick.getUtils();
		FileUtils fileUtils = eazyNick.getFileUtils();
		LanguageFileUtils languageFileUtils = eazyNick.getLanguageFileUtils();
		MySQLNickManager mysqlNickManager = eazyNick.getMySQLNickManager();
		MySQLPlayerDataManager mysqlPlayerDataManager = eazyNick.getMySQLPlayerDataManager();
		
		Player p = e.getPlayer();
		NickManager api = new NickManager(p);

		if(eazyNick.getVersion().equals("1_7_R4"))
			new PacketInjector_1_7().init();
		else
			new PacketInjector().init();
		
		utils.getNameCache().put(p.getUniqueId(), p.getName());
		
		if (!(utils.getCanUseNick().containsKey(p.getUniqueId())))
			utils.getCanUseNick().put(p.getUniqueId(), true);

		if (!(eazyNick.getVersion().equalsIgnoreCase("1_7_R4")))
			p.setCustomName(p.getName());

		if(fileUtils.cfg.getBoolean("OverwriteJoinQuitMessages")) {
			String message = fileUtils.getConfigString("OverwrittenMessages.Join");
			
			if(mysqlNickManager.isPlayerNicked(p.getUniqueId()))
				message = message.replace("%name%", mysqlNickManager.getNickName(p.getUniqueId())).replace("%displayName%", mysqlPlayerDataManager.getChatPrefix(p.getUniqueId()) + mysqlNickManager.getNickName(p.getUniqueId()) + mysqlPlayerDataManager.getChatSuffix(p.getUniqueId()));
			else if(utils.getPlayerNicknames().containsKey(p.getUniqueId()))
				message = message.replace("%name%", utils.getPlayerNicknames().get(p.getUniqueId()).replace("%displayName%", utils.getChatPrefixes().get(p.getUniqueId()) + utils.getPlayerNicknames().get(p.getUniqueId()) + utils.getChatSuffixes().get(p.getUniqueId())));
			else
				message = message.replace("%name%", p.getName()).replace("%displayName%", p.getDisplayName());
			
			e.setJoinMessage(message);
		} else if ((e.getJoinMessage() != null) && (e.getJoinMessage() != "")) {
			if (fileUtils.cfg.getBoolean("BungeeCord") && !(fileUtils.cfg.getBoolean("LobbyMode")) && mysqlNickManager.isPlayerNicked(p.getUniqueId())) {
				if (e.getJoinMessage().contains("formerly known as"))
					e.setJoinMessage("§e" + p.getName() + " joined the game");

				e.setJoinMessage(e.getJoinMessage().replace(p.getName(), mysqlNickManager.getNickName(p.getUniqueId())));
			} else if (utils.getPlayerNicknames().containsKey(p.getUniqueId())) {
				if (e.getJoinMessage().contains("formerly known as"))
					e.setJoinMessage("§e" + p.getName() + " joined the game");

				e.setJoinMessage(e.getJoinMessage().replace(p.getName(), utils.getPlayerNicknames().get(p.getUniqueId())));
			}
		}
		
		Bukkit.getScheduler().runTaskLater(eazyNick, new Runnable() {

			@EventHandler
			public void run() {
				if(p.hasPermission("nick.bypass")) {
					if((eazyNick.getMySQL() != null) && eazyNick.getMySQL().isConnected()) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							NickManager apiAll = new NickManager(all);
							
							if (apiAll.isNicked()) {
								String name = apiAll.getNickName();

								apiAll.unnickPlayerWithoutRemovingMySQL(false);
								
								Bukkit.getScheduler().runTaskLater(eazyNick, new Runnable() {

									@Override
									public void run() {
										Bukkit.getPluginManager().callEvent(new PlayerNickEvent(all, name, mysqlNickManager.getSkinName(all.getUniqueId()),
												mysqlPlayerDataManager.getChatPrefix(all.getUniqueId()),
												mysqlPlayerDataManager.getChatSuffix(all.getUniqueId()),
												mysqlPlayerDataManager.getTabPrefix(all.getUniqueId()),
												mysqlPlayerDataManager.getTabSuffix(all.getUniqueId()),
												mysqlPlayerDataManager.getTagPrefix(all.getUniqueId()),
												mysqlPlayerDataManager.getTagSuffix(all.getUniqueId()),
												false,
												true,
												"NONE"));
									}
								}, 5);
							}
						}
					}
				}
				
				if (fileUtils.cfg.getBoolean("BungeeCord")) {
					if (!(fileUtils.cfg.getBoolean("LobbyMode"))) {
						if (mysqlNickManager.isPlayerNicked(p.getUniqueId())) {
							if (!(api.isNicked())) {
								Bukkit.getScheduler().runTaskLater(eazyNick, new Runnable() {

									@Override
									public void run() {
										p.chat("/renick");
									}
								}, 15);
							}
						}
					} else {
						if (mysqlNickManager.isPlayerNicked(p.getUniqueId())) {
							if (fileUtils.cfg.getBoolean("GetNewNickOnEveryServerSwitch")) {
								String name = api.getRandomName();
								
								mysqlNickManager.removePlayer(p.getUniqueId());
								mysqlNickManager.addPlayer(p.getUniqueId(), name, name);
							}
						}
					}

					if (fileUtils.cfg.getBoolean("NickItem.getOnJoin")) {
						if (p.hasPermission("nick.item")) {
							if (!(mysqlNickManager.isPlayerNicked(p.getUniqueId())))
								p.getInventory().setItem(fileUtils.cfg.getInt("NickItem.Slot") - 1, utils.createItem(Material.getMaterial(fileUtils.cfg.getString("NickItem.ItemType.Disabled")), fileUtils.cfg.getInt("NickItem.ItemAmount.Disabled"), fileUtils.cfg.getInt("NickItem.MetaData.Disabled"), languageFileUtils.getConfigString("NickItem.BungeeCord.DisplayName.Disabled"), languageFileUtils.getConfigString("NickItem.ItemLore.Disabled").replace("&n", "\n"), fileUtils.cfg.getBoolean("NickItem.Enchanted.Disabled")));
							else
								p.getInventory().setItem(fileUtils.cfg.getInt("NickItem.Slot") - 1, utils.createItem(Material.getMaterial(fileUtils.cfg.getString("NickItem.ItemType.Enabled")), fileUtils.cfg.getInt("NickItem.ItemAmount.Enabled"), fileUtils.cfg.getInt("NickItem.MetaData.Enabled"), languageFileUtils.getConfigString("NickItem.BungeeCord.DisplayName.Enabled"), languageFileUtils.getConfigString("NickItem.ItemLore.Enabled").replace("&n", "\n"), fileUtils.cfg.getBoolean("NickItem.Enchanted.Enabled")));
						}
					}
				} else {
					if (fileUtils.cfg.getBoolean("NickItem.getOnJoin")) {
						if (p.hasPermission("nick.item")) {
							if (fileUtils.cfg.getBoolean("NickOnWorldChange"))
								p.getInventory().setItem(fileUtils.cfg.getInt("NickItem.Slot") - 1, utils.createItem(Material.getMaterial(fileUtils.cfg.getString("NickItem.ItemType.Disabled")), fileUtils.cfg.getInt("NickItem.ItemAmount.Disabled"), fileUtils.cfg.getInt("NickItem.MetaData.Disabled"), languageFileUtils.getConfigString("NickItem.WorldChange.DisplayName.Disabled"), languageFileUtils.getConfigString("NickItem.ItemLore.Disabled").replace("&n", "\n"), fileUtils.cfg.getBoolean("NickItem.Enchanted.Disabled")));
							else
								p.getInventory().setItem(fileUtils.cfg.getInt("NickItem.Slot") - 1, utils.createItem(Material.getMaterial(fileUtils.cfg.getString("NickItem.ItemType.Disabled")), fileUtils.cfg.getInt("NickItem.ItemAmount.Disabled"), fileUtils.cfg.getInt("NickItem.MetaData.Disabled"), languageFileUtils.getConfigString("NickItem.DisplayName.Disabled"), languageFileUtils.getConfigString("NickItem.ItemLore.Disabled").replace("&n", "\n"), fileUtils.cfg.getBoolean("NickItem.Enchanted.Disabled")));
						}
					}
				}
				
				if (fileUtils.cfg.getBoolean("JoinNick")) {
					if (!(api.isNicked())) {
						Bukkit.getScheduler().runTaskLater(eazyNick, new Runnable() {

							@Override
							public void run() {
								p.chat("/nick");
							}
						}, 10);
					}
				} else if (!(fileUtils.cfg.getBoolean("DiconnectUnnick"))) {
					if((eazyNick.getMySQL() != null) && eazyNick.getMySQL().isConnected()) {
						if (api.isNicked()) {
							api.unnickPlayerWithoutRemovingMySQL(false);
							
							Bukkit.getScheduler().runTaskLater(eazyNick, new Runnable() {
	
								@Override
								public void run() {
									Bukkit.getPluginManager().callEvent(new PlayerNickEvent(p, api.getNickName(), mysqlNickManager.getSkinName(p.getUniqueId()),
											mysqlPlayerDataManager.getChatPrefix(p.getUniqueId()),
											mysqlPlayerDataManager.getChatSuffix(p.getUniqueId()),
											mysqlPlayerDataManager.getTabPrefix(p.getUniqueId()),
											mysqlPlayerDataManager.getTabSuffix(p.getUniqueId()),
											mysqlPlayerDataManager.getTagPrefix(p.getUniqueId()),
											mysqlPlayerDataManager.getTagSuffix(p.getUniqueId()),
											false,
											false,
											"NONE"));
								}
							}, 10);
							
							return;
						}
					}
				}
			}
		}, 5);
	}

}
