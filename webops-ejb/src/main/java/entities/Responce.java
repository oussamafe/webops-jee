package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Responce implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String reponce;
	/**
	 * if reponce is true then is valide take true else false
	 */
	private boolean IsValid;

	@JsonBackReference(value="questionReponce-movement")
	@ManyToOne
	private Question questionReponce;

	public Responce() {
		super();
	}

	public String getReponce() {
		return reponce;
	}

	public void setReponce(String reponce) {
		this.reponce = reponce;
	}

	public boolean IsValid() {
		return IsValid;
	}

	public void setIsValid(boolean isValid) {
		IsValid = isValid;
	}

	public Question getQuestionReponce() {
		return questionReponce;
	}

	public void setQuestionReponce(Question questionReponce) {
		this.questionReponce = questionReponce;
	}

	public int getId() {
		return id;
	}

}
