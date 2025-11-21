package net.minecraft.item.recipe;

import net.minecraft.item.container.inventory.InventoryCrafting;
import net.minecraft.item.core.ItemStack;

public interface IRecipe {
	boolean func_21134_a(net.minecraft.item.container.inventory.InventoryCrafting var1);

	ItemStack func_21136_b(InventoryCrafting var1);

	int getRecipeSize();

	ItemStack getRecipeOutput();
}
