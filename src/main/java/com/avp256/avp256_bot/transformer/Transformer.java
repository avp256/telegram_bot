package com.avp256.avp256_bot.transformer;

public interface Transformer<FROM, TO> {
    TO transform(FROM chat);
}
