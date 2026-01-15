package net.minecraft.block.redstone;

import net.minecraft.block.core.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLockedChest extends Block {
	public BlockLockedChest(int var1) {
		super(var1, Material.wood);
		this.blockIndexInTexture = 26;
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture - 1 : (var1 == 0 ? this.blockIndexInTexture - 1 : (var1 == 3 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture));
	}

	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
		return true;
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		var1.setBlockWithNotify(var2, var3, var4, 0);
	}
}
