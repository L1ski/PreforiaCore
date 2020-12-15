package preforia.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import preforia.diamondradar.DaimondRadarCmd;
import preforia.diamondradar.DiamondRadarEvents;
import preforia.doublejump.JumpEvents;
import preforia.format.ChatFormat;
import preforia.format.JoinAndLeave;
import preforia.gamemode.GamemodeCmd;
import preforia.gamemode.GamemodeEvents;
import preforia.gamemode.GamemodeTab;
import preforia.homes.DelHomeCmd;
import preforia.homes.HomeCmd;
import preforia.homes.HomeTab;
import preforia.homes.HomesCmd;
import preforia.homes.SetHomeCmd;
import preforia.homes.TpHomeCmd;
import preforia.invsee.InvCmd;
import preforia.joinitems.CompassHandler;
import preforia.joinitems.ItemUseListener;
import preforia.joinitems.OnJoinEvent;
import preforia.lootboxes.DebugCmd;
import preforia.lootboxes.LootboxEvents;
import preforia.nametag.RegisterOnJoin;
import preforia.rtp.RtpCmd;
import preforia.sit.SitCmd;
import preforia.sit.SitRemove;
import preforia.spawn.DelSpawnCmd;
import preforia.spawn.SetSpawnCmd;
import preforia.spawn.TeleportOnJoin;
import preforia.tp.TpCmd;
import preforia.tpa.OnlineListener;
import preforia.tpa.TpaAcceptCmd;
import preforia.tpa.TpaCmd;
import preforia.tpa.TpaDenyCmd;
import preforia.vanish.VanishCmd;
import preforia.vanish.VanishEvents;
import preforia.worldguard.ProtectCmd;
import preforia.worldguard.ProtectEvents;

public class Main extends JavaPlugin implements PluginMessageListener {

	private static Main instance;
	public static ArrayList<Player> vanished = new ArrayList<>();
	public static ArrayList<Player> IGNORE_PLAYERS = new ArrayList<>();
	
	public static ArrayList<FallingBlock> LOOT_DROP = new ArrayList<>();
	
	public static ArrayList<Player> DIAMOND_RADAR = new ArrayList<>();
	public static ArrayList<Player> DIAMOND_RADAR_COOLDOWN = new ArrayList<>();
	
	public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	
	public static HashMap<Player, Chest> silentchest = new HashMap<Player, Chest>();
	
	public static HashMap<Player, Location> loc1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> loc2 = new HashMap<Player, Location>();
	
	@Override
	public void onEnable() {
		
		//Other
		System.out.println("PreforiaCore käynnistyy...");
		System.out.println("  §b                                                                ");
		System.out.println("      §b______               __               _                     ");
		System.out.println("      §b| ___ \\             / _|             (_)                   ");
		System.out.println("      §b| |_/ / _ __   ___ | |_   ___   _ __  _   __ _              ");
		System.out.println("      §b|  __/ | '__| / _ \\|  _| / _ \\ | '__|| | / _` |           ");
		System.out.println("      §b| |    | |   |  __/| |  | (_) || |   | || (_| |             ");
		System.out.println("      §b\\_|    |_|    \\___||_|   \\___/ |_|   |_| \\__,_|         ");
		System.out.println("  §b                                                                ");
		System.out.println("  §b                       §fBy §bL1ski                             ");
		System.out.println("  §b                                                                ");
		super.onEnable();
		Main.instance = this;
		PluginManager pm = getServer().getPluginManager();
		
		
		//gamemode
		getCommand("gamemode").setExecutor(new GamemodeCmd());
		getCommand("gamemode").setTabCompleter(new GamemodeTab());//Tabcompleter
		pm.registerEvents(new GamemodeEvents(), this);

		
		//sit
		getCommand("sit").setExecutor(new SitCmd());
		pm.registerEvents(new SitRemove(), this);
		
		
		//Double jupm
		pm.registerEvents(new JumpEvents(), this);
		

		//tp
		getCommand("tp").setExecutor(new TpCmd());
		
		
		//Join items
		pm.registerEvents(new ItemUseListener(this), this);
		pm.registerEvents(new OnJoinEvent(), this);
		pm.registerEvents(new CompassHandler(), this);

		
		//Format chat & tablist
		pm.registerEvents(new JoinAndLeave(), this);
		pm.registerEvents(new ChatFormat(), this);
		

		//Invsee
		getCommand("inv").setExecutor(new InvCmd());
		
		
		//Lootboxes
		getCommand("spawnloot").setExecutor(new DebugCmd());
		pm.registerEvents(new LootboxEvents(), this);
		
		
		//Rtp
		getCommand("rtp").setExecutor(new RtpCmd());
		

		//Vanish
		getCommand("vanish").setExecutor(new VanishCmd(this));
		pm.registerEvents(new VanishEvents(this), this);

		
		//Tpa
		getCommand("tpa").setExecutor(new TpaCmd());
		getCommand("tpaaccept").setExecutor(new TpaAcceptCmd());
		getCommand("tpadeny").setExecutor(new TpaDenyCmd());
		pm.registerEvents(new OnlineListener(), this);
		
		
		//Names
		pm.registerEvents(new RegisterOnJoin(), this);
		
		
		//Homes
		getCommand("home").setExecutor(new HomeCmd());
		getCommand("sethome").setExecutor(new SetHomeCmd());
		getCommand("delhome").setExecutor(new DelHomeCmd());
		getCommand("homes").setExecutor(new HomesCmd());
		getCommand("tphome").setExecutor(new TpHomeCmd());
		getCommand("delhome").setTabCompleter(new HomeTab());//Tabcompleter
		getCommand("home").setTabCompleter(new HomeTab());//Tabcompleter
		
		//Worldsecure
		pm.registerEvents(new ProtectEvents(), this);
		getCommand("protectworld").setExecutor(new ProtectCmd());
		
		
		//Diamond radar
		getCommand("diamondradar").setExecutor(new DaimondRadarCmd());
		pm.registerEvents(new DiamondRadarEvents(), this);

		//Spawn
		getCommand("setspawn").setExecutor(new SetSpawnCmd());
		getCommand("delspawn").setExecutor(new DelSpawnCmd());
		pm.registerEvents(new TeleportOnJoin(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("PreforiaCore has been disabled...");
	}
	
	public static Main getInstance(){
		return instance;
	}
	
    public static void sendPlayerToServer(Player player, String server) {
        try {
          ByteArrayOutputStream b = new ByteArrayOutputStream();
          DataOutputStream out = new DataOutputStream(b);
          out.writeUTF("Connect");
          out.writeUTF(server);
          player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
        } catch (IOException exception){
        	exception.printStackTrace();
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
      if (!channel.equals("BungeeCord")) {
        return;
      }
    }
	
}
