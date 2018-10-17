package fr.bnpp.pf.mytecweb.rest.models;

import java.util.List;
import java.util.Map;

public class Mail {

    private String from;
    private String to;
    private String cc;
    private String bcc;
    
    private String subject;
    private List<Object> attachments;
    private String templateName; 
    private Map<String, Object> templateVariables;
    private List<String> templateInlineImgs;

    public Mail() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

       
    
    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Map<String, Object> getTemplateVariables() {
		return templateVariables;
	}

	public void setTemplateVariables(Map<String, Object> templateVariables) {
		this.templateVariables = templateVariables;
	}

	public List<String> getTemplateInlineImgs() {
		return templateInlineImgs;
	}

	public void setTemplateInlineImgs(List<String> templateInlineImgs) {
		this.templateInlineImgs = templateInlineImgs;
	}

	
	
	
}
