package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CustomerService;
import service.ICustomerService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")
    public String index(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers",customerList);
        return "/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model){
        Optional<Customer> customeredit = Optional.ofNullable(customerService.findById(id));
        customeredit.ifPresent(customer -> model.addAttribute("customer",customer));
        return "/edit";

    }
    @RequestMapping(value = "/add")
    public String update(Model model){
        model.addAttribute("customer",new Customer());
        return "/create";

    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String remove(@RequestParam("id")int id,Model model){
        customerService.remove(id);
        return "redirect:/";

    }
}
