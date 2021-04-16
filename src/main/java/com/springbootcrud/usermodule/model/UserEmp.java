package com.springbootcrud.usermodule.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="user")

public class UserEmp 
{ 
	
	 public UserEmp() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotBlank(message = "First name is mandatory")
	@Column
	private String name;
	
	@NotBlank(message = "Last name is mandatory")
	@Column(name="last_name")
	private String last_name;
	
	@NotBlank(message = "Gender is mandatory")
	@Column
	private String  gender;
	
	@NotBlank(message=" Please enter email")
	@Email(message = "Enter a valid email")
	@Column
	private String email;
	
	@NotNull(message = "DOB is mandatory")
	@Column
	private Date dob;
	
	@NotNull(message = "DOB is mandatory")
	@Column
	private Date doj;
	
	@NotBlank(message = "Department is mandatory")
	@Column
	private String department;

	@NotNull(message = "Pin code is mandatory")
	@Column(name="pin_code")
	private String pin_code;
	
	@Column
	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
