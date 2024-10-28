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
        //获取加入玩家并判断是否为新玩家
        Player joinplayer = event.getPlayer();
        if (!joinplayer.hasPlayedBefore()) {
            //获取新手玩家ID
            String name = joinplayer.getName();
            //获取全体在线玩家
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            //向全体玩家发送欢迎按钮
            for (Player player : players) {
                //排除新玩家
                if (player != joinplayer) {
                    player.spigot().sendMessage(getButton(name));
                }
            }
        }
    }

    //返回推送给在线玩家的欢迎按钮
    private TextComponent getButton(String name) {
        String style = Objects.requireNonNull(Main.onEnable.getConfig().getString("Main.button")).replace("&", "§");
        String hover = Objects.requireNonNull(Main.onEnable.getConfig().getString("Main.button_message")).replace("&", "§").replace("[new]", name);
        String text = Objects.requireNonNull(Main.onEnable.getConfig().getString("Main.welcome")).replace("[new]", name);
        TextComponent WelcomeMessage = new TextComponent(style);
        WelcomeMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        WelcomeMessage.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, text));
        return WelcomeMessage;
    }
}
