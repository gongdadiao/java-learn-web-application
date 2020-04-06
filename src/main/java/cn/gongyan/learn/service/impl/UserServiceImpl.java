/***********************************************************
 * @Description : 用户服务
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.service.impl;

import cn.gongyan.learn.beans.dto.RegisterDTO;
import cn.gongyan.learn.beans.dto.SettingDTO;
import cn.gongyan.learn.beans.entity.Action;
import cn.gongyan.learn.beans.entity.Page;
import cn.gongyan.learn.beans.entity.Role;
import cn.gongyan.learn.beans.entity.User;
import cn.gongyan.learn.beans.qo.LoginQo;
import cn.gongyan.learn.beans.vo.*;
import cn.gongyan.learn.enums.LoginTypeEnum;
import cn.gongyan.learn.enums.RoleEnum;
import cn.gongyan.learn.repository.ActionRepository;
import cn.gongyan.learn.repository.PageRepository;
import cn.gongyan.learn.repository.RoleRepository;
import cn.gongyan.learn.repository.UserRepository;
import cn.gongyan.learn.service.UserService;
import cn.gongyan.learn.utils.JwtUtils;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PageRepository pageRepository;
    @Autowired
    ActionRepository actionRepository;
    @Value("${user.default.avatar}")
    private String defaultAvatar;
    @Value("${user.default.username}")
    private String defaultUsername;


    @Override
    public User register(RegisterDTO registerDTO) {
        try {
            if(registerDTO.getInvitedCode().compareTo("123")!=0){
                return null;
            }
            User example = new User();
            example.setUserUsername(registerDTO.getUserRegName());
            if(userRepository.findOne(Example.of(example)).orElse(null)!=null){
                //已经存在
                return null;
            }

            User user = new User();
            user.setUserId(IdUtil.simpleUUID());
            // 好像还缺少个用户名,用"exam_user_手机号"来注册：需要校验唯一性数据字段已经设置unique了，失败会异常地
            user.setUserUsername(registerDTO.getUserRegName());
            // 初始化昵称和用户名相同
            user.setUserNickname(user.getUserUsername());
            // 这里还需要进行加密处理，后续解密用Base64.decode()
            user.setUserPassword(Base64.encode(registerDTO.getPassword()));
            // 默认设置为学生身份，需要老师和学生身份地话需要管理员修改
            user.setUserRoleId(RoleEnum.STUDENT.getId());
            // 设置头像图片地址, 先默认一个地址，后面用户可以自己再改
            user.setUserAvatar(defaultAvatar);
            // 设置描述信息，随便设置段默认的
            user.setUserDescription("welcome to online exam system");

            userRepository.save(user);
            System.out.println(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace(); // 用户已经存在
            // 出异常，返回null，表示注册失败
            return null;
        }
    }

    @Override
    public String login(LoginQo loginQo) {
        User user;
        Integer tmp_role=3;
        if (LoginTypeEnum.TEACHER.getType().equals(loginQo.getLoginType())) {
            tmp_role=2;
        } else if(LoginTypeEnum.STUDENT.getType().equals(loginQo.getLoginType())){
            tmp_role=3;
        }

        User tmp_user=new User();
        tmp_user.setUserRoleId(tmp_role);
        tmp_user.setUserUsername(loginQo.getUserName());
        Example<User> example=Example.of(tmp_user);
        user = userRepository.findOne(example).orElse(null);
        System.out.println(user.toString());
        if (user != null) {
            // 如果user不是null即能找到，才能验证用户名和密码
            // 数据库存的密码
            String passwordDb = Base64.decodeStr(user.getUserPassword());
            // 用户请求参数中的密码
            String passwordQo = loginQo.getPassword();
            System.out.println(passwordDb);
            System.out.println(passwordQo);
            if (passwordQo.equals(passwordDb)) {
                // 如果密码相等地话说明认证成功,返回生成的token，有效期为一天
                return JwtUtils.genJsonWebToken(user);
            }
        }
        return null;
    }

    @Override
    public UserVo getUserInfo(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        UserVo userVo = new UserVo();
        assert user != null;
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public UserInfoVo getInfo(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        UserInfoVo userInfoVo = new UserInfoVo();
        // 1.尽可能的拷贝属性
        BeanUtils.copyProperties(user, userInfoVo);
        Integer roleId = user.getUserRoleId();
        Role role = roleRepository.findById(roleId).orElse(null);
        assert role != null;
        String roleName = role.getRoleName();

        // 2.设置角色名称
        userInfoVo.setRoleName(roleName);

        // 3.设置当前用户的角色细节
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role, roleVo);

        // 4.设置角色的可访问页面
        String rolePageIds = role.getRolePageIds();
        String[] pageIdArr = rolePageIds.split("-");
        List<PageVo> pageVoList = new ArrayList<>();
        for (String pageIdStr : pageIdArr) {
            // 获取页面的id
            Integer pageId = Integer.parseInt(pageIdStr);

            // 4.1 向Role中添加Page
            Page page = pageRepository.findById(pageId).orElse(null);
            PageVo pageVo = new PageVo();
            BeanUtils.copyProperties(page, pageVo);

            // 4.2 向Page中添加action
            List<ActionVo> actionVoList = new ArrayList<>();
            String actionIdsStr = page.getActionIds();
            String[] actionIdArr = actionIdsStr.split("-");
            for (String actionIdStr : actionIdArr) {
                Integer actionId = Integer.parseInt(actionIdStr);
                Action action = actionRepository.findById(actionId).orElse(null);
                ActionVo actionVo = new ActionVo();
                assert action != null;
                BeanUtils.copyProperties(action, actionVo);
                actionVoList.add(actionVo);
            }
            // 设置actionVoList到pageVo中，然后把pageVo加到pageVoList中
            pageVo.setActionVoList(actionVoList);
            // 设置pageVoList，下面再设置到RoleVo中
            pageVoList.add(pageVo);
        }
        // 设置PageVo的集合到RoleVo中
        roleVo.setPageVoList(pageVoList);
        // 最终把PageVo设置到UserInfoVo中，这样就完成了拼接
        userInfoVo.setRoleVo(roleVo);
        return userInfoVo;
    }

    @Override
    public Integer setUserInfo(String userId,SettingDTO settingDTO) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            user.setUserEmail(settingDTO.getEmail());
            if(settingDTO.getAvartar()!=null)
                user.setUserAvatar(settingDTO.getAvartar());
            user.setUserNickname(settingDTO.getNickName());
            user.setUserDescription(settingDTO.getBio());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
