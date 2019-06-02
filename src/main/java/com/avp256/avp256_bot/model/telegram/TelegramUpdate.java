package com.avp256.avp256_bot.model.telegram;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUpdate {
    @Id
    Integer id;
    LocalDateTime creationDate;
    @OneToOne
    TelegramMessage message;
}
