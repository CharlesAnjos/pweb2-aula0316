package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;

@Transactional
@Controller
@RequestMapping("vendas")
public class VendasController {

  @Autowired
  VendaRepository vendaRepository;

  public VendasController(){
    vendaRepository = new VendaRepository();
  }

  @GetMapping("/list")
  public ModelAndView list(ModelMap model) {
    model.addAttribute("vendas", vendaRepository.vendas());
    return new ModelAndView("/vendas/list", model);
  }

  @GetMapping("/remove/{id}")
  public ModelAndView remove(@PathVariable("id") Long id){
    vendaRepository.remove(id);
    return new ModelAndView("redirect:/vendas/list");
  }

  @GetMapping("/venda/{id}")
  public ModelAndView venda(@PathVariable("id") Long id, ModelMap model) {
    var venda = vendaRepository.venda(id);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    model.addAttribute("venda", venda);
    model.addAttribute("formatter", formatter);
    return new ModelAndView("/vendas/venda", model);
  }
}

