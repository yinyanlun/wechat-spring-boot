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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yin.wechat.Repository.UserInfoDao;
import com.yin.wechat.model.UserInfo;

@Service("userInfoService")
public class UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	
	public UserInfo save(UserInfo userinfo){
		return userInfoDao.save(userinfo);
	}
	public UserInfo getUserInfoByUserName(String userName){
		return userInfoDao.getUserInfoByUserName(userName);
	}
	
	public List<String> getUserNameList()
	{
		return userInfoDao.getUserNameList();
	}
	public Page<UserInfo> pageUserInfoByParam(final UserInfo userInfo, Pageable pageable) {
		Specification<UserInfo> specification = new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				//参数非空判断。不为空则加此条件s
				if(null!=userInfo.getRealName()&&userInfo.getRealName().trim()!="")
				{
					Predicate payuser =
					criteriaBuilder.or(criteriaBuilder.like(root.get("username"), "%"+userInfo.getRealName()+"%"),
							criteriaBuilder.like(root.get("realName"), "%"+userInfo.getRealName()+"%"));
					predicates.add(payuser);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
		};
		return userInfoDao.findAll(specification, pageable);
	}
}
