package net.minecraft.world.gen;

import net.minecraft.block.core.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.living.creature.animal.EntityChicken;
import net.minecraft.entity.living.creature.animal.EntityCow;
import net.minecraft.entity.living.creature.mob.EntityCreeper;
import net.minecraft.entity.living.creature.animal.EntityPig;
import net.minecraft.entity.living.creature.animal.EntitySheep;
import net.minecraft.entity.living.creature.mob.EntitySkeleton;
import net.minecraft.entity.living.creature.mob.EntitySlime;
import net.minecraft.entity.living.creature.mob.EntitySpider;
import net.minecraft.entity.living.creature.animal.EntitySquid;
import net.minecraft.entity.living.creature.mob.EntityZombie;
import net.minecraft.entity.SpawnListEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeGenBase {
	public static final BiomeGenBase rainforest = (new BiomeGenRainforest()).setColor(588342).setBiomeName("Rainforest").func_4080_a(2094168);
	public static final BiomeGenBase swampland = (new BiomeGenSwamp()).setColor(522674).setBiomeName("Swampland").func_4080_a(9154376);
	public static final BiomeGenBase seasonalForest = (new BiomeGenBase()).setColor(10215459).setBiomeName("Seasonal Forest");
	public static final BiomeGenBase forest = (new BiomeGenForest()).setColor(353825).setBiomeName("Forest").func_4080_a(5159473);
	public static final BiomeGenBase savanna = (new BiomeGenDesert()).setColor(14278691).setBiomeName("Savanna");
	public static final BiomeGenBase shrubland = (new BiomeGenBase()).setColor(10595616).setBiomeName("Shrubland");
	public static final BiomeGenBase taiga = (new BiomeGenTaiga()).setColor(3060051).setBiomeName("Taiga").setEnableSnow().func_4080_a(8107825);
	public static final BiomeGenBase desert = (new BiomeGenDesert()).setColor(16421912).setBiomeName("Desert").setDisableRain();
	public static final BiomeGenBase plains = (new BiomeGenDesert()).setColor(16767248).setBiomeName("Plains");
	public static final BiomeGenBase iceDesert = (new BiomeGenDesert()).setColor(16772499).setBiomeName("Ice Desert").setEnableSnow().setDisableRain().func_4080_a(12899129);
	public static final BiomeGenBase tundra = (new BiomeGenBase()).setColor(5762041).setBiomeName("Tundra").setEnableSnow().func_4080_a(12899129);
	public static final BiomeGenBase hell = (new BiomeGenHell()).setColor(16711680).setBiomeName("Hell").setDisableRain();
	public static final BiomeGenBase sky = (new BiomeGenSky()).setColor(8421631).setBiomeName("Sky").setDisableRain();
	public String biomeName;
	public int color;
	public byte topBlock = (byte) Block.grass.blockID;
	public byte fillerBlock = (byte) Block.dirt.blockID;
	public int field_6161_q = 5169201;
	protected List<SpawnListEntry> spawnableMonsterList = new ArrayList<>();
	protected List<SpawnListEntry> spawnableCreatureList = new ArrayList<>();
	protected List<SpawnListEntry> spawnableWaterCreatureList = new ArrayList<>();
	private boolean enableSnow;
	private boolean enableRain = true;
	private static final BiomeGenBase[] biomeLookupTable = new BiomeGenBase[4096];

	protected BiomeGenBase() {
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10));
	}

	private BiomeGenBase setDisableRain() {
		this.enableRain = false;
		return this;
	}

	public static void generateBiomeLookup() {
		for(int var0 = 0; var0 < 64; ++var0) {
			for(int var1 = 0; var1 < 64; ++var1) {
				biomeLookupTable[var0 + var1 * 64] = getBiome((float)var0 / 63.0F, (float)var1 / 63.0F);
			}
		}

		desert.topBlock = desert.fillerBlock = (byte) Block.sand.blockID;
		iceDesert.topBlock = iceDesert.fillerBlock = (byte) Block.sand.blockID;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return var1.nextInt(10) == 0 ? new WorldGenBigTree() : new WorldGenTrees();
	}

	protected BiomeGenBase setEnableSnow() {
		this.enableSnow = true;
		return this;
	}

	protected BiomeGenBase setBiomeName(String var1) {
		this.biomeName = var1;
		return this;
	}

	protected BiomeGenBase func_4080_a(int var1) {
		this.field_6161_q = var1;
		return this;
	}

	protected BiomeGenBase setColor(int var1) {
		this.color = var1;
		return this;
	}

	public static BiomeGenBase getBiomeFromLookup(double temperature, double humidity) {
		int var4 = (int)(temperature * 63.0D);
		int var5 = (int)(humidity * 63.0D);
		return biomeLookupTable[var4 + var5 * 64];
	}

	public static BiomeGenBase getBiome(float temperature, float humidity) {
		humidity *= temperature;

		if (temperature < 0.1F) {
			return tundra;
		}

		if (humidity < 0.2F) {
			if (temperature < 0.5F) {
				return tundra;
			}
			if (temperature < 0.95F) {
				return savanna;
			}
			return desert;
		}

		if (humidity > 0.5F && temperature < 0.7F) {
			return swampland;
		}

		if (temperature < 0.5F) {
			return taiga;
		}

		if (temperature < 0.97F) {
			if (humidity < 0.35F) {
				return shrubland;
			}
			return forest;
		}

		if (humidity < 0.45F) {
			return plains;
		}

		if (humidity < 0.9F) {
			return seasonalForest;
		}

		return rainforest;
	}


	public List getSpawnableList(net.minecraft.entity.EnumCreatureType var1) {
		return var1 == net.minecraft.entity.EnumCreatureType.monster ? this.spawnableMonsterList : (var1 == net.minecraft.entity.EnumCreatureType.creature ? this.spawnableCreatureList : (var1 == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
	}

	public boolean getEnableSnow() {
		return this.enableSnow;
	}

	public boolean canSpawnLightningBolt() {
		return this.enableSnow ? false : this.enableRain;
	}

	static {
		generateBiomeLookup();
	}
}
