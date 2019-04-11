package com.cwn.springboot.api;

import com.cwn.springboot.bean.UserVO;
import com.cwn.springboot.service.IndexService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**需要用户登陆验证
 * 访问：http://localhost:8080/swagger-ui.html
 */
@RestController
@RequestMapping(value = "api") //配置返回值 application/json
@Api("SwaggerDemoApi相关的api")
public class SwaggerDemoApi {

    @Autowired
    private IndexService indexService;


   //限流：对外提供一个服务接口，允许最大并发数为10
    private final Semaphore permit = new Semaphore(100, true);


    @RequestMapping(value="/get/userinfo", method=RequestMethod.GET)
//    @RequestMapping(value = "/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id查询用户信息", notes = "查询数据库中某个的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",paramType = "query", value = "用户ID", required = true),
            @ApiImplicitParam(name = "Authorization",value = "验证信息",required = true,paramType = "query",dataType = "string")
    })
    //@ApiImplicitParam(name = "userid", value = "用户ID", paramType = "path", required = true, dataType = "Long")
   public  UserVO getUserInfo(@RequestParam int userid,@RequestParam String Authorization) throws InterruptedException {

       //打开限流
        permit.acquire();

        System.out.println("Authorization:"+Authorization);

        //释放限流
        permit.release();

        return indexService.getUserInfoById(userid);
    }



    @RequestMapping(value="/get/userinfoall", method=RequestMethod.GET)
//    @RequestMapping(value = "/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",value = "验证信息",required = true,paramType = "query",dataType = "string")
    })
    //@ApiImplicitParam(name = "userid", value = "用户ID", paramType = "path", required = true, dataType = "Long")
    public List<UserVO> getUserAll(@RequestParam String Authorization) throws InterruptedException {


        System.out.println("Authorization:"+Authorization);

        return indexService.listUser();
    }


}

