package com.mehmetberkan.notificationservice.consumer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserCreatedEvent {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean status;

    public UserCreatedEvent() {
    }

    public UserCreatedEvent(Long id, String firstName, String lastName, String email, Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }
}
