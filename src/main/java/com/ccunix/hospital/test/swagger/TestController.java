package com.ccunix.hospital.test.swagger;

import com.ccunix.hospital.common.domain.AjaxResult;
import com.ccunix.hospital.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "用户测试1信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController {
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<>();
    {
        users.put(1,new UserEntity(1,"admin","admin123","13345455656"));
        users.put(2,new UserEntity(2,"luck","luck123","13345457788"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public AjaxResult userList(){
        List<UserEntity> userList = new ArrayList<>(users.values());
        return AjaxResult.success(userList);
    }
    @ApiOperation("按照用户名和手机号查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",required = true, value = "用户名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "mobile", value = "用户手机", dataType = "String", dataTypeClass = String.class)
    })
    @GetMapping("/query")
    public AjaxResult query(String username,String mobile){
        AjaxResult ajax = AjaxResult.success();
        Collection<UserEntity> collection = users.values();
        UserEntity user = null;
        for(Iterator<UserEntity> iterator = collection.iterator();iterator.hasNext();){
            UserEntity userEntity = iterator.next();
            if(userEntity.getUsername().contains(username==null?"":username) || userEntity.getMobile().contains(mobile==null?"":mobile)){
                user = userEntity;
                break;
            }
        }
        ajax.put("data",user);
        return ajax;
    }
    @ApiOperation("获取用户信息") //  /test/user/1
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/{userId}")
    public AjaxResult getUser(@PathVariable Integer userId){
        if(!users.isEmpty() && users.containsKey(userId)){
            return AjaxResult.success(users.get(userId));
        }else{
            return AjaxResult.error("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id2", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "username", value = "用户名称2", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "用户密码2", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "mobile", value = "用户手机2", dataType = "String", dataTypeClass = String.class)
    })*/
    @PostMapping("/save")
    public AjaxResult save(UserEntity user){
        if(StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())){
            return AjaxResult.error("用户ID不能为空");
        }
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody UserEntity user){
        if(StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())){
            return AjaxResult.error("用户ID不能为空");
        }
        if(users.isEmpty() || !users.containsKey(user.getUserId())){
            return AjaxResult.error("用户不存在");
        }
        users.remove(user.getUserId());
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @DeleteMapping("/{userId}")
    public AjaxResult delete(@PathVariable Integer userId){
        if(!users.isEmpty() && users.containsKey(userId)){
            users.remove(userId);
            return AjaxResult.success();
        }else{
            return AjaxResult.error("用户不存在");
        }
    }
}
