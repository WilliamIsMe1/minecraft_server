package net.minecraft.block.redstone;

import net.minecraft.block.core.Block;
import net.minecraft.block.core.IBlockAccess;
import net.minecraft.block.material.Material;
import net.minecraft.core.Vec3D;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.world.World;

public class BlockTrapDoor extends net.minecraft.block.core.Block {
	protected BlockTrapDoor(int var1, net.minecraft.block.material.Material var2) {
		super(var1, var2);
		this.blockIndexInTexture = 84;
		if(var2 == net.minecraft.block.material.Material.iron) {
			++this.blockIndexInTexture;
		}

		float var3 = 0.5F;
		float var4 = 1.0F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean isACube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(net.minecraft.world.World var1, int var2, int var3, int var4) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
		this.func_28039_c(var1.getBlockMetadata(var2, var3, var4));
	}

	public void func_28039_c(int var1) {
		float var2 = 3.0F / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var2, 1.0F);
		if(func_28038_d(var1)) {
			if((var1 & 3) == 0) {
				this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
			}

			if((var1 & 3) == 1) {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
			}

			if((var1 & 3) == 2) {
				this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}

			if((var1 & 3) == 3) {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
			}
		}

	}

	public void onBlockClicked(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.living.EntityPlayer var5) {
		this.blockActivated(var1, var2, var3, var4, var5);
	}

	public boolean blockActivated(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.living.EntityPlayer var5) {
		if(this.blockMaterial == Material.iron) {
			return true;
		} else {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4);
			var1.func_28101_a(var5, 1003, var2, var3, var4, 0);
			return true;
		}
	}

	public void func_28040_a(net.minecraft.world.World var1, int var2, int var3, int var4, boolean var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		boolean var7 = (var6 & 4) > 0;
		if(var7 != var5) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4);
			var1.func_28101_a((EntityPlayer)null, 1003, var2, var3, var4, 0);
		}
	}

	public void onNeighborBlockChange(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
		if(!var1.singleplayerWorld) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			int var7 = var2;
			int var8 = var4;
			if((var6 & 3) == 0) {
				var8 = var4 + 1;
			}

			if((var6 & 3) == 1) {
				--var8;
			}

			if((var6 & 3) == 2) {
				var7 = var2 + 1;
			}

			if((var6 & 3) == 3) {
				--var7;
			}

			if(!var1.isBlockNormalCube(var7, var3, var8)) {
				var1.setBlockWithNotify(var2, var3, var4, 0);
				this.dropBlockAsItem(var1, var2, var3, var4, var6);
			}

			if(var5 > 0 && Block.blocksList[var5].canProvidePower()) {
				boolean var9 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
				this.func_28040_a(var1, var2, var3, var4, var9);
			}

		}
	}

	public MovingObjectPosition collisionRayTrace(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.core.Vec3D var5, Vec3D var6) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
	}

	public void onBlockPlaced(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
		byte var6 = 0;
		if(var5 == 2) {
			var6 = 0;
		}

		if(var5 == 3) {
			var6 = 1;
		}

		if(var5 == 4) {
			var6 = 2;
		}

		if(var5 == 5) {
			var6 = 3;
		}

		var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
	}

	public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
		if(var5 == 0) {
			return false;
		} else if(var5 == 1) {
			return false;
		} else {
			if(var5 == 2) {
				++var4;
			}

			if(var5 == 3) {
				--var4;
			}

			if(var5 == 4) {
				++var2;
			}

			if(var5 == 5) {
				--var2;
			}

			return var1.isBlockNormalCube(var2, var3, var4);
		}
	}

	public static boolean func_28038_d(int var0) {
		return (var0 & 4) != 0;
	}
}
