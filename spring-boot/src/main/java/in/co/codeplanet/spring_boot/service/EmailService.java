package in.co.codeplanet.spring_boot.service;

import in.co.codeplanet.spring_boot.bean.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(EmailDetails emailDetails){
       try{
           SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
           simpleMailMessage.setFrom("mr.vinod615@gmail.com");
           simpleMailMessage.setTo(emailDetails.getRecipient());
           simpleMailMessage.setSubject(emailDetails.getSubject());
           simpleMailMessage.setText(emailDetails.getBody());
           javaMailSender.send(simpleMailMessage);
           return "sent successfully";
       }
       catch (Exception e){
           return "Something went wrong.";
       }
    }
}
