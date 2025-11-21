package net.minecraft.item.core;

import net.minecraft.achievement.stats.StatList;
import net.minecraft.block.core.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.EntityLiving;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.util.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class ItemStack {
	public int stackSize;
	public int animationsToGo;
	public int itemID;
	private int itemDamage;

	public ItemStack(Block var1) {
		this((Block)var1, 1);
	}

	public ItemStack(Block var1, int var2) {
		this(var1.blockID, var2, 0);
	}

	public ItemStack(Block var1, int var2, int var3) {
		this(var1.blockID, var2, var3);
	}

	public ItemStack(net.minecraft.item.core.Item var1) {
		this(var1.shiftedIndex, 1, 0);
	}

	public ItemStack(net.minecraft.item.core.Item var1, int var2) {
		this(var1.shiftedIndex, var2, 0);
	}

	public ItemStack(net.minecraft.item.core.Item var1, int var2, int var3) {
		this(var1.shiftedIndex, var2, var3);
	}

	public ItemStack(int var1, int var2, int var3) {
		this.stackSize = 0;
		this.itemID = var1;
		this.stackSize = var2;
		this.itemDamage = var3;
	}

	public ItemStack(NBTTagCompound var1) {
		this.stackSize = 0;
		this.readFromNBT(var1);
	}

	public ItemStack splitStack(int var1) {
		this.stackSize -= var1;
		return new ItemStack(this.itemID, var1, this.itemDamage);
	}

	public net.minecraft.item.core.Item getItem() {
		return net.minecraft.item.core.Item.itemsList[this.itemID];
	}

	public boolean useItem(net.minecraft.entity.living.EntityPlayer var1, World var2, int var3, int var4, int var5, int var6) {
		boolean var7 = this.getItem().onItemUse(this, var1, var2, var3, var4, var5, var6);
		if(var7) {
			var1.addStat(net.minecraft.achievement.stats.StatList.field_25107_A[this.itemID], 1);
		}

		return var7;
	}

	public float getStrVsBlock(Block var1) {
		return this.getItem().getStrVsBlock(this, var1);
	}

	public ItemStack useItemRightClick(World var1, net.minecraft.entity.living.EntityPlayer var2) {
		return this.getItem().onItemRightClick(this, var1, var2);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound var1) {
		var1.setShort("id", (short)this.itemID);
		var1.setByte("Count", (byte)this.stackSize);
		var1.setShort("Damage", (short)this.itemDamage);
		return var1;
	}

	public void readFromNBT(NBTTagCompound var1) {
		this.itemID = var1.getShort("id");
		this.stackSize = var1.getByte("Count");
		this.itemDamage = var1.getShort("Damage");
	}

	public int getMaxStackSize() {
		return this.getItem().getItemStackLimit();
	}

	public boolean func_21132_c() {
		return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
	}

	public boolean isItemStackDamageable() {
		return net.minecraft.item.core.Item.itemsList[this.itemID].getMaxDamage() > 0;
	}

	public boolean getHasSubtypes() {
		return net.minecraft.item.core.Item.itemsList[this.itemID].getHasSubtypes();
	}

	public boolean isItemDamaged() {
		return this.isItemStackDamageable() && this.itemDamage > 0;
	}

	public int getItemDamageForDisplay() {
		return this.itemDamage;
	}

	public int getItemDamage() {
		return this.itemDamage;
	}

	public void setItemDamage(int var1) {
		this.itemDamage = var1;
	}

	public int getMaxDamage() {
		return net.minecraft.item.core.Item.itemsList[this.itemID].getMaxDamage();
	}

	public void damageItem(int var1, net.minecraft.entity.Entity var2) {
		if(this.isItemStackDamageable()) {
			this.itemDamage += var1;
			if(this.itemDamage > this.getMaxDamage()) {
				if(var2 instanceof net.minecraft.entity.living.EntityPlayer) {
					((net.minecraft.entity.living.EntityPlayer)var2).addStat(net.minecraft.achievement.stats.StatList.field_25105_B[this.itemID], 1);
				}

				--this.stackSize;
				if(this.stackSize < 0) {
					this.stackSize = 0;
				}

				this.itemDamage = 0;
			}

		}
	}

	public void hitEntity(net.minecraft.entity.living.EntityLiving var1, net.minecraft.entity.living.EntityPlayer var2) {
		boolean var3 = net.minecraft.item.core.Item.itemsList[this.itemID].hitEntity(this, var1, var2);
		if(var3) {
			var2.addStat(net.minecraft.achievement.stats.StatList.field_25107_A[this.itemID], 1);
		}

	}

	public void func_25124_a(int var1, int var2, int var3, int var4, net.minecraft.entity.living.EntityPlayer var5) {
		boolean var6 = net.minecraft.item.core.Item.itemsList[this.itemID].func_25007_a(this, var1, var2, var3, var4, var5);
		if(var6) {
			var5.addStat(net.minecraft.achievement.stats.StatList.field_25107_A[this.itemID], 1);
		}

	}

	public int getDamageVsEntity(net.minecraft.entity.Entity var1) {
		return net.minecraft.item.core.Item.itemsList[this.itemID].getDamageVsEntity(var1);
	}

	public boolean canHarvestBlock(Block var1) {
		return net.minecraft.item.core.Item.itemsList[this.itemID].canHarvestBlock(var1);
	}

	public void func_577_a(net.minecraft.entity.living.EntityPlayer var1) {
	}

	public void useItemOnEntity(EntityLiving var1) {
		net.minecraft.item.core.Item.itemsList[this.itemID].saddleEntity(this, var1);
	}

	public ItemStack copy() {
		return new ItemStack(this.itemID, this.stackSize, this.itemDamage);
	}

	public static boolean areItemStacksEqual(ItemStack var0, ItemStack var1) {
		return var0 == null && var1 == null ? true : (var0 != null && var1 != null ? var0.isItemStackEqual(var1) : false);
	}

	private boolean isItemStackEqual(ItemStack var1) {
		return this.stackSize != var1.stackSize ? false : (this.itemID != var1.itemID ? false : this.itemDamage == var1.itemDamage);
	}

	public boolean isItemEqual(ItemStack var1) {
		return this.itemID == var1.itemID && this.itemDamage == var1.itemDamage;
	}

	public static ItemStack func_20117_a(ItemStack var0) {
		return var0 == null ? null : var0.copy();
	}

	public String toString() {
		return this.stackSize + "x" + net.minecraft.item.core.Item.itemsList[this.itemID].getItemName() + "@" + this.itemDamage;
	}

	public void func_28143_a(World var1, Entity var2, int var3, boolean var4) {
		if(this.animationsToGo > 0) {
			--this.animationsToGo;
		}

		net.minecraft.item.core.Item.itemsList[this.itemID].func_28018_a(this, var1, var2, var3, var4);
	}

	public void func_28142_b(World var1, EntityPlayer var2) {
		var2.addStat(StatList.field_25093_z[this.itemID], this.stackSize);
		Item.itemsList[this.itemID].func_28020_c(this, var1, var2);
	}

	public boolean func_28144_c(ItemStack var1) {
		return this.itemID == var1.itemID && this.stackSize == var1.stackSize && this.itemDamage == var1.itemDamage;
	}
}
