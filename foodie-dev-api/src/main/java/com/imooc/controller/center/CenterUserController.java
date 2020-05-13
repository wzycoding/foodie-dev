package com.imooc.controller.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户信息接口", tags = "用户信息相关接口")
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "GET")
    @PostMapping("update")
    public IMOOCJSONResult update(@ApiParam(value = "用户id", required = true)
                                  @RequestParam String userId,
                                  @RequestBody @Valid CenterUserBO centerUserBO,
                                  BindingResult result,
                                  HttpServletRequest request,
                                  HttpServletResponse response
                                  ) {
        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);
        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            // 不再使用errorMsg
            return IMOOCJSONResult.errorMap(errorMap);
        }
        setNullProperty(userResult);
        // 最后一个参数为是否加密
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话
        return IMOOCJSONResult.ok();
    }

    private Map<String, String> getErrors(BindingResult result) {

        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            // 发生验证错误的某一个属性
            String errorField = error.getField();
            // 验证错误的信息
            String errorMsg = error.getDefaultMessage();
            map.put(errorField, errorMsg);
        }
        return map;
    }

    private void setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setBirthday(null);
    }

}
