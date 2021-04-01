package xyz.damt;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import xyz.damt.commands.test.TestCommand;

import java.util.Arrays;

public class Bot {

    private final JDA jda;

    @SneakyThrows
    public Bot() {
        jda = JDABuilder.createDefault("your token").build();

        Arrays.asList(
                new TestCommand()
        ).forEach(command -> command.register(jda, true));
    }

    public static void main(String[] args) {
        new Bot();
    }

}
