package net.minecraft.item.recipe;

import java.util.Comparator;

public class RecipeSorter implements Comparator {
	final net.minecraft.item.recipe.CraftingManager craftingManager;

	RecipeSorter(CraftingManager var1) {
		this.craftingManager = var1;
	}

	public int compareRecipes(net.minecraft.item.recipe.IRecipe var1, net.minecraft.item.recipe.IRecipe var2) {
		return var1 instanceof ShapelessRecipes && var2 instanceof ShapedRecipes ? 1 : (var2 instanceof ShapelessRecipes && var1 instanceof ShapedRecipes ? -1 : (var2.getRecipeSize() < var1.getRecipeSize() ? -1 : (var2.getRecipeSize() > var1.getRecipeSize() ? 1 : 0)));
	}

	public int compare(Object var1, Object var2) {
		return this.compareRecipes((net.minecraft.item.recipe.IRecipe)var1, (IRecipe)var2);
	}
}
