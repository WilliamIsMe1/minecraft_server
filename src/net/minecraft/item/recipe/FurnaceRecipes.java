package net.minecraft.item.recipe;

import net.minecraft.block.core.Block;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;

import java.util.HashMap;
import java.util.Map;

public class FurnaceRecipes {
	private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
	private Map smeltingList = new HashMap();

	public static final FurnaceRecipes smelting() {
		return smeltingBase;
	}

	private FurnaceRecipes() {
		this.addSmelting(Block.oreIron.blockID, new ItemStack(net.minecraft.item.core.Item.ingotIron));
		this.addSmelting(Block.oreGold.blockID, new ItemStack(net.minecraft.item.core.Item.ingotGold));
		this.addSmelting(Block.oreDiamond.blockID, new ItemStack(net.minecraft.item.core.Item.diamond));
		this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
		this.addSmelting(net.minecraft.item.core.Item.porkRaw.shiftedIndex, new ItemStack(net.minecraft.item.core.Item.porkCooked));
		this.addSmelting(net.minecraft.item.core.Item.fishRaw.shiftedIndex, new ItemStack(net.minecraft.item.core.Item.fishCooked));
		this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
		this.addSmelting(net.minecraft.item.core.Item.clay.shiftedIndex, new ItemStack(net.minecraft.item.core.Item.brick));
		this.addSmelting(Block.cactus.blockID, new ItemStack(net.minecraft.item.core.Item.dyePowder, 1, 2));
		this.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
	}

	public void addSmelting(int var1, ItemStack var2) {
		this.smeltingList.put(Integer.valueOf(var1), var2);
	}

	public ItemStack getSmeltingResult(int var1) {
		return (ItemStack)this.smeltingList.get(Integer.valueOf(var1));
	}

	public Map getSmeltingList() {
		return this.smeltingList;
	}
}
