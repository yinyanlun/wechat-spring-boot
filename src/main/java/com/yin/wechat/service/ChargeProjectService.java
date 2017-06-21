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

import com.yin.wechat.Repository.ChargeProjectDao;
import com.yin.wechat.model.ChargeProject;

@Service("chargeProjectService")
public class ChargeProjectService {
	
	
	@Autowired
	private ChargeProjectDao chargeProjectDao;
	
	public void save(ChargeProject chargeProject) {
		chargeProjectDao.save(chargeProject);
	}

	public void delete(int id) {
		 chargeProjectDao.delete(id);
	}

	public void update(ChargeProject chargeProject) {
		chargeProjectDao.saveAndFlush(chargeProject);
	}

	public List<ChargeProject> findAll() {
		return chargeProjectDao.findAll();
	}
	public int updateStateId(Integer id,String state)
	{
		return chargeProjectDao.updateStateId(id, state);
	}
	public ChargeProject findById(int id){
		return chargeProjectDao.findOne(id);
	}

	public Page<ChargeProject> pagechargeProjectServiceByParam(final ChargeProject chargeProject,Pageable pageable) {
		Specification<ChargeProject> specification = new Specification<ChargeProject>() {
			@Override
			public Predicate toPredicate(Root<ChargeProject> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				//参数非空判断。不为空则加此条件s
				if(null!=chargeProject.getCpName()&&chargeProject.getCpName().trim()!="")
				{
					Predicate payuser =
					criteriaBuilder.or(criteriaBuilder.like(root.get("cpName"), "%"+chargeProject.getCpName()+"%"),
							criteriaBuilder.like(root.get("createUserName"), "%"+chargeProject.getCreateUserName()+"%"));
					predicates.add(payuser);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
		};
		return chargeProjectDao.findAll(specification, pageable);
	}

	public List<ChargeProject> getAllOrderListByState(String state) {
		// TODO Auto-generated method stub
		return chargeProjectDao.getAllOrderListByState(state);
	}
	

}
