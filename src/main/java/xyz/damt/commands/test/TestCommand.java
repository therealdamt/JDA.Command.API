package xyz.damt.commands.test;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import xyz.damt.commands.Command;

public class TestCommand extends Command {

    public TestCommand() {
        super("test", Permission.ADMINISTRATOR, "-test test test");

        this.isMemberOnly = true;
    }

    @Override
    public void execute(Member member, Message message, Guild guild, TextChannel channel, String[] args) {
        if (member.getUser().isBot() || member.getUser().isFake()) return;

        if (args.length != 2) {
            channel.sendMessage(getUsage()).queue();
            return;
        }

        if (args[0].isEmpty() || !args[0].equalsIgnoreCase("test")) {
            channel.sendMessage("Invalid Value").queue();
            return;
        }

        if (args[1].isEmpty() || !args[1].equalsIgnoreCase("test")) {
            channel.sendMessage("Invalid Value").queue();
            return;
        }

        channel.sendMessage("Passed").queue();
    }

    @Override //can be removed
    public String getPrefix() {
        return "-";
    }

    @Override //can be removed
    public String getNoPermissionMessage() {
        return "You don't have the permission %permission% to do this command!";
    }
}
