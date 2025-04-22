package ru.aplk.dnd_service.additions.types.functions;

import java.util.List;

public interface FindAll<ObjectType> {
    List<ObjectType> apply();
}
