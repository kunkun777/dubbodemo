package com.zxc.inters;

import com.zxc.common.entity.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CompanyInter {
    public String findCompanyById_(String id);
}

