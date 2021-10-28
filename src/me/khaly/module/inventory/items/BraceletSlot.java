package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.core.enums.AccessoryType;
import me.khaly.module.inventory.items.object.InventorySlot;

public class BraceletSlot extends InventorySlot {

	public BraceletSlot() {
		super(new ItemBuilder(Material.INK_SACK, 1, (short)	8) {{
			setName("&cRanura para pulsera");
		}}, "bracelet_1", 11, AccessoryType.BRACELET);
		
		this.setChangableItem(true);
	}

}
