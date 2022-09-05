package com.payman.event.listener;

import com.payman.event.ResetPasswordSendEvent;
import com.payman.service.CustomerService;
import com.payman.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResetPasswordSendEventListener implements ApplicationListener<ResetPasswordSendEvent> {
    @Autowired
    private CustomerService customerService;

    @Autowired
    AES aes;

    @Override
    public void onApplicationEvent(ResetPasswordSendEvent event) {

        // Create the verification token for the user with the link
        String token = UUID.randomUUID().toString();

        customerService.savePasswordResetToken(event.getMobile(),token);

        // send mail to user
        String url = event.getApplicationUrl() + "/customer/reset/"+token;
        System.out.println(url);
    }
}
