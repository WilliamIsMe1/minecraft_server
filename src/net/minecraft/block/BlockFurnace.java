package net.minecraft.block;

import net.minecraft.block.core.Block;
import net.minecraft.block.core.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.tileentity.TileEntityFurnace;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.living.EntityLiving;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.item.core.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFurnace extends BlockContainer {
	private Random field_28033_a = new Random();
	private final boolean isActive;
	private static boolean field_28034_c = false;

	protected BlockFurnace(int var1, boolean var2) {
		super(var1, Material.rock);
		this.isActive = var2;
		this.blockIndexInTexture = 45;
	}

	public int idDropped(int var1, Random var2) {
		return net.minecraft.block.core.Block.stoneOvenIdle.blockID;
	}

	public void onBlockAdded(net.minecraft.world.World var1, int var2, int var3, int var4) {
		super.onBlockAdded(var1, var2, var3, var4);
		this.setDefaultDirection(var1, var2, var3, var4);
	}

	private void setDefaultDirection(net.minecraft.world.World var1, int var2, int var3, int var4) {
		if(!var1.singleplayerWorld) {
			int var5 = var1.getBlockId(var2, var3, var4 - 1);
			int var6 = var1.getBlockId(var2, var3, var4 + 1);
			int var7 = var1.getBlockId(var2 - 1, var3, var4);
			int var8 = var1.getBlockId(var2 + 1, var3, var4);
			byte var9 = 3;
			if(net.minecraft.block.core.Block.opaqueCubeLookup[var5] && !net.minecraft.block.core.Block.opaqueCubeLookup[var6]) {
				var9 = 3;
			}

			if(net.minecraft.block.core.Block.opaqueCubeLookup[var6] && !net.minecraft.block.core.Block.opaqueCubeLookup[var5]) {
				var9 = 2;
			}

			if(net.minecraft.block.core.Block.opaqueCubeLookup[var7] && !net.minecraft.block.core.Block.opaqueCubeLookup[var8]) {
				var9 = 5;
			}

			if(net.minecraft.block.core.Block.opaqueCubeLookup[var8] && !net.minecraft.block.core.Block.opaqueCubeLookup[var7]) {
				var9 = 4;
			}

			var1.setBlockMetadataWithNotify(var2, var3, var4, var9);
		}
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture + 17 : (var1 == 0 ? this.blockIndexInTexture + 17 : (var1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
	}

	public boolean blockActivated(net.minecraft.world.World var1, int var2, int var3, int var4, EntityPlayer var5) {
		if(var1.singleplayerWorld) {
			return true;
		} else {
			net.minecraft.block.tileentity.TileEntityFurnace var6 = (net.minecraft.block.tileentity.TileEntityFurnace)var1.getBlockTileEntity(var2, var3, var4);
			var5.displayGUIFurnace(var6);
			return true;
		}
	}

	public static void updateFurnaceBlockState(boolean var0, net.minecraft.world.World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		net.minecraft.block.tileentity.TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
		field_28034_c = true;
		if(var0) {
			var1.setBlockWithNotify(var2, var3, var4, net.minecraft.block.core.Block.stoneOvenActive.blockID);
		} else {
			var1.setBlockWithNotify(var2, var3, var4, Block.stoneOvenIdle.blockID);
		}

		field_28034_c = false;
		var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
		var6.validate();
		var1.setBlockTileEntity(var2, var3, var4, var6);
	}

	protected net.minecraft.block.tileentity.TileEntity getBlockEntity() {
		return new net.minecraft.block.tileentity.TileEntityFurnace();
	}

	public void onBlockPlacedBy(net.minecraft.world.World var1, int var2, int var3, int var4, EntityLiving var5) {
		int var6 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if(var6 == 0) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 2);
		}

		if(var6 == 1) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 5);
		}

		if(var6 == 2) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 3);
		}

		if(var6 == 3) {
			var1.setBlockMetadataWithNotify(var2, var3, var4, 4);
		}

	}

	public void onBlockRemoval(World var1, int var2, int var3, int var4) {
		if(!field_28034_c) {
			net.minecraft.block.tileentity.TileEntityFurnace var5 = (TileEntityFurnace)var1.getBlockTileEntity(var2, var3, var4);

			for(int var6 = 0; var6 < var5.getSizeInventory(); ++var6) {
				ItemStack var7 = var5.getStackInSlot(var6);
				if(var7 != null) {
					float var8 = this.field_28033_a.nextFloat() * 0.8F + 0.1F;
					float var9 = this.field_28033_a.nextFloat() * 0.8F + 0.1F;
					float var10 = this.field_28033_a.nextFloat() * 0.8F + 0.1F;

					while(var7.stackSize > 0) {
						int var11 = this.field_28033_a.nextInt(21) + 10;
						if(var11 > var7.stackSize) {
							var11 = var7.stackSize;
						}

						var7.stackSize -= var11;
						net.minecraft.entity.EntityItem var12 = new EntityItem(var1, (double)((float)var2 + var8), (double)((float)var3 + var9), (double)((float)var4 + var10), new ItemStack(var7.itemID, var11, var7.getItemDamage()));
						float var13 = 0.05F;
						var12.motionX = (double)((float)this.field_28033_a.nextGaussian() * var13);
						var12.motionY = (double)((float)this.field_28033_a.nextGaussian() * var13 + 0.2F);
						var12.motionZ = (double)((float)this.field_28033_a.nextGaussian() * var13);
						var1.entityJoinedWorld(var12);
					}
				}
			}
		}

		super.onBlockRemoval(var1, var2, var3, var4);
	}
}
