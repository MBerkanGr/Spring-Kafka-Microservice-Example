package com.mehmetberkan.userservice.dto;

import com.mehmetberkan.userservice.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserCreatedPayload {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String addressText;
    private Date createdAt;
    private Date updatedAt;
    private Boolean status;

    public static UserCreatedPayload getUserCreatedPayload(User user, String addressText) {
        return UserCreatedPayload.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .addressText(addressText)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .status(user.getStatus())
                .build();
    }
}
