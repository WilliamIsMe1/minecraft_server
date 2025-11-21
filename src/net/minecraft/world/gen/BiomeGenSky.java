package net.minecraft.world.gen;

import net.minecraft.entity.living.creature.animal.EntityChicken;
import net.minecraft.src.SpawnListEntry;

public class BiomeGenSky extends BiomeGenBase {
	public BiomeGenSky() {
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10));
	}
}
