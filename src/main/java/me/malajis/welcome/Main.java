package me.malajis.welcome;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    static Main onEnable;

    @Override
    public void onEnable() {
        //启动文本
        getLogger().info("""

                 __      __       .__                              \s
                /  \\    /  \\ ____ |  |   ____  ____   _____   ____ \s
                \\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /    \\_/ __ \\\s
                 \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/\s
                  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >
                       \\/       \\/          \\/            \\/     \\/""");
        onEnable = this;
        //生成配置文件
        saveDefaultConfig();
        //注册命令
        getCommand("welcome").setExecutor(new Command());
        getCommand("welcome").setTabCompleter(new Command());
        //注册事件监听器
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        //卸载文本
        getLogger().info("""

                 __      __       .__                              \s
                /  \\    /  \\ ____ |  |   ____  ____   _____   ____ \s
                \\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\\s
                 \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/\s
                  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >
                       \\/       \\/          \\/            \\/     \\/""");
    }
}
