package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.Pessoa;
import edu.ifto.pweb2.aula0316.model.repository.PessoaRepository;
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
@RequestMapping("pessoas")
public class PessoasController {

  @Autowired
  PessoaRepository repository;

  public PessoasController(){
    repository = new PessoaRepository();
  }

  /**
   * @param pessoa necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
   * @return
   */
  @GetMapping("/form")
  public String form(Pessoa pessoa){
    return "/pessoas/form";
  }

  @GetMapping("/list")
  public ModelAndView listar(ModelMap model) {
    model.addAttribute("pessoas", repository.pessoas());
    return new ModelAndView("/pessoas/list", model);
  }

  @PostMapping("/save")
  public ModelAndView save(Pessoa pessoa){
    System.out.println(pessoa);
    repository.save(pessoa);
    return new ModelAndView("redirect:/pessoas/list");
  }

  /**
   * @param id
   * @return
   * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
   */
  @GetMapping("/remove/{id}")
  public ModelAndView remove(@PathVariable("id") Long id){
    repository.remove(id);
    return new ModelAndView("redirect:/pessoas/list");
  }

  /**
   * @param id
   * @return
   * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
   */
  @GetMapping("/edit/{id}")
  public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
    var pessoa = repository.pessoa(id);
    model.addAttribute("pessoa", pessoa);
    return new ModelAndView("/pessoas/form", model);
  }

  @PostMapping("/update")
  public ModelAndView update(Pessoa pessoa) {
    repository.update(pessoa);
    return new ModelAndView("redirect:/pessoas/list");
  }

}