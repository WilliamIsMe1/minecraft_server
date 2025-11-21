package net.minecraft.item;

import net.minecraft.item.core.ItemBlock;

public class ItemLeaves extends ItemBlock {
	public ItemLeaves(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int var1) {
		return var1 | 8;
	}
}
