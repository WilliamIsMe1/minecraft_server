package net.minecraft.item.recipe;

import net.minecraft.block.core.Block;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;

public class RecipesFood {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.bowlSoup), new Object[]{"Y", "X", "#", Character.valueOf('X'), Block.mushroomBrown, Character.valueOf('Y'), Block.mushroomRed, Character.valueOf('#'), net.minecraft.item.core.Item.bowlEmpty});
		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.bowlSoup), new Object[]{"Y", "X", "#", Character.valueOf('X'), Block.mushroomRed, Character.valueOf('Y'), Block.mushroomBrown, Character.valueOf('#'), net.minecraft.item.core.Item.bowlEmpty});
		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.cookie, 8), new Object[]{"#X#", Character.valueOf('X'), new ItemStack(net.minecraft.item.core.Item.dyePowder, 1, 3), Character.valueOf('#'), Item.wheat});
	}
}
