package com.springbootcrud.usermodule.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.springbootcrud.usermodule.model.UserEmp;

public interface UserEmpDao 
{

	List<UserEmp> getEmpuser();
	
	UserEmp getempuserbyID(int id);
	
	void save(UserEmp emp);
	
	UserEmp delete(int id);

	List<UserEmp> searchUserByFilter(String firstName, String lastName, String pinCode,boolean sort);

	//void update(UserEmp emp);

	UserEmp findById(int userId);


	UserEmp update(int id);

//	UserEmp getempuserbymultiple(String name, String lastname, int pin);
	
}
