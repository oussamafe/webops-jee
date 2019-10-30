package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Av_For")
public class Availability implements Serializable{
	
	private static final long serialVersionUID =1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date date;
	private int start_hour;
	private int end_hour;
	private boolean state;
	
	
	public boolean getState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}


	public Availability() {
		super();
	}

	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getStart_hour() {
		return start_hour;
	}

	public void setStart_hour(int start_hour) {
		this.start_hour = start_hour;
	}

	public int getEnd_hour() {
		return end_hour;
	}

	public void setEnd_hour(int end_hour) {
		this.end_hour = end_hour;
	}

	public int getId() {
		return id;
	}


}
