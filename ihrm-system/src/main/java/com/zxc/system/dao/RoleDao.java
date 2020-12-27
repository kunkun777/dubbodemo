package com.zxc.system.dao;

import com.zxc.model.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: hyl
 * @date: 2020/01/15
 **/
public interface RoleDao extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {

}
