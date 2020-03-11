package net.dev.eazynick.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.dev.eazynick.api.NickManager;

import me.clip.deluxechat.events.DeluxeChatEvent;
import me.clip.deluxechat.objects.DeluxeFormat;

public class DeluxeChatHookListener implements Listener {

	@EventHandler
	public void onDeluxeChat(DeluxeChatEvent e) {
		Player p = e.getPlayer();
		NickManager api = new NickManager(p);
		
		if(api.isNicked()) {
			DeluxeFormat format = e.getDeluxeFormat();
			format.setPrefix(api.getChatPrefix());
			format.setSuffix(api.getChatSuffix());
			format.setName(api.getNickName());
			format.setNameColor("");
			format.setChatColor("");
			e.setDeluxeFormat(format);
		}
	}

}
