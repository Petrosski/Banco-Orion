import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class TelaEsqueceuSenha extends JFrame {
    private JTextField TextCPF;
    private JButton enviarButton;
    private JPanel PNLTelaEsqueceuSenha;
    private JButton voltarButton;
    private JTextField TextErroLogar1;
    private Bd bd = new Bd();

    public TelaEsqueceuSenha() {
        AddListeners();
        IniciarComponentes();
        Conecta();

    }
    public void Conecta() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(bd.URL, bd.USER, bd.PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtRecuperaSenha;
            final PreparedStatement stmtAlteraSenha;

            stmtRecuperaSenha = connection.prepareStatement(bd.RecuperaSenha);
            stmtAlteraSenha = connection.prepareStatement(bd.AlteraSenha);
            enviarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cpf = TextCPF.getText();
                    try {
                        stmtRecuperaSenha.setString(1, cpf);
                        ResultSet rs = stmtRecuperaSenha.executeQuery();
                        int count = 0;
                        while (rs.next()) {
                            count = 1;
                        }
                        if (count > 0) {
                            Random random = new Random();
                            String senha = String.valueOf(random.nextInt(10000000, 90000000));
                            stmtAlteraSenha.setInt(1, Integer.parseInt(senha));
                            stmtAlteraSenha.setString(2, cpf);
                            int linhasAfetadas = stmtAlteraSenha.executeUpdate();
                            System.out.println("Linhas afetadas: " + linhasAfetadas);
                            JOptionPane.showMessageDialog(enviarButton,senha);
                            TextCPF.setText("");
                        } else {
                            TextErroLogar1.setText("Usuário inválido");
                            TextCPF.setText("");
                            System.out.println("CPF inválido.");
                        }
                    }catch (SQLException ex){
                        System.out.println("erro ao consultar");
                    }
                }
            });
        }catch (SQLException ex){
            System.out.println("Erro ao conectar");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddListeners(){
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);
                dispose();
            }
        });
    }
    public void IniciarComponentes(){
        JPanel TelaEsqueceuSenha = new JPanel();
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(PNLTelaEsqueceuSenha);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Esqueceu a senha");
        setVisible(true);
    }
}
