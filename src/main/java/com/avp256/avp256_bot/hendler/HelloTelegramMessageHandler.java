package com.avp256.avp256_bot.hendler;

import com.avp256.avp256_bot.model.telegram.TelegramUpdate;
import com.avp256.avp256_bot.model.telegram.TelegramUser;
import com.avp256.avp256_bot.service.Avp256Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HelloTelegramMessageHandler implements TelegramMessageHandler {
    Avp256Bot avp256Bot;

    @Override
    public void handle(TelegramUpdate telegramUpdate) {
        if (!telegramUpdate.getMessage().getText().startsWith(Avp256Bot.START_COMMAND)
                && !telegramUpdate.getMessage().getText().equals(Avp256Bot.HELLO_BUTTON)) {
            return;
        }
        Long chatId = telegramUpdate.getMessage().getChat().getId();
        TelegramUser user = telegramUpdate.getMessage().getFrom();
        String text = Stream.of("Hello,", user.getLastName(), user.getFirstName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        avp256Bot.sendTextMessage(chatId, text);
    }
}
