package com.prashan.springit.controllers;

import com.prashan.springit.models.Link;
import com.prashan.springit.repositories.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LinkController {
    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    private LinkRepository linkRepository;

    @Autowired
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    // listing all links
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("links", linkRepository.findAll());
        return "link/links";
    }

    /**
     * CRUD Operations
     */
    @GetMapping("link/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if(link.isPresent()){
            model.addAttribute("link", link.get());
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
       model.addAttribute("link", new Link());
       return "link/submit";
   }

   @PostMapping("/link/submit")
   public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
       if(bindingResult.hasErrors()){
           logger.info("Validation errors were found while submitting a new link");
           model.addAttribute("link", link);
           return "link/submit";
       }else {
            linkRepository.save(link);
            logger.info("New link was saved successfully");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/{id}";
       }
   }
}
