package com.hippomanager.backend.Controller;

import com.hippomanager.backend.Mapper.entity.User;
import com.hippomanager.backend.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping(value = "/backend")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = {"/index", "/"})
    public String indexPage(){
        return "index/index";
    }


//    @RequestMapping(value="/update")//更新用户信息
//    public String updateUser(User user){
//        userService.updateUser(user);
//        return"redirect:FirstHtml/user";
//    }

    public void deleteBYIdentity(char identity,List<User> userList){
        for(int i=userList.size()-1;i>=0;i--){
            if(userList.get(i).getIdentity()== identity){
                userList.remove(userList.get(i));
            }
        }
    }
//    @RequestMapping(value = "/welcome.html")//测试方法
//    public String welcomePage(){
//        return "index/welcome";
//    }

    @RequestMapping(value = "/userPage")//用户页面
    public String userPage(Model model){
        List<User> userList = userService.selectUserAll();
//        for(int i=userList.size()-1;i>=0;i--){
//            if(userList.get(i).getIdentity()=='m'){
//                userList.remove(userList.get(i));
//            }
//        }
        deleteBYIdentity('m', userList);
        System.out.println(userList);
        model.addAttribute("userList",userList);
        return "index/user-page";
    }

    @RequestMapping(value = "/managerPage")//管理员页面
    public String managerPage(Model model){
        List<User> managerList = userService.selectUserAll();
//        for(int i=managerList.size()-1;i>=0;i--){
//            if(managerList.get(i).getIdentity()=='u'){
//                managerList.remove(managerList.get(i));
//            }
//        }
        deleteBYIdentity('u', managerList);
        System.out.println(managerList);
        model.addAttribute("managerList",managerList);
        return "index/manager-page";
    }

    @RequestMapping(value = "/user-delete.do")//删除用户
    public String userDelete(long id) throws Exception{
        int result=userService.deleteUserById(id);
        if(result==0){
            throw new Exception("删除失败");
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/manager-delete.do")//删除管理员
    public String managerDelete(long id) throws Exception{
        int result=userService.deleteUserById(id);
        if(result==0){
            throw new Exception("删除失败");
        }
        return "redirect:/index";
    }

    @RequestMapping(value="/adminInformation")//个人页面
    public String managerPage(){
        return "index/admin-information";
    }

    @RequestMapping(value="/noNeed")//不能操作提示页面
    public String noNeedPage(){
        return "index/no-need";
    }

    @RequestMapping(value = "/selectUserByName.do")//根据用户名和状态选择数据
    public String selectUserByName(Model model, String username){
            List<User> userList = userService.selectByName(username);
//        for(int i=userList.size()-1;i>=0;i--){
//            if(userList.get(i).getIdentity()=='m'){
//                userList.remove(userList.get(i));
//            }
//        }
        deleteBYIdentity('m',userList);
        System.out.println(userList);
            model.addAttribute("userList",userList);
            return "index/user-page";
    }

    @RequestMapping(value = "/selectManagerByName.do")//根据用户名和状态选择数据
    public String selectManagerByName(Model model, String username){
        List<User> managerList = userService.selectByName(username);
//        for(int i=userList.size()-1;i>=0;i--){
//            if(userList.get(i).getIdentity()=='m'){
//                userList.remove(userList.get(i));
//            }
//        }
        deleteBYIdentity('u',managerList);
        System.out.println(managerList);
        model.addAttribute("managerList",managerList);
        return "index/manager-page";
    }

    @RequestMapping(value = "/user-status-change.do")//封禁、解封用户
    public String userUpdate(long id,long status) throws Exception{
        User user= userService.getUserById(id);
        if(status==1){
            user.setStatus(0);
            System.out.println("改成了0");
        }
        else {
            user.setStatus(1);
            System.out.println("改成了1");
        }
        int result=userService.changeUserStatus(user);
        if(result==0){
            throw new Exception("操作失败");
        }
        return "redirect:/index";
    }
}

