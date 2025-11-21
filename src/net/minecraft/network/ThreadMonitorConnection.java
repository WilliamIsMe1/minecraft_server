package net.minecraft.network;

public class ThreadMonitorConnection extends Thread {
	final net.minecraft.network.NetworkManager netManager;

	ThreadMonitorConnection(net.minecraft.network.NetworkManager var1) {
		this.netManager = var1;
	}

	public void run() {
		try {
			Thread.sleep(2000L);
			if(net.minecraft.network.NetworkManager.isRunning(this.netManager)) {
				NetworkManager.getWriteThread(this.netManager).interrupt();
				this.netManager.networkShutdown("disconnect.closed", new Object[0]);
			}
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}
}
