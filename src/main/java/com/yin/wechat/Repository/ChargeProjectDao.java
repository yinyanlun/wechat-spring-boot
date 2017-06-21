package com.yin.wechat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yin.wechat.model.ChargeProject;

@Repository
public interface ChargeProjectDao extends JpaRepository<ChargeProject, Integer>,JpaSpecificationExecutor<ChargeProject>{

	@Modifying 
	@Transactional
	@Query("update ChargeProject c set c.state=:state where c.id=:id")
	int updateStateId(@Param("id") Integer id,@Param("state") String state);
	@Query("from ChargeProject r where r.state=:state") 
	List<ChargeProject> getAllOrderListByState(@Param("state") String state);

}
