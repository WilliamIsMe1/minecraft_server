package net.minecraft.item.recipe;

import net.minecraft.block.core.Block;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;

public class RecipesWeapons {
	private String[][] recipePatterns = new String[][]{{"X", "X", "#"}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone, net.minecraft.item.core.Item.ingotIron, net.minecraft.item.core.Item.diamond, net.minecraft.item.core.Item.ingotGold}, {net.minecraft.item.core.Item.swordWood, net.minecraft.item.core.Item.swordStone, net.minecraft.item.core.Item.swordSteel, net.minecraft.item.core.Item.swordDiamond, net.minecraft.item.core.Item.swordGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				net.minecraft.item.core.Item var5 = (net.minecraft.item.core.Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), net.minecraft.item.core.Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.bow, 1), new Object[]{" #X", "# X", " #X", Character.valueOf('X'), net.minecraft.item.core.Item.silk, Character.valueOf('#'), net.minecraft.item.core.Item.stick});
		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.arrow, 4), new Object[]{"X", "#", "Y", Character.valueOf('Y'), net.minecraft.item.core.Item.feather, Character.valueOf('X'), net.minecraft.item.core.Item.flint, Character.valueOf('#'), Item.stick});
	}
}
