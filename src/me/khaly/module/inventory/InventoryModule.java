package me.khaly.module.inventory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.khaly.core.api.events.UserJoinEvent;
import me.khaly.core.module.Module;
import me.khaly.core.user.User;
import me.khaly.module.inventory.items.BackpackSlot;
import me.khaly.module.inventory.items.BraceletSlot;
import me.khaly.module.inventory.items.CollarSlot;
import me.khaly.module.inventory.items.RingFirstSlot;
import me.khaly.module.inventory.items.RingSecondSlot;
import me.khaly.module.inventory.items.object.InventorySlot;

public class InventoryModule extends Module implements Listener {
	
	private Map<Integer, InventorySlot> items;
	private static InventoryModule instance;
	
	public InventoryModule() {
		super("Inventory", "inventory", 0.1F);
		this.items = new ConcurrentHashMap<>();
	}

	@Override
	public void load() {
		instance = this;
		
		Arrays.asList(
				new RingFirstSlot(),
				new RingSecondSlot(),
				new BraceletSlot(),
				new CollarSlot(),
				new BackpackSlot()
			).forEach(slot -> {
				slot.register();
			});
		
	}
	
	public Map<Integer, InventorySlot> getItems() {
		return items;
	}
	
	public static InventoryModule getInstance() {
		return instance;
	}
		
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getClickedInventory() == null ||
				event.getInventory() == null || 
				event.getAction() == InventoryAction.NOTHING ||
				event.isCancelled() ||
				event.getClickedInventory().getType() != InventoryType.PLAYER) {
			return;
		}
		
		HumanEntity player = event.getWhoClicked();
		int slot = event.getSlot();
		User user = getProvider().getUser((Player) player);
		InventorySlot inventorySlot = items.get(slot);

		if(inventorySlot == null) {
			return;
		}
		
		if (inventorySlot.isChangableItem()) {
			PlayerInventory inventory = user.getBukkitPlayer().getInventory();
			ItemStack cursor = event.getCursor();
			ItemStack item = inventory.getItem(inventorySlot.getInventorySlot());

			if (!inventorySlot.isDefaultItem(item)) {
				event.getWhoClicked().setItemOnCursor(item);
				inventory.setItem(slot, inventorySlot.getDefaultItemStack());
			} else {
				if((cursor == null || cursor.getType() == Material.AIR) && inventorySlot.isDefaultItem(item)) {
					event.setCancelled(true);
				} else {
					if(inventorySlot.isDefaultItem(item) && cursor != null) {
						inventory.setItem(slot, cursor);
						player.setItemOnCursor(null);
					} else if(!inventorySlot.isDefaultItem(item) && cursor == null) {
						player.setItemOnCursor(item);
						inventory.setItem(slot, inventorySlot.getDefaultItemStack());
						inventorySlot.onRemove().accept(user, item);
					} else if(!inventorySlot.isDefaultItem(item) && cursor != null) {
						player.setItemOnCursor(item);
						inventory.setItem(slot, cursor);
						inventorySlot.onRemove().accept(user, item);
					} else {
						player.sendMessage("§cW-what? ._.");
					}
				}
			}
		}
		
		event.setCancelled(inventorySlot.onAction().test(user, event));
	}
	
	@EventHandler
	public void onJoin(UserJoinEvent event) {
		PlayerInventory inventory = event.getPlayer().getInventory();
		
		for(Map.Entry<Integer, InventorySlot> entry : items.entrySet()) {
			int slot = entry.getKey();
			InventorySlot inventorySlot = entry.getValue();
			
			if(inventory.getItem(slot) == null || inventory.getItem(slot).getType() == Material.AIR) {
				inventory.setItem(slot, inventorySlot.getDefaultItemStack());
			}
		}
	}
}
