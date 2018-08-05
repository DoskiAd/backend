package io.shifu.doskiad.services;

import io.shifu.doskiad.model.User;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void sendRegistrationEmail(User user);
}
