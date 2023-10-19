package cz.uhk.ppro.aop.aopdemo.web;

import cz.uhk.ppro.aop.aopdemo.model.Person;
import cz.uhk.ppro.aop.aopdemo.repos.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    private IPersonRepository personRepository;

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //list all persons using peopleList view
    @GetMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("persons", personRepository.getAll());
        return "peopleList";
    }
    @GetMapping("/edit")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }
    @PostMapping("/edit")
    public String personSubmit(Person person) {
        personRepository.save(person);
        return "redirect:/list";
    }
}
