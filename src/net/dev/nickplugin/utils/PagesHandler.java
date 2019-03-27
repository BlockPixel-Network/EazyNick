package net.dev.nickplugin.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.dev.nickplugin.main.Main;

public class PagesHandler<T> {

	private int pageSize;
	private List<List<T>> pages;

	public PagesHandler(int pageSize) {

		this.pageSize = pageSize;
		this.pages = new ArrayList<>();

		this.pages.add(new ArrayList<T>());

	}

	public void addObject(T object) {
		int pageNum = pages.size() - 1;
		List<T> currentPage = this.pages.get(pageNum);

		if (currentPage.size() >= this.pageSize) {
			currentPage = new ArrayList<>();
			this.pages.add(currentPage);
		}

		currentPage.add(object);
	}

	public List<T> getPage(int pageNum) {
		if (this.pages.size() == 0)
			return null;

		return this.pages.get(pageNum);
	}

	public List<List<T>> getPages() {
		return this.pages;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void createPage(Player p, int page) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("NickNameGUI.InventoryTitle")).replace("%currentPage%", "" + (page + 1)));
		
		for (T nickName : getPage(page)) {
			if(Main.version.equalsIgnoreCase("1_13_R1"))
				inv.setItem(inv.firstEmpty(), Utils_1_13.createSkull(1, ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("NickNameGUI.NickNameSkull.DisplayName")).replace("%nickName%", (String) nickName), (String) nickName));
			else
				inv.setItem(inv.firstEmpty(), Utils.createSkull(1, ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("NickNameGUI.NickNameSkull.DisplayName")).replace("%nickName%", (String) nickName), (String) nickName));
		}

		inv.setItem(38, Utils.createItem(Material.ARROW, 1, 0, ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("NickNameGUI.BackItem.DisplayName"))));
		inv.setItem(42, Utils.createItem(Material.ARROW, 1, 0, ChatColor.translateAlternateColorCodes('&', LanguageFileUtils.cfg.getString("NickNameGUI.NextItem.DisplayName"))));
		
		p.openInventory(inv);
	}
}
