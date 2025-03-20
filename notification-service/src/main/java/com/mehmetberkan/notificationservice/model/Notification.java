package com.mehmetberkan.notificationservice.model;

import com.mehmetberkan.notificationservice.consumer.UserCreatedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@Builder
public class Notification {
    @Id
    private String id;
    private Long userId;
    private String email;
    private Boolean isSend;

    public static Notification eventToNotificationEntity(UserCreatedEvent event) {
        return Notification.builder()
                .userId(event.getId())
                .email(event.getEmail())
                .isSend(Boolean.TRUE)
                .build();
    }
}
