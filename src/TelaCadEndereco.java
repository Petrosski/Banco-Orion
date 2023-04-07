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
    private JButton btnVoltar;
    private String codigomax;

    private TelaCadastro telaCadastro = new TelaCadastro();
    private String cpf = telaCadastro.cpfCliente;
    private Bd bd;
    private Connection conn;
    TelaCadEndereco() {
        IniciarComponentes();
        Conecta();
    }

   public void Conecta(){
        try {
            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TelaCadastro cadastro = new TelaCadastro();
                    cadastro.setVisible(true);
                    dispose();
                }
            });
            this.bd = new Bd();
            this.conn = this.bd.getConexao();
            System.out.println("Conectado tela endereço");

            final PreparedStatement stmtRegistrarEndereco;
            stmtRegistrarEndereco = conn.prepareStatement(bd.RegistrarEndereco);
            final PreparedStatement stmtConsultaConta;
            stmtConsultaConta = conn.prepareStatement(bd.ConsultaConta);
            btnRegistrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cep = TextCEP.getText();
                    String cidade = TextCidade.getText();
                    String uf = TextUF.getText();
                    String bairro = TextBairro.getText();
                    String rua = TextRua.getText();
                    String numCasa = TextNumCasa.getText();
                    String complemento = TextComplemento.getText();
                    try {
                        stmtConsultaConta.setString(1, cpf);
                        ResultSet rs1= stmtConsultaConta.executeQuery();
                        if (rs1.next()) {
                            codigomax = rs1.getString("numConta");
                        }
                        int numCasaInt = Integer.parseInt(numCasa);
                        stmtRegistrarEndereco.setString(1, codigomax);
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
                        System.out.println("Erro ao inserir endereço"+ex.getMessage());
                    }
                    TelaInicial telaInicial = new TelaInicial();
                    telaInicial.setVisible(true);
                    dispose();
                }
            });
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar BD");
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
