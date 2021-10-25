package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class CollarSlot extends InventorySlot {

	public CollarSlot() {
		super(new ItemBuilder(Material.INK_SACK, 1, (short)	7) {{
			setName("&cRanura para collar");
		}}, "collar_1", 12);
	}

}