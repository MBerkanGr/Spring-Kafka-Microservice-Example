package com.mehmetberkan.addressservice.consumer;

import com.mehmetberkan.addressservice.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class UserCreatedEvent {
    private Long id;
    private String addressText;

    public UserCreatedEvent() {}

    public UserCreatedEvent(Long id, String addressText) {
        this.id = id;
        this.addressText = addressText;
    }

    public static Address getAddressEntityFromEvent(UserCreatedEvent event) {
        return Address.builder()
                .userId(event.getId())
                .addressText(event.getAddressText())
                .build();
    }
}
