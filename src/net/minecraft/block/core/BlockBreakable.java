package net.minecraft.block.core;

import net.minecraft.block.material.Material;

public class BlockBreakable extends Block {
	private boolean field_6084_a;

	protected BlockBreakable(int id, int blockIndexInTexture, Material material, boolean var4) {
		super(id, blockIndexInTexture, material);
		this.field_6084_a = var4;
	}

	public boolean isOpaqueCube() {
		return false;
	}
}
