package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.*;
import edu.ifto.pweb2.aula0316.model.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("loja")
@Scope("request")
public class LojaController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    Venda venda;

    public LojaController(){
        produtoRepository = new ProdutoRepository();
        itemRepository = new ItemRepository();
        vendaRepository = new VendaRepository();
        pessoaFisicaRepository = new PessoaFisicaRepository();
        pessoaJuridicaRepository = new PessoaJuridicaRepository();
    }
    @GetMapping("/list")
    public ModelAndView loja(Item item, ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        return new ModelAndView("/loja/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Item item) {
        Produto p = produtoRepository.produto(item.getProduto().getId());
        item.setProduto(p);
        if(item.getQuantidade() > 0 || item.getQuantidade() != null) {
            List<Item> itemsVenda;
            if(venda.getItems() == null) {
                itemsVenda = new ArrayList<>();
                venda.setItems(itemsVenda);
            } else {
                itemsVenda = venda.getItems();
            }
            itemsVenda.add(item);
            item.setVenda(venda);
        }
        return new ModelAndView("redirect:/loja/list");
    }

    @GetMapping("/removeitem/{index}")
    public ModelAndView removeitem(@PathVariable("index") int index){
        venda.getItems().remove(index);
        return new ModelAndView("redirect:/loja/list");
    }

    @Transactional
    @GetMapping("/finalizar")
    public ModelAndView finalizar(HttpSession session) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.pessoaFisica(1L);
        venda.setData(LocalDateTime.now());
        venda.setPessoa(pessoaFisica);
        vendaRepository.save(venda);
        session.invalidate();
        return new ModelAndView("redirect:/vendas/list");
    }
}
