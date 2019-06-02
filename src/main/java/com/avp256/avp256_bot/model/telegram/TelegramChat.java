package com.avp256.avp256_bot.model.telegram;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramChat {
    @Id
    Long id;
    LocalDateTime creationDate;
    Boolean userChat;
    Boolean groupChat;
    Boolean channelChat;
    Boolean superGroupChart;
    @ManyToOne
    TelegramUser user;
}
