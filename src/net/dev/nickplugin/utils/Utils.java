package net.dev.nickplugin.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Scoreboard;

import net.dev.nickplugin.main.Main;
import net.dev.nickplugin.utils.scoreboard.ScoreboardTeamManager;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Node;

public class Utils {

	public static String PREFIX;
	public static String NO_PERM;
	public static String NOT_PLAYER;

	public static Field field;
	public static PagesHandler<String> nickNamesHandler;

	public static boolean cloudNetStatus() {
		if (Bukkit.getPluginManager().getPlugin("CloudNetAPI") != null) {
			return true;
		}

		return false;
	}

	public static boolean coloredTagsStatus() {
		if (Bukkit.getPluginManager().getPlugin("ColoredTags") != null) {
			return true;
		}

		return false;
	}

	public static boolean nameTagEditStatus() {
		if (Bukkit.getPluginManager().getPlugin("NametagEdit") != null) {
			return true;
		}

		return false;
	}

	public static boolean permissionsExStatus() {
		if (Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
			return true;
		}

		return false;
	}
	
	public static boolean luckPermsStatus() {
		if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
			return true;
		}

		return false;
	}

	public static ArrayList<UUID> nickedPlayers = new ArrayList<>();
	public static ArrayList<UUID> nickOnWorldChangePlayers = new ArrayList<>();
	public static HashMap<UUID, String> playerNicknames = new HashMap<>();
	public static List<String> nickNames = new ArrayList<>();
	public static List<String> blackList = new ArrayList<>();
	public static List<String> worldBlackList = new ArrayList<>();
	public static HashMap<UUID, String> oldDisplayNames = new HashMap<>();
	public static HashMap<UUID, String> oldPlayerListNames = new HashMap<>();
	public static HashMap<UUID, Boolean> canUseNick = new HashMap<>();
	public static HashMap<UUID, Integer> nickNameListPage = new HashMap<>();
	public static HashMap<UUID, String> oldPermissionsExPrefixes = new HashMap<>();
	public static HashMap<UUID, String> oldPermissionsExSuffixes = new HashMap<>();
	public static HashMap<UUID, String> oldCloudNETPrefixes = new HashMap<>();
	public static HashMap<UUID, String> oldCloudNETSuffixes = new HashMap<>();
	public static HashMap<UUID, String[]> oldPermissionsExGroups = new HashMap<>();
	public static HashMap<UUID, String> luckPermsPrefixes = new HashMap<>();
	public static HashMap<UUID, String> luckPermsSufixes = new HashMap<>();
	
	public static HashMap<UUID, Double> health = new HashMap<>();
	public static HashMap<UUID, Integer> food = new HashMap<>();
	public static HashMap<UUID, Integer> heldItemSlots = new HashMap<>();
	public static HashMap<UUID, ItemStack[]> armor = new HashMap<>();
	public static HashMap<UUID, HashMap<Integer, ItemStack>> items = new HashMap<>();
	public static HashMap<UUID, Location> locations = new HashMap<>();
	public static HashMap<UUID, Scoreboard> scoreBoards = new HashMap<>();
	public static HashMap<UUID, ScoreboardTeamManager> scoreboardTeamManagers = new HashMap<>();
	public static ArrayList<String> scoreboardTeamContents = new ArrayList<>();

	public static void sendConsole(String msg) {
		Bukkit.getConsoleSender().sendMessage(PREFIX + msg);
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createItem(Material mat, int amount, int metaData, String displayName, String lore,
			boolean enchantedItem) {
		ItemStack is = new ItemStack(mat, amount, (byte) metaData);
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(displayName.trim());
		m.setLore(Arrays.asList(lore));

		if (enchantedItem) {
			m.addEnchant(Enchantment.DURABILITY, 1, true);

			if(!(Main.version.equalsIgnoreCase("1_7_R4"))) {
				m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES,
						ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE });
			}
		}

		is.setItemMeta(m);
		
		return is;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createItem(Material mat, int amount, int metaData, String displayName) {
		ItemStack is = new ItemStack(mat, amount, (byte) metaData);
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(displayName.trim());

		is.setItemMeta(m);

		return is;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createSkull(int amount, String displayName, String owner) {
		ItemStack is = new ItemStack(Material.getMaterial("SKULL_ITEM"), amount, (byte) 3);
		SkullMeta m = (SkullMeta) is.getItemMeta();
		m.setDisplayName(displayName);
		m.setOwner(owner);
		is.setItemMeta(m);
		
		return is;
	}

	public static boolean hasLuckPermsPermission(UUID uuid, String permission) {
		if(luckPermsStatus()) {
			for (Node perm : LuckPerms.getApi().getUser(uuid).getPermissions()) {
				if(perm.toString().toLowerCase().equals(permission.toLowerCase())) {
					return true;
				}
			}
		}
		
		return false;
	}

}