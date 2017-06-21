package com.yin.wechat.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yin.wechat.Repository.UserRepository;
import com.yin.wechat.model.User;
@Service("userservice")
public class UserService {
	
	@Autowired
	private UserRepository UserRepository;
	
	
	public User saveUser(User user)
	{
		return UserRepository.save(user);
	}
	public User getUserById(Integer id){
		return UserRepository.findOne(id);
	}
	public User getUserByUsernamePassrowd(String userName,String password){
		return UserRepository.getUserByUsernamePassrowd(userName,password);
	}
	public List<User> getallUser(){
		return UserRepository.findAll();
	}
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return UserRepository.findAll(pageable);
	}
	public Page<User> pageUserByParam(final User user,Pageable pageable) {
		Specification<User> specification = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				//参数非空判断。不为空则加此条件s
				Predicate password = criteriaBuilder.equal(root.get("password"), user.getPassword());
				predicates.add(password);
				Predicate userName = criteriaBuilder.like(root.get("userName"), "%"+user.getUserName()+"%");
				predicates.add(userName);
				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
		};
		return UserRepository.findAll(specification, pageable);
	}
	public User getUserByUserName(String userName){
		return UserRepository.getUserByUserName(userName);
	}
}
