package com.innowise.task.service;

import com.innowise.task.components.BotCommands;
import com.innowise.task.components.Buttons;
import com.innowise.task.config.BotConfig;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class CryptocurrencyBot extends TelegramLongPollingBot implements BotCommands {
    private BotConfig botConfig;

    private String cryptoRate;

    private final String HELP_TEXT = "This bot will help to get crypto rate:\n\n" +
            "/start - start the bot\n" +
            "/current - get current rate crypto\n" +
            "/help - help menu";

    public void setCryptoRate(String cryptoRate) {
        this.cryptoRate = cryptoRate;
    }

    public CryptocurrencyBot(BotConfig botConfig) {
        this.botConfig = botConfig;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        long chatId = 0;
        long userId = 0; //это нам понадобится позже
        String userName = null;
        String receivedMessage;

        //если получено сообщение текстом
//        if (update.hasMessage()) {
//            chatId = update.getMessage().getChatId();
//            userId = update.getMessage().getFrom().getId();
//            userName = update.getMessage().getFrom().getFirstName();
//
//            if (update.getMessage().hasText()) {
//                receivedMessage = update.getMessage().getText();
//                botAnswerUtils(receivedMessage, chatId, userName);
//            }
//        } else {
            //если нажата одна из кнопок бота
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }
//        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage) {
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId, HELP_TEXT);
                break;
            case "/current":
                sendCurrentRate(chatId, cryptoRate);
                break;
            default:
                break;
        }
    }

    private void sendCurrentRate(long chatId, String cryptoRate) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(cryptoRate);
        message.setReplyMarkup(Buttons.inlineMarkup());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + userName + "! I'm a Telegram bot.");
        message.setReplyMarkup(Buttons.inlineMarkup());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
