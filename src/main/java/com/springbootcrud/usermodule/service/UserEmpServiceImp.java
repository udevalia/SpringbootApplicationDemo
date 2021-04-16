package com.springbootcrud.usermodule.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springbootcrud.usermodule.dao.UserEmpDao;
import com.springbootcrud.usermodule.model.UserEmp;

@Service
public class UserEmpServiceImp implements UserEmpService {


	@Autowired
	UserEmpDao userEmpDao;
	
	@Transactional
	@Override
	public List<UserEmp> getEmpuser() {
		return userEmpDao.getEmpuser();
	}

	@Transactional
	@Override
	public UserEmp getempuserbyID(int id) {
		// TODO Auto-generated method stub
		return userEmpDao.getempuserbyID(id);
	}

	@Transactional
	@Override
	public void save(UserEmp userDto)
	{
		
		/*
		 * UserEmp user =
		 * UserEmp.builder().name(userDto.getName()).last_name(userDto.getLast_name())
		 * .email(userDto.getEmail()).dob(userDto.getDob()).gender(userDto.getGender()).
		 * department(userDto.getDepartment()).doj(userDto.getDoj())
		 * .pin_code(userDto.getPin_code()).build();
		 */
		
		 userEmpDao.save(userDto);
		//return userEmp;
	}
	
	@Transactional
	@Override
	public void delete(int id) {
		try {
			System.out.println("Deleting the record for ID : "+id);
			userEmpDao.delete(id);
			System.out.println("Record is deleted successfully");
		} catch (Exception e) {
			System.out.println("Problem while deleting the record....");
		}

	}

	@Transactional
	@Override
	public List<UserEmp> searchUsersByFilter(String firstName, String lastName, String pinCode,boolean sort) {
		return userEmpDao.searchUserByFilter(firstName, lastName, pinCode,false);
	}

	@Transactional
	@Override
	public String hardDeleteUser(int userId, boolean isHardDelete) {
		// TODO Auto-generated method stub
		UserEmp user = userEmpDao.findById(userId);
		if(user!=null){
			//UserEmp findUser = (UserEmp)user.get();
			if(isHardDelete){
				userEmpDao.delete(userId);
				return "User is hard deleted successfully.";
			}else{
				user.setStatus(false);
				userEmpDao.update(userId);
				return "User is update successfully.";
			}
		}
		return "No such user found.";
	}

	
	@Transactional
	@Override
	public UserEmp updateUser(int userId) {
		UserEmp user = userEmpDao.findById(userId);
		if(user!=null) {
			System.out.println("User found so update the details");
			user=userEmpDao.update(userId);
			return user;
		}
		else
		{
			System.out.println("User did not found..");
			return user;
			//return "No Such User Found";
		}
	}
	
}
