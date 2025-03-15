package com.mehmetberkan.userservice.config.kafka.producer;

import com.mehmetberkan.userservice.config.kafka.properties.UserCreatedTopicProperties;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserCreatedTopicProperties topicProperties;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, UserCreatedTopicProperties topicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicProperties = topicProperties;
    }

    public void sendMessage(String key, Object payload) {
        Message<Object> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topicProperties.getTopicName())
                .setHeader(KafkaHeaders.KEY, key)
                .build();

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(message);

        future.thenAccept(result -> {
            RecordMetadata metadata = result.getRecordMetadata();
            System.out.println(
                    "Message sent successfully to topic: " + metadata.topic() +
                    " partition: " + metadata.partition() +
                    " at offset: " + metadata.offset());
        }).exceptionally(ex -> {
            System.err.println("Failed to send message: " + ex.getMessage());
            return null;
        });
    }
}
