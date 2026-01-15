package net.minecraft.server;

import java.util.Vector;
import javax.swing.JList;

import net.minecraft.entity.living.EntityPlayerMP;

public class PlayerListBox extends JList<String> implements IUpdatePlayerListBox {
	private final MinecraftServer mcServer;
	private int updateCounter = 0;

	public PlayerListBox(MinecraftServer var1) {
		this.mcServer = var1;
		var1.func_6022_a(this);
	}

	public void update() {
		if(this.updateCounter++ % 20 == 0) {
			Vector<String> var1 = new Vector<>();

			for(int var2 = 0; var2 < this.mcServer.configManager.playerEntities.size(); ++var2) {
				var1.add(((EntityPlayerMP)this.mcServer.configManager.playerEntities.get(var2)).username);
			}

			this.setListData(var1);
		}

	}
}
