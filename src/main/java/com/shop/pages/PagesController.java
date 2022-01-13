package com.shop.pages;

import com.shop.model.Customer;
import com.shop.model.Promotion;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.GoodRepository;
import com.shop.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class PagesController {
    private final GoodRepository goodRepository;
    private final CustomerRepository customerRepository;
    private final PromotionRepository promotionRepository;

    public PagesController(GoodRepository goodRepository, CustomerRepository customerRepository, PromotionRepository promotionRepository) {
        this.goodRepository = goodRepository;
        this.customerRepository = customerRepository;
        this.promotionRepository = promotionRepository;
    }

    // GET http://localhost:8080/
    @GetMapping("/")
    public String index(Model model) {
        return "index"; //
    }

    @GetMapping("/goods")
    public String goodPage(Model model) {
        model.addAttribute("goods", goodRepository.findAll());
        return "goods";
    }
}
