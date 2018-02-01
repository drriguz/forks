package com.riguz.commons.event;

public interface Handler<E, R> {
    R handle(E event);
}
