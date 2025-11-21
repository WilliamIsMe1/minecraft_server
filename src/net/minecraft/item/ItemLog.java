package net.minecraft.item;

import net.minecraft.item.core.ItemBlock;

public class ItemLog extends ItemBlock {
	public ItemLog(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int var1) {
		return var1;
	}
}
