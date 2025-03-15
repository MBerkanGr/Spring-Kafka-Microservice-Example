package com.mehmetberkan.userservice.dto;

import com.mehmetberkan.userservice.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponseDto address;

    public static UserResponse getUserResponseWithAddress(User user, AddressResponseDto address) {
        UserResponse response = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(address)
                .build();
        response.setId(user.getId());
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
