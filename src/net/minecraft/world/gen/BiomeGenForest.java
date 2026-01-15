package net.minecraft.world.gen;

import net.minecraft.entity.living.creature.mob.EntityWolf;
import net.minecraft.entity.SpawnListEntry;

import java.util.Random;

public class BiomeGenForest extends BiomeGenBase {
	public BiomeGenForest() {
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2));
	}

	public net.minecraft.world.gen.WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(5) == 0 ? new WorldGenForest() : (var1.nextInt(3) == 0 ? new WorldGenBigTree() : new WorldGenTrees()));
	}
}
