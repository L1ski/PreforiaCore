package preforia.api;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messages {
	
	public static ArrayList<Player> cooldown = new ArrayList<>();
	public static ArrayList<Player> itemcooldown = new ArrayList<>();
	
	//Themecolors
	public static String color1 = "§a";
	public static String color2 = "§f";
	
	//Prefix
	public static String prefix = "§8[" + color1 + "Pref" + color2 + "oria" + "§8]" + " §f» §r§7";
	public static String error = "§c§lError §8» ";
	public static String alert = "§c§l! §f» §7";
	public static String onlyplayerscommand = "Only players can execute this command!";
	public static String nopermission = error + "You dont have permission for this command!";
	public static String offlineplayer = error + "Player not found!";
	public static String CANT_DO = "§c§lHey!§7 You cant do that here!";
	
	//Messages
	public static String gamemodeset = prefix + "Your gamemode has been set to " + color1;
	public static String etvoirikkoa = error + "You cant break blocks here!";
	public static String nohomes = error + "This server doesn't have any homes yet!";
	public static String nonametag = error + "There is no nametag group with this name!";
	
	public static void cooldown(Player player, String message) {
		if (!cooldown.contains(player)) {
			player.sendMessage(message);
			cooldown.add(player);
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					cooldown.remove(player);
				}
			}, 10*1);
		}
	}
	public static void Diamondcooldown(Player player) {
		if (!Main.DIAMOND_RADAR_COOLDOWN.contains(player)) {
			Main.DIAMOND_RADAR_COOLDOWN.add(player);
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					Main.DIAMOND_RADAR_COOLDOWN.remove(player);
				}
			}, 20*7);
		}
	}
	public static void worldguardCooldown(Player player) {
		cooldown(player, CANT_DO);
	}
	public static boolean itemUseCooldown(Player player) {
		if (!cooldown.contains(player)) {
			cooldown.add(player);
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					cooldown.remove(player);
				}
			}, 20*1);
			return true;
		} else {
			return false;
		}
	}
	
}
