import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelaCadastro extends JFrame{
    private JTextField TextNome;
    private JTextField TextSobrenome;
    private JTextField TextCPF;
    private JTextField TextEmail;
    private JButton próximoButton;
    private JPasswordField TextSenha;
    private JPanel PNLTelaCadastro;
    private JPasswordField TextConfirmeSenha;
    private JButton btnSair;
    public static String nomeCliente;
    public static String sobrenomeCliente;
    public static String cpfCliente;
    public static String emailCliente;
    public String senhaCliente;
    public String confirmaSenhaCliente;
    private Bd bd;
    private Connection conn;

    public TelaCadastro() {
        //AddListeners();
        IniciarComponentes();
        ConectaCadastro();
    }
    /*public void AddListeners(){
        próximoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadEndereco telaCadEndereco = new TelaCadEndereco();
                telaCadEndereco.setVisible(true);
                dispose();
                nomeCliente = TextNome.getText();
                sobrenomeCliente = TextSobrenome.getText();
                cpfCliente = TextCPF.getText();
                emailCliente = TextEmail.getText();
                senhaCliente = TextSenha.getText();
                confirmaSenhaCliente = TextConfirmeSenha.getText();
            }
        });
    } */
        public void ConectaCadastro () {
            try {
                btnSair.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TelaInicial telaInicial = new TelaInicial();
                        telaInicial.setVisible(true);
                        dispose();
                    }
                });
                próximoButton.addActionListener(new ActionListener() {
                    private Bd bd;
                    private Connection conn;

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (senhaCliente.equals(confirmaSenhaCliente)) {
                            try {
                                this.bd = new Bd();
                                this.conn = this.bd.getConexao();
                                final PreparedStatement stmtRegistrarCliente;
                                stmtRegistrarCliente = conn.prepareStatement(bd.RegistrarCliente);
                                int senhaInt = Integer.parseInt(senhaCliente);
                                stmtRegistrarCliente.setString(1, nomeCliente);
                                stmtRegistrarCliente.setString(2, sobrenomeCliente);
                                stmtRegistrarCliente.setString(3, cpfCliente);
                                stmtRegistrarCliente.setString(4, emailCliente);
                                stmtRegistrarCliente.setInt(5, senhaInt);
                                int linhasAfetadas = stmtRegistrarCliente.executeUpdate();
                                System.out.println("Linhas afetadas: " + linhasAfetadas);
                            } catch (Exception ex) {
                                System.out.println("Erro ao conectar" + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
                        }
                    }
                });
            }catch (Exception ex){
                System.out.println("Erro");
            }
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


