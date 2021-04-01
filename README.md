# JDA.Command.API
Do you code discord bot with JDA? This is the perfect command API for you!

### Registering

```java
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
```

###Example Method

```java
public class TestCommand extends Command {

    public TestCommand() {
        super("test", Permission.ADMINISTRATOR, "-test test");

        this.isMemberOnly = true;
    }

    @Override
    public void execute(Member member, Guild guild, TextChannel channel, String[] args) {
        if (member.getUser().isBot() || member.getUser().isFake()) return;

        if (args.length != 1) {
            channel.sendMessage(getUsage()).queue();
            return;
        }

        if (args[0].isEmpty() || !args[0].equalsIgnoreCase("test")) {
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
```
