package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class RingSecondSlot extends InventorySlot {

	public RingSecondSlot() {
		super(new ItemBuilder(Material.INK_SACK, 2, (short)7) {{
			setName("&cRanura para anillo #2");
		}}, "ring_2", 10);

		this.setChangableItem(true);
	}

}
