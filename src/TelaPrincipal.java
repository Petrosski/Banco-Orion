import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaPrincipal extends JFrame{
    private JTextField TextSaldo;
    private JButton transferirButton;
    private JButton depositarButton;
    private JPanel PNLTelaPrincipal;
    private JTextField TextSaudacao;
    private JMenu Jmenu;
    private JMenuItem ItemSair;
    private JMenuItem ItemAlterarSenha;
    private String nome;
    private Bd bd;
    private Connection conn;

    private TelaInicial telaInicial = new TelaInicial();
    private String cpf = telaInicial.cpf;


    public TelaPrincipal() {
        AddListeners();
        IniciarComponentes();
        Conecta();
    }
        public void Conecta(){

        try {
            this.bd = new Bd();
            this.conn = this.bd.getConexao();
            System.out.println("Conectado");

            final PreparedStatement stmt = conn.prepareStatement(bd.Consultar);
            try{
                stmt.setString(1, cpf);
               ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    nome = resultado.getString("nome");
                }
                String nomeMaiuscula = Character.toUpperCase(nome.charAt(0)) + nome.substring(1);
                System.out.println(nomeMaiuscula);
                TextSaudacao.setText("Olá "+nomeMaiuscula);
                TextSaldo.setText(resultado.getString("saldo"));

            }catch (SQLException ex) {
                System.out.println("Erro ao consultar");
            }
            transferirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            depositarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }catch (SQLException e){
            System.out.println("Erro ao consultar");
        }
    }

    public void AddListeners(){
        ItemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null,"Deseja realmente sair?","Confirmação",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (i == JOptionPane.YES_OPTION) {
                    TelaInicial telaInicial1 = new TelaInicial();
                    dispose();
                    telaInicial1.setVisible(true);
                }
            }
        });
        ItemAlterarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAlterarSenha telaAlterarSenha = new TelaAlterarSenha();
            }
        });
    }

    public void IniciarComponentes(){
        JPanel TelaPrincipal = new JPanel();
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(PNLTelaPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Orion Bank");
        setVisible(true);
    }
}
