package com.springbootcrud.usermodule.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.springbootcrud.usermodule.model.UserEmp;

@Repository
public class UserEmpDaoImp implements UserEmpDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<UserEmp> getEmpuser() {
		Session currentsession=entityManager.unwrap(Session.class);
		Query<UserEmp> query=currentsession.createQuery("from UserEmp",UserEmp.class);
		List<UserEmp> listOfUser=query.getResultList();
		return listOfUser;
	}

	@Override
	public UserEmp getempuserbyID(int id) {
		Session currentsession=entityManager.unwrap(Session.class);
		UserEmp userEmpbyID=currentsession.get(UserEmp.class, id);
		System.out.println("User ID:"+userEmpbyID);
		return userEmpbyID;
	}

	@Override
	public void save(UserEmp emp) {
		Session currentSession= entityManager.unwrap(Session.class);
		currentSession.save(emp);
	}

	@Override
	public UserEmp update(int id) {
		Session currentSession= entityManager.unwrap(Session.class);
		UserEmp userUpdate=currentSession.get(UserEmp.class,id);
		currentSession.update(userUpdate);
		return userUpdate;
	}

	@Override
	public UserEmp delete(int id) {
		Session currentSession= entityManager.unwrap(Session.class);
		UserEmp userdeletebyID=currentSession.get(UserEmp.class, id);
		currentSession.delete(userdeletebyID);
		return userdeletebyID;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<UserEmp> searchUserByFilter(String firstName, String lastName, String pinCode,boolean sort)
	{

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEmp> cq = cb.createQuery(UserEmp.class);

		Root<UserEmp> user = cq.from(UserEmp.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		TypedQuery<UserEmp> query = null;
		if(!sort) {
			if (!StringUtils.isEmpty(firstName)) {
				predicates.add(cb.like(user.get("name"), "%" + firstName + "%"));
			}
			if (!StringUtils.isEmpty(lastName)) {
				predicates.add(cb.like(user.get("last_name"), "%" + lastName + "%"));
			}
			if (!StringUtils.isEmpty(pinCode)) {
				predicates.add(cb.like(user.get("pin_code"), "%" + pinCode + "%"));
			}
		}else {
			cq.orderBy (cb.asc(user.get("dob")),
					cb.desc(user.get("doj")));
		}
		cq.where(predicates.toArray(new Predicate[] {}));
		query = entityManager.createQuery(cq);
		return query.getResultList();
	}
	/*
	 * @Override public UserEmp getempuserbymultiple(String name, String lastname,
	 * int pin) { // TODO Auto-generated method stub Session
	 * currentsession=entityManager.unwrap(Session.class); //UserEmp
	 * userEmpbyID=currentsession.get(UserEmp.class, name);
	 * System.out.println("User ID:"+userEmpbyID); return userEmpbyID; }
	 */

	@Override
	public UserEmp findById(int userId) {
		Session currentsession=entityManager.unwrap(Session.class);
		UserEmp userEmpbyID=currentsession.get(UserEmp.class, userId);
		System.out.println("User ID:"+userEmpbyID);
		return userEmpbyID;
	}


}
