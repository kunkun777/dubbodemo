package com.zxc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zxc.common.controller.BaseController;
import com.zxc.common.entity.PageResult;
import com.zxc.common.entity.Result;
import com.zxc.common.entity.ResultCode;
import com.zxc.common.poi.ExcelImportUtil;
import com.zxc.inters.CompanyInter;
import com.zxc.model.system.User;
import com.zxc.model.system.response.ProfileResult;
import com.zxc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Reference(version = "1.0.0",url="dubbo://127.0.0.1:12340")
    private CompanyInter companyInter;

    /**
     * @Target: 测试是否与company联通
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(){
        System.out.println("调用到这个方法了，"+companyInter.toString());
        return companyInter.findCompanyById("1");
    }

    /**
     * 更新用户图片
     * @param id String
     * @param file MultipartFile
     * @throws Exception Exception
     */
    @RequestMapping("/user/upload/{id}")
    public Result upload(@PathVariable String id , @RequestParam(name = "file") MultipartFile file) throws Exception {

        //1.调用service保存图片
        String imgUrl = null;
        try {
            //需要配置百度AI,如果不正确会抛出异常
            imgUrl = userService.uploadImage(id , file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2.返回数据
        return new Result(ResultCode.SUCCESS , imgUrl);
    }

    /**
     * 导入Excel,添加用户
     */
    @RequestMapping(value = "/user/import" , method = RequestMethod.POST)
    public Result importUser(@RequestParam(name = "file") MultipartFile file) throws Exception {
        List<User> list = new ExcelImportUtil(User.class).readExcel(file.getInputStream(), 1, 1);
        //3.批量保存用户
        userService.saveAll(list , companyId , companyName);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 分配角色
     */
    @RequestMapping(value = "/user/assignRoles" , method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String,Object> map){

        //获取被分配的用户id
        String userId = (String) map.get("id");
        //获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        //调用service完成角色分配
        userService.assignRoles(userId , roleIds);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    public Result save(@RequestBody User user){
        //设置保存的用户id
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //调用service完成保存用户
        userService.save(user);
        //构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public Result findAll(int page, int size, @RequestParam() Map map){
        //获取当前的企业id
        map.put("companyId" , companyId);
        Page<User> pageUser = userService.findAll(map, page, size);
        //构造返回结果
        PageResult<User> pageResult = new PageResult<>(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS , pageResult);
    }

    /**
     * 根据Id查询
     */
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id){
        //添加roleIds(用户已经具有的角色id数组)
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS , user);
    }

    /**
     * 修改User
     */
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id , @RequestBody User user){
        //调用Service更新
        userService.update(id , user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据Id删除
     */
    @RequiresPermissions(value = "API-USER-DELETE")
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.DELETE , name = "API-USER-DELETE")
    public Result delete(@PathVariable(value = "id") String id){
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录接口")
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,Object> loginMap){
        String mobile = (String) loginMap.get("mobile");
        String password = (String) loginMap.get("password");
        try {
            //如果该公司被禁止登录,直接返回
            User user = userService.findByMobile(mobile);
            //如果这个组件等级
//            if (!Constant.UserLevel.SAASADMIN.equals(user.getLevel())){
//                //返回时List<LinkedHashMap> ,需要对其解析
//                LinkedHashMap linkedHashMap
//                        = (LinkedHashMap) companyInter.findCompanyById_(user.getCompanyId()).getData();
//                Company company = JSON.parseObject(JSON.toJSONString(linkedHashMap), new TypeReference<Company>() {
//                });
//                if (!ObjectUtils.isEmpty(company)){
//                    if (Constant.Company.COMPANY_DISABLE.equals(String.valueOf(company.getState()))){
//                        return new Result(ResultCode.UNAUTHORISE);
//                    }
//                }
//            }
            //构造登录令牌
            password = new Md5Hash(password , mobile , 3).toString();
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile , password);
            //获取subject
            Subject subject = SecurityUtils.getSubject();
            //调用login方法,进入realm完成认证
            subject.login(upToken);
            //获取sessionId
            String sessionId = (String) subject.getSession().getId();
            System.out.println("zxc:====================>"+subject.getSession().toString());
            //构造返回结果
            return new Result(ResultCode.SUCCESS , sessionId);
        }catch (Exception e){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     * 用户登录成功之后,获取用户信息
     */
    @RequestMapping(value = "/profile" , method = RequestMethod.POST)
    public Result profile(HttpServletRequest request) throws Exception {

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        //获取安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();

        return new Result(ResultCode.SUCCESS,result);
    }

}
