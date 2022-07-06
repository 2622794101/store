package com.lgm.store.controller;

import com.lgm.store.entitly.User;
import com.lgm.store.service.IUserService;
import com.lgm.store.service.ex.file.*;
import com.lgm.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController extends BaseController {
    /**
     * 项目父路径
     */
    public static final String AVATAR_PARENT_PATH = "src/main/resources/static/upload";
    /**
     * 上传文件大小限制
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 运行上传文件的类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /**
     * 初始化允许上传文件的类型
     */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/git");
    }

    @Autowired
    public IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> regiester(User user) {
        userService.register(user);
        JsonResult jsonResult = new JsonResult(Ok);
        return jsonResult;
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        JsonResult<User> jsonResult = new JsonResult(Ok);
        jsonResult.setData(user);

        //把uid与username放在session中
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());

        return jsonResult;
    }

    @RequestMapping("changePassword")
    public JsonResult changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String modifiedUser = session.getAttribute("username").toString();
        userService.changePassword(uid, oldPassword, newPassword, modifiedUser);
        JsonResult<Void> jsonResult = new JsonResult<>(Ok);
        return jsonResult;
    }

    public JsonResult changeUserData(String phone
            , String email
            , Integer gender
            , HttpSession session) {

        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        userService.changUserData(uid, phone, email, gender, username);

        return new JsonResult(Ok);
    }

    @RequestMapping("getByUid")
    public JsonResult<User> getByUid(Integer uid) {
        User userByUid = userService.getUserByUid(uid);
        JsonResult<User> jsonResult = new JsonResult<>(Ok);
        jsonResult.setData(userByUid);
        return jsonResult;
    }

    @RequestMapping("changeInfoByUid")
    public JsonResult<User> changeInfoByUid(User user, HttpSession session) {
        String phone = user.getPhone();
        Integer gender = user.getGender();
        String email = user.getEmail();
        String modifiedUser = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        userService.changUserData(uid, phone, email, gender, modifiedUser);
        return new JsonResult(Ok);
    }

    @RequestMapping("changeAvatar")
    public JsonResult changeAvatar(MultipartFile file, HttpSession session) {
        if (file.isEmpty()) {
            throw new FileEmptyException("不允许上传空文件");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件过大");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType)) {
            throw new FileTypeException("，允许上传的文件 \n" + AVATAR_TYPES);
        }
        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");

        File dir = new File(parent).getAbsoluteFile();


        if (!dir.exists()) {
            dir.mkdirs();
        }
        //取出后缀
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }

        //文件名
        String fileName = UUID.randomUUID().toString().replace("-", "_") + suffix;
        File desc = new File(dir, fileName);

        try {
            file.transferTo(desc);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件状态异常，文件可能被删除或移动");
        } catch (IOException e) {
            throw new FileUploadIOException("上传文件是读写错误，请稍后重试");
        }
        String avatarPath = "/upload/" + fileName;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeAvatarByUid(uid, avatarPath, username);
        return new JsonResult(Ok, avatarPath);
    }


}
