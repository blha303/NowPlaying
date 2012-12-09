package com.thegleek.bukkit.NowPlaying;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

/**
 * @author thegleek
 * 
 */
public class cChat {
	private static final Logger log = Logger.getLogger("Minecraft");
	public static Player player = null;
	public static NowPlaying plugin;

	public cChat(NowPlaying instance) {
		plugin = instance;
	}

	/**
	 * @param player
	 */
	public static void save(Player player) {
		cChat.player = player;
	}

	/**
	 * @param player
	 * @param message
	 */
	public static void send(Player player, String message) {
		player.sendMessage(message);
	}

	/**
	 * @param message
	 */
	public static void send(String message) {
		if (player != null)
			player.sendMessage(message);
	}

	/**
	 * @param message
	 */
	public static void broadcast(String message) {
		// slow ghetto way
		// for (Player p :
		// cPlayerListener.plugin.getServer().getOnlinePlayers()) {
		// p.sendMessage(message);
		// }

		// simple quick efficient way
		cPlayerListener.StaticNP.getServer().broadcastMessage(message);

		log.info("[NowPlaying] broadcasting message: " + message);
	}

	// the following code is courtesy of angelsl's MinecraftFontWidthCalculator
	// class
	private static String charWidthIndexIndex = " !\"#$%&'()*+,-./0123456789:;<=>?@"
			+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "[\\]^_'abcdefghijklmnopqrstuvwxyz"
			+ "{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»";

	private static int[] charWidths = { 4, 2, 5, 6, 6, 6, 6, 3, 5, 5, 5, 6, 2,
			6, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 5, 6, 5, 6, 7, 6, 6,
			6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 4, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 5, 6, 6, 2, 6, 5, 3, 6, 6,
			6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 5, 2, 5, 7, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 6, 6, 6, 4, 6, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
			6, 6, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 2, 6, 6, 8,
			9, 9, 6, 6, 6, 8, 8, 6, 8, 8, 8, 8, 8, 6, 6, 9, 9, 9, 9, 9, 9, 9,
			9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6, 9, 9, 9,
			5, 9, 9, 8, 7, 7, 8, 7, 8, 8, 8, 7, 8, 8, 7, 9, 9, 6, 7, 7, 7, 7,
			7, 9, 6, 7, 8, 7, 6, 6, 9, 7, 6, 7, 1 };

	/**
	 * @param s
	 * @return
	 */
	public static int getStringWidth(String s) {
		int i = 0;
		if (s != null)
			for (int j = 0; j < s.length(); j++)
				i += getCharWidth(s.charAt(j));
		return i;
	}

	/**
	 * @param c
	 * @return
	 */
	public static int getCharWidth(char c) {
		int k = charWidthIndexIndex.indexOf(c);
		if (c != '\247' && k >= 0)
			return charWidths[k];
		return 0;
	}

	/**
	 * @param name
	 * @param left
	 * @param cColor
	 * @return
	 */
	public static ArrayList<String> aSubstring(String name, int left,
			ChatColor cColor) {
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder sbLeft = new StringBuilder();
		StringBuilder sbRight = new StringBuilder();
		String[] sSplit;
		String sTemp = "";
		int tLen = 0;
		boolean bFlag = true;

		sSplit = name.split(" ");

		for (String s : sSplit) {
			int i = getStringWidth(s);
			tLen += i;
			if ((tLen < left) && bFlag) {
				sTemp = sbLeft.toString() + s + " ";

				if (getStringWidth(sTemp) > left) {
					sbRight.append(s + " ");
					bFlag = false;
				} else {
					sbLeft.append(s + " ");
				}
			} else {
				sbRight.append(s + " ");
			}
		}

		ret.add(cColor + sbLeft.toString());

		if (sbRight.length() > 0) {
			ret.add(cColor + sbRight.toString());
		} else {
			ret.add("");
		}

		return ret;
	}

	/**
	 * @param length
	 * @return
	 */
	public static String sWhitespace(int length) {
		int spaceWidth = getStringWidth(" ");

		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < length; i += spaceWidth) {
			ret.append(" ");
		}

		return ret.toString();
	}
}
