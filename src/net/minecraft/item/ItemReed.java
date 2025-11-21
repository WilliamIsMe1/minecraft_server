package net.minecraft.item;

import net.minecraft.block.core.Block;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.item.core.Item;
import net.minecraft.item.core.ItemStack;
import net.minecraft.world.World;

// TODO: Maybe it uses a pure-item texture, but has a block, so Notch just reused it? Why not rename it afterwards? And why is ItemRedstone different? Maybe it places differently?
public class ItemReed extends Item { // TODO: Figure out how this is a sugarcane, cake, and repeater all at once
	private final int idOfBlock;

	public ItemReed(int itemId, Block block) {
		super(itemId);
		this.idOfBlock = block.blockID;
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
		if(var3.getBlockId(var4, var5, var6) == Block.snow.blockID) {
			var7 = 0;
		} else {
			if(var7 == 0) {
				--var5;
			}

			if(var7 == 1) {
				++var5;
			}

			if(var7 == 2) {
				--var6;
			}

			if(var7 == 3) {
				++var6;
			}

			if(var7 == 4) {
				--var4;
			}

			if(var7 == 5) {
				++var4;
			}
		}

		if(var1.stackSize == 0) {
			return false;
		} else {
			if(var3.canBlockBePlacedAt(this.idOfBlock, var4, var5, var6, false, var7)) {
				Block var8 = Block.blocksList[this.idOfBlock];
				if(var3.setBlockWithNotify(var4, var5, var6, this.idOfBlock)) {
					Block.blocksList[this.idOfBlock].onBlockPlaced(var3, var4, var5, var6, var7);
					Block.blocksList[this.idOfBlock].onBlockPlacedBy(var3, var4, var5, var6, var2);
					var3.playSoundEffect((float)var4 + 0.5F, (float)var5 + 0.5F, (float)var6 + 0.5F, var8.stepSound.func_737_c(), (var8.stepSound.getVolume() + 1.0F) / 2.0F, var8.stepSound.getPitch() * 0.8F);
					--var1.stackSize;
				}
			}

			return true;
		}
	}
}
