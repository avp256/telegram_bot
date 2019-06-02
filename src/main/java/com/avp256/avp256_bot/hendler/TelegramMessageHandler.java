package com.avp256.avp256_bot.hendler;

import com.avp256.avp256_bot.model.telegram.TelegramUpdate;

public interface TelegramMessageHandler {
    void handle(TelegramUpdate telegramUpdate);
}
