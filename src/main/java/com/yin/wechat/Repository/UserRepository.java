package com.yin.wechat.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yin.wechat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>{

	@Query("select u from User u where u.userName=:userName and u.password=:password") 
	User getUserByUsernamePassrowd(@Param("userName") String userName, @Param("password")String password);

	@Query("select u from User u where u.userName=:userName") 
	User getUserByUserName(@Param("userName") String userName);	
}
