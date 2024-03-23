package com.innowise.task.components;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Start");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");
    private static final InlineKeyboardButton PERCENT_3_BUTTON = new InlineKeyboardButton("Get rate on 3 percent");
    private static final InlineKeyboardButton PERCENT_5_BUTTON = new InlineKeyboardButton("Get rate on 5 percent");
    private static final InlineKeyboardButton CURRENT_RATE_BUTTON = new InlineKeyboardButton("Current rate");

    public static InlineKeyboardMarkup inlineMarkup() {
        START_BUTTON.setCallbackData("/start");
        HELP_BUTTON.setCallbackData("/help");
        PERCENT_3_BUTTON.setCallbackData("/3percent");
        PERCENT_5_BUTTON.setCallbackData("/5percent");
        CURRENT_RATE_BUTTON.setCallbackData("/current");

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON, PERCENT_3_BUTTON,
                PERCENT_5_BUTTON, CURRENT_RATE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}
