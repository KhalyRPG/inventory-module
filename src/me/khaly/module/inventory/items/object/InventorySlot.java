package me.khaly.module.inventory.items.object;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.core.user.User;
import me.khaly.module.inventory.InventoryModule;

public abstract class InventorySlot {
	
	private boolean changableItem;
	private ItemBuilder defaultItem;
	private String id;
	private int inventorySlot;
	private BiPredicate<User, InventoryClickEvent> onAction;
	private BiConsumer<User, ItemStack> onRemove;
	
	public InventorySlot(ItemBuilder defaultItem, String id, int inventorySlot) {
		this.defaultItem = defaultItem;
		this.inventorySlot = inventorySlot;
		this.id = id;
		this.setAction((user, event) -> {
			return true;
		});
		this.onRemove = (user, item) -> {};
	}
	
	public boolean isChangableItem() {
		return this.changableItem;
	}
	
	protected boolean setChangableItem(boolean changableItem) {
		this.changableItem = changableItem;
		return changableItem;
	}
	
	public int getInventorySlot() {
		return inventorySlot;
	}
	
	public BiPredicate<User, InventoryClickEvent> onAction() {
		return onAction;
	}
	
	public BiConsumer<User, ItemStack> onRemove() {
		return onRemove;
	}
	
	public String getId() {
		return id;
	}
	
	public ItemStack getDefaultItemStack() {
		return defaultItem.build();
	}
	
	protected void setAction(BiPredicate<User, InventoryClickEvent> action) {
		this.onAction = action;
	}
	
	protected void setRemove(BiConsumer<User, ItemStack> action) {
		this.onRemove = action;
	}
	
	public boolean isDefaultItem(ItemStack item) {
		return item != null && item.isSimilar(defaultItem.build());
	}
	
	public void register() {
		InventoryModule.getInstance().getItems().put(getInventorySlot(), this);
	}
	
}
