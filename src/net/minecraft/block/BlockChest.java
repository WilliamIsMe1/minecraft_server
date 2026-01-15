package net.minecraft.block;

import net.minecraft.block.core.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.tileentity.TileEntityChest;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.item.container.inventory.InventoryLargeChest;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.container.inventory.IInventory;
import net.minecraft.world.World;

import java.util.Random;

public class BlockChest extends BlockContainer {
	private Random random = new Random();

	public BlockChest(int var1) {
		super(var1, Material.wood);
		this.blockIndexInTexture = 26;
	}

	public int getBlockTextureFromSide(int var1) {
		return var1 == 1 ? this.blockIndexInTexture - 1 : (var1 == 0 ? this.blockIndexInTexture - 1 : (var1 == 3 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture));
	}

	public boolean canPlaceBlockAt(net.minecraft.world.World var1, int var2, int var3, int var4) {
		int var5 = 0;
		if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID) {
			++var5;
		}

		if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID) {
			++var5;
		}

		if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID) {
			++var5;
		}

		if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID) {
			++var5;
		}

		return var5 > 1 ? false : (this.isThereANeighborChest(var1, var2 - 1, var3, var4) ? false : (this.isThereANeighborChest(var1, var2 + 1, var3, var4) ? false : (this.isThereANeighborChest(var1, var2, var3, var4 - 1) ? false : !this.isThereANeighborChest(var1, var2, var3, var4 + 1))));
	}

	private boolean isThereANeighborChest(net.minecraft.world.World var1, int var2, int var3, int var4) {
		return var1.getBlockId(var2, var3, var4) != this.blockID ? false : (var1.getBlockId(var2 - 1, var3, var4) == this.blockID ? true : (var1.getBlockId(var2 + 1, var3, var4) == this.blockID ? true : (var1.getBlockId(var2, var3, var4 - 1) == this.blockID ? true : var1.getBlockId(var2, var3, var4 + 1) == this.blockID)));
	}

	public void onBlockRemoval(net.minecraft.world.World var1, int var2, int var3, int var4) {
		net.minecraft.block.tileentity.TileEntityChest var5 = (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);

		for(int var6 = 0; var6 < var5.getSizeInventory(); ++var6) {
			ItemStack var7 = var5.getStackInSlot(var6);
			if(var7 != null) {
				float var8 = this.random.nextFloat() * 0.8F + 0.1F;
				float var9 = this.random.nextFloat() * 0.8F + 0.1F;
				float var10 = this.random.nextFloat() * 0.8F + 0.1F;

				while(var7.stackSize > 0) {
					int var11 = this.random.nextInt(21) + 10;
					if(var11 > var7.stackSize) {
						var11 = var7.stackSize;
					}

					var7.stackSize -= var11;
					net.minecraft.entity.EntityItem var12 = new EntityItem(var1, (double)((float)var2 + var8), (double)((float)var3 + var9), (double)((float)var4 + var10), new ItemStack(var7.itemID, var11, var7.getItemDamage()));
					float var13 = 0.05F;
					var12.motionX = (double)((float)this.random.nextGaussian() * var13);
					var12.motionY = (double)((float)this.random.nextGaussian() * var13 + 0.2F);
					var12.motionZ = (double)((float)this.random.nextGaussian() * var13);
					var1.entityJoinedWorld(var12);
				}
			}
		}

		super.onBlockRemoval(var1, var2, var3, var4);
	}

	public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
		Object var6 = (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2, var3, var4);
		if(var1.isBlockNormalCube(var2, var3 + 1, var4)) {
			return true;
		} else if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID && var1.isBlockNormalCube(var2 - 1, var3 + 1, var4)) {
			return true;
		} else if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID && var1.isBlockNormalCube(var2 + 1, var3 + 1, var4)) {
			return true;
		} else if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID && var1.isBlockNormalCube(var2, var3 + 1, var4 - 1)) {
			return true;
		} else if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID && var1.isBlockNormalCube(var2, var3 + 1, var4 + 1)) {
			return true;
		} else {
			if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID) {
				var6 = new net.minecraft.item.container.inventory.InventoryLargeChest("Large chest", (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2 - 1, var3, var4), (net.minecraft.item.container.inventory.IInventory)var6);
			}

			if(var1.getBlockId(var2 + 1, var3, var4) == this.blockID) {
				var6 = new net.minecraft.item.container.inventory.InventoryLargeChest("Large chest", (net.minecraft.item.container.inventory.IInventory)var6, (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2 + 1, var3, var4));
			}

			if(var1.getBlockId(var2, var3, var4 - 1) == this.blockID) {
				var6 = new net.minecraft.item.container.inventory.InventoryLargeChest("Large chest", (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2, var3, var4 - 1), (net.minecraft.item.container.inventory.IInventory)var6);
			}

			if(var1.getBlockId(var2, var3, var4 + 1) == this.blockID) {
				var6 = new InventoryLargeChest("Large chest", (net.minecraft.item.container.inventory.IInventory)var6, (net.minecraft.block.tileentity.TileEntityChest)var1.getBlockTileEntity(var2, var3, var4 + 1));
			}

			if(!var1.multiplayerWorld) {
				return true;
			} else {
				var5.displayGUIChest((IInventory)var6);
				return true;
			}
		}
	}

	protected net.minecraft.block.tileentity.TileEntity getBlockEntity() {
		return new TileEntityChest();
	}
}
