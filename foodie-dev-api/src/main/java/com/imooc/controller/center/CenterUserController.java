package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.resource.FileUpload;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户信息接口", tags = "用户信息相关接口")
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("update")
    public IMOOCJSONResult update(@ApiParam(name = "userId", value = "用户id", required = true)
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

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public IMOOCJSONResult uploadFace(@ApiParam(name = "userId", value = "用户id", required = true)
                                  @RequestParam String userId,
                                  @ApiParam(name = "file", value = "用户头像", required = true)MultipartFile file,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 头像保存的地址
//        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为每一个用户增加一个userId，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;

        // 开始文件上传
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // 获得文件上传的文件名称
                String filename = file.getOriginalFilename();
                if (StringUtils.isNotBlank(filename)) {

                    // 文件重名名
                    String[] fileNameArr = filename.split("\\.");
                    // 获取文件后缀名
                    // face-{userId}.png
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    // 判断后缀名防止被攻击，防止上传sh，或者php
                    if (!suffix.equalsIgnoreCase("png") &&
                            !suffix.equalsIgnoreCase("jpg") &&
                            !suffix.equalsIgnoreCase("jpeg")&&
                            !suffix.equalsIgnoreCase("gif")) {
                        return IMOOCJSONResult.errorMsg("图片格式不正确！");
                    }
                    // 文件名重组 覆盖式上传 增量式：额外拼接时间
                    String newFileName = "face-" + userId + "." + suffix;
                    // 上传头像最终保存的位置
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;

                    // 用于提供给web服务访问的地址
                    uploadPathPrefix += ("/" + newFileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // 创建文件夹
                        boolean mkdirs = outFile.getParentFile().mkdirs();
                        System.out.println(mkdirs);
                    }
                    // 文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

        } else {
            return IMOOCJSONResult.errorMsg("文件不能为空！");
        }
        // 获得图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();

        // 由于浏览器可能存在缓存，所以需要加上时间戳来保证更新后的图片即时刷新
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        // 更新用户头像到数据库
        Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);

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
