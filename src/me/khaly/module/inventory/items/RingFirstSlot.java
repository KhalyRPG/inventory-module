package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class RingFirstSlot extends InventorySlot {

	public RingFirstSlot() {
		super(new ItemBuilder(Material.INK_SACK, 1, (short)7) {{
			setName("&cRanura para anillo n� 1");
		}}, "ring_1", 9);

		this.setChangableItem(true);
	}

}