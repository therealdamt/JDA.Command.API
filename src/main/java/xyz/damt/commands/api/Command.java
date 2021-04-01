package xyz.damt.commands.api;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Command extends ListenerAdapter {

    public abstract void execute(Member member, Guild guild, TextChannel channel, String[] args);

    private final String name;
    private final Permission permission;
    private final String usage;

    public Command(String name, Permission permission, String usage) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
    }


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (!args[0].equalsIgnoreCase(getPrefix() + name)) {
            return;
        }

        List<String> arguments = new ArrayList<>();
        for (String arg : args) {
            arguments.add(arg);
        }

        arguments.remove(0);
        args = arguments.toArray(new String[0]);

        if (permission != null && !Objects.requireNonNull(event.getMember()).hasPermission(permission)) {
            event.getChannel().sendMessage(getNoPermissionMessage().replace("%permission%", permission.getName())).queue();
            return;
        }

        execute(event.getMember(), event.getGuild(), event.getChannel(), args);
    }

    public Permission getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getNoPermissionMessage() {
        return "You must have the permission %permission% to do this command!";
    }

    public String getPrefix() {
        return "-";
    }

    public void register(JDA jda, boolean log) {
        jda.addEventListener(this);

        if (log) {
            System.out.println("The command " + getName() + " has been registered!");
        }

    }
}
