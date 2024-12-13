package ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ecourse.model.Categories;
import ecourse.repository.CategoriesRepository;



@Controller
public class CategoriesController {
    @Autowired private CategoriesRepository categoriesRepository;
    @GetMapping("/admin/categories")
    public String index(Model model) {
        model.addAttribute("categories", categoriesRepository.findAll());
        return "admin/categories/index";
    }
    @GetMapping("/admin/categories/add")
    public String add() {
        return "admin/categories/add";
    }
    @PostMapping("/admin/categories/add")
    public String add(@ModelAttribute Categories post) {
        categoriesRepository.save(post);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("categories", categoriesRepository.findById(id).orElse(null));
        return "admin/categories/edit";
    }
    @PostMapping("/admin/categories/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute Categories post) {
        post.setCategoryId(id);
        categoriesRepository.save(post);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoriesRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
}
