package ru.nikituz.simplespringcrudapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nikituz.simplespringcrudapp.dto.PersonDto;
import ru.nikituz.simplespringcrudapp.services.PersonService;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String people(Model model){
        model.addAttribute("people", this.personService.getAll());
        return "people";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") long id, Model model){
        model.addAttribute("person", this.personService.getById(id));
        return "person-info";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", this.personService.getById(id));
        return "person-edit";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new PersonDto());
        return "person-new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid PersonDto personDto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person-new";
        }
        this.personService.create(personDto);
        return "redirect:people";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid PersonDto updatedPersonDto,
                               BindingResult bindingResult, @PathVariable("id") long id){
        if(bindingResult.hasErrors()){
            return "person-info";
        }
        this.personService.updateById(id, updatedPersonDto);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") long id){
        this.personService.deleteById(id);
        return "redirect:/people";
    }
}
