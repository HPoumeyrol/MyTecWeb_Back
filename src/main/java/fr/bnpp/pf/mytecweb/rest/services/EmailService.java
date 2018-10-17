package fr.bnpp.pf.mytecweb.rest.services;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.bnpp.pf.mytecweb.rest.models.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    public boolean sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException {
    	
    		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        
        String html = "";
        Template t;
        try {
        		freemarkerConfig.clearTemplateCache();
        		t = freemarkerConfig.getTemplate(mail.getTemplateName());
        		html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getTemplateVariables());
			
				
			helper.setTo(mail.getTo());
			helper.setCc(mail.getCc());
			helper.setBcc(mail.getBcc());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			
			// add inline ressources (img)
			if(mail.getTemplateInlineImgs() != null) {
				for(String imgName : mail.getTemplateInlineImgs() ) {
					System.out.println("image = " + imgName);
					ClassPathResource logoPath = new ClassPathResource(imgName);
		        		helper.addInline(imgName, logoPath);
				}
			}
			
			System.out.println("EmailService sendSimpleMessage : sending mail FROM " + mail.getFrom()  +  "   TO " + mail.getTo() );
			
			emailSender.send(message);
			
			
			System.out.println("mail sent.");
			return true;
        		
        } catch (Exception e) {
        		e.printStackTrace();
        		System.out.println("EmailService sendSimpleMessage : an error occurs");
        		return false;
        }
       
    }

}
