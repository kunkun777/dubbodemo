package com.zxc.controller;


import com.zxc.common.entity.Result;
import com.zxc.common.entity.ResultCode;
import com.zxc.common.exception.CommonException;
import com.zxc.model.system.City;
import com.zxc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/sys/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value="",method = RequestMethod.POST)
    public Result save(@RequestBody City city)  {
        cityService.add(city);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id更新
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id, @RequestBody City city ) {
        //业务操作
        city.setId(id);
        cityService.update(city);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id删除
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value="id") String id) {
        cityService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id查询
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value="id") String id) throws CommonException {
        City city = cityService.findById(id);
        return new Result(ResultCode.SUCCESS,city);
    }

    //查询全部
    @RequestMapping(value="",method = RequestMethod.GET)
    public Result findAll() {
        List<City> list = cityService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }
}
