package net.minecraft.core.save;

import net.minecraft.misc.IProgressUpdate;

public interface ISaveFormat {
	boolean isOldSaveType(String var1);

	boolean converMapToMCRegion(String var1, IProgressUpdate var2);
}
