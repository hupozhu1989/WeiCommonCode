package com.wei.spring.ioc;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/30
 */
public class UserController {

    @AutoWiredWei
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    /*public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}
