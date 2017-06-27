package jlquiros.blog.model;

import java.util.Date;

public class OutputMessage {

	private String from;
    private String message;

    private Date time = new Date();

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public OutputMessage(String from, String message) {
		super();
		this.from = from;
		this.message = message;
	}
    
    

}