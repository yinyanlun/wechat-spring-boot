package com.yin.wechat.Repository;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yin.wechat.weixin.app.model.WeixinUserInfo;

//,QueryDslPredicateExecutor<WeixinUserInfo>
@Repository
public interface WeixinUserInfoDao extends JpaRepository<WeixinUserInfo,Integer>, JpaSpecificationExecutor<WeixinUserInfo>{

	 
	 
	@Query("select u from WeixinUserInfo u where u.openId=:openId") 
	WeixinUserInfo getWeixinUserInfoByopenId(@Param("openId")String openId);

	@Modifying 
	@Transactional
	@Query("update WeixinUserInfo u set u.state=:state where u.id=:id")
	int updateStateById(@Param("id") Integer id,@Param("state") String state);

	@Modifying 
	@Transactional
	@Query("update WeixinUserInfo u set u.state=:state,u.subscribe=:subscribe,u.subscribeTime=:subscribeTime,u.nickname=:nickname,u.sex=:sex,u.country=:country,u.province=:province,"
			+ "u.city=:city,u.language=:language,u.headImgUrl=:headImgUrl,u.subscribeDate=:subscribeDate where u.id=:id")
	int refreshWeixinUserInfo(@Param("id") Integer id, @Param("subscribe") int subscribe,@Param("subscribeTime") String subscribeTime,@Param("nickname") String nickname,@Param("sex") int sex,@Param("country") String country,
			@Param("province") String province,@Param("city") String city,@Param("language") String language,@Param("headImgUrl") String headImgUrl,@Param("state") String state,@Param("subscribeDate") Date subscribeDate);
}
