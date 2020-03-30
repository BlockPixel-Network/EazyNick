package net.dev.eazynick.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.bukkit.entity.Player;

import net.dev.eazynick.EazyNick;
import net.dev.eazynick.utils.Utils;

public class SpigotUpdater {

	public void checkForUpdates() {
		EazyNick eazyNick = EazyNick.getInstance();
		
		System.out.println("[Updater] Checking for updates...");
		
		ReadableByteChannel channel = null;
		double newVersion = 0.0;
		
		File file = eazyNick.getPluginFile();

		try {
			URL versionURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=51398");
			BufferedReader reader = new BufferedReader(new InputStreamReader(versionURL.openStream()));
			newVersion = Double.valueOf(reader.readLine()).doubleValue();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (newVersion > Double.valueOf(EazyNick.getInstance().getDescription().getVersion()).doubleValue()) {
			System.out.println("[Updater] Found a new version (" + newVersion + ")");

			if (eazyNick.getFileUtils().cfg.getBoolean("AutoUpdater")) {
				System.out.println("[Updater] Starting download...");

				try {
					HttpURLConnection downloadURL = (HttpURLConnection) new URL("http://myblaze.eu/Justix/files/EazyNick%20-%20NickSystem%20-%20by%20Justix.jar").openConnection();
					downloadURL.setRequestProperty("User-Agent", "JustixDevelopment/Updater");
					channel = Channels.newChannel(downloadURL.getInputStream());
				} catch (IOException e) {
					throw new RuntimeException("Download failed", e);
				}

				try {
					FileOutputStream output = new FileOutputStream(file);
					output.getChannel().transferFrom(channel, 0L, Long.MAX_VALUE);
					output.flush();
					output.close();
				} catch (IOException e) {
					throw new RuntimeException("File could not be saved", e);
				}

				System.out.println("[Updater] Successfully updated plugin to version " + newVersion + ". Please reload/restart your server now.");
			} else
				System.out.println("[Updater] Download the update here: https://www.spigotmc.org/resources/eazynick-nicksystem-api-src-bungeecord-multiworld-1-7-10-1-12-2.51398/");
		} else
			System.out.println("[Updater] No new version available");
	}
	
	public void checkForUpdates(Player p) {
		EazyNick eazyNick = EazyNick.getInstance();
		Utils utils = eazyNick.getUtils();
		
		p.sendMessage(utils.getPrefix() + "§3Updater §8» §rChecking for updates...");
		
		ReadableByteChannel channel = null;
		double newVersion = 0.0;
		
		File file = eazyNick.getPluginFile();

		try {
			URL versionURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=51398");
			BufferedReader reader = new BufferedReader(new InputStreamReader(versionURL.openStream()));
			newVersion = Double.valueOf(reader.readLine()).doubleValue();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (newVersion > Double.valueOf(EazyNick.getInstance().getDescription().getVersion()).doubleValue()) {
			p.sendMessage(utils.getPrefix() + "§3Updater §8» §rFound a new version (" + newVersion + ")");

			if (eazyNick.getFileUtils().cfg.getBoolean("AutoUpdater")) {
				p.sendMessage(utils.getPrefix() + "§3Updater §8» §rStarting download...");

				try {
					HttpURLConnection downloadURL = (HttpURLConnection) new URL("http://myblaze.eu/Justix/files/EazyNick%20-%20NickSystem%20-%20by%20Justix.jar").openConnection();
					downloadURL.setRequestProperty("User-Agent", "JustixDevelopment/Updater");
					channel = Channels.newChannel(downloadURL.getInputStream());
				} catch (IOException e) {
					throw new RuntimeException("Download failed", e);
				}

				try {
					FileOutputStream output = new FileOutputStream(file);
					output.getChannel().transferFrom(channel, 0L, Long.MAX_VALUE);
					output.flush();
					output.close();
				} catch (IOException e) {
					throw new RuntimeException("File could not be saved", e);
				}

				p.sendMessage(utils.getPrefix() + "§3Updater §8» §rSuccessfully updated plugin to version " + newVersion + ". Please restart/reload your server");
			} else
				p.sendMessage(utils.getPrefix() + "§3Updater §8» §rDownload the update here: https://www.spigotmc.org/resources/eazynick-nicksystem-api-src-bungeecord-multiworld-1-7-10-1-12-2.51398/");
		} else
			p.sendMessage(utils.getPrefix() + "§3Updater §8» §rNo new version available");
	}

}
