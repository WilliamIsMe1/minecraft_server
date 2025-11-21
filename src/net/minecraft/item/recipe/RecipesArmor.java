package net.minecraft.item.recipe;

import net.minecraft.block.core.Block;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;

public class RecipesArmor {
	private String[][] recipePatterns = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private Object[][] recipeItems = new Object[][]{{net.minecraft.item.core.Item.leather, Block.fire, net.minecraft.item.core.Item.ingotIron, net.minecraft.item.core.Item.diamond, net.minecraft.item.core.Item.ingotGold}, {net.minecraft.item.core.Item.helmetLeather, net.minecraft.item.core.Item.helmetChain, net.minecraft.item.core.Item.helmetSteel, net.minecraft.item.core.Item.helmetDiamond, net.minecraft.item.core.Item.helmetGold}, {net.minecraft.item.core.Item.plateLeather, net.minecraft.item.core.Item.plateChain, net.minecraft.item.core.Item.plateSteel, net.minecraft.item.core.Item.plateDiamond, net.minecraft.item.core.Item.plateGold}, {net.minecraft.item.core.Item.legsLeather, net.minecraft.item.core.Item.legsChain, net.minecraft.item.core.Item.legsSteel, net.minecraft.item.core.Item.legsDiamond, net.minecraft.item.core.Item.legsGold}, {net.minecraft.item.core.Item.bootsLeather, net.minecraft.item.core.Item.bootsChain, net.minecraft.item.core.Item.bootsSteel, net.minecraft.item.core.Item.bootsDiamond, net.minecraft.item.core.Item.bootsGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				net.minecraft.item.core.Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('X'), var3});
			}
		}

	}
}
