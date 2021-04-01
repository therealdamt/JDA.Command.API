package xyz.damt.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import xyz.damt.commands.api.Command;

public class TestCommand extends Command {

    public TestCommand() {
        super("test", Permission.ADMINISTRATOR, "-test lol");
    }

    @Override
    public void execute(Member member, Guild guild, TextChannel channel, String[] args) {
        if (member.getUser().isBot() || member.getUser().isFake()) return;

        if (args.length != 1) {
            channel.sendMessage(getUsage()).queue();
            return;
        }

        if (args[0].isEmpty() || !args[0].equalsIgnoreCase("lol")) {
            channel.sendMessage("Invalid Value").queue();
            return;
        }

        channel.sendMessage("Passed").queue();
    }

    @Override
    public String getPrefix() {
        return "-";
    }

    @Override
    public String getNoPermissionMessage() {
        return "You don't have the permission %permission% to do this command!";
    }
}
