package com.avp256.avp256_bot.service;

import com.avp256.avp256_bot.model.telegram.TelegramChat;
import com.avp256.avp256_bot.model.telegram.TelegramMessage;
import com.avp256.avp256_bot.model.telegram.TelegramUpdate;
import com.avp256.avp256_bot.model.telegram.TelegramUser;
import com.avp256.avp256_bot.repository.telegram.TelegramChatRepository;
import com.avp256.avp256_bot.repository.telegram.TelegramMessageRepository;
import com.avp256.avp256_bot.repository.telegram.TelegramUpdateRepository;
import com.avp256.avp256_bot.repository.telegram.TelegramUserRepository;
import com.avp256.avp256_bot.transformer.Transformer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TelegramUpdateService {
    Transformer<Update, TelegramUpdate> updateToTelegramUpdateTransformer;
    Transformer<Message, TelegramMessage> messageToTelegramMessageTransformer;
    Transformer<User, TelegramUser> userToTelegramUserTransformer;
    Transformer<Chat, TelegramChat> chatToTelegramChatTransformer;
    TelegramUpdateRepository telegramUpdateRepository;
    TelegramMessageRepository telegramMessageRepository;
    TelegramUserRepository telegramUserRepository;
    TelegramChatRepository telegramChatRepository;

    public TelegramUpdate save(Update update) {
        TelegramUser telegramUser = telegramUserRepository.findById(update.getMessage().getFrom().getId())
                .orElseGet(() ->
                        telegramUserRepository.save(
                                userToTelegramUserTransformer.transform(update.getMessage().getFrom())
                        )
                );

        TelegramChat telegramChat = telegramChatRepository.findById(update.getMessage().getChat().getId())
                .orElseGet(() ->
                        {
                            TelegramChat transformedChat = chatToTelegramChatTransformer.transform(update.getMessage().getChat());
                            transformedChat.setUser(telegramUser);
                            return telegramChatRepository.save(transformedChat);
                        }
                );

        TelegramMessage telegramMessage = messageToTelegramMessageTransformer.transform(update.getMessage());
        telegramMessage.setFrom(telegramUser);
        telegramMessage.setChat(telegramChat);
        TelegramMessage savedTelegramMessage = telegramMessageRepository.save(telegramMessage);

        TelegramUpdate telegramUpdate = updateToTelegramUpdateTransformer.transform(update);
        telegramUpdate.setMessage(savedTelegramMessage);
        return telegramUpdateRepository.save(telegramUpdate);
    }
}
