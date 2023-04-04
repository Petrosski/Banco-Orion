import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private Integer numConta;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(bd.URL, bd.USER, bd.PASSWORD);
            System.out.println("Conectado tela endereço");

            final PreparedStatement stmtRegistrarCliente;
            stmtRegistrarCliente = connection.prepareStatement(bd.RegistrarCliente);
            final PreparedStatement stmtRegistrarEndereco;
            stmtRegistrarEndereco = connection.prepareStatement(bd.RegistrarEndereco);
            final PreparedStatement stmtConsultaConta;
            stmtConsultaConta = connection.prepareStatement(bd.ConsultaConta);
            btnRegistrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cep = TextCEP.getText();
                    String cidade = TextCidade.getText();
                    String uf = TextUF.getText();
                    String  bairro = TextBairro.getText();
                    String rua = TextRua.getText();
                    String numCasa = TextNumCasa.getText();
                    String complemento = TextComplemento.getText();
                    try {
                        int senhaInt = Integer.parseInt(senha);
                        stmtRegistrarCliente.setString(1, nome);
                        stmtRegistrarCliente.setString(2, sobrenome);
                        stmtRegistrarCliente.setString(3, cpf);
                        stmtRegistrarCliente.setString(4, email);
                        stmtRegistrarCliente.setInt(5, senhaInt);
                        stmtRegistrarCliente.setInt(6, numConta);
                        int linhasAfetadas = stmtRegistrarCliente.executeUpdate();
                        System.out.println("Linhas afetadas: " + linhasAfetadas);

                        stmtConsultaConta.setString(1, cpf);
                        ResultSet rs1= stmtConsultaConta.executeQuery();
                        //System.out.println(resultado);
                        if (rs1.next()) {
                            int codigomax = rs1.getInt("numConta");
                        }

                    }catch (SQLException ex){
                        System.out.println("Erro ao inserir cliente");
                    }
                    try {
                        int numCasaInt = Integer.parseInt(numCasa);
                        stmtRegistrarEndereco.setString(1, cpf);
                        stmtRegistrarEndereco.setString(2, cep);
                        stmtRegistrarEndereco.setString(3, cidade);
                        stmtRegistrarEndereco.setString(4, uf);
                        stmtRegistrarEndereco.setString(5, bairro);
                        stmtRegistrarEndereco.setString(6, rua);
                        stmtRegistrarEndereco.setInt(7, numCasaInt);
                        stmtRegistrarEndereco.setString(8, complemento);
                        int linhasAfetadas2 = stmtRegistrarEndereco.executeUpdate();
                        System.out.println("Linhas afetadas: " + linhasAfetadas2);
                    }catch (SQLException ex){
                        System.out.println("Erro ao inserir endereço");
                    }

                    TelaInicial telaInicial = new TelaInicial();
                    telaInicial.setVisible(true);
                    dispose();
                }
            });
        } catch (SQLException ex) {
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
