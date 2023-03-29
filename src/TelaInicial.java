import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.lang.String.valueOf;

public class TelaInicial extends JFrame{

    private JTextField TextCPF;
    private JPasswordField PassSenha;
    private JButton btnLogar;
    private JButton btnRecuperarSenha;
    private JButton btnCadastro;
    private JPanel PNLTelaInicial;

    final String URL = "jdbc:mysql://localhost:3306/bancoOrion";
    final String USER = "root";
    final String PASSWORD = "root";
    final String ValidaUsuario = "SELECT * FROM usuario WHERE CPF = ? AND senha = ?";


public TelaInicial() {
    AddListeners();
    IniciarComponentes();
    Conecta();
}

    private void AddListeners() {
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro();
                cadastro.setVisible(true);
                dispose();
            }
        });
    }

    public void Conecta() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtValidar;

            stmtValidar = connection.prepareStatement(ValidaUsuario);

            btnLogar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String cpf = TextCPF.getText();
                    int senha = Integer.parseInt(PassSenha.getText());
                    try{
                        stmtValidar.setString(1, cpf);
                        stmtValidar.setInt(2,senha);

                        ResultSet rs = stmtValidar.executeQuery();
                        System.out.println("dados consultado");
                        System.out.println(cpf);
                        System.out.println(senha);

                        int count = 0;
                        while (rs.next()) {
                            count = 1;
                        }

                        if (count > 0) {
                            System.out.println("Usuário autenticado com sucesso!");
                            TelaPrincipal home = new TelaPrincipal();
                            home.setVisible(true);
                            dispose();
                        } else {
                            System.out.println("Usuário ou senha inválidos.");
                        }

                    } catch (SQLException ex) {
                        System.out.println("Erro ao consultar");
                    }
                }
            });
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    public void IniciarComponentes(){
        JPanel TelaInicial = new JPanel();
        setSize(1000, 750);
        setContentPane(PNLTelaInicial);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Orion Bank");
        setVisible(true);
    }
    public static void main(String[] args) {
        TelaInicial TelaCadAluno = new TelaInicial();
    }
}
