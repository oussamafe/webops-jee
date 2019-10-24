package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_comment ;
	
	private String contentC;
	
	@Temporal (TemporalType.DATE)
	private Date dateC;

	public int getId_comment() {
		return id_comment;
	}

	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}

	public String getContentC() {
		return contentC;
	}

	public void setContentC(String contentC) {
		this.contentC = contentC;
	}

	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public Comment(int id_comment, String contentC, Date dateC) {
		super();
		this.id_comment = id_comment;
		this.contentC = contentC;
		this.dateC = dateC;
	}

	public Comment() {
		super();
	}
	

}
