public class Bd {
        final String URL = "jdbc:mysql://localhost:3306/bancoOrion";
        final String USER = "root";
        final String PASSWORD = "root";
        final String ValidaUsuario = "SELECT * FROM usuario WHERE CPF = ? AND senha = ?";
        final String RecuperaSenha = "SELECT * FROM usuario WHERE CPF = ?";
        final String Consultar = "SELECT * FROM usuario WHERE CPF = ?";
        final String AlteraSenha = "UPDATE usuario SET senha = ? WHERE CPF = ?";
        final String AlteraSaldo = "UPDATE usuario SET saldo = ? WHERE CPF = ?";

        final String RegistrarCliente = "UPDATE ";
        final String RegistrarEndereco = "UPDATE ";
}