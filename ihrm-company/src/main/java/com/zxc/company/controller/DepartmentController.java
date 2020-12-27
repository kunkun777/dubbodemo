package com.zxc.company.controller;

import com.zxc.common.controller.BaseController;
import com.zxc.common.entity.Result;
import com.zxc.common.entity.ResultCode;
import com.zxc.inters.DepartmentInter;
import com.zxc.model.company.Company;
import com.zxc.model.company.Department;
import com.zxc.model.company.response.DeptListResult;
import com.zxc.company.service.CompanyService;
import com.zxc.company.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
@Api(tags = "Department-controller",description = "About 部门")
public class DepartmentController extends BaseController implements DepartmentInter {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "保存部门")
    @RequestMapping(value="/department",method = RequestMethod.POST)
    public Result save(@RequestBody Department department) {
        department.setCompanyId(companyId);
        departmentService.save(department);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "根据企业id查询企业下的部门列表")
    @RequestMapping(value="/department",method = RequestMethod.GET)
    public Result findAll() {
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        DeptListResult deptListResult = new DeptListResult(company,list);
        return new Result(ResultCode.SUCCESS,deptListResult);
    }

    @ApiOperation(value = "根据ID查询部门")
    @RequestMapping(value="/department/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value="id") String id) {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    @ApiOperation(value = "根据ID修改部门")
    @RequestMapping(value="/department/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id,@RequestBody Department department) {
        //1.设置修改的部门id
        department.setId(id);
        //2.调用service更新
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "根据ID删除部门")
    @RequestMapping(value="/department/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value="id") String id) {
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    @ApiOperation(value = "根据部门编码和公司id查询部门")
    @RequestMapping(value = "/department/search" , method = RequestMethod.POST)
    public Department findByCode(@RequestParam("code") String code,@RequestParam("companyId") String companyId){
        return departmentService.findByCode(code , companyId);
    }
}
