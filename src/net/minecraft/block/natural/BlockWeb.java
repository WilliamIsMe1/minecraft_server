package net.minecraft.block.natural;

import net.minecraft.block.core.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.core.Item;
import net.minecraft.src.*;
import net.minecraft.world.World;

import java.util.Random;

public class BlockWeb extends Block {
	public BlockWeb(int var1, int var2) {
		super(var1, var2, Material.web);
	}

	public void onEntityCollidedWithBlock(net.minecraft.world.World var1, int var2, int var3, int var4, Entity var5) {
		var5.field_27012_bb = true;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
		return null;
	}

	public boolean isACube() {
		return false;
	}

	public int idDropped(int var1, Random var2) {
		return Item.silk.shiftedIndex;
	}
}
