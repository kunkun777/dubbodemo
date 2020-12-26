package com.zxc.inters;

import com.zxc.common.entity.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CompanyInter {
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result findCompanyById(@PathVariable(value="id") String id);
}

