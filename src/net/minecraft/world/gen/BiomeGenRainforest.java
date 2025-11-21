package net.minecraft.world.gen;

import java.util.Random;

public class BiomeGenRainforest extends BiomeGenBase {
	public net.minecraft.world.gen.WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenBigTree() : new WorldGenTrees());
	}
}
