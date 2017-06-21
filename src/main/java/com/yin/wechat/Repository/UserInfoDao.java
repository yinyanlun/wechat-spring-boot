package com.yin.wechat.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yin.wechat.model.UserInfo;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long>,JpaSpecificationExecutor<UserInfo>{

	@Query("select u from UserInfo u where u.username=:userName") 
	UserInfo getUserInfoByUserName(@Param("userName")String userName);


	@Query("select u.username from UserInfo u") 
	List<String> getUserNameList();


	
}
