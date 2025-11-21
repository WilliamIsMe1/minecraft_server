package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.living.creature.mob.EntityWaterMob;
import net.minecraft.entity.living.creature.animal.EntityAnimal;
import net.minecraft.entity.living.creature.mob.IMob;

public enum EnumCreatureType {
	monster(IMob.class, 70, net.minecraft.block.material.Material.air, false),
	creature(EntityAnimal.class, 15, net.minecraft.block.material.Material.air, true),
	waterCreature(EntityWaterMob.class, 5, net.minecraft.block.material.Material.water, true);

	private final Class creatureClass;
	private final int maxNumberOfCreature;
	private final net.minecraft.block.material.Material creatureMaterial;
	private final boolean field_21106_g;

	private EnumCreatureType(Class var3, int var4, net.minecraft.block.material.Material var5, boolean var6) {
		this.creatureClass = var3;
		this.maxNumberOfCreature = var4;
		this.creatureMaterial = var5;
		this.field_21106_g = var6;
	}

	public Class getCreatureClass() {
		return this.creatureClass;
	}

	public int getMaxNumberOfCreature() {
		return this.maxNumberOfCreature;
	}

	public Material getCreatureMaterial() {
		return this.creatureMaterial;
	}

	public boolean func_21103_d() {
		return this.field_21106_g;
	}
}
