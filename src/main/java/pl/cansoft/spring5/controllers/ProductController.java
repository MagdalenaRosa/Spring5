package pl.cansoft.spring5.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.cansoft.spring5.models.Product;
import pl.cansoft.spring5.services.ProductService;

/**
 * CRUD - create / read / update / delete
 * MVC - model (klasy z danymi) | view (template html) | controller (zarządca ruchu)
 * Web 1.0 - read
 * Web 2.0 - read & write
 */
@RequiredArgsConstructor
@Controller
public class ProductController {

    final ProductService productService;

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "WebCoders - Products");
        model.addAttribute("db", productService.findAllProducts());
        model.addAttribute("actionUri", "/saveProduct");
        return "product/products";
    }

    @GetMapping("/productDetails/{productId}")
    public String productDetails(@PathVariable Integer productId, Model model) {
        model.addAttribute("id", productId);
        bindProductToModel(productId, model);
        return "product/product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product productFrom, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", productFrom);
        } else {
            productService.insertProduct(productFrom);
        }
        return "redirect:/";
    }

    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(@PathVariable Integer productId, Model model) {
        model.addAttribute("actionUri", "/editedProduct/" + productId);
        bindProductToModel(productId, model);
        return "product/edit-product";
    }

    @PostMapping("/editedProduct/{productId}") // /editedProduct/4
    public String saveEditedProduct(@PathVariable Integer productId, @Valid Product productFrom, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            return "redirect:/editProduct/" + productId;
        } else {
            productService.updateProduct(productFrom, productId);
            return "redirect:/";
        }
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        productService.removeProduct(productId);
        return "redirect:/";
    }

    private void bindProductToModel(Integer productId, Model model) {
        var optionalProduct = productService.findProductById(productId);
        if (optionalProduct.isEmpty()) {
            // todo: nie odnalazłem elementu!
        } else {
            var product = optionalProduct.get();
            model.addAttribute("product", product);
        }
    }
}
