package edu.ifto.pweb2.aula0316;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoHDB implements ConexaoJDBC{
    @Override
    public Connection criarConexao() {
        try {
            //carregar o driver de conexão
            Class.forName("org.h2.Driver");
            //parâmetros
            String url = "jdbc:h2:mem:testdb";
            String usuario = "root";
            String senha = "123123";
            //retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexaoHDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}