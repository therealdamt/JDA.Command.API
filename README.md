# JDA.Command.API
Do you code discord bot with JDA? This is the perfect command API for you!

### Credits
This API was created by damt.

Telegram -> https://t.me/therealdamt
Discord -> damt#3333

### Rights
Any user has full rights to resell bots using this API, and using it.
However, the user may not claim this API as his own.

### Registering

```java
public class Bot { //The class "Bot" must be your main class!

    private final JDA jda;

    @SneakyThrows
    public Bot() {
        jda = JDABuilder.createDefault("your token").build();

        Arrays.asList(
                new TestCommand()
        ).forEach(command -> command.register(jda, true)); //You can do it like this or you can identify each command and register it seperately
    }

    public static void main(String[] args) {
        new Bot();
    }
```

### Example Method

```java
public class TestCommand extends Command {

    public TestCommand() {
        super("test", Permission.ADMINISTRATOR, "-test test");

        this.isMemberOnly = true; //sets if the command is member only
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
