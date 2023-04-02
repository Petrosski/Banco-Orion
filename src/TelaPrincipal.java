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

    private TelaInicial telaInicial = new TelaInicial();
    private String cpf = telaInicial.cpf;

    private Bd bd = new Bd();

    public TelaPrincipal() {
        AddListeners();
        IniciarComponentes();
        Conecta();
    }
        public void Conecta(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(bd.URL, bd.USER, bd.PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtConsultar;


            stmtConsultar = connection.prepareStatement(bd.Consultar);
            try{
                stmtConsultar.setString(1, cpf);
               ResultSet resultado = stmtConsultar.executeQuery();

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
        }catch (SQLException | ClassNotFoundException ex){
            System.out.println("Erro ao consultar");
        }
    }

    public void AddListeners(){
        ItemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null,"Deseja realmente sair?","Confirmação",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                TelaInicial telaInicial1 = new TelaInicial();
                dispose();
                telaInicial1.setVisible(true);
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
