package com.mehmetberkan.addressservice.config.kafka;

import com.mehmetberkan.addressservice.consumer.UserCreatedEvent;
import com.mehmetberkan.addressservice.model.Address;
import com.mehmetberkan.addressservice.service.AddressService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventConsumer {

    private final AddressService addressService;

    public UserCreatedEventConsumer(AddressService addressService) {
        this.addressService = addressService;
    }

    @KafkaListener(topics = "${kafka.topics.user-created.topic}",
            groupId = "${kafka.topics.user-created.consumerGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeCreatedUserEvent(@Payload UserCreatedEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {
        System.out.println("UserCreatedEventConsumer.consumeApprovalRequestResultedEvent consumed EVENT: " + eventData +
                " from partition: " + consumerRecord.partition() +
                " with offset: " + consumerRecord.offset() +
                " thread: " + Thread.currentThread().getName() +
                " for message key: " + consumerRecord.key());

        Address address = UserCreatedEvent.getAddressEntityFromEvent(eventData);

        addressService.save(address);
    }
}
