package com.hippomanager.backend.Controller;

import com.hippomanager.backend.Mapper.entity.Ord;
import com.hippomanager.backend.Mapper.entity.Product;
import com.hippomanager.backend.Mapper.entity.User;
import com.hippomanager.backend.service.IOrdService;
import com.hippomanager.backend.service.IProductService;
import com.hippomanager.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DataViewController {
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    IOrdService ordService;
    @RequestMapping(value = "/data-view.html")//测试方法
    public String dataViewPage(Model model){
//        List<User> userList = userService.selectUserAll();
//        List<Product> productList=productService.selectProductAll();
//        List<Ord> ordList=ordService.selectOrdAll();
//        model.addAttribute("userNum",userList.size());
//        model.addAttribute("productNum",productList.size());
//        model.addAttribute("ordNum",ordList.size());
//        System.out.println(userList.size());

        return "index/data-view";
    }

}
