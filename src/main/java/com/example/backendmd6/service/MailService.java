package com.example.backendmd6.service;

import com.example.backendmd6.model.DataMailDTO;

import javax.mail.MessagingException;


public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
