package net.minecraft.server;

import net.minecraft.entity.living.EntityPlayer;

public interface IPlayerFileData {
	void writePlayerData(net.minecraft.entity.living.EntityPlayer var1);

	void readPlayerData(EntityPlayer var1);
}
