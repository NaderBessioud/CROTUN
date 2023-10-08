package tn.esprit.CROTUN.messagenotifapi;

import java.util.HashMap;
import java.util.Map;

public class Mail {
	private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> Props;

    public Mail() {
    	Props = new HashMap<>();
    }

    
    public Mail(String from, String to, String subject, String content, Map<String, Object> Props) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.Props = Props;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


	public Map<String, Object> getProps() {
		return Props;
	}


	public void setProps(Map<String, Object> props) {
		Props = props;
	}

  
}
