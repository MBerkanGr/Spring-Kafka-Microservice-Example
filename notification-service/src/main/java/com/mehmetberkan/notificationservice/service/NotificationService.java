package com.mehmetberkan.notificationservice.service;

import com.mehmetberkan.notificationservice.model.Notification;
import com.mehmetberkan.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(Notification notification) {
        Notification savedEntity = notificationRepository.save(notification);
        System.out.println("The notification has been successfully saved ! Notification : " + notification);
    }

}
