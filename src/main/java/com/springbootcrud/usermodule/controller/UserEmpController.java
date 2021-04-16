package com.springbootcrud.usermodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootcrud.usermodule.dao.ResponseDto;
import com.springbootcrud.usermodule.exception.UserServiceException;
import com.springbootcrud.usermodule.model.UserEmp;
import com.springbootcrud.usermodule.service.UserEmpService;

@RestController
@RequestMapping("/api")
public class UserEmpController
{
	@Autowired
	private UserEmpService userEmpService;

	@GetMapping("/test")
	public String Test()
	{
		return"Test Demo";
	}

	@GetMapping("/userdetails")
	public List<UserEmp> getEmp()
	{
		return userEmpService.getEmpuser();
	}


	@PostMapping("/register") 
	public UserEmp save(@RequestBody UserEmp userEmp) 
	{
		if(userEmp.getId()==null || userEmp.getId()==0) 
		{
			userEmpService.save(userEmp); 
		} return userEmp;
	}

	/*
	 * @PostMapping("/register") public ResponseEntity<ResponseDto>
	 * save(@RequestBody UserDto userEmp) { ResponseDto responseDto =
	 * userEmpService.save(userEmp); if (responseDto.getErrors() != null) { return
	 * ResponseEntity.badRequest().body(responseDto); } return
	 * ResponseEntity.ok(responseDto); }
	 */


	//localhost:8080/api/search?name=Umesh&lastName=Devalia&pin=400001
	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchUser(@RequestParam(value = "name", required = false) String firstName,
			@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "pin_code", required = false) String pinCode) 
	{
		return ResponseEntity
				.ok(ResponseDto.builder().data(userEmpService.searchUsersByFilter(firstName, lastName, pinCode,false)).build());
	}

	@GetMapping("/sort")
	public ResponseEntity<ResponseDto> sortUser() 
	{
		return ResponseEntity
				.ok(ResponseDto.builder().data(userEmpService.searchUsersByFilter(null, null, null,true)).build());
	}
	

 
	
	/*
	 * @PutMapping public ResponseEntity<ResponseDto> editUser(@RequestBody UserEmp
	 * userDto) throws UserServiceException { ResponseDto responseDto =
	 * userEmpService.editUser(userDto); if (responseDto.getErrors() != null) {
	 * return ResponseEntity.badRequest().body(responseDto); } return
	 * ResponseEntity.ok(responseDto); }
	 */

	@GetMapping("/userdetail/{id}")
	public UserEmp getEmpbyID(@PathVariable int id) throws UserServiceException
	{
		UserEmp empuserbyID= userEmpService.getempuserbyID(id);
		if(empuserbyID==null) {
			throw new UserServiceException("Employee with id :"+ id+" not found");
		}
		return empuserbyID;
	}



	@DeleteMapping("/userdetail/{id}") 
	public String delete(@PathVariable int id)throws UserServiceException 
	{
		userEmpService.delete(id); 
		return "User deleted..";
	}

	/*
	 * @PutMapping("/updateuserdetail/{id}") public void update(@RequestBody int
	 * userEmp) { userEmpService.update(userEmp); //return userEmp; }
	 */


	@PutMapping("/updateuserdetail/{userId}")
	public ResponseEntity<UserEmp> updates(@PathVariable("userId") int userId)
	{
		UserEmp status = userEmpService.updateUser(userId); 
		return new ResponseEntity<UserEmp>(status, HttpStatus.OK); 
	}



	/*
	 * @PutMapping public ResponseEntity<ResponseDto> editUser(@RequestBody UserDto
	 * userDto) throws UserServiceException { ResponseDto responseDto =
	 * userEmpService.editUser(userDto); if (responseDto.getErrors() != null) {
	 * return ResponseEntity.badRequest().body(responseDto); } return
	 * ResponseEntity.ok(responseDto); }
	 */

	@DeleteMapping("/hardDelete/{userId}")
	public ResponseEntity<String> hardDeleteUser(@PathVariable("userId") int userId){
		String status = userEmpService.hardDeleteUser(userId, true);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@DeleteMapping("/softDelete/{userId}")
	public ResponseEntity<String> softDeleteUser(@PathVariable("userId") int userId){
		String status = userEmpService.hardDeleteUser(userId, false);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

}
