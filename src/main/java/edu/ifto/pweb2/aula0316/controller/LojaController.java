package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.entity.*;
import edu.ifto.pweb2.aula0316.model.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    CidadeRepository cidadeRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    Venda venda;

    public LojaController(){
        produtoRepository = new ProdutoRepository();
        itemRepository = new ItemRepository();
        vendaRepository = new VendaRepository();
        pessoaFisicaRepository = new PessoaFisicaRepository();
        pessoaJuridicaRepository = new PessoaJuridicaRepository();
        cidadeRepository = new CidadeRepository();
        enderecoRepository = new EnderecoRepository();
    }

    @GetMapping("/")
    public ModelAndView loja(Endereco endereco, Item item, ModelMap model,@RequestParam(name="tipo",required=false) String tipo) {
        model.addAttribute("produtos", produtoRepository.produtos());
        model.addAttribute("tipo", tipo);
        if (venda.getPessoa() != null) {
            model.addAttribute("pessoaSelecionada", venda.getPessoa().getNome());
        } else {
            model.addAttribute("pessoaSelecionada", "Nenhum cliente selecionado. Selecione um cliente antes de continuar");
        }
        if (venda.getEnderecoEntrega() != null) {
            model.addAttribute("enderecoEntrega", venda.getEnderecoEntrega().toString());
        } else {
            model.addAttribute("enderecoEntrega", "Nenhum endereço de entrega disponível. Selecione um cliente ou entre com um endereço de entrega abaixo");
        }
        model.addAttribute("pessoasFisicas", pessoaFisicaRepository.pessoasFisicas());
        model.addAttribute("pessoasJuridicas", pessoaJuridicaRepository.pessoasJuridicas());
        model.addAttribute("cidades", cidadeRepository.cidades());
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
        return new ModelAndView("redirect:/loja/");
    }

    @PostMapping("/saveendereco")
    public ModelAndView saveEndereco(Endereco endereco) {
        Cidade c = cidadeRepository.cidade(endereco.getCidade().getId());
        endereco.setCidade(c);
        Endereco e = enderecoRepository.saveAndReturn(endereco);
        venda.setEnderecoEntrega(e);
        return new ModelAndView("redirect:/loja/");
    }

    @GetMapping("/removeitem/{index}")
    public ModelAndView removeitem(@PathVariable("index") int index){
        venda.getItems().remove(index);
        return new ModelAndView("redirect:/loja/");
    }

    @PostMapping("/setpf")
    public ModelAndView setPessoaFisica(@ModelAttribute("pessoa") PessoaFisica pessoa){
        pessoa = pessoaFisicaRepository.pessoaFisica(pessoa.getId());
        venda.setPessoa(pessoa);
        return new ModelAndView("redirect:/loja/");
    }

    @PostMapping("/setpj")
    public ModelAndView setPessoaJuridica(@ModelAttribute("pessoa") PessoaJuridica pessoa){
        pessoa = pessoaJuridicaRepository.pessoaJuridica(pessoa.getId());
        venda.setPessoa(pessoa);
        return new ModelAndView("redirect:/loja/");
    }

    @Transactional
    @GetMapping("/finalizar")
    public ModelAndView finalizar(HttpSession session) {
        venda.setData(LocalDateTime.now());
        vendaRepository.save(venda);
        session.invalidate();
        return new ModelAndView("redirect:/vendas/list");
    }
}
