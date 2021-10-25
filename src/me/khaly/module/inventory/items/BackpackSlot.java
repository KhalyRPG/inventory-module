package me.khaly.module.inventory.items;

import me.khaly.core.builder.SkullItemBuilder;
import me.khaly.module.inventory.items.object.InventorySlot;

public class BackpackSlot extends InventorySlot {

	public BackpackSlot() {
		super(new SkullItemBuilder() {{
			setName("&aMochila");
			setTexture("http://textures.minecraft.net/texture/b12c0a39cf46a72c18fba307b815462d19974feee7a078643569decbf5b7");
		}}, "backpack_1", 13, null);
		
		this.setAction((user, event) -> {
			user.sendMessage("&cNot yet.");
			return true;
		});
	}

}
