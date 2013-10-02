package com.pulsior.thundereffect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class ThunderEffect extends JavaPlugin implements Listener{

	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable(){
		getLogger().info("Goodbye world!");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("thundereffect")){ 
			Player player = Bukkit.getServer().getPlayer(sender.getName());
			ItemStack item = player.getItemInHand();
			if(item != null){
				ItemMeta meta =  item.getItemMeta();
				List<String> list = new ArrayList<String>();
				list.add("thunder");
				meta.setLore(list);
				item.setItemMeta(meta);
				getLogger().info(meta.getDisplayName());
			}
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("removethunder")){
			Player player = Bukkit.getServer().getPlayer(sender.getName());
			ItemStack item = player.getItemInHand();
			if(item != null){
				ItemMeta meta =  item.getItemMeta();;
				meta.setLore(null);
				item.setItemMeta(meta);
			}
			return true;
		}
		return false; 
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if(item != null){
			ItemMeta meta = item.getItemMeta();
			if(meta.hasLore()){
				List<String> list = meta.getLore();
				if(list.get(0).equals("thunder")){
					player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
				}
			}
		}
	}


}
