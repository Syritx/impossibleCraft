package syritx.main;
import org.bukkit.plugin.java.JavaPlugin;

public class Launcher extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new Events(), this);
	}
}
