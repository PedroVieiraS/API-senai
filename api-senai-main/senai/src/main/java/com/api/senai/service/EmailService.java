package com.api.senai.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.senai.classes.Cliente;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailByJavaMail(Cliente cliente) {
        String from = "apifullstacksenai@gmail.com";
        String to = "y-al.s@hotmail.com";
        String subject = "Novo Cliente Cadastrado";
        String text = "Cliente " + cliente.getNome()
                + " cadastrado com sucesso!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);

        mailSender.send(message);
    }

    public void sendEmailByJakartaMail(Cliente cliente) {

        // Inserir o envio de email dentro de um bloco de try/catch
        // final String username = "apifullstacksenai@gmail.com";
        // final String password = "Senh4da@pi";

        // String from = "apifullstacksenai@gmail.com";
        // String to = "y-al.s@hotmail.com";
        // String subject = "Novo Cliente Cadastrado";
        // String text = "Cliente " + cliente.getNome()
        //         + " cadastrado com sucesso!";

        // Properties props = new Properties();
        // props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.smtp.host", "smtp.gmail.com");
        // props.put("mail.smtp.port", "587");

        // Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
        //     protected PasswordAuthentication getPasswordAuthentication() {
        //         return new PasswordAuthentication(username, password);
        //     }
        // });

        // try {
        //     MimeMessage message = new MimeMessage(
        //             session);
        //     message.setFrom(new InternetAddress(username));
        //     message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        //     message.setSubject(subject);
        //     message.setText(text);

        //     Transport.send(message);
        // } catch (MessagingException e) {
        //     throw new RuntimeException(e);
        // }
    }
}
