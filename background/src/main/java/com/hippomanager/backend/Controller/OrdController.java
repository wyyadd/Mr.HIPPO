package com.hippomanager.backend.Controller;

import com.hippomanager.backend.Mapper.entity.Ord;
import com.hippomanager.backend.Mapper.entity.Product;
import com.hippomanager.backend.service.IOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrdController {
    @Autowired
    IOrdService ordService;

    @RequestMapping(value="/ordInformation")
    public String ordInformationPage(Model model){
        List<Ord> ordList = ordService.selectOrdAll();
        System.out.println(ordList);
        model.addAttribute("ordList",ordList);
        return "index/ord-information";
    }
}
