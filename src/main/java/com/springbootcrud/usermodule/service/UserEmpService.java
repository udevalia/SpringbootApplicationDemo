package com.springbootcrud.usermodule.service;

import java.util.List;

import com.springbootcrud.usermodule.model.UserEmp;

public interface UserEmpService
{
	List<UserEmp> getEmpuser();

	UserEmp getempuserbyID(int id);

	

	void delete(int id);

	String hardDeleteUser(int userId, boolean b);

	List<UserEmp> searchUsersByFilter(String firstName, String lastName, String pinCode, boolean sort);

	UserEmp updateUser(int userId);

	void save(UserEmp userDto);


}
