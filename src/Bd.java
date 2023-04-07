import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Bd {
        final String URL = "jdbc:mysql://localhost:3306/bancoOrion";
        final String USER = "root";
        final String PASSWORD = "root";

        public Connection getConexao(){
                try{
                        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
                        Statement stmt = conn.createStatement();
                        return conn;
                }catch (Exception e){
                        System.out.println("Erro ao conectar"+e.getMessage());
                }
                return null;
        }

        final String ValidaUsuario = "SELECT * FROM usuario WHERE CPF = ? AND senha = ?";
        final String RecuperaSenha = "SELECT * FROM usuario WHERE CPF = ?";
        final String Consultar = "SELECT * FROM usuario WHERE CPF = ?";
        final String AlteraSenha = "UPDATE usuario SET senha = ? WHERE CPF = ?";
        final String AlteraSaldo = "UPDATE usuario SET saldo = ? WHERE CPF = ?";
        final String ConsultaConta = "SELECT numConta FROM usuario WHERE CPF = ?";
        final String RegistrarCliente = "INSERT INTO bancoOrion.usuario (nome, sobrenome, CPF, email, senha, saldo) VALUES (?, ?, ?, ?, ?, 0)";
        final String RegistrarEndereco = "INSERT INTO bancoOrion.endereco (numConta, CEP, cidade, UF, bairro, rua, numeroCasa, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        final String ExcluirUsuario = "DELETE FROM usuario WHERE numConta = ?";
        final String ExcluirEndereco = "DELETE FROM endereco WHERE numConta = ?";
        final String CONSULTAR = "SELECT * FROM usuario";
}