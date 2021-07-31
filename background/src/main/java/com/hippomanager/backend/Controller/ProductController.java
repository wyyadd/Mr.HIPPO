package com.hippomanager.backend.Controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hippomanager.backend.Mapper.entity.Product;
import com.hippomanager.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping(value = "/backend")
public class ProductController {
    @Autowired
    IProductService productService;

    @RequestMapping(value = "/product-add.do")//用户在AddInputPage页面点击提交后，本方法向数据库写入数据
    public ModelAndView productAdd(Product product) throws Exception{
        int result = productService.addProduct(product);
        if(result == 0){
            throw new Exception("保存失败");
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("product",product);
        mv.setViewName("index/product-add-result");
        System.out.println(product);
        return  mv;
    }

    @RequestMapping(value = "/product-delete.do")//删除商品
    public String productDelete(long id)throws Exception{
        int result=productService.deleteProduct(id);
        if(result==0){
            throw new Exception("删除失败");
        }
        // ModelAndView mv=new ModelAndView();
        //mv.setViewName("index/product-manage");
        return "redirect:/product-manage";
    }

    @RequestMapping(value = "/productManage")//商品管理
    public String productManagePage(Model model, int pageNum){
          IPage<Product> productPage = productService.GetPageProduct(pageNum);
            List<Product> realProductPage=productPage.getRecords();
            System.out.println(realProductPage);
            model.addAttribute("productList",realProductPage);
            return "index/product-manage";
    }

    @RequestMapping(value = "/productAdd")//添加商品
    public String productAddInputPage(){
        return "index/product-add";
    }

    @RequestMapping(value = "/danyeDetail")
    public String danyeDetail(){
        return "index/danye-detail";
    }


    @RequestMapping(value = "/changeProduct")
    public String productChangeStatus(Model model,long id){
        Product changedProduct = productService.selectProductById(id);
        System.out.println(changedProduct);
        model.addAttribute("changedProduct",changedProduct);
        return "index/product-change";
    }

//    @RequestMapping(value = "/product-update.do")
//    public ModelAndView productUpdate(Product product) throws Exception{
//        int result = productService.updateProductById(product);
//        if(result == 0){
//            throw new Exception("更新失败");
//        }
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("product",product);
//        mv.setViewName("index/product-add-result");
//        System.out.println(product);
//        return  mv;
//    }

}
