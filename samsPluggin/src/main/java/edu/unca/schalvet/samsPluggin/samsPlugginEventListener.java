package edu.unca.schalvet.samsPluggin;

/*
    This file is part of samsPluggin

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.text.MessageFormat;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class samsPlugginEventListener implements Listener {

	private final samsPluggin plugin;

	public samsPlugginEventListener(samsPluggin plugin) {
		this.plugin = plugin;
	}


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled()) return;
		
		if (event.getBlock().getType() == Material.TNT){
			event.setCancelled(true);
		}
		
		//Bukkit.getServer().broadcastMessage("Player " + event.getPlayer().getName() + " placed " + event.getBlock().getType() + " at " + event.getBlock().getLocation());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void whatsInMyHand(PlayerItemHeldEvent event) {
		
		int length =event.getPlayer().getInventory().getItemInHand().toString().length();
		String item=event.getPlayer().getInventory().getItemInHand().toString().substring(10, (length-5));
		
		//randomGuy.getInventory().getItemInHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
		Bukkit.getServer().broadcastMessage("Player " + event.getPlayer().getName() + " had a " + item + " in his hand ");
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void damaged(EntityDamageEvent event) {
		
		
		int damage=event.getDamage();
		String cause=event.getCause().name();
		String type=event.getEntity().getType().name();
		
		
		Bukkit.getServer().broadcastMessage(type+ " suffered "+damage+" in damage because of an "+ cause);
	}
}
