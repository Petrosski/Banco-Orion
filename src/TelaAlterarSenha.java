import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TelaAlterarSenha extends JFrame {
    private JPasswordField PassSenha;
    private JPasswordField NewSenha;
    private JButton btnConfirmar;
    private JTextField TextErroLogar;
    private JButton btnVoltar;
    private JPanel PNLTelaAlterarSenha;
    private Bd bd;
    private Connection conn;
    private TelaInicial telaInicial = new TelaInicial();
    private String cpf = telaInicial.cpf;

    public TelaAlterarSenha() {
        AddListeners();
        IniciarComponentes();
        Conecta();
        TextErroLogar.setVisible(false);
    }

    private void AddListeners() {
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal home = new TelaPrincipal();
                home.setVisible(true);
                dispose();
            }
        });
    }
        public void Conecta() {
            try {
                this.bd = new Bd();
                this.conn = this.bd.getConexao();
                System.out.println("Conectado tela inicial");
                final PreparedStatement stmtValidaUsuario = conn.prepareStatement(bd.ValidaUsuario);
                final PreparedStatement stmtAlteraSenha = conn.prepareStatement(bd.AlteraSenha);
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int senha = Integer.parseInt(PassSenha.getText());
                        try {
                            stmtValidaUsuario.setString(1, cpf);
                            stmtValidaUsuario.setInt(2, senha);
                            ResultSet rs = stmtValidaUsuario.executeQuery();
                            int count = 0;
                            while (rs.next()) {
                                count = 1;
                            }
                            if (count > 0) {
                                String newSenha = String.valueOf(Integer.parseInt(NewSenha.getText()));
                                stmtAlteraSenha.setInt(1, Integer.parseInt(newSenha));
                                stmtAlteraSenha.setString(2, cpf);
                                int linhasAfetadas = stmtAlteraSenha.executeUpdate();
                                System.out.println("Linhas afetadas: " + linhasAfetadas);
                                TelaPrincipal home = new TelaPrincipal();
                                home.setVisible(true);
                                dispose();
                            } else {
                                TextErroLogar.setVisible(true);
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
        public void IniciarComponentes () {
        dispose();
            JPanel TelaAlterarSenha = new JPanel();
            setExtendedState(MAXIMIZED_BOTH);
            setContentPane(PNLTelaAlterarSenha);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Alterar senha");
            setVisible(true);
        }
}

