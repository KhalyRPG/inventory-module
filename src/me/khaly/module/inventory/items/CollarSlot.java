package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.core.enums.AccessoryType;
import me.khaly.module.inventory.items.object.InventorySlot;

public class CollarSlot extends InventorySlot {

	public CollarSlot() {
		super(new ItemBuilder(Material.INK_SACK, 1, (short)	8) {{
			setName("&cRanura para collar");
		}}, "collar_1", 12, AccessoryType.COLLAR);
	}

}
