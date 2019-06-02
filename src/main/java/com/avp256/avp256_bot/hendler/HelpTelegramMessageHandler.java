package com.avp256.avp256_bot.hendler;

import com.avp256.avp256_bot.model.telegram.TelegramUpdate;
import com.avp256.avp256_bot.model.telegram.TelegramUser;
import com.avp256.avp256_bot.service.Avp256Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HelpTelegramMessageHandler implements TelegramMessageHandler {
    Avp256Bot avp256Bot;

    @Override
    public void handle(TelegramUpdate telegramUpdate) {
        if (!telegramUpdate.getMessage().getText().startsWith(Avp256Bot.HELP_BUTTON)) {
            return;
        }
        Long chatId = telegramUpdate.getMessage().getChat().getId();
        String text;
        if (Objects.isNull(telegramUpdate.getMessage().getFrom().getPerson())) {
            text = "Help service is allowed only for authorized users";
        } else {
            text = "We will help you";
        }
        TelegramUser user = telegramUpdate.getMessage().getFrom();
        avp256Bot.sendTextMessage(chatId, text);
    }
}
