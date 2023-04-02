import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadEndereco extends JFrame{
    private JTextField TextCEP;
    private JTextField TextCidade;
    private JTextField TextUF;
    private JTextField TextBairro;
    private JTextField TextRua;
    private JTextField TextNumCasa;
    private JButton btnRegistrar;
    private JTextField TextComplemento;
    private JPanel PNLTelaCadEndereco;

    private TelaCadastro telaCadastro = new TelaCadastro();
    String nome = telaCadastro.nomeCliente;
    String sobrenome = telaCadastro.sobrenomeCliente;
    String cpf = telaCadastro.cpfCliente;
    String email = telaCadastro.emailCliente;
    String senha = telaCadastro.senhaCliente;
    private Bd bd = new Bd();

    public TelaCadEndereco() {
        IniciarComponentes();
        IniciarComponentes();
        Conecta();
    }

    public void Conecta(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(bd.URL, bd.USER, bd.PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtRegistrarCliente;
            stmtRegistrarCliente = connection.prepareStatement(bd.RegistrarCliente);
            final PreparedStatement stmtRegistrarEndereco;
            stmtRegistrarEndereco = connection.prepareStatement(bd.RegistrarEndereco);
            btnRegistrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        } catch (SQLException e) {
            System.out.println("Erro ao conectar BD");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro");
        }
    }


    public void IniciarComponentes () {
        JPanel TelaCadEndereco = new JPanel();
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(PNLTelaCadEndereco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro de endereço");
        setVisible(true);
    }
}
