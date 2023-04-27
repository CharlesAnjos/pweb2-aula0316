package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.Item;
import edu.ifto.pweb2.aula0316.model.entity.Produto;
import edu.ifto.pweb2.aula0316.model.entity.Venda;
import edu.ifto.pweb2.aula0316.model.repository.ItemRepository;
import edu.ifto.pweb2.aula0316.model.repository.ProdutoRepository;
import edu.ifto.pweb2.aula0316.model.repository.VendaRepository;
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
@RequestMapping("loja")
public class LojaController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    VendaRepository vendaRepository;

    public LojaController(){
        produtoRepository = new ProdutoRepository();
        itemRepository = new ItemRepository();
        vendaRepository = new VendaRepository();
    }
    @GetMapping("/loja")
    public ModelAndView loja(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        return new ModelAndView("/loja/loja", model);
    }

    @PostMapping("/saveItem")
    public ModelAndView save(Item item){
        System.out.println(item);
        itemRepository.save(item);
        return new ModelAndView("redirect:/loja/loja");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        produtoRepository.remove(id);
        return new ModelAndView("redirect:/produtos/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/venda/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        var venda = vendaRepository.venda(id);
        model.addAttribute("venda", venda);
        return new ModelAndView("/loja/venda", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }
}
