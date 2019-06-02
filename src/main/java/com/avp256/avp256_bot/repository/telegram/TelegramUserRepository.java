package com.avp256.avp256_bot.repository.telegram;

import com.avp256.avp256_bot.model.telegram.TelegramUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "telegram_users", path = "users")
public interface TelegramUserRepository extends PagingAndSortingRepository<TelegramUser, Integer> {
}
