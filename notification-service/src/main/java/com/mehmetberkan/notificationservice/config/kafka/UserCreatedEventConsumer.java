package com.mehmetberkan.notificationservice.config.kafka;

import com.mehmetberkan.notificationservice.consumer.UserCreatedEvent;
import com.mehmetberkan.notificationservice.model.Notification;
import com.mehmetberkan.notificationservice.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventConsumer {

    private final NotificationService notificationService;

    public UserCreatedEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "${kafka.topics.user-created.topic}",
            groupId = "${kafka.topics.user-created.consumerGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeCreatedUserEvent(@Payload UserCreatedEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {

        System.out.println("UserCreatedEventConsumer.consumeApprovalRequestResultedEvent consumed EVENT : " + eventData +
                " from partition: " + consumerRecord.partition() +
                " with offset: " + consumerRecord.offset() +
                " thread: " + Thread.currentThread().getName() +
                " for message key: " + consumerRecord.key());

        Notification notification = Notification.eventToNotificationEntity(eventData);

        notificationService.save(notification);
    }

}
