package com.riguz.gags.event;

public interface Handler<E, R> {
    R handle(E event);
}
