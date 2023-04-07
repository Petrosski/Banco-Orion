import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class TelaInicial extends JFrame{
    private JTextField TextCPF;
    private JPasswordField PassSenha;
    private JButton btnLogar;
    private JButton btnRecuperarSenha;
    private JButton btnCadastro;
    private JPanel PNLTelaInicial;
    private JTextField TextErroLogar;
    public static String cpf;
    private Bd bd;
    private Connection conn;
public TelaInicial() {
    AddListeners();
    IniciarComponentes();
    Conecta();
    TextErroLogar.setVisible(false);
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
        btnRecuperarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaEsqueceuSenha esqueceuSenha = new TelaEsqueceuSenha();
                esqueceuSenha.setVisible(true);
                dispose();
            }
        });
    }
    public void Conecta() {
        try {
            this.bd = new Bd();
            this.conn = this.bd.getConexao();
            System.out.println("Conectado tela inicial");
            final PreparedStatement stmt = this.conn.prepareStatement(bd.ValidaUsuario);
            btnLogar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cpf = TextCPF.getText();
                    int senha = Integer.parseInt(PassSenha.getText());
                    try{
                        stmt.setString(1, cpf);
                        stmt.setInt(2,senha);
                        ResultSet rs = stmt.executeQuery();
                        System.out.println("dados consultado");
                        System.out.println(cpf);
                        System.out.println(senha);
                        int count = 0;
                        while (rs.next()) {
                            count = 1;
                        }
                        if (count > 0) {
                            if (cpf.equals("admin")){
                                TelaAdmin telaAdmin;
                                telaAdmin = new TelaAdmin();
                                telaAdmin.setVisible(true);
                                dispose();
                            }else {
                                System.out.println("Usuário autenticado com sucesso!");
                                TelaPrincipal home = new TelaPrincipal();
                                home.setVisible(true);
                                dispose();
                            }
                        }else {
                            TextErroLogar.setVisible(true);
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
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(PNLTelaInicial);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bem vindo - Orion Bank");
        setVisible(true);
    }
    public static void main(String[] args) {
        TelaInicial TelaCadAluno = new TelaInicial();
    }
}
