package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.PessoaJuridica;
import edu.ifto.pweb2.aula0316.model.repository.PessoaJuridicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoasjuridicas")
public class PessoasJuridicasController {

  @Autowired
  PessoaJuridicaRepository repository;

  public PessoasJuridicasController(){
    PessoaJuridicaRepository repository = new PessoaJuridicaRepository();
  }

  @GetMapping("/form")
  public String form(PessoaJuridica pessoaJuridica){
    return "/pessoasjuridicas/form";
  }

  @GetMapping("/list")
  public ModelAndView listar(ModelMap model) {
    model.addAttribute("pessoasJuridicas", repository.pessoasJuridicas());
    return new ModelAndView("/pessoasjuridicas/list", model);
  }

  @PostMapping("/save")
  public ModelAndView save(PessoaJuridica pessoaJuridica){
    repository.save(pessoaJuridica);
    return new ModelAndView("redirect:/pessoasjuridicas/list");
  }

  @GetMapping("/remove/{id}")
  public ModelAndView remove(@PathVariable("id") Long id){
    repository.remove(id);
    return new ModelAndView("redirect:/pessoasjuridicas/list");
  }

  @GetMapping("/edit/{id}")
  public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
    var pessoaJuridica = repository.pessoaJuridica(id);
    model.addAttribute("pessoaJuridica", pessoaJuridica);
    return new ModelAndView("/pessoasjuridicas/form", model);
  }

  @PostMapping("/update")
  public ModelAndView update(PessoaJuridica pessoaJuridica) {
    repository.update(pessoaJuridica);
    return new ModelAndView("redirect:/pessoasjuridicas/list");
  }

}