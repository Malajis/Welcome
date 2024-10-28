package me.malajis.welcome;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Command implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        //前缀和无权限字符串变量
        String prefix = Objects.requireNonNull(Main.onEnable.getConfig().getString("Messages.prefix")).replace("&", "§");
        String error = Objects.requireNonNull(Main.onEnable.getConfig().getString("Messages.error")).replace("&", "§");
        //命令权限判断和对应方法
        if (strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reload") && commandSender.hasPermission("welcome.reload")) {
                Main.onEnable.reloadConfig();
                commandSender.sendMessage((prefix + Main.onEnable.getConfig().getString("Messages.reload")).replace("&", "§"));
                return true;
            }
            if (strings[0].equalsIgnoreCase("try") && commandSender.hasPermission("welcome.try")) {
                FakeButton fakeButton = new FakeButton();
                fakeButton.FakeSender();
                return true;
            }
            commandSender.sendMessage(prefix + error);
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String
            s, String[] strings) {
        //补全
        if (strings.length == 1) {
            List<String> completions = new ArrayList<>();
            String input = strings[0];
            if ("reload".startsWith(input) && commandSender.hasPermission("welcome.reload")) {
                completions.add("reload");
            }
            if ("try".startsWith(input) && commandSender.hasPermission("welcome.try")) {
                completions.add("try");
            }
            return completions;
        }
        return null;
    }
}