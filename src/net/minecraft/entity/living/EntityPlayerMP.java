package net.minecraft.entity.living;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.minecraft.achievement.stats.StatBase;
import net.minecraft.block.tileentity.TileEntity;
import net.minecraft.block.tileentity.TileEntityDispenser;
import net.minecraft.block.tileentity.TileEntityFurnace;
import net.minecraft.core.EnumStatus;
import net.minecraft.core.StringTranslate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.container.inventory.SlotCrafting;
import net.minecraft.item.core.ItemStack;
import net.minecraft.item.container.Container;
import net.minecraft.item.container.ContainerWorkbench;
import net.minecraft.item.container.inventory.IInventory;
import net.minecraft.item.core.Item;
import net.minecraft.item.gear.ItemMapBase;
import net.minecraft.item.recipe.ICrafting;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet200Statistic;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.ChunkCoordIntPair;

public class EntityPlayerMP extends net.minecraft.entity.living.EntityPlayer implements ICrafting {
	public NetServerHandler playerNetServerHandler;
	public MinecraftServer mcServer;
	public net.minecraft.item.ItemInWorldManager itemInWorldManager;
	public double field_9155_d;
	public double field_9154_e;
	public List<ChunkCoordIntPair> loadedChunks = new LinkedList<>();
	public Set<ChunkCoordIntPair> field_420_ah = new HashSet<>();
	private int lastHealth = -99999999;
	private int ticksOfInvuln = 60;
	private final ItemStack[] playerInventory = new ItemStack[]{null, null, null, null, null};
	private int currentWindowId = 0;
	public boolean isChangingQuantityOnly;

	public EntityPlayerMP(MinecraftServer var1, net.minecraft.world.World var2, String var3, net.minecraft.item.ItemInWorldManager var4) {
		super(var2);
		var4.thisPlayer = this;
		this.itemInWorldManager = var4;
		net.minecraft.world.chunk.ChunkCoordinates var5 = var2.getSpawnPoint();
		int var6 = var5.posX;
		int var7 = var5.posZ;
		int var8 = var5.posY;
		if(!var2.worldProvider.field_4306_c) {
			var6 += this.rand.nextInt(20) - 10;
			var8 = var2.findTopSolidBlock(var6, var7);
			var7 += this.rand.nextInt(20) - 10;
		}

		this.setLocationAndAngles((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
		this.mcServer = var1;
		this.stepHeight = 0.0F;
		this.username = var3;
		this.yOffset = 0.0F;
	}

	public void setWorldHandler(net.minecraft.world.World var1) {
		super.setWorldHandler(var1);
		this.itemInWorldManager = new net.minecraft.item.ItemInWorldManager((net.minecraft.world.WorldServer)var1);
		this.itemInWorldManager.thisPlayer = this;
	}

	public void func_20057_k() {
		this.currentCraftingInventory.onCraftGuiOpened(this);
	}

	public ItemStack[] getInventory() {
		return this.playerInventory;
	}

	protected void resetHeight() {
		this.yOffset = 0.0F;
	}

	public float getEyeHeight() {
		return 1.62F;
	}

	public void onUpdate() {
		this.itemInWorldManager.func_328_a();
		--this.ticksOfInvuln;
		this.currentCraftingInventory.updateCraftingMatrix();

		for(int var1 = 0; var1 < 5; ++var1) {
			ItemStack var2 = this.getEquipmentInSlot(var1);
			if(var2 != this.playerInventory[var1]) {
				this.mcServer.getEntityTracker(this.dimension).sendPacketToTrackedPlayers(this, new net.minecraft.network.packet.Packet5PlayerInventory(this.entityId, var1, var2));
				this.playerInventory[var1] = var2;
			}
		}

	}

	public ItemStack getEquipmentInSlot(int var1) {
		return var1 == 0 ? this.inventory.getCurrentItem() : this.inventory.armorInventory[var1 - 1];
	}

	public void onDeath(net.minecraft.entity.Entity var1) {
		this.inventory.dropAllItems();
	}

	public boolean attackEntityFrom(net.minecraft.entity.Entity var1, int var2) {
		if(this.ticksOfInvuln > 0) {
			return false;
		} else {
			if(!this.mcServer.pvpOn) {
				if(var1 instanceof net.minecraft.entity.living.EntityPlayer) {
					return false;
				}

				if(var1 instanceof net.minecraft.entity.projectile.EntityArrow) {
					net.minecraft.entity.projectile.EntityArrow var3 = (net.minecraft.entity.projectile.EntityArrow)var1;
					if(var3.owner instanceof EntityPlayer) {
						return false;
					}
				}
			}

			return super.attackEntityFrom(var1, var2);
		}
	}

	protected boolean isPVPEnabled() {
		return this.mcServer.pvpOn;
	}

	public void heal(int var1) {
		super.heal(var1);
	}

	public void onUpdateEntity(boolean var1) {
		super.onUpdate();

		for(int var2 = 0; var2 < this.inventory.getSizeInventory(); ++var2) {
			ItemStack var3 = this.inventory.getStackInSlot(var2);
			if(var3 != null && net.minecraft.item.core.Item.itemsList[var3.itemID].func_28019_b() && this.playerNetServerHandler.getNumChunkDataPackets() <= 2) {
				net.minecraft.network.packet.Packet var4 = ((ItemMapBase) Item.itemsList[var3.itemID]).func_28022_b(var3, this.worldObj, this);
				if(var4 != null) {
					this.playerNetServerHandler.sendPacket(var4);
				}
			}
		}

		if(var1 && !this.loadedChunks.isEmpty()) {
			net.minecraft.world.chunk.ChunkCoordIntPair var7 = (ChunkCoordIntPair)this.loadedChunks.get(0);
			if(var7 != null) {
				boolean var8 = false;
				if(this.playerNetServerHandler.getNumChunkDataPackets() < 4) {
					var8 = true;
				}

				if(var8) {
					WorldServer var9 = this.mcServer.getWorldManager(this.dimension);
					this.loadedChunks.remove(var7);
					this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet51MapChunk(var7.chunkXPos * 16, 0, var7.chunkZPos * 16, 16, 128, 16, var9));
					List var5 = var9.getTileEntityList(var7.chunkXPos * 16, 0, var7.chunkZPos * 16, var7.chunkXPos * 16 + 16, 128, var7.chunkZPos * 16 + 16);

					for(int var6 = 0; var6 < var5.size(); ++var6) {
						this.getTileEntityInfo((net.minecraft.block.tileentity.TileEntity)var5.get(var6));
					}
				}
			}
		}

		if(this.inPortal) {
			if(this.mcServer.propertyManagerObj.getBooleanProperty("allow-nether", true)) {
				if(this.currentCraftingInventory != this.personalCraftingInventory) {
					this.usePersonalCraftingInventory();
				}

				if(this.ridingEntity != null) {
					this.mountEntity(this.ridingEntity);
				} else {
					this.timeInPortal += 0.0125F;
					if(this.timeInPortal >= 1.0F) {
						this.timeInPortal = 1.0F;
						this.timeUntilPortal = 10;
						this.mcServer.configManager.sendPlayerToOtherDimension(this);
					}
				}

				this.inPortal = false;
			}
		} else {
			if(this.timeInPortal > 0.0F) {
				this.timeInPortal -= 0.05F;
			}

			if(this.timeInPortal < 0.0F) {
				this.timeInPortal = 0.0F;
			}
		}

		if(this.timeUntilPortal > 0) {
			--this.timeUntilPortal;
		}

		if(this.health != this.lastHealth) {
			this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet8UpdateHealth(this.health));
			this.lastHealth = this.health;
		}

	}

	private void getTileEntityInfo(TileEntity var1) {
		if(var1 != null) {
			Packet var2 = var1.getDescriptionPacket();
			if(var2 != null) {
				this.playerNetServerHandler.sendPacket(var2);
			}
		}

	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void onItemPickup(net.minecraft.entity.Entity var1, int var2) {
		if(!var1.isDead) {
			net.minecraft.entity.EntityTracker var3 = this.mcServer.getEntityTracker(this.dimension);
			if(var1 instanceof net.minecraft.entity.EntityItem) {
				var3.sendPacketToTrackedPlayers(var1, new net.minecraft.network.packet.Packet22Collect(var1.entityId, this.entityId));
			}

			if(var1 instanceof EntityArrow) {
				var3.sendPacketToTrackedPlayers(var1, new net.minecraft.network.packet.Packet22Collect(var1.entityId, this.entityId));
			}
		}

		super.onItemPickup(var1, var2);
		this.currentCraftingInventory.updateCraftingMatrix();
	}

	public void swingItem() {
		if(!this.isSwinging) {
			this.swingProgressInt = -1;
			this.isSwinging = true;
			net.minecraft.entity.EntityTracker var1 = this.mcServer.getEntityTracker(this.dimension);
			var1.sendPacketToTrackedPlayers(this, new net.minecraft.network.packet.Packet18Animation(this, 1));
		}

	}

	public void func_22068_s() {
	}

	public net.minecraft.core.EnumStatus goToSleep(int var1, int var2, int var3) {
		net.minecraft.core.EnumStatus var4 = super.goToSleep(var1, var2, var3);
		if(var4 == EnumStatus.OK) {
			net.minecraft.entity.EntityTracker var5 = this.mcServer.getEntityTracker(this.dimension);
			net.minecraft.network.packet.Packet17Sleep var6 = new net.minecraft.network.packet.Packet17Sleep(this, 0, var1, var2, var3);
			var5.sendPacketToTrackedPlayers(this, var6);
			this.playerNetServerHandler.teleportTo(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.playerNetServerHandler.sendPacket(var6);
		}

		return var4;
	}

	public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
		if(this.func_22057_E()) {
			net.minecraft.entity.EntityTracker var4 = this.mcServer.getEntityTracker(this.dimension);
			var4.sendPacketToTrackedPlayersAndTrackedEntity(this, new net.minecraft.network.packet.Packet18Animation(this, 3));
		}

		super.wakeUpPlayer(var1, var2, var3);
		if(this.playerNetServerHandler != null) {
			this.playerNetServerHandler.teleportTo(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		}

	}

	public void mountEntity(Entity var1) {
		super.mountEntity(var1);
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet39AttachEntity(this, this.ridingEntity));
		this.playerNetServerHandler.teleportTo(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	}

	protected void updateFallState(double var1, boolean var3) {
	}

	public void handleFalling(double var1, boolean var3) {
		super.updateFallState(var1, var3);
	}

	private void getNextWidowId() {
		this.currentWindowId = this.currentWindowId % 100 + 1;
	}

	public void displayWorkbenchGUI(int var1, int var2, int var3) {
		this.getNextWidowId();
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet100OpenWindow(this.currentWindowId, 1, "Crafting", 9));
		this.currentCraftingInventory = new ContainerWorkbench(this.inventory, this.worldObj, var1, var2, var3);
		this.currentCraftingInventory.windowId = this.currentWindowId;
		this.currentCraftingInventory.onCraftGuiOpened(this);
	}

	public void displayGUIChest(IInventory var1) {
		this.getNextWidowId();
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet100OpenWindow(this.currentWindowId, 0, var1.getInvName(), var1.getSizeInventory()));
		this.currentCraftingInventory = new net.minecraft.item.container.ContainerChest(this.inventory, var1);
		this.currentCraftingInventory.windowId = this.currentWindowId;
		this.currentCraftingInventory.onCraftGuiOpened(this);
	}

	public void displayGUIFurnace(TileEntityFurnace var1) {
		this.getNextWidowId();
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet100OpenWindow(this.currentWindowId, 2, var1.getInvName(), var1.getSizeInventory()));
		this.currentCraftingInventory = new net.minecraft.item.container.ContainerFurnace(this.inventory, var1);
		this.currentCraftingInventory.windowId = this.currentWindowId;
		this.currentCraftingInventory.onCraftGuiOpened(this);
	}

	public void displayGUIDispenser(TileEntityDispenser var1) {
		this.getNextWidowId();
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet100OpenWindow(this.currentWindowId, 3, var1.getInvName(), var1.getSizeInventory()));
		this.currentCraftingInventory = new net.minecraft.item.container.ContainerDispenser(this.inventory, var1);
		this.currentCraftingInventory.windowId = this.currentWindowId;
		this.currentCraftingInventory.onCraftGuiOpened(this);
	}

	public void updateCraftingInventorySlot(net.minecraft.item.container.Container var1, int var2, ItemStack var3) {
		if(!(var1.getSlot(var2) instanceof SlotCrafting)) {
			if(!this.isChangingQuantityOnly) {
				this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet103SetSlot(var1.windowId, var2, var3));
			}
		}
	}

	public void func_28017_a(net.minecraft.item.container.Container var1) {
		this.updateCraftingInventory(var1, var1.func_28127_b());
	}

	public void updateCraftingInventory(net.minecraft.item.container.Container var1, List var2) {
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet104WindowItems(var1.windowId, var2));
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
	}

	public void updateCraftingInventoryInfo(Container var1, int var2, int var3) {
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet105UpdateProgressbar(var1.windowId, var2, var3));
	}

	public void onItemStackChanged(ItemStack var1) {
	}

	public void usePersonalCraftingInventory() {
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet101CloseWindow(this.currentCraftingInventory.windowId));
		this.closeCraftingGui();
	}

	public void updateHeldItem() {
		if(!this.isChangingQuantityOnly) {
			this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
		}
	}

	public void closeCraftingGui() {
		this.currentCraftingInventory.onCraftGuiClosed(this);
		this.currentCraftingInventory = this.personalCraftingInventory;
	}

	public void setMovementType(float var1, float var2, boolean var3, boolean var4, float var5, float var6) {
		this.moveStrafing = var1;
		this.moveForward = var2;
		this.isJumping = var3;
		this.setSneaking(var4);
		this.rotationPitch = var5;
		this.rotationYaw = var6;
	}

	public void addStat(StatBase var1, int var2) {
		if(var1 != null) {
			if(!var1.field_27088_g) {
				while(var2 > 100) {
					this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet200Statistic(var1.statId, 100));
					var2 -= 100;
				}

				this.playerNetServerHandler.sendPacket(new Packet200Statistic(var1.statId, var2));
			}

		}
	}

	public void func_30002_A() {
		if(this.ridingEntity != null) {
			this.mountEntity(this.ridingEntity);
		}

		if(this.riddenByEntity != null) {
			this.riddenByEntity.mountEntity(this);
		}

		if(this.sleeping) {
			this.wakeUpPlayer(true, false, false);
		}

	}

	public void func_30001_B() {
		this.lastHealth = -99999999;
	}

	public void func_22061_a(String var1) {
		net.minecraft.core.StringTranslate var2 = StringTranslate.getInstance();
		String var3 = var2.translateKey(var1);
		this.playerNetServerHandler.sendPacket(new net.minecraft.network.packet.Packet3Chat(var3));
	}
}
