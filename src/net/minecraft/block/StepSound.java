package net.minecraft.block;

public class StepSound {
	public final String field_1029_a;
	public final float field_1028_b;
	public final float field_1030_c;

	public StepSound(String var1, float volume, float pitch) {
		this.field_1029_a = var1;
		this.field_1028_b = volume;
		this.field_1030_c = pitch;
	}

	public float getVolume() {
		return this.field_1028_b;
	}

	public float getPitch() {
		return this.field_1030_c;
	}

	public String func_737_c() {
		return "step." + this.field_1029_a;
	}
}
