package edu.ifto.pweb2.aula0316.controller;

import edu.ifto.pweb2.aula0316.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutosController {
    @Autowired
    ProdutoRepository repository;
}
