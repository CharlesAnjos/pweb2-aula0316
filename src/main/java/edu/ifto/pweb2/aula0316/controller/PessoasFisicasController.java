package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.PessoaFisica;
import edu.ifto.pweb2.aula0316.model.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoasfisicas")
public class PessoasFisicasController {

  @Autowired
  PessoaFisicaRepository repository;

  public PessoasFisicasController(){
    PessoaFisicaRepository repository = new PessoaFisicaRepository();
  }

  @GetMapping("/form")
  public String form(PessoaFisica pessoaFisica){
    return "/pessoasfisicas/form";
  }

  @GetMapping("/list")
  public ModelAndView listar(ModelMap model) {
    model.addAttribute("pessoasFisicas", repository.pessoasFisicas());
    return new ModelAndView("/pessoasfisicas/list", model);
  }

  @PostMapping("/save")
  public ModelAndView save(PessoaFisica pessoaFisica){
    repository.save(pessoaFisica);
    return new ModelAndView("redirect:/pessoasfisicas/list");
  }

  @GetMapping("/remove/{id}")
  public ModelAndView remove(@PathVariable("id") Long id){
    repository.remove(id);
    return new ModelAndView("redirect:/pessoasfisicas/list");
  }

  @GetMapping("/edit/{id}")
  public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
    var pessoaFisica = repository.pessoaFisica(id);
    model.addAttribute("pessoaFisica", pessoaFisica);
    return new ModelAndView("/pessoasfisicas/form", model);
  }

  @PostMapping("/update")
  public ModelAndView update(PessoaFisica pessoaFisica) {
    repository.update(pessoaFisica);
    return new ModelAndView("redirect:/pessoasfisicas/list");
  }

}