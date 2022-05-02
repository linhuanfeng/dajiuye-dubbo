package com.lhf.dajiuye.web.app.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lhf.dajiuye.api.service.user.MyUserService;
import com.lhf.dajiuye.api.service.user.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: csp1999
 * @Date: 2020/10/31/16:40
 * @Description: OSS 文件上传controller
 */
@Slf4j
@RequestMapping("/user/oss")
@RestController
public class OssFileController {
    @DubboReference(interfaceClass = OssFileService.class,version = "1.0.0",check = false)
    private OssFileService fileUploadService;

    @DubboReference(interfaceClass = MyUserService.class,version = "1.0.0",check = false)
    MyUserService userService;

    /*
     * 文件上传api
     * @param: file
     * @return: com.alibaba.fastjson.JSONObject
     * @create: 2020/10/31 17:35
     * @author: csp1999
     */
    @PostMapping("all")
    public String uploadAll(@RequestParam("all") MultipartFile file,
                            @RequestParam("openId") String openId,
                            @RequestParam("fileName") String fileName,
                            HttpServletRequest req, HttpServletResponse resp) {
        log.info("file:"+file+" openId:"+openId+" fileName"+fileName);
        JSONObject jsonObject = new JSONObject();
        if (file != null) {
            String returnFileUrl = fileUploadService.upload(file,fileName);
            if (returnFileUrl.equals("error")) {
                jsonObject.put("error", "文件上传失败！");
                return "文件上传失败";
            }
            jsonObject.put("success", "文件上传成功！");
            jsonObject.put("returnFileUrl", returnFileUrl);

//            System.out.println(EncryptUtil.decryptUtil(openId));
//            int i = userService.updateUserResumeByOpenId(returnFileUrl, EncryptUtil.decryptUtil(openId));
//            log.info("更新简历后i={}",i);

            return returnFileUrl;
        } else {
            jsonObject.put("error", "文件上传失败！");
            return "文件上传失败2";
        }
    }

    /*
     * 文件下载api
     * @param: fileName
     * @param: response
     * @return: com.alibaba.fastjson.JSONObject
     * @create: 2020/10/31 17:35
     * @author: csp1999
     */
    @GetMapping(value = "download/{fileName}")
    public JSONObject download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();

        String status = fileUploadService.download(fileName, response);
        if (status.equals("error")) {
            jsonObject.put("error", "文件下载失败！");
            return jsonObject;
        } else {
            jsonObject.put("success", "文件下载成功！");
            return jsonObject;
        }
    }

    /*
     * 文件删除api
     * @param: fileName
     * @return: com.alibaba.fastjson.JSONObject
     * @create: 2020/10/31 17:35
     * @author: csp1999
     */
    @GetMapping("/delete/{fileName}")
    public JSONObject DeleteFile(@PathVariable("fileName") String fileName) {
        JSONObject jsonObject = new JSONObject();

        String status = fileUploadService.delete(fileName);
        if (status.equals("error")) {
            jsonObject.put("error", "文件删除失败！");
            return jsonObject;
        } else {
            jsonObject.put("success", "文件删除成功！");
            return jsonObject;
        }
    }
}
