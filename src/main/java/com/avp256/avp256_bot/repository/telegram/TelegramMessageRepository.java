package com.avp256.avp256_bot.repository.telegram;

import com.avp256.avp256_bot.model.telegram.TelegramMessage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "telegram_messages", path = "messages")
public interface TelegramMessageRepository extends PagingAndSortingRepository<TelegramMessage, Integer> {
}
