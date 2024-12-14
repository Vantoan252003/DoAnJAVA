package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Payments;
import ecourse.repository.PaymentsRepository;

@Controller
public class PaymentsController {
    
    @Autowired 
    private PaymentsRepository paymentsRepository;
    
    @GetMapping("/admin/payments")
    public String index(Model model){
        model.addAttribute("list", paymentsRepository.findAll());
        return "admin/payments/index";
    }

    // Thêm dữ liệu payment
    @GetMapping("/admin/payments/add")
    public String add(){
        return "admin/payments/add";
    }
   
    @PostMapping("/admin/payments/add")
    public String add(@ModelAttribute Payments payments){
        paymentsRepository.save(payments);
        return "redirect:/admin/payments";
    }
    
    // Sửa dữ liệu payment
    @GetMapping("/admin/payments/edit/{paymentsId}")
    public String edit(@PathVariable("paymentsId") short paymentId, Model model) {
        Payments payment = paymentsRepository.findById(paymentId).orElse(null);
        model.addAttribute("payment", payment);
        return "admin/payments/edit";
    }

    @PostMapping("/admin/payments/edit/{paymentsId}")
    public String update(@PathVariable("paymentsId") short paymentId, @ModelAttribute Payments post) {
        post.setPaymentsId(paymentId);
        paymentsRepository.save(post);
        return "redirect:/admin/payments";
    }
    
    // Xóa payment
    @GetMapping("/admin/payments/delete/{paymentsId}")
    public String delete(@PathVariable("paymentsId") short paymentId) {
        paymentsRepository.deleteById(paymentId);
        return "redirect:/admin/payments";
    }
}
