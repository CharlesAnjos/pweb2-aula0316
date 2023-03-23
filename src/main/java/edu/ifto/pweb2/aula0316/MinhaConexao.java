package edu.ifto.pweb2.aula0316;

import java.sql.Connection;

public class MinhaConexao{

    public static Connection conexao(){
        ConexaoJDBC conexao = new ConexaoHDB();
        return conexao.criarConexao();
    }

}