package com.zxc.dao;


import com.zxc.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * 自定义dao接口继承
 *      JpaRepository<实体类，主键>
 *      JpaSpecificationExecutor<实体类>
 */
@Component
public interface CompanyDao extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {
}