package net.minecraft.item.recipe;

import net.minecraft.block.core.Block;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;

public class RecipesTools {
	private String[][] recipePatterns = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone, net.minecraft.item.core.Item.ingotIron, net.minecraft.item.core.Item.diamond, net.minecraft.item.core.Item.ingotGold}, {net.minecraft.item.core.Item.pickaxeWood, net.minecraft.item.core.Item.pickaxeStone, net.minecraft.item.core.Item.pickaxeSteel, net.minecraft.item.core.Item.pickaxeDiamond, net.minecraft.item.core.Item.pickaxeGold}, {net.minecraft.item.core.Item.shovelWood, net.minecraft.item.core.Item.shovelStone, net.minecraft.item.core.Item.shovelSteel, net.minecraft.item.core.Item.shovelDiamond, net.minecraft.item.core.Item.shovelGold}, {net.minecraft.item.core.Item.axeWood, net.minecraft.item.core.Item.axeStone, net.minecraft.item.core.Item.axeSteel, net.minecraft.item.core.Item.axeDiamond, net.minecraft.item.core.Item.axeGold}, {net.minecraft.item.core.Item.hoeWood, net.minecraft.item.core.Item.hoeStone, net.minecraft.item.core.Item.hoeSteel, net.minecraft.item.core.Item.hoeDiamond, net.minecraft.item.core.Item.hoeGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				net.minecraft.item.core.Item var5 = (net.minecraft.item.core.Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), net.minecraft.item.core.Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(net.minecraft.item.core.Item.field_31022_bc), new Object[]{" #", "# ", Character.valueOf('#'), Item.ingotIron});
	}
}
