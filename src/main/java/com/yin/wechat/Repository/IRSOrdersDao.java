package com.yin.wechat.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yin.wechat.model.RSOrders;



@Repository
public interface IRSOrdersDao extends  JpaRepository<RSOrders, Serializable>,JpaSpecificationExecutor<RSOrders>{
	
	@Query("from RSOrders r where r.paytime >=:start and r.paytime <=:end")  
	List<RSOrders> querListOrder(@Param("start")String openid,@Param("end")String patientId);
	
	@Modifying 
	@Transactional
	@Query("update RSOrders r set r.state=:state where r.tradeno=:tradeNo")
	int updateOrderStateByOrderNo(@Param("tradeNo") String tradeNo,@Param("state") String state);
	
	@Query("from RSOrders r where r.tradeno =:tradeNo") 
	RSOrders findRSOrdersByTradeno(@Param("tradeNo")String tradeNo);
	 
	@Query("select sum(cast(paymoney as float)) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo")
	double getPaySum(@Param("state") String state,@Param("tradeNo") String tradeNo);
	
	@Query("select count(*) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo")
	int getPayCount(@Param("state") String state,@Param("tradeNo") String tradeNo);
//	@Query("select sum(cast(paymoney as float)) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo and r. paytime between :startTime and :endTime")
	@Query("select sum(cast(paymoney as float)) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo and r. paytime >= :startTime and r. paytime<=:endTime")
	double getPaysSumByBetweenDatetime(@Param("state") String state,@Param("tradeNo") String tradeNo,@Param ("startTime")Date startTime,@Param ("endTime")Date endTime);
	
	@Query("select count(*) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo and r.userName=:userName and r. paytime >= :startTime and r. paytime<=:endTime")
	int getPayCountByUserNameAndBetweenDatetimeAndTradeno(
			@Param("state") String state,
			@Param("tradeNo") String tradeNo,
			@Param("userName") String userName,
			@Param ("startTime")Date startTime,
			@Param ("endTime")Date endTime);
	@Query("select sum(cast(paymoney as float)) as tmp from RSOrders r where r.state=:state and r.tradeno like :tradeNo and r.userName=:userName and r. paytime >= :startTime and r. paytime<=:endTime")
	double getPaySumByUserNameAndBetweenDatetimeAndTradeno(
			@Param("state") String state,
			@Param("tradeNo") String tradeNo,
			@Param("userName") String userName,
			@Param ("startTime")Date startTime,
			@Param ("endTime")Date endTime);
}
