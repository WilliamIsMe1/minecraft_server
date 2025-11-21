package net.minecraft.block.core;

import net.minecraft.achievement.stats.StatCollector;
import net.minecraft.achievement.stats.StatList;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.block.natural.BlockCactus;
import net.minecraft.block.natural.BlockClay;
import net.minecraft.block.natural.BlockCrops;
import net.minecraft.block.natural.BlockDeadBush;
import net.minecraft.block.natural.BlockDirt;
import net.minecraft.block.natural.BlockFarmland;
import net.minecraft.block.natural.BlockFlower;
import net.minecraft.block.natural.BlockGlowStone;
import net.minecraft.block.natural.BlockGrass;
import net.minecraft.block.natural.BlockGravel;
import net.minecraft.block.natural.BlockIce;
import net.minecraft.block.natural.BlockLeaves;
import net.minecraft.block.natural.BlockLog;
import net.minecraft.block.natural.BlockMushroom;
import net.minecraft.block.natural.BlockNetherrack;
import net.minecraft.block.natural.BlockObsidian;
import net.minecraft.block.natural.BlockOre;
import net.minecraft.block.natural.BlockPumpkin;
import net.minecraft.block.natural.BlockReed;
import net.minecraft.block.natural.BlockSand;
import net.minecraft.block.natural.BlockSandStone;
import net.minecraft.block.natural.BlockSapling;
import net.minecraft.block.natural.BlockSnow;
import net.minecraft.block.natural.BlockSnowBlock;
import net.minecraft.block.natural.BlockSoulSand;
import net.minecraft.block.natural.BlockStone;
import net.minecraft.block.natural.BlockTallGrass;
import net.minecraft.block.natural.BlockWeb;
import net.minecraft.block.redstone.BlockButton;
import net.minecraft.block.redstone.BlockDetectorRail;
import net.minecraft.block.redstone.BlockDispenser;
import net.minecraft.block.redstone.BlockDoor;
import net.minecraft.block.redstone.BlockJukeBox;
import net.minecraft.block.redstone.BlockLever;
import net.minecraft.block.redstone.BlockLockedChest;
import net.minecraft.block.redstone.BlockMobSpawner;
import net.minecraft.block.redstone.BlockNote;
import net.minecraft.block.redstone.BlockPistonBase;
import net.minecraft.block.redstone.BlockPistonExtension;
import net.minecraft.block.redstone.BlockPistonMoving;
import net.minecraft.block.redstone.BlockPressurePlate;
import net.minecraft.block.redstone.BlockRail;
import net.minecraft.block.redstone.BlockRedstoneOre;
import net.minecraft.block.redstone.BlockRedstoneRepeater;
import net.minecraft.block.redstone.BlockRedstoneTorch;
import net.minecraft.block.redstone.BlockRedstoneWire;
import net.minecraft.block.redstone.BlockTNT;
import net.minecraft.block.redstone.BlockTrapDoor;
import net.minecraft.block.tileentity.TileEntitySign;
import net.minecraft.core.Vec3D;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EnumMobType;
import net.minecraft.entity.living.EntityLiving;
import net.minecraft.entity.living.EntityPlayer;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.core.Item;
import net.minecraft.item.core.ItemBlock;
import net.minecraft.item.redstone.ItemPiston;
import net.minecraft.src.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class Block {
	public static final net.minecraft.block.StepSound soundPowderFootstep = new net.minecraft.block.StepSound("stone", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundWoodFootstep = new net.minecraft.block.StepSound("wood", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundGravelFootstep = new net.minecraft.block.StepSound("gravel", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundGrassFootstep = new net.minecraft.block.StepSound("grass", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundStoneFootstep = new net.minecraft.block.StepSound("stone", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundMetalFootstep = new net.minecraft.block.StepSound("stone", 1.0F, 1.5F);
	public static final net.minecraft.block.StepSound soundGlassFootstep = new net.minecraft.block.StepSoundStone("stone", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundClothFootstep = new net.minecraft.block.StepSound("cloth", 1.0F, 1.0F);
	public static final net.minecraft.block.StepSound soundSandFootstep = new net.minecraft.block.StepSoundSand("sand", 1.0F, 1.0F);
	public static final Block[] blocksList = new Block[256];
	public static final boolean[] tickOnLoad = new boolean[256];
	public static final boolean[] opaqueCubeLookup = new boolean[256];
	public static final boolean[] isBlockContainer = new boolean[256];
	public static final int[] lightOpacity = new int[256];
	public static final boolean[] canBlockGrass = new boolean[256];
	public static final int[] lightValue = new int[256];
	public static final boolean[] requiresSelfNotify = new boolean[256];
	public static final Block stone = (new BlockStone(1, 1)).setHardness(1.5F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("stone");
	public static final BlockGrass grass = (BlockGrass)(new BlockGrass(2)).setHardness(0.6F).setStepSound(soundGrassFootstep).setBlockName("grass");
	public static final Block dirt = (new BlockDirt(3, 2)).setHardness(0.5F).setStepSound(soundGravelFootstep).setBlockName("dirt");
	public static final Block cobblestone = (new Block(4, 16, net.minecraft.block.material.Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("stonebrick");
	public static final Block planks = (new Block(5, 4, net.minecraft.block.material.Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setBlockName("wood").setRequiresSelfNotify();
	public static final Block sapling = (new BlockSapling(6, 15)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("sapling").setRequiresSelfNotify();
	public static final Block bedrock = (new Block(7, 17, net.minecraft.block.material.Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundStoneFootstep).setBlockName("bedrock").disableStats();
	public static final Block waterMoving = (new BlockFlowing(8, net.minecraft.block.material.Material.water)).setHardness(100.0F).setLightOpacity(3).setBlockName("water").disableStats().setRequiresSelfNotify();
	public static final Block waterStill = (new BlockStationary(9, net.minecraft.block.material.Material.water)).setHardness(100.0F).setLightOpacity(3).setBlockName("water").disableStats().setRequiresSelfNotify();
	public static final Block lavaMoving = (new BlockFlowing(10, net.minecraft.block.material.Material.lava)).setHardness(0.0F).setLightValue(1.0F).setLightOpacity(255).setBlockName("lava").disableStats().setRequiresSelfNotify();
	public static final Block lavaStill = (new BlockStationary(11, net.minecraft.block.material.Material.lava)).setHardness(100.0F).setLightValue(1.0F).setLightOpacity(255).setBlockName("lava").disableStats().setRequiresSelfNotify();
	public static final Block sand = (new BlockSand(12, 18)).setHardness(0.5F).setStepSound(soundSandFootstep).setBlockName("sand");
	public static final Block gravel = (new BlockGravel(13, 19)).setHardness(0.6F).setStepSound(soundGravelFootstep).setBlockName("gravel");
	public static final Block oreGold = (new BlockOre(14, 32)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreGold");
	public static final Block oreIron = (new BlockOre(15, 33)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreIron");
	public static final Block oreCoal = (new BlockOre(16, 34)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreCoal");
	public static final Block wood = (new BlockLog(17)).setHardness(2.0F).setStepSound(soundWoodFootstep).setBlockName("log").setRequiresSelfNotify();
	public static final BlockLeaves leaves = (BlockLeaves)(new BlockLeaves(18, 52)).setHardness(0.2F).setLightOpacity(1).setStepSound(soundGrassFootstep).setBlockName("leaves").disableStats().setRequiresSelfNotify();
	public static final Block sponge = (new net.minecraft.block.BlockSponge(19)).setHardness(0.6F).setStepSound(soundGrassFootstep).setBlockName("sponge");
	public static final Block glass = (new net.minecraft.block.BlockGlass(20, 49, net.minecraft.block.material.Material.glass, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setBlockName("glass");
	public static final Block oreLapis = (new BlockOre(21, 160)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreLapis");
	public static final Block blockLapis = (new Block(22, 144, net.minecraft.block.material.Material.rock)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("blockLapis");
	public static final Block dispenser = (new BlockDispenser(23)).setHardness(3.5F).setStepSound(soundStoneFootstep).setBlockName("dispenser").setRequiresSelfNotify();
	public static final Block sandStone = (new BlockSandStone(24)).setStepSound(soundStoneFootstep).setHardness(0.8F).setBlockName("sandStone");
	public static final Block musicBlock = (new BlockNote(25)).setHardness(0.8F).setBlockName("musicBlock").setRequiresSelfNotify();
	public static final Block bed = (new net.minecraft.block.BlockBed(26)).setHardness(0.2F).setBlockName("bed").disableStats().setRequiresSelfNotify();
	public static final Block railPowered = (new BlockRail(27, 179, true)).setHardness(0.7F).setStepSound(soundMetalFootstep).setBlockName("goldenRail").setRequiresSelfNotify();
	public static final Block railDetector = (new BlockDetectorRail(28, 195)).setHardness(0.7F).setStepSound(soundMetalFootstep).setBlockName("detectorRail").setRequiresSelfNotify();
	public static final Block pistonStickyBase = (new BlockPistonBase(29, 106, true)).setBlockName("pistonStickyBase").setRequiresSelfNotify();
	public static final Block web = (new BlockWeb(30, 11)).setLightOpacity(1).setHardness(4.0F).setBlockName("web");
	public static final BlockTallGrass tallGrass = (BlockTallGrass)(new BlockTallGrass(31, 39)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("tallgrass");
	public static final BlockDeadBush deadBush = (BlockDeadBush)(new BlockDeadBush(32, 55)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("deadbush");
	public static final Block pistonBase = (new BlockPistonBase(33, 107, false)).setBlockName("pistonBase").setRequiresSelfNotify();
	public static final BlockPistonExtension pistonExtension = (BlockPistonExtension)(new BlockPistonExtension(34, 107)).setRequiresSelfNotify();
	public static final Block cloth = (new net.minecraft.block.BlockCloth()).setHardness(0.8F).setStepSound(soundClothFootstep).setBlockName("cloth").setRequiresSelfNotify();
	public static final BlockPistonMoving pistonMoving = new BlockPistonMoving(36);
	public static final BlockFlower plantYellow = (BlockFlower)(new BlockFlower(37, 13)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("flower");
	public static final BlockFlower plantRed = (BlockFlower)(new BlockFlower(38, 12)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("rose");
	public static final BlockFlower mushroomBrown = (BlockFlower)(new BlockMushroom(39, 29)).setHardness(0.0F).setStepSound(soundGrassFootstep).setLightValue(2.0F / 16.0F).setBlockName("mushroom");
	public static final BlockFlower mushroomRed = (BlockFlower)(new BlockMushroom(40, 28)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("mushroom");
	public static final Block blockGold = (new net.minecraft.block.BlockOreStorage(41, 23)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setBlockName("blockGold");
	public static final Block blockSteel = (new net.minecraft.block.BlockOreStorage(42, 22)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setBlockName("blockIron");
	public static final Block stairDouble = (new net.minecraft.block.BlockStep(43, true)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("stoneSlab");
	public static final Block stairSingle = (new net.minecraft.block.BlockStep(44, false)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("stoneSlab");
	public static final Block brick = (new Block(45, 7, net.minecraft.block.material.Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("brick");
	public static final Block tnt = (new BlockTNT(46, 8)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("tnt");
	public static final Block bookShelf = (new net.minecraft.block.BlockBookshelf(47, 35)).setHardness(1.5F).setStepSound(soundWoodFootstep).setBlockName("bookshelf");
	public static final Block cobblestoneMossy = (new Block(48, 36, net.minecraft.block.material.Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("stoneMoss");
	public static final Block obsidian = (new BlockObsidian(49, 37)).setHardness(10.0F).setResistance(2000.0F).setStepSound(soundStoneFootstep).setBlockName("obsidian");
	public static final Block torchWood = (new net.minecraft.block.BlockTorch(50, 80)).setHardness(0.0F).setLightValue(15.0F / 16.0F).setStepSound(soundWoodFootstep).setBlockName("torch").setRequiresSelfNotify();
	public static final net.minecraft.block.BlockFire fire = (net.minecraft.block.BlockFire)(new net.minecraft.block.BlockFire(51, 31)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setBlockName("fire").disableStats().setRequiresSelfNotify();
	public static final Block mobSpawner = (new BlockMobSpawner(52, 65)).setHardness(5.0F).setStepSound(soundMetalFootstep).setBlockName("mobSpawner").disableStats();
	public static final Block stairCompactPlanks = (new net.minecraft.block.BlockStairs(53, planks)).setBlockName("stairsWood").setRequiresSelfNotify();
	public static final Block chest = (new net.minecraft.block.BlockChest(54)).setHardness(2.5F).setStepSound(soundWoodFootstep).setBlockName("chest").setRequiresSelfNotify();
	public static final Block redstoneWire = (new BlockRedstoneWire(55, 164)).setHardness(0.0F).setStepSound(soundPowderFootstep).setBlockName("redstoneDust").disableStats().setRequiresSelfNotify();
	public static final Block oreDiamond = (new BlockOre(56, 50)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreDiamond");
	public static final Block blockDiamond = (new net.minecraft.block.BlockOreStorage(57, 24)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setBlockName("blockDiamond");
	public static final Block workbench = (new net.minecraft.block.BlockWorkbench(58)).setHardness(2.5F).setStepSound(soundWoodFootstep).setBlockName("workbench");
	public static final Block crops = (new BlockCrops(59, 88)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("crops").disableStats().setRequiresSelfNotify();
	public static final Block tilledField = (new BlockFarmland(60)).setHardness(0.6F).setStepSound(soundGravelFootstep).setBlockName("farmland");
	public static final Block stoneOvenIdle = (new net.minecraft.block.BlockFurnace(61, false)).setHardness(3.5F).setStepSound(soundStoneFootstep).setBlockName("furnace").setRequiresSelfNotify();
	public static final Block stoneOvenActive = (new net.minecraft.block.BlockFurnace(62, true)).setHardness(3.5F).setStepSound(soundStoneFootstep).setLightValue(14.0F / 16.0F).setBlockName("furnace").setRequiresSelfNotify();
	public static final Block signPost = (new net.minecraft.block.BlockSign(63, net.minecraft.block.tileentity.TileEntitySign.class, true)).setHardness(1.0F).setStepSound(soundWoodFootstep).setBlockName("sign").disableStats().setRequiresSelfNotify();
	public static final Block doorWood = (new BlockDoor(64, net.minecraft.block.material.Material.wood)).setHardness(3.0F).setStepSound(soundWoodFootstep).setBlockName("doorWood").disableStats().setRequiresSelfNotify();
	public static final Block ladder = (new net.minecraft.block.BlockLadder(65, 83)).setHardness(0.4F).setStepSound(soundWoodFootstep).setBlockName("ladder").setRequiresSelfNotify();
	public static final Block minecartTrack = (new BlockRail(66, 128, false)).setHardness(0.7F).setStepSound(soundMetalFootstep).setBlockName("rail").setRequiresSelfNotify();
	public static final Block stairCompactCobblestone = (new net.minecraft.block.BlockStairs(67, cobblestone)).setBlockName("stairsStone").setRequiresSelfNotify();
	public static final Block signWall = (new net.minecraft.block.BlockSign(68, TileEntitySign.class, false)).setHardness(1.0F).setStepSound(soundWoodFootstep).setBlockName("sign").disableStats().setRequiresSelfNotify();
	public static final Block lever = (new BlockLever(69, 96)).setHardness(0.5F).setStepSound(soundWoodFootstep).setBlockName("lever").setRequiresSelfNotify();
	public static final Block pressurePlateStone = (new BlockPressurePlate(70, stone.blockIndexInTexture, net.minecraft.entity.EnumMobType.mobs, net.minecraft.block.material.Material.rock)).setHardness(0.5F).setStepSound(soundStoneFootstep).setBlockName("pressurePlate").setRequiresSelfNotify();
	public static final Block doorSteel = (new BlockDoor(71, net.minecraft.block.material.Material.iron)).setHardness(5.0F).setStepSound(soundMetalFootstep).setBlockName("doorIron").disableStats().setRequiresSelfNotify();
	public static final Block pressurePlatePlanks = (new BlockPressurePlate(72, planks.blockIndexInTexture, EnumMobType.everything, net.minecraft.block.material.Material.wood)).setHardness(0.5F).setStepSound(soundWoodFootstep).setBlockName("pressurePlate").setRequiresSelfNotify();
	public static final Block oreRedstone = (new BlockRedstoneOre(73, 51, false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreRedstone").setRequiresSelfNotify();
	public static final Block oreRedstoneGlowing = (new BlockRedstoneOre(74, 51, true)).setLightValue(10.0F / 16.0F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("oreRedstone").setRequiresSelfNotify();
	public static final Block torchRedstoneIdle = (new BlockRedstoneTorch(75, 115, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setBlockName("notGate").setRequiresSelfNotify();
	public static final Block torchRedstoneActive = (new BlockRedstoneTorch(76, 99, true)).setHardness(0.0F).setLightValue(0.5F).setStepSound(soundWoodFootstep).setBlockName("notGate").setRequiresSelfNotify();
	public static final Block button = (new BlockButton(77, stone.blockIndexInTexture)).setHardness(0.5F).setStepSound(soundStoneFootstep).setBlockName("button").setRequiresSelfNotify();
	public static final Block snow = (new BlockSnow(78, 66)).setHardness(0.1F).setStepSound(soundClothFootstep).setBlockName("snow");
	public static final Block ice = (new BlockIce(79, 67)).setHardness(0.5F).setLightOpacity(3).setStepSound(soundGlassFootstep).setBlockName("ice");
	public static final Block blockSnow = (new BlockSnowBlock(80, 66)).setHardness(0.2F).setStepSound(soundClothFootstep).setBlockName("snow");
	public static final Block cactus = (new BlockCactus(81, 70)).setHardness(0.4F).setStepSound(soundClothFootstep).setBlockName("cactus");
	public static final Block blockClay = (new BlockClay(82, 72)).setHardness(0.6F).setStepSound(soundGravelFootstep).setBlockName("clay");
	public static final Block reed = (new BlockReed(83, 73)).setHardness(0.0F).setStepSound(soundGrassFootstep).setBlockName("reeds").disableStats();
	public static final Block jukebox = (new BlockJukeBox(84, 74)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setBlockName("jukebox").setRequiresSelfNotify();
	public static final Block fence = (new net.minecraft.block.BlockFence(85, 4)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setBlockName("fence").setRequiresSelfNotify();
	public static final Block pumpkin = (new BlockPumpkin(86, 102, false)).setHardness(1.0F).setStepSound(soundWoodFootstep).setBlockName("pumpkin").setRequiresSelfNotify();
	public static final Block bloodStone = (new BlockNetherrack(87, 103)).setHardness(0.4F).setStepSound(soundStoneFootstep).setBlockName("hellrock");
	public static final Block slowSand = (new BlockSoulSand(88, 104)).setHardness(0.5F).setStepSound(soundSandFootstep).setBlockName("hellsand");
	public static final Block glowStone = (new BlockGlowStone(89, 105, net.minecraft.block.material.Material.rock)).setHardness(0.3F).setStepSound(soundGlassFootstep).setLightValue(1.0F).setBlockName("lightgem");
	public static final net.minecraft.block.BlockPortal portal = (net.minecraft.block.BlockPortal)(new net.minecraft.block.BlockPortal(90, 14)).setHardness(-1.0F).setStepSound(soundGlassFootstep).setLightValue(12.0F / 16.0F).setBlockName("portal");
	public static final Block pumpkinLantern = (new BlockPumpkin(91, 102, true)).setHardness(1.0F).setStepSound(soundWoodFootstep).setLightValue(1.0F).setBlockName("litpumpkin").setRequiresSelfNotify();
	public static final Block cake = (new net.minecraft.block.BlockCake(92, 121)).setHardness(0.5F).setStepSound(soundClothFootstep).setBlockName("cake").disableStats().setRequiresSelfNotify();
	public static final Block redstoneRepeaterIdle = (new BlockRedstoneRepeater(93, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setBlockName("diode").disableStats().setRequiresSelfNotify();
	public static final Block redstoneRepeaterActive = (new BlockRedstoneRepeater(94, true)).setHardness(0.0F).setLightValue(10.0F / 16.0F).setStepSound(soundWoodFootstep).setBlockName("diode").disableStats().setRequiresSelfNotify();
	public static final Block lockedChest = (new BlockLockedChest(95)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setBlockName("lockedchest").setTickOnLoad(true).setRequiresSelfNotify();
	public static final Block trapdoor = (new BlockTrapDoor(96, net.minecraft.block.material.Material.wood)).setHardness(3.0F).setStepSound(soundWoodFootstep).setBlockName("trapdoor").disableStats().setRequiresSelfNotify();
	public int blockIndexInTexture;
	public final int blockID;
	public float blockHardness;
	public float blockResistance;
	public boolean blockConstructorCalled;
	public boolean enableStats;
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public net.minecraft.block.StepSound stepSound;
	public float blockParticleGravity;
	public final net.minecraft.block.material.Material blockMaterial;
	public float slipperiness;
	private String blockName;

	protected Block(int var1, net.minecraft.block.material.Material var2) {
		this.blockConstructorCalled = true;
		this.enableStats = true;
		this.stepSound = soundPowderFootstep;
		this.blockParticleGravity = 1.0F;
		this.slipperiness = 0.6F;
		if(blocksList[var1] != null) {
			throw new IllegalArgumentException("Slot " + var1 + " is already occupied by " + blocksList[var1] + " when adding " + this);
		} else {
			this.blockMaterial = var2;
			blocksList[var1] = this;
			this.blockID = var1;
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			opaqueCubeLookup[var1] = this.isOpaqueCube();
			lightOpacity[var1] = this.isOpaqueCube() ? 255 : 0;
			canBlockGrass[var1] = !var2.getCanBlockGrass();
			isBlockContainer[var1] = false;
		}
	}

	protected Block setRequiresSelfNotify() {
		requiresSelfNotify[this.blockID] = true;
		return this;
	}

	protected void setFireBurnRates() {
	}

	protected Block(int var1, int var2, Material var3) {
		this(var1, var3);
		this.blockIndexInTexture = var2;
	}

	protected Block setStepSound(StepSound var1) {
		this.stepSound = var1;
		return this;
	}

	protected Block setLightOpacity(int var1) {
		lightOpacity[this.blockID] = var1;
		return this;
	}

	protected Block setLightValue(float var1) {
		lightValue[this.blockID] = (int)(15.0F * var1);
		return this;
	}

	protected Block setResistance(float var1) {
		this.blockResistance = var1 * 3.0F;
		return this;
	}

	public boolean isACube() {
		return true;
	}

	protected Block setHardness(float var1) {
		this.blockHardness = var1;
		if(this.blockResistance < var1 * 5.0F) {
			this.blockResistance = var1 * 5.0F;
		}

		return this;
	}

	protected Block setBlockUnbreakable() {
		this.setHardness(-1.0F);
		return this;
	}

	public float getHardness() {
		return this.blockHardness;
	}

	protected Block setTickOnLoad(boolean var1) {
		tickOnLoad[this.blockID] = var1;
		return this;
	}

	public void setBlockBounds(float var1, float var2, float var3, float var4, float var5, float var6) {
		this.minX = (double)var1;
		this.minY = (double)var2;
		this.minZ = (double)var3;
		this.maxX = (double)var4;
		this.maxY = (double)var5;
		this.maxZ = (double)var6;
	}

	public boolean shouldSideBeRendered(net.minecraft.block.core.IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return var1.getBlockMaterial(var2, var3, var4).isSolid();
	}

	public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
		return this.getBlockTextureFromSide(var1);
	}

	public int getBlockTextureFromSide(int var1) {
		return this.blockIndexInTexture;
	}

	public void getCollidingBoundingBoxes(net.minecraft.world.World var1, int var2, int var3, int var4, AxisAlignedBB var5, ArrayList var6) {
		AxisAlignedBB var7 = this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
		if(var7 != null && var5.intersectsWith(var7)) {
			var6.add(var7);
		}

	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(net.minecraft.world.World var1, int var2, int var3, int var4) {
		return AxisAlignedBB.getBoundingBoxFromPool((double)var2 + this.minX, (double)var3 + this.minY, (double)var4 + this.minZ, (double)var2 + this.maxX, (double)var3 + this.maxY, (double)var4 + this.maxZ);
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean canCollideCheck(int var1, boolean var2) {
		return this.isCollidable();
	}

	public boolean isCollidable() {
		return true;
	}

	public void updateTick(net.minecraft.world.World var1, int var2, int var3, int var4, Random var5) {
	}

	public void onBlockDestroyedByPlayer(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
	}

	public void onNeighborBlockChange(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
	}

	public int tickRate() {
		return 10;
	}

	public void onBlockAdded(net.minecraft.world.World var1, int var2, int var3, int var4) {
	}

	public void onBlockRemoval(net.minecraft.world.World var1, int var2, int var3, int var4) {
	}

	public int quantityDropped(Random var1) {
		return 1;
	}

	public int idDropped(int var1, Random var2) {
		return this.blockID;
	}

	public float blockStrength(net.minecraft.entity.living.EntityPlayer var1) {
		return this.blockHardness < 0.0F ? 0.0F : (!var1.canHarvestBlock(this) ? 1.0F / this.blockHardness / 100.0F : var1.getCurrentPlayerStrVsBlock(this) / this.blockHardness / 30.0F);
	}

	public final void dropBlockAsItem(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
		this.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, 1.0F);
	}

	public void dropBlockAsItemWithChance(net.minecraft.world.World var1, int var2, int var3, int var4, int var5, float var6) {
		if(!var1.singleplayerWorld) {
			int var7 = this.quantityDropped(var1.rand);

			for(int var8 = 0; var8 < var7; ++var8) {
				if(var1.rand.nextFloat() <= var6) {
					int var9 = this.idDropped(var5, var1.rand);
					if(var9 > 0) {
						this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var9, 1, this.damageDropped(var5)));
					}
				}
			}

		}
	}

	protected void dropBlockAsItem_do(net.minecraft.world.World var1, int var2, int var3, int var4, ItemStack var5) {
		if(!var1.singleplayerWorld) {
			float var6 = 0.7F;
			double var7 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
			double var9 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
			double var11 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
			net.minecraft.entity.EntityItem var13 = new EntityItem(var1, (double)var2 + var7, (double)var3 + var9, (double)var4 + var11, var5);
			var13.delayBeforeCanPickup = 10;
			var1.entityJoinedWorld(var13);
		}
	}

	protected int damageDropped(int var1) {
		return 0;
	}

	public float getExplosionResistance(net.minecraft.entity.Entity var1) {
		return this.blockResistance / 5.0F;
	}

	public MovingObjectPosition collisionRayTrace(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.core.Vec3D var5, net.minecraft.core.Vec3D var6) {
		this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
		var5 = var5.addVector((double)(-var2), (double)(-var3), (double)(-var4));
		var6 = var6.addVector((double)(-var2), (double)(-var3), (double)(-var4));
		net.minecraft.core.Vec3D var7 = var5.getIntermediateWithXValue(var6, this.minX);
		net.minecraft.core.Vec3D var8 = var5.getIntermediateWithXValue(var6, this.maxX);
		net.minecraft.core.Vec3D var9 = var5.getIntermediateWithYValue(var6, this.minY);
		net.minecraft.core.Vec3D var10 = var5.getIntermediateWithYValue(var6, this.maxY);
		net.minecraft.core.Vec3D var11 = var5.getIntermediateWithZValue(var6, this.minZ);
		net.minecraft.core.Vec3D var12 = var5.getIntermediateWithZValue(var6, this.maxZ);
		if(!this.isVecInsideYZBounds(var7)) {
			var7 = null;
		}

		if(!this.isVecInsideYZBounds(var8)) {
			var8 = null;
		}

		if(!this.isVecInsideXZBounds(var9)) {
			var9 = null;
		}

		if(!this.isVecInsideXZBounds(var10)) {
			var10 = null;
		}

		if(!this.isVecInsideXYBounds(var11)) {
			var11 = null;
		}

		if(!this.isVecInsideXYBounds(var12)) {
			var12 = null;
		}

		net.minecraft.core.Vec3D var13 = null;
		if(var7 != null && (var13 == null || var5.distanceTo(var7) < var5.distanceTo(var13))) {
			var13 = var7;
		}

		if(var8 != null && (var13 == null || var5.distanceTo(var8) < var5.distanceTo(var13))) {
			var13 = var8;
		}

		if(var9 != null && (var13 == null || var5.distanceTo(var9) < var5.distanceTo(var13))) {
			var13 = var9;
		}

		if(var10 != null && (var13 == null || var5.distanceTo(var10) < var5.distanceTo(var13))) {
			var13 = var10;
		}

		if(var11 != null && (var13 == null || var5.distanceTo(var11) < var5.distanceTo(var13))) {
			var13 = var11;
		}

		if(var12 != null && (var13 == null || var5.distanceTo(var12) < var5.distanceTo(var13))) {
			var13 = var12;
		}

		if(var13 == null) {
			return null;
		} else {
			byte var14 = -1;
			if(var13 == var7) {
				var14 = 4;
			}

			if(var13 == var8) {
				var14 = 5;
			}

			if(var13 == var9) {
				var14 = 0;
			}

			if(var13 == var10) {
				var14 = 1;
			}

			if(var13 == var11) {
				var14 = 2;
			}

			if(var13 == var12) {
				var14 = 3;
			}

			return new MovingObjectPosition(var2, var3, var4, var14, var13.addVector((double)var2, (double)var3, (double)var4));
		}
	}

	private boolean isVecInsideYZBounds(net.minecraft.core.Vec3D var1) {
		return var1 == null ? false : var1.yCoord >= this.minY && var1.yCoord <= this.maxY && var1.zCoord >= this.minZ && var1.zCoord <= this.maxZ;
	}

	private boolean isVecInsideXZBounds(net.minecraft.core.Vec3D var1) {
		return var1 == null ? false : var1.xCoord >= this.minX && var1.xCoord <= this.maxX && var1.zCoord >= this.minZ && var1.zCoord <= this.maxZ;
	}

	private boolean isVecInsideXYBounds(net.minecraft.core.Vec3D var1) {
		return var1 == null ? false : var1.xCoord >= this.minX && var1.xCoord <= this.maxX && var1.yCoord >= this.minY && var1.yCoord <= this.maxY;
	}

	public void onBlockDestroyedByExplosion(net.minecraft.world.World var1, int var2, int var3, int var4) {
	}

	public boolean canPlaceBlockOnSide(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
		return this.canPlaceBlockAt(var1, var2, var3, var4);
	}

	public boolean canPlaceBlockAt(net.minecraft.world.World var1, int var2, int var3, int var4) {
		int var5 = var1.getBlockId(var2, var3, var4);
		return var5 == 0 || blocksList[var5].blockMaterial.func_27090_g();
	}

	public boolean blockActivated(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.living.EntityPlayer var5) {
		return false;
	}

	public void onEntityWalking(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.Entity var5) {
	}

	public void onBlockPlaced(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
	}

	public void onBlockClicked(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.living.EntityPlayer var5) {
	}

	public void velocityToAddToEntity(net.minecraft.world.World var1, int var2, int var3, int var4, net.minecraft.entity.Entity var5, Vec3D var6) {
	}

	public void setBlockBoundsBasedOnState(net.minecraft.block.core.IBlockAccess var1, int var2, int var3, int var4) {
	}

	public boolean isPoweringTo(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return false;
	}

	public boolean canProvidePower() {
		return false;
	}

	public void onEntityCollidedWithBlock(net.minecraft.world.World var1, int var2, int var3, int var4, Entity var5) {
	}

	public boolean isIndirectlyPoweringTo(net.minecraft.world.World var1, int var2, int var3, int var4, int var5) {
		return false;
	}

	public void harvestBlock(net.minecraft.world.World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
		var2.addStat(net.minecraft.achievement.stats.StatList.mineBlockStatArray[this.blockID], 1);
		this.dropBlockAsItem(var1, var3, var4, var5, var6);
	}

	public boolean canBlockStay(net.minecraft.world.World var1, int var2, int var3, int var4) {
		return true;
	}

	public void onBlockPlacedBy(net.minecraft.world.World var1, int var2, int var3, int var4, EntityLiving var5) {
	}

	public Block setBlockName(String var1) {
		this.blockName = "tile." + var1;
		return this;
	}

	public String getNameLocalizedForStats() {
		return StatCollector.translateToLocal(this.getBlockName() + ".name");
	}

	public String getBlockName() {
		return this.blockName;
	}

	public void playBlock(World var1, int var2, int var3, int var4, int var5, int var6) {
	}

	public boolean getEnableStats() {
		return this.enableStats;
	}

	protected Block disableStats() {
		this.enableStats = false;
		return this;
	}

	public int getMobilityFlag() {
		return this.blockMaterial.getMaterialMobility();
	}

	static {
		net.minecraft.item.core.Item.itemsList[cloth.blockID] = (new net.minecraft.item.ItemCloth(cloth.blockID - 256)).setItemName("cloth");
		net.minecraft.item.core.Item.itemsList[wood.blockID] = (new net.minecraft.item.ItemLog(wood.blockID - 256)).setItemName("log");
		net.minecraft.item.core.Item.itemsList[stairSingle.blockID] = (new net.minecraft.item.ItemSlab(stairSingle.blockID - 256)).setItemName("stoneSlab");
		net.minecraft.item.core.Item.itemsList[sapling.blockID] = (new net.minecraft.item.ItemSapling(sapling.blockID - 256)).setItemName("sapling");
		net.minecraft.item.core.Item.itemsList[leaves.blockID] = (new net.minecraft.item.ItemLeaves(leaves.blockID - 256)).setItemName("leaves");
		net.minecraft.item.core.Item.itemsList[pistonBase.blockID] = new ItemPiston(pistonBase.blockID - 256);
		net.minecraft.item.core.Item.itemsList[pistonStickyBase.blockID] = new ItemPiston(pistonStickyBase.blockID - 256);

		for(int var0 = 0; var0 < 256; ++var0) {
			if(blocksList[var0] != null && net.minecraft.item.core.Item.itemsList[var0] == null) {
				Item.itemsList[var0] = new ItemBlock(var0 - 256);
				blocksList[var0].setFireBurnRates();
			}
		}

		canBlockGrass[0] = true;
		StatList.func_25088_a();
	}
}
