package com.yin.wechat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.yin.wechat.model.SysRole;
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole,Long>, JpaSpecificationExecutor<SysRole>{

}
