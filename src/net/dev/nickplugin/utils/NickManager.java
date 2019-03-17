package net.dev.nickplugin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.nametagedit.plugin.NametagEdit;

import net.dev.nickplugin.main.Main;
import net.dev.nickplugin.sql.MySQLNickManager;
import net.dev.nickplugin.utils.nickutils.NickManager_1_10_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_11_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_12_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_13_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_13_R2;
import net.dev.nickplugin.utils.nickutils.NickManager_1_7_R4;
import net.dev.nickplugin.utils.nickutils.NickManager_1_8_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_8_R2;
import net.dev.nickplugin.utils.nickutils.NickManager_1_8_R3;
import net.dev.nickplugin.utils.nickutils.NickManager_1_9_R1;
import net.dev.nickplugin.utils.nickutils.NickManager_1_9_R2;
import net.dev.nickplugin.utils.nickutils.UUIDFetcher;
import net.dev.nickplugin.utils.nickutils.UUIDFetcher_1_7;
import net.dev.nickplugin.utils.nickutils.UUIDFetcher_1_8_R1;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionEntity;
import fr.xephi.authme.AuthMe;
import fr.xephi.authme.api.NewAPI;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class NickManager {

	private Player p;
	private String chatPrefix = "";
	private String chatSuffix = "";
	private String tabPrefix = "";
	private String tabSuffix = "";
	
	public NickManager(Player p) {
		this.p = p;
	}
	
	public void setPlayerListName(String name) {
		if(Main.version == "1_7_R4") {
			NickManager_1_7_R4.setPlayerListName(((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_8_R1") {
			NickManager_1_8_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_8_R2") {
			NickManager_1_8_R2.setPlayerListName(((org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_8_R3") {
			NickManager_1_8_R3.setPlayerListName(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_9_R1") {
			NickManager_1_9_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_9_R2") {
			NickManager_1_9_R2.setPlayerListName(((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_10_R1") {
			NickManager_1_10_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_11_R1") {
			NickManager_1_11_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_12_R1") {
			NickManager_1_12_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_13_R1") {
			NickManager_1_13_R1.setPlayerListName(((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)p), name);
		} else if(Main.version == "1_13_R2") {
			NickManager_1_13_R2.setPlayerListName(((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)p), name);
		}
	}
	
	public void changeSkin(String skinName) {
		if(FileUtils.cfg.getBoolean("Settings.NameChangeOptions.RefreshPlayer") == true) {
			if(Main.version == "1_7_R4") {
				NickManager_1_7_R4.changeSkin(((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_8_R1") {
				NickManager_1_8_R1.changeSkin(((org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_8_R2") {
				NickManager_1_8_R2.changeSkin(((org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_8_R3") {
				NickManager_1_8_R3.changeSkin(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_9_R1") {
				NickManager_1_9_R1.changeSkin(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_9_R2") {
				NickManager_1_9_R2.changeSkin(((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_10_R1") {
				NickManager_1_10_R1.changeSkin(((org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_11_R1") {
				NickManager_1_11_R1.changeSkin(((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_12_R1") {
				NickManager_1_12_R1.changeSkin(((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_13_R1") {
				NickManager_1_13_R1.changeSkin(((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)p), skinName);
			} else if(Main.version == "1_13_R2") {
				NickManager_1_13_R2.changeSkin(((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)p), skinName);
			}
		}
	}
	
	public void refreshPlayer() {
		if(FileUtils.cfg.getBoolean("Settings.NameChangeOptions.RefreshPlayer") == true) {
			if(Main.version == "1_7_R4") {
				NickManager_1_7_R4.refreshPlayer(((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)p));
			} else if(Main.version == "1_8_R1") {
				NickManager_1_8_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_8_R2") {
				NickManager_1_8_R2.refreshPlayer(((org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer)p));
			} else if(Main.version == "1_8_R3") {
				NickManager_1_8_R3.refreshPlayer(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p));
			} else if(Main.version == "1_9_R1") {
				NickManager_1_9_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_9_R2") {
				NickManager_1_9_R2.refreshPlayer(((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)p));
			} else if(Main.version == "1_10_R1") {
				NickManager_1_10_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_11_R1") {
				NickManager_1_11_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_12_R1") {
				NickManager_1_12_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_13_R1") {
				NickManager_1_13_R1.refreshPlayer(((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)p));
			} else if(Main.version == "1_13_R2") {
				NickManager_1_13_R2.refreshPlayer(((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)p));
			}
		}
		
		if(Main.version == "1_7_R4") {
			String nameFormatChat = this.chatPrefix + p.getName() + this.chatSuffix;
			String nameFormatTab = this.tabPrefix + p.getName() + this.tabSuffix;
			
			if(nameFormatTab.length() <= 16) {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(nameFormatTab);
			} else {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(p.getName());
			}
		} else {
			p.setDisplayName(this.chatPrefix + p.getName() + this.chatSuffix);
			setPlayerListName(this.tabPrefix + p.getName() + this.tabSuffix);
		}
	}
	
	public void setName(String nickName) {
		if(FileUtils.cfg.getBoolean("Settings.NameChangeOptions.RefreshPlayer") == true) {
			if(Main.version == "1_7_R4") {
				NickManager_1_7_R4.setName(((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_8_R1") {
				NickManager_1_8_R1.setName(((org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_8_R2") {
				NickManager_1_8_R2.setName(((org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_8_R3") {
				NickManager_1_8_R3.setName(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_9_R1") {
				NickManager_1_9_R1.setName(((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_9_R2") {
				NickManager_1_9_R2.setName(((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_10_R1") {
				NickManager_1_10_R1.setName(((org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_11_R1") {
				NickManager_1_11_R1.setName(((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_12_R1") {
				NickManager_1_12_R1.setName(((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_13_R1") {
				NickManager_1_13_R1.setName(((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)p), nickName);
			} else if(Main.version == "1_13_R2") {
				NickManager_1_13_R2.setName(((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)p), nickName);
			}
		}
		
		if(Utils.nameTagEditStatus()) {
			NametagEdit.getApi().reloadNametag(p);
		}
		
		if(Utils.permissionsExStatus()) {
			PermissionUser user = PermissionsEx.getUser(p);
			
			user.setPrefix(Utils.oldPermissionsExPrefixes.get(p.getUniqueId()), p.getWorld().getName());
			user.setSuffix(Utils.oldPermissionsExSuffixes.get(p.getUniqueId()), p.getWorld().getName());
		}
		
		if(Utils.cloudNetStatus()) {
			CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
			
			CloudServer.getInstance().updateNameTags(p);
			CloudAPI.getInstance().updatePlayer(cloudPlayer);
		}
		
		if(Utils.authMeStatus()) {
			performAuthMeLogin();
		}
		
		if(Utils.datenschutzStatus()) {
			try {
				new me.tim.Main.Main().setDatenschutz(p, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void performAuthMeLogin() {
		NewAPI api = AuthMe.getApi();
		String name = p.getName();
		
		if(!(api.isRegistered(name)))
			api.forceRegister(p, Base64.getEncoder().encodeToString(UUID.randomUUID().toString().replace("-", "").getBytes()).substring(0, 10), true);
		else
			api.forceLogin(p);
	}

	public void nickPlayer(String nickName) {
		p.setCustomName(nickName);
		
		MySQLNickManager.addPlayer(p.getUniqueId(), nickName);
		
		setName(new StringUtils(nickName).removeColorCodes().getString());
		changeSkin(new StringUtils(nickName).removeColorCodes().getString());
		
		Utils.nickedPlayers.add(p.getUniqueId());
		Utils.playerNicknames.put(p.getUniqueId(), nickName);
		
		if(FileUtils.cfg.getBoolean("NickItem.getOnJoin") == true && (p.hasPermission("nick.item") || Utils.hasLuckPermsPermission(p.getUniqueId(), "nick.item"))) {
			for (int slot = 0; slot < p.getInventory().getSize(); slot++) {
				ItemStack item = p.getInventory().getItem(slot);
				
				if((item != null) && !(item.getType().equals(Material.AIR)) && (item.getItemMeta().getDisplayName() != null)) {
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',
							FileUtils.cfg.getString("NickItem.DisplayName.Disabled")))) {
						p.getInventory().setItem(slot,
								Utils.createItem(Material.getMaterial(FileUtils.cfg.getString("NickItem.ItemType.Enabled")),
										FileUtils.cfg.getInt("NickItem.ItemAmount.Enabled"),
										FileUtils.cfg.getInt("NickItem.MetaData.Enabled"),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.DisplayName.Enabled")),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.ItemLore.Enabled").replace("&n", "\n")),
										FileUtils.cfg.getBoolean("NickItem.Enchanted.Enabled")));
					}
				}
			}
		}
	}
	
	public void nickPlayer(String nickName, String skinName) {
		p.setCustomName(nickName);
		
		MySQLNickManager.addPlayer(p.getUniqueId(), nickName);
		
		setName(new StringUtils(nickName).removeColorCodes().getString());
		changeSkin(new StringUtils(skinName).removeColorCodes().getString());
		
		Utils.nickedPlayers.add(p.getUniqueId());
		Utils.playerNicknames.put(p.getUniqueId(), nickName);
		
		if(FileUtils.cfg.getBoolean("NickItem.getOnJoin") == true && (p.hasPermission("nick.item") || Utils.hasLuckPermsPermission(p.getUniqueId(), "nick.item"))) {
			for (int slot = 0; slot < p.getInventory().getSize(); slot++) {
				ItemStack item = p.getInventory().getItem(slot);
				
				if((item != null) && !(item.getType().equals(Material.AIR)) && (item.getItemMeta().getDisplayName() != null)) {
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',
							FileUtils.cfg.getString("NickItem.DisplayName.Disabled")))) {
						p.getInventory().setItem(slot,
								Utils.createItem(Material.getMaterial(FileUtils.cfg.getString("NickItem.ItemType.Enabled")),
										FileUtils.cfg.getInt("NickItem.ItemAmount.Enabled"),
										FileUtils.cfg.getInt("NickItem.MetaData.Enabled"),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.DisplayName.Enabled")),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.ItemLore.Enabled").replace("&n", "\n")),
										FileUtils.cfg.getBoolean("NickItem.Enchanted.Enabled")));
					}
				}
			}
		}
	}
	
	public void unnickPlayer() {
		String nickName = getRealName();
		
		p.setCustomName(nickName);
		
		MySQLNickManager.removePlayer(p.getUniqueId());
		
		Utils.nickedPlayers.remove(p.getUniqueId());
		Utils.playerNicknames.remove(p.getUniqueId());
		
		setName(nickName);
		changeSkin(nickName);

		p.setDisplayName(Utils.oldDisplayNames.get(p.getUniqueId()));
		setPlayerListName(Utils.oldPlayerListNames.get(p.getUniqueId()));
		
		Utils.oldDisplayNames.remove(p.getUniqueId());
		Utils.oldPlayerListNames.remove(p.getUniqueId());
		
		if(FileUtils.cfg.getBoolean("NickItem.getOnJoin") == true && (p.hasPermission("nick.item") || Utils.hasLuckPermsPermission(p.getUniqueId(), "nick.item"))) {
			for (int slot = 0; slot < p.getInventory().getSize(); slot++) {
				ItemStack item = p.getInventory().getItem(slot);
				
				if((item != null) && !(item.getType().equals(Material.AIR)) && (item.getItemMeta().getDisplayName() != null)) {
					if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',
							FileUtils.cfg.getString("NickItem.DisplayName.Enabled")))) {
						p.getInventory().setItem(slot,
								Utils.createItem(Material.getMaterial(FileUtils.cfg.getString("NickItem.ItemType.Disabled")),
										FileUtils.cfg.getInt("NickItem.ItemAmount.Disabled"),
										FileUtils.cfg.getInt("NickItem.MetaData.Disabled"),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.DisplayName.Disabled")),
										ChatColor.translateAlternateColorCodes('&',
												FileUtils.cfg.getString("NickItem.ItemLore.Disabled").replace("&n", "\n")),
										FileUtils.cfg.getBoolean("NickItem.Enchanted.Disabled")));
					}
				}
			}
		}
	}
	
	public String getRealName() {
		String realName = p.getName();
		
		if(Main.version.equalsIgnoreCase("1_7_R4")) {
			realName = UUIDFetcher_1_7.getName(p.getUniqueId());
		} else if(Main.version.equalsIgnoreCase("1_8_R1")) {
			realName = UUIDFetcher_1_8_R1.getName(p.getUniqueId());
		} else {
			realName = UUIDFetcher.getName(p.getUniqueId());
		}
		
		return realName;
	}
	
	public void changeCloudNET(String prefix, String suffix) {
		CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
		
		if(FileUtils.cfg.getBoolean("ServerIsUsingCloudNETPrefixes")) {
			PermissionEntity entity = cloudPlayer.getPermissionEntity();
			
			if(Utils.oldCloudNETPrefixes.containsKey(p.getUniqueId()))
				Utils.oldCloudNETPrefixes.remove(p.getUniqueId());
			
			if(Utils.oldCloudNETSuffixes.containsKey(p.getUniqueId()))
				Utils.oldCloudNETSuffixes.remove(p.getUniqueId());
			
			Utils.oldCloudNETPrefixes.put(p.getUniqueId(), entity.getPrefix());
			Utils.oldCloudNETSuffixes.put(p.getUniqueId(), entity.getSuffix());
			
			entity.setPrefix(prefix);
			entity.setSuffix(suffix);
		}
		
		CloudServer.getInstance().updateNameTags(p);
		CloudAPI.getInstance().updatePlayer(cloudPlayer);
	}
	
	public void resetCloudNET() {
		CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
		
		if(FileUtils.cfg.getBoolean("ServerIsUsingCloudNETPrefixes")) {
			PermissionEntity entity = cloudPlayer.getPermissionEntity();
			
			if(Utils.oldCloudNETPrefixes.containsKey(p.getUniqueId())) {
				entity.setPrefix(Utils.oldCloudNETPrefixes.get(p.getUniqueId()));
				Utils.oldCloudNETPrefixes.remove(p.getUniqueId());
			}
			
			if(Utils.oldCloudNETSuffixes.containsKey(p.getUniqueId())) {
				entity.setSuffix(Utils.oldCloudNETSuffixes.get(p.getUniqueId()));
				Utils.oldCloudNETSuffixes.remove(p.getUniqueId());
			}
		}
		
		CloudServer.getInstance().updateNameTags(p);
		CloudAPI.getInstance().updatePlayer(cloudPlayer);
	}
	
	public String getChatPrefix() {
		return chatPrefix;
	}

	public void setChatPrefix(String chatPrefix) {
		this.chatPrefix = ChatColor.translateAlternateColorCodes('&', chatPrefix);
		
		if(Main.version == "1_7_R4") {
			String nameFormatChat = this.chatPrefix + p.getName() + this.chatSuffix;
			String nameFormatTab = this.tabPrefix + p.getName() + this.tabSuffix;
			
			if(nameFormatTab.length() <= 16) {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(nameFormatTab);
			} else {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(p.getName());
			}
		} else {
			p.setDisplayName(this.chatPrefix + p.getName() + this.chatSuffix);
			setPlayerListName(this.tabPrefix + p.getName() + this.tabSuffix);
		}
	}

	public String getChatSuffix() {
		return chatSuffix;
	}

	public void setChatSuffix(String chatSuffix) {
		this.chatSuffix = ChatColor.translateAlternateColorCodes('&', chatSuffix);
		
		if(Main.version == "1_7_R4") {
			String nameFormatChat = this.chatPrefix + p.getName() + this.chatSuffix;
			String nameFormatTab = this.tabPrefix + p.getName() + this.tabSuffix;
			
			if(nameFormatTab.length() <= 16) {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(nameFormatTab);
			} else {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(p.getName());
			}
		} else {
			p.setDisplayName(this.chatPrefix + p.getName() + this.chatSuffix);
			setPlayerListName(this.tabPrefix + p.getName() + this.tabSuffix);
		}
	}

	public String getTabPrefix() {
		return tabPrefix;
	}

	public void setTabPrefix(String tabPrefix) {
		this.tabPrefix = ChatColor.translateAlternateColorCodes('&', tabPrefix);
		
		if(Main.version == "1_7_R4") {
			String nameFormatChat = this.chatPrefix + p.getName() + this.chatSuffix;
			String nameFormatTab = this.tabPrefix + p.getName() + this.tabSuffix;
			
			if(nameFormatTab.length() <= 16) {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(nameFormatTab);
			} else {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(p.getName());
			}
		} else {
			p.setDisplayName(this.chatPrefix + p.getName() + this.chatSuffix);
			setPlayerListName(this.tabPrefix + p.getName() + this.tabSuffix);
		}
	}

	public String getTabSuffix() {
		return tabSuffix;
	}

	public void setTabSuffix(String tabSuffix) {
		this.tabSuffix = ChatColor.translateAlternateColorCodes('&', tabSuffix);
		
		if(Main.version == "1_7_R4") {
			String nameFormatChat = this.chatPrefix + p.getName() + this.chatSuffix;
			String nameFormatTab = this.tabPrefix + p.getName() + this.tabSuffix;
			
			if(nameFormatTab.length() <= 16) {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(nameFormatTab);
			} else {
				p.setDisplayName(nameFormatChat);
				setPlayerListName(p.getName());
			}
		} else {
			p.setDisplayName(this.chatPrefix + p.getName() + this.chatSuffix);
			setPlayerListName(this.tabPrefix + p.getName() + this.tabSuffix);
		}
	}

	public boolean isNicked() {
		return Utils.nickedPlayers.contains(p.getUniqueId());
	}
	
	public String getRandomStringFromList(ArrayList<String> list) {
		return list.size() != 0 ? list.get((new Random()).nextInt(list.size())) : p.getName();
	}
	
	public static String getRandomName() {
		return Utils.nickNames.get((new Random()).nextInt(Utils.nickNames.size()));
	}
	
	public String getNickName() {
		return (Utils.playerNicknames.containsKey(p.getUniqueId()) ? Utils.playerNicknames.get(p.getUniqueId()) : p.getName());
	}
	
	public String getNickFormat() {
		return getChatPrefix() + getNickName() + getChatSuffix();
	}
	
	public String getOldDisplayName() {
		return Utils.oldDisplayNames.containsKey(p.getUniqueId()) ? Utils.oldDisplayNames.get(p.getUniqueId()) : p.getDisplayName();
	}
	
	public String getOldPlayerListName() {
		return Utils.oldPlayerListNames.containsKey(p.getUniqueId()) ? Utils.oldPlayerListNames.get(p.getUniqueId()) : p.getDisplayName();
	}
	
	public Object getGameProfile() {
		Object gp = null;
		
		if(Main.version == "1_7_R4") {
			gp = ((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_8_R1") {
			gp = ((org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_8_R2") {
			gp = ((org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_8_R3") {
			gp = ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_9_R1") {
			gp = ((org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_9_R2") {
			gp = ((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_10_R1") {
			gp = ((org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_11_R1") {
			gp = ((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_12_R1") {
			gp = ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_13_R1") {
			gp = ((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)p).getProfile();
		} else if(Main.version == "1_13_R2") {
			gp = ((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)p).getProfile();
		}
		
		return gp;
	}

}
