package vb.$titlesend;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.java.*;
import org.bukkit.util.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;
	private static Object localVariableScope = new Object();

	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		getServer().getPluginManager().registerEvents(this, this);
		Object localVariableScope = new Object();
		saveDefaultConfig();
		try {
			Bukkit.getConsoleSender().sendMessage(PluginMain
					.color(PluginMain.color("&3&lTitlesend has been &2&lEnabled! &4[Made by ZeeKungYT/Sirawat]")));
			Bukkit.getConsoleSender().sendMessage(PluginMain.color(PluginMain.color("&2&lPlugin version: 1.12")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
	}

	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		if (command.getName().equalsIgnoreCase("titlereload")) {
			try {
				Object localVariableScope = new Object();
				PluginMain.getInstance().reloadConfig();
				commandSender.sendMessage(PluginMain.color("&3&l[Titlesend] &2Reloading Config!"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (command.getName().equalsIgnoreCase("alert")) {
			try {
				Object localVariableScope = new Object();
				if (PluginMain.createList(commandArgs).isEmpty()) {
					commandSender.sendMessage(PluginMain.color(PluginMain.color("&3Usage : /alert <message>")));
				}
				if (!PluginMain.createList(commandArgs).isEmpty()) {
					Bukkit.broadcastMessage(PluginMain.color(
							((PluginMain.color(String.valueOf(PluginMain.getInstance().getConfig().get("prefix")))
									+ PluginMain.color(" &l"))
									+ PluginMain.color(String.join(" ",
											(List<String>) (Object) PluginMain.createList(commandArgs))))));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (command.getName().equalsIgnoreCase("titlesend")) {
			try {
				Object localVariableScope = new Object();
				if (PluginMain.createList(commandArgs).isEmpty()) {
					commandSender.sendMessage(PluginMain.color("Send title to everyone"));
					commandSender.sendMessage(PluginMain.color("Usage: /titlesend <message>"));
				} else {
					for (Object loopValue0 : PluginMain.createList(Bukkit.getOnlinePlayers())) {
						((org.bukkit.entity.Player) loopValue0).sendTitle(
								PluginMain.color(PluginMain.color(
										String.join(" ", (List<String>) (Object) PluginMain.createList(commandArgs)))),
								PluginMain.color(((java.lang.String) null)), ((int) 5d),
								((int) (((Number) PluginMain.getInstance().getConfig().get("stay")).doubleValue()
										* 20d)),
								((int) 5d));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static void procedure(String procedure, List<?> procedureArgs) throws Exception {
	}

	public static Object function(String function, List<?> functionArgs) throws Exception {
		return null;
	}

	public static List<Object> createList(Object obj) {
		List<Object> list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static String color(String string) {
		return string != null ? ChatColor.translateAlternateColorCodes('&', string) : null;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}
}
