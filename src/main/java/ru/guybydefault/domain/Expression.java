package ru.guybydefault.domain;

import ru.guybydefault.services.Serializer;

public interface Expression {
    String serialize(Serializer serializer);
}
