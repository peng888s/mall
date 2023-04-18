package com.bdqn.mall.controller.force;

import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.Address;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.AddressService;
import com.bdqn.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class ForeRegisterController extends BaseController {
    @Resource
    private AddressService addressService;
    @Resource
    private UserService userService;

    //转到前台Mall-用户注册页
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String goToPage(Map<String,Object> map) {
        String addressId = "110000";
        String cityAddressId = "110100";
        logger.info("获取省份信息");
        List<Address> addressList = addressService.getRoot();
        logger.info("获取addressId为{}的市级地址信息", addressId);
        List<Address> cityAddress = addressService.getList(null, addressId);
        logger.info("获取cityAddressId为{}的区级地址信息", cityAddressId);
        List<Address> districtAddress = addressService.getList(null, cityAddressId);
        map.put("addressList", addressList);
        map.put("cityList", cityAddress);
        map.put("districtList", districtAddress);
        map.put("addressId", addressId);
        map.put("cityAddressId", cityAddressId);
        logger.info("转到前台-用户注册页");
        return "fore/register";
    }

    //Mall前台-用户注册-ajax
    @ResponseBody
    @RequestMapping(value = "register/doRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(
            @RequestParam(value = "userName") String userName  /*用户名 */,
            @RequestParam(value = "userNickName") String userNickName  /*用户昵称 */,
            @RequestParam(value = "userPassword") String userPassword  /*用户密码*/,
            @RequestParam(value = "userGender") String userGender  /*用户性别*/,
            @RequestParam(value = "userBirthday") String userBirthday /*用户生日*/,
            @RequestParam(value = "userAddress") String userAddress  /*用户所在地 */
    ) throws ParseException {
        logger.info("验证用户名是否存在");
        User user = new User();
        user.setUserName(userName);
        Integer count = userService.getTotal(user);
        if (count > 0) {
            logger.info("用户名已存在，返回错误信息!");
            JSONObject object = new JSONObject();
            object.put("success", false);
            object.put("msg", "用户名已存在，请重新输入！");
            return object.toJSONString();
        }
        logger.info("创建用户对象");
        User userNew = new User();
        userNew.setUserName(userName);
        userNew.setUserNickName(userNickName);
        userNew.setUserPassword(userPassword);
        userNew.setUserGender(Byte.valueOf(userGender));
        userNew.setUserBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userBirthday));
        userNew.setUserAddress(new Address(userAddress));
        userNew.setUserHomePlace(new Address("130000"));
        logger.info("用户注册");
        if (userService.add(userNew)) {
            logger.info("注册成功");
            JSONObject object = new JSONObject();
            object.put("success", true);
            return object.toJSONString();
        } else {
            throw new RuntimeException();
        }
    }
}
