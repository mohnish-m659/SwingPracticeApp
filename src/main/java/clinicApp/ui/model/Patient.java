package clinicApp.ui.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = Patient.PATIENT_ALL, query = "select p from Patient p"),
	@NamedQuery(name = Patient.PATIENT_BY_ID, query = "select p from Patient p where p.id = :patient_id"),
	@NamedQuery(name = Patient.PATIENT_COUNT, query = "select count(*) from Patient")
})
public class Patient {
	
	public static final String PATIENT_ALL = "getAllPatients";
	public static final String PATIENT_BY_ID = "getPatientById";
	public static final String PATIENT_COUNT = "getPatientCount";
	
	public enum Gender{
		MALE, FEMALE, NOT_SELECTED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String firstName;
	String lastName;
	String age;
	
	@Enumerated(EnumType.ORDINAL)
	Gender gender;
	LocalDate dateOfBirth;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	

}
