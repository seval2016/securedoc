package com.getarrayz.securedoc.event;

import com.getarrayz.securedoc.entity.UserEntity;
import com.getarrayz.securedoc.enumaration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private UserEntity user;
    private EventType type;
    private Map<?,?> data;
}
