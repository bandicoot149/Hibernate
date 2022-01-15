package com.shop.pages;

import com.shop.model.Promotion;
import com.shop.model.Shop;
import com.shop.model.customer.Customer;
import com.shop.model.generators.*;
import com.shop.model.good.Good;
import com.shop.model.good.accessory.Accessory;
import com.shop.model.good.bike.Bike;
import com.shop.model.good.component.Component;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.GoodRepository;
import com.shop.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

import static com.shop.model.good.GoodStatus.IN_STOCK;
import static com.shop.model.good.GoodStatus.SOLD_OUT;

@Controller
public class PagesController {
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PromotionRepository promotionRepository;

    private Shop shop = new Shop();
    private List<Customer> customers = new ArrayList<>();
    private List<Promotion> promotions = new ArrayList<>();


    // GET http://localhost:8080/
    @GetMapping("/")
    public String index(Model model) {
        return "index"; //
    }

    @GetMapping("/components")
    public String componentsPage(Model model) {
        model.addAttribute("inStockComponents", goodRepository.findAllComponentsByStatus(IN_STOCK));
        model.addAttribute("soldComponents", goodRepository.findAllComponentsByStatus(SOLD_OUT));
        return "components";
    }

    @GetMapping("/bikes")
    public String bikesPage(Model model) {
        model.addAttribute("inStockBikes", goodRepository.findAllBikesByStatus(IN_STOCK));
        model.addAttribute("soldBikes", goodRepository.findAllBikesByStatus(SOLD_OUT));
        return "bikes";
    }

    @GetMapping("/accessories")
    public String accessoriesPage(Model model) {
        model.addAttribute("inStockAccessories", goodRepository.findAllAccessoriesByStatus(IN_STOCK));
        model.addAttribute("soldAccessories", goodRepository.findAllAccessoriesByStatus(SOLD_OUT));
        return "accessories";
    }

    @GetMapping("/promotions")
    public String promotionsPage(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        return "promotions";
    }

    @GetMapping("/customers")
    public String customersPage(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/statistic")
    public String statisticPage(Model model) {
        HashMap<Customer, Double> topBuyers = shop.getTopBuyersList(goodRepository.findAllByStatus(SOLD_OUT));
        model.addAttribute("customers", topBuyers);
        model.addAttribute("sum", topBuyers.entrySet().stream().map(g -> g.getValue())
                .reduce(Double::sum)
                .get());
        return "statistic";
    }

    @GetMapping("/customer/{id}")
    public String customersPage(@PathVariable("id") String idStr,
                                Model model) {
        UUID id = UUID.fromString(idStr);
        Optional<Customer> customer = customerRepository.findById(id);
        List<Bike> bikes = new ArrayList<>();
        List<Component> components = new ArrayList<>();
        List<Accessory> accessories = new ArrayList<>();

        bikes.addAll(goodRepository.findAllBikesByCustomerId(id));
        components.addAll(goodRepository.findAllComponentsByCustomerId(id));
        accessories.addAll(goodRepository.findAllAccessoriesByCustomerId(id));
        model.addAttribute("soldBikes", bikes);
        model.addAttribute("soldComponents", components);
        model.addAttribute("soldAccessories", accessories);
        model.addAttribute("customer", customer.get());
        return "customer";
    }

    @GetMapping("/work")
    public String shopWork(Model model) {
        shop = Shop.builder()
                .balance(0)
                .accessories(goodRepository.findAllAccessories())
                .bikes(goodRepository.findAllBikes())
                .components(goodRepository.findAllComponents())
                .promotions(promotionRepository.findAll())
                .build();
        customers.addAll(customerRepository.findAll());
        promotions.addAll(promotionRepository.findAll());
        goodRepository.saveAll(shop.simulateWork(customers));
        return "redirect:/";
    }

    @GetMapping("/dataGen")
    public String dataGeneration(Model model) {
        return "dataGen";
    }

    @PostMapping("/dataGen/run")
    public String generateData(
            @RequestParam("bikesCount") int bikesCount,
            @RequestParam("accessoriesCount") int accessoriesCount,
    @RequestParam("componentsCount") int componentsCount,
    @RequestParam("customersCount") int customersCount,
    @RequestParam("promotionsCount") int promotionsCount
    ) {
        List<Good> goods = new ArrayList<>();
        List<Promotion> promotions = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();


        for (int i = 0; i < bikesCount; i++) {
            goods.add(BikeGenerator.generate());
        }
        for (int i = 0; i < accessoriesCount; i++) {
            goods.add(AccessoryGenerator.generate());
        }
        for (int i = 0; i < componentsCount; i++) {
            goods.add(ComponentGenerator.generate());
        }
        for (int i = 0; i < promotionsCount; i++) {
            promotions.add(PromotionGenerator.generate());
        }
        for (int i = 0; i < customersCount; i++) {
            customers.add(CustomerGenerator.generate());
        }

        goodRepository.saveAll(goods);
        promotionRepository.saveAll(promotions);
        customerRepository.saveAll(customers);
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clearDatabases(Model model) {
        goodRepository.deleteAll();
        customerRepository.deleteAll();
        promotionRepository.deleteAll();
        return "redirect:/";
    }

}
