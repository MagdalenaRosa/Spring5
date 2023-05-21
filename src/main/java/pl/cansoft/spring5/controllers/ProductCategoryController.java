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
import pl.cansoft.spring5.models.ProductCategory;
import pl.cansoft.spring5.services.ProductCategoryService;


/**
 * Request (zaytanie) // przechwytywany przez Controller
 * Logika biznesowa (Serwis) // delegowana do serwisu + oczekiwanie na zwrotkę
 * Response (odpowiedź) // serwuje odp. do usera danymi z serwisu
 */
@RequiredArgsConstructor // DI
@Controller // MVC
public class ProductCategoryController {

    /**
     * IoC - inversion of control (odwrócenie sterowania)
     * DI - dependency injection (wstrzykiwanie zależności)
     */
    final ProductCategoryService productCategoryService; // dependency (zależność)

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("db", productCategoryService.findAllCategoryProducts());
        model.addAttribute("actionUri", "/saveCategory");
        return "category/categories";
    }

    @GetMapping("/categoryDetails/{categoryId}") // /categoryDetails/1
    public String showCategory(Model model, @PathVariable Integer categoryId) {
        var optionalProductCategory = productCategoryService.findProductCategoryById(categoryId);
        if (optionalProductCategory.isEmpty()) {
            // todo: error page?
        } else {
            var product = optionalProductCategory.get();
            model.addAttribute("product", product);
        }

        return "category/category"; // ścieżka do pliku HTML /resources/templates/...(ścieżka do pliku)
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@Valid ProductCategory productCategory, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("category", productCategory);
        } else {
            productCategoryService.insertProductCategory(productCategory);
        }
        return "redirect:/categories"; // zwracany jest adres URL wskazany po ":" (redirect:ADRES_URL)
    }

    @GetMapping("/editCategory/{categoryId}")
    public String showEditCategoryForm(@PathVariable Integer categoryId, Model model) {
        var optionalProductCategory = productCategoryService.findProductCategoryById(categoryId);
        if (optionalProductCategory.isPresent()) {
            var category = optionalProductCategory.get();
            model.addAttribute("actionUri", "/editedCategory/" + categoryId);
            model.addAttribute("category", category);
        } else {
            // todo: optional jest nullem :)
            // optionalProductCategory.get(); // null pointer exception
        }

        return "category/edit-category"; // jaki html?
    }

    @PostMapping("/editedCategory/{categoryId}")
    public String saveEditedCategory(@PathVariable Integer categoryId, @Valid ProductCategory categoryForm, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            return "redirect:/editCategory/" + categoryId;
        } else {
            productCategoryService.updateProductCategory(categoryId, categoryForm);
            return "redirect:/categories";
        }
    }

    @GetMapping("/removeCategory/{categoryId}") // /removeCategory/1
    public String removeCategory(@PathVariable Integer categoryId) {
        productCategoryService.removeProductCategory(categoryId);
        return "redirect:/categories";
    }
}
