package net.minecraft.item.gear;

import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.item.core.Item;
import net.minecraft.item.core.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;

public class ItemMapBase extends Item {
	protected ItemMapBase(int var1) {
		super(var1);
	}

	public boolean func_28019_b() {
		return true;
	}

	public Packet func_28022_b(ItemStack var1, World var2, EntityPlayer var3) {
		return null;
	}
}
