package me.malajis.welcome;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.Objects;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joinplayer = event.getPlayer();
        if (!joinplayer.hasPlayedBefore()) {
            String name = joinplayer.getName();
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            for (Player player : players) {
                if (player != joinplayer) {
                    player.spigot().sendMessage(getButton(name));
                }
            }
        }
    }

    private TextComponent getButton(String name) {
        String style = getConfigString("Main.button");
        String hover = getConfigString("Main.button_message").replace("[new]", name);
        String command = getConfigString("Main.button_command").replace("[new]", name);

        TextComponent welcomeMessage = new TextComponent(style);
        welcomeMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        welcomeMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

        return welcomeMessage;
    }

    private String getConfigString(String path) {
        return Objects.requireNonNull(Main.onEnable.getConfig().getString(path)).replace("&", "§");
    }
}