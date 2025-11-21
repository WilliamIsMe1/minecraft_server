package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ChatAllowedCharacters {
	public static final String allowedCharacters = getAllowedCharacters();
	public static final char[] field_22175_b = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

	private static String getAllowedCharacters() {
		String var0 = "";

		try {
			BufferedReader var1 = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChatAllowedCharacters.class.getResourceAsStream("/font.txt")), StandardCharsets.UTF_8));
			String var2;

			while(true) {
				var2 = var1.readLine();
				if(var2 == null) {
					var1.close();
					break;
				}

				if(!var2.startsWith("#")) {
					var0 = var0 + var2;
				}
			}
		} catch (Exception ignored) {
		}

		return var0;
	}
}
