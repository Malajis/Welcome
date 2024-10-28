package me.malajis.welcome;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Objects;

public class FakeButton {
    public void FakeSender() {
        String fake = "测试";
        //获取全体在线玩家
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        //向全体玩家发送欢迎按钮
        for (Player player : players) {
            player.spigot().sendMessage(getButton(fake));
        }
    }


    //返回假按钮
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