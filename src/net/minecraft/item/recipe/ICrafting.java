package net.minecraft.item.recipe;

import net.minecraft.item.core.ItemStack;
import net.minecraft.item.container.Container;

import java.util.List;

public interface ICrafting {
	void updateCraftingInventory(net.minecraft.item.container.Container var1, List var2);

	void updateCraftingInventorySlot(net.minecraft.item.container.Container var1, int var2, ItemStack var3);

	void updateCraftingInventoryInfo(Container var1, int var2, int var3);
}
