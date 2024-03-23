package com.innowise.task.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/3percent", "get rate to change on 3 percent"),
            new BotCommand("/5percent", "get rate to change on 5 percent"),
            new BotCommand("/current", "current rate")
    );
}
