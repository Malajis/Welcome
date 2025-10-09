package me.malajis.welcome;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Command implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        String prefix = getConfigString("Messages.prefix");
        String error = getConfigString("Messages.error");

        if (strings.length > 0) {
            if (strings[0].equalsIgnoreCase("reload") && commandSender.hasPermission("welcome.reload")) {
                Main.onEnable.reloadConfig();
                commandSender.sendMessage(prefix + getConfigString("Messages.reload"));
                return true;
            }
            if (strings[0].equalsIgnoreCase("try") && commandSender.hasPermission("welcome.try")) {
                FakeButton fakeButton = new FakeButton();
                fakeButton.FakeSender();
                return true;
            }
            if (strings[0].equalsIgnoreCase("msg") && commandSender.hasPermission("welcome.msg")) {
                if(strings.length>1){
                    String newPlayerName = strings[1];
                    String text = getConfigString("Main.welcome").replace("[new]", newPlayerName);
                    Player player = (Player) commandSender;
                    player.chat(text);
                } else{
                    commandSender.sendMessage(prefix + error);
                }
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
        List<String> completions = new ArrayList<>();
        if (strings.length == 1) {
            String input = strings[0];
            if ("reload".startsWith(input) && commandSender.hasPermission("welcome.reload")) {
                completions.add("reload");
            }
            if ("try".startsWith(input) && commandSender.hasPermission("welcome.try")) {
                completions.add("try");
            }
            if ("msg".startsWith(input) && commandSender.hasPermission("welcome.msg")) {
                completions.add("msg");
            }
            return completions;
        }else if(strings.length == 2 && strings[0].equalsIgnoreCase("msg") && commandSender.hasPermission("welcome.msg")){
            completions.add("[玩家ID]");
            return completions;
        }
        return null;
    }

    private String getConfigString(String path) {
        return Objects.requireNonNull(Main.onEnable.getConfig().getString(path)).replace("&", "§");
    }
}