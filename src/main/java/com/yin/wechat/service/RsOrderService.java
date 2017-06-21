package com.yin.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.yin.wechat.Repository.IRSOrdersDao;
import com.yin.wechat.model.RSOrders;


@Service("rsOrderService")
public class RsOrderService {

	@Autowired
	private IRSOrdersDao rsorderDao;

	public void save(RSOrders orders) {
		rsorderDao.save(orders);
	}

	public void delete(Long id) {
		rsorderDao.delete(id);
	}

	public void update(RSOrders orders) {
		rsorderDao.saveAndFlush(orders);
	}

	public List<RSOrders> findAll() {
		return rsorderDao.findAll();
	}
	public RSOrders findRSOrdersByTradeno(String tradeno){
		return rsorderDao.findRSOrdersByTradeno(tradeno);
	}
	public int updateOrderStateByOrderNo(String tradeno,String state){
		return rsorderDao.updateOrderStateByOrderNo(tradeno,state);
	}
	public void updateOrder(String tradeNo,String state)
	{
		rsorderDao.updateOrderStateByOrderNo(tradeNo, state);
	}
	public RSOrders findById(int id){
		return rsorderDao.findOne(id);
	}
	
	public List<RSOrders> getOrderList(String start,String end,String condtion)
	{
		return rsorderDao.querListOrder(start,end);
	}
	public Page<RSOrders> pageRSOrdersByParam(final RSOrders rSOrders,Pageable pageable) {
		Specification<RSOrders> specification = new Specification<RSOrders>() {
			@Override
			public Predicate toPredicate(Root<RSOrders> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				//参数非空判断。不为空则加此条件s
				if(rSOrders.getPayuser()!=null&&rSOrders.getPayuser().trim()!="")
				{
					Predicate payuser =
					criteriaBuilder.or(criteriaBuilder.like(root.get("payuser"), "%"+rSOrders.getPayuser()+"%"),
							criteriaBuilder.like(root.get("cpName"), "%"+rSOrders.getCpName()+"%"));
					predicates.add(payuser);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
		};
		return rsorderDao.findAll(specification, pageable);
	}
	public double getPaySum(String start,String tradeNo)
	{
		return rsorderDao.getPaySum(start,tradeNo);
	}
	public int getPayCount(String start,String tradeNo)
	{
		return rsorderDao.getPayCount(start,tradeNo);
	}
	public double getPaysSumByBetweenDatetime(String start,String tradeNo,Date startTime,Date endTime){
		return rsorderDao.getPaysSumByBetweenDatetime(start,tradeNo, startTime, endTime);
	}
	public int getPayCountByUserNameAndBetweenDatetimeAndTradeno(String state,String tradeNo,String userName,Date startTime,Date endTime)
	{
		return rsorderDao.getPayCountByUserNameAndBetweenDatetimeAndTradeno(state,tradeNo,userName,startTime,endTime);
	}
	public double getPaySumByUserNameAndBetweenDatetimeAndTradeno(String state,String tradeNo,String userName,Date startTime,Date endTime)
	{
		return rsorderDao.getPaySumByUserNameAndBetweenDatetimeAndTradeno(state,tradeNo,userName,startTime,endTime);
	}
}
