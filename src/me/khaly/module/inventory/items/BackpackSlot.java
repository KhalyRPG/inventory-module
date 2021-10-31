package me.khaly.module.inventory.items;

import org.bukkit.Bukkit;

import me.khaly.core.builder.SkullItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class BackpackSlot extends InventorySlot {

	public BackpackSlot() {
		super(new SkullItemBuilder() {{
			setName("&aMochila");
			setTexture("http://textures.minecraft.net/texture/516051ce4b71ffb836429e59bc6d8bd016649193f1fade975e8784eef3738");
		}}, "backpack_1", 13, null);
		
		this.setAction((user, event) -> {
			Bukkit.dispatchCommand(user.getBukkitPlayer(), "backpack");
			return true;
		});
	}

}
