package com.mehmetberkan.userservice.service;

import com.mehmetberkan.userservice.config.kafka.producer.KafkaProducer;
import com.mehmetberkan.userservice.config.kafka.properties.UserCreatedTopicProperties;
import com.mehmetberkan.userservice.dto.UserCreateRequest;
import com.mehmetberkan.userservice.dto.UserCreatedPayload;
import com.mehmetberkan.userservice.model.User;
import com.mehmetberkan.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    public User create(UserCreateRequest userCreateRequest) {
        User user = User.getUser(userCreateRequest);
        User savedUser = userRepository.save(user);

        UserCreatedPayload payload = UserCreatedPayload.getUserCreatedPayload(savedUser, userCreateRequest.getAddressText());

        kafkaProducer.sendMessage(savedUser.getId().toString(), payload);
        return savedUser;
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

}
