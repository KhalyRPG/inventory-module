package me.khaly.module.inventory.items;

import org.bukkit.Material;

import me.khaly.core.builder.ItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class BackpackSlot extends InventorySlot {

	public BackpackSlot() {
		super(new ItemBuilder(Material.INK_SACK, 1, (short)	7) {{
			setName("&aMochila");
		}}, "backpack_1", 13);
		
		this.setAction((user, event) -> {
			user.sendMessage("&cNot yet.");
			return true;
		});
	}

}
