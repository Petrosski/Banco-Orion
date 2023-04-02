import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame{
    private JTextField TextNome;
    private JTextField TextSobrenome;
    private JTextField TextCPF;
    private JTextField TextEmail;
    private JButton próximoButton;
    private JPasswordField TextSenha;
    private JPanel PNLTelaCadastro;
    private JPasswordField TextConfirmeSenha;
    public static String nomeCliente;
    public static String sobrenomeCliente;
    public static String cpfCliente;
    public static String emailCliente;
    public static String senhaCliente;
    public static String confirmaSenhaCliente;

    public TelaCadastro() {
        AddListeners();
        IniciarComponentes();
    }
        public void AddListeners () {

            próximoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nomeCliente = TextNome.getText();
                    sobrenomeCliente = TextSobrenome.getText();
                    cpfCliente = TextCPF.getText();
                    emailCliente = TextEmail.getText();
                    senhaCliente = TextSenha.getText();
                    confirmaSenhaCliente = TextConfirmeSenha.getText();
                    TelaCadEndereco telaCadEndereco = new TelaCadEndereco();
                    telaCadEndereco.setVisible(true);
                    dispose();
                }
            });
        }

        public void IniciarComponentes () {
            JPanel TelaCadastro = new JPanel();
            setExtendedState(MAXIMIZED_BOTH);
            setContentPane(PNLTelaCadastro);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Cadastro de Cliente");
            setVisible(true);
        }
    }


