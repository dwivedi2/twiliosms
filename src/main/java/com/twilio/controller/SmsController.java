package com.twilio.controller;

import com.twilio.payload.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @PostMapping("/send-sms")
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        Message message = Message.creator(
                new PhoneNumber(smsRequest.getPhoneNumber()),
                new PhoneNumber(twilioPhoneNumber),
                smsRequest.getMessage()
        ).create();

        System.out.println("SMS sent with SID: " + message.getSid());
    }
}

