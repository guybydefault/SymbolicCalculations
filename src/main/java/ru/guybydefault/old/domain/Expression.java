package ru.guybydefault.old.domain;

import ru.guybydefault.old.services.Serializer;

public interface Expression {
    String serialize(Serializer serializer);
}
