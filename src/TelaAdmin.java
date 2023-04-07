import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaAdmin extends JFrame{
    private JPanel PNLTelaAdmin;
    private JTable tblTable;
    private JButton btnSair;
    private JButton btnExcluir;
    private Bd bd;
    private Connection conn;

    public TelaAdmin() {
        AddListeners();
        IniciarComponentes();
       // Conecta();
    }
    public void AddListeners() {
        btnSair.addActionListener(new ActionListener() {
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
        btnExcluir.addActionListener(new ActionListener() {
            private Bd bd;
            private Connection conn;

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int linhaSelecionada = tblTable.getSelectedRow();
                    if (linhaSelecionada < 0){
                        JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
                        return;
                    }
                    int numConta = (int) tblTable.getValueAt(linhaSelecionada,5);
                    this.bd = new Bd();
                    this.conn = this.bd.getConexao();
                    final PreparedStatement stmt = this.conn.prepareStatement(bd.ExcluirUsuario);
                    final PreparedStatement stmtE = this.conn.prepareStatement(bd.ExcluirEndereco);

                    stmt.setInt(1, numConta);
                    stmtE.setInt(1,numConta);
                    int resultado = stmtE.executeUpdate();
                    int resultado2 = stmt.executeUpdate();
                    if (resultado == 1 && resultado2 ==1) {
                        JOptionPane.showMessageDialog(null, "Registro exlcuido com sucesso!");
                    }else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir registro!");
                    }
                    DefaultTableModel aluno = (DefaultTableModel) tblTable.getModel();
                    aluno.removeRow(linhaSelecionada);
                }catch (SQLException ex){
                    System.out.println("Erro ao exluir registro: "+ex.getMessage());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
                }
            }
        });

        DefaultTableModel alunos = new DefaultTableModel();
        alunos.addColumn("Nome");
        alunos.addColumn("Sobrenome");
        alunos.addColumn("CPF");
        alunos.addColumn("Email");
        alunos.addColumn("Senha");
        alunos.addColumn("numConta");
        tblTable.setModel(alunos);
        Connection connection = null;
        try {
            this.bd = new Bd();
            this.conn = this.bd.getConexao();
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(bd.CONSULTAR);
            JOptionPane.showMessageDialog(null,"Arrumar o cabeçalho da tabela");
            while (rs.next()) {
                String rsAdmin = rs.getString(1);
                if (!rsAdmin.equals("admin")) {
                    Object[] row = new Object[6];
                    row[0] = rs.getObject(1);
                    row[1] = rs.getObject(2);
                    row[2] = rs.getObject(3);
                    row[3] = rs.getObject(4);
                    row[4] = rs.getObject(5);
                    row[5] = rs.getObject(6);
                    alunos.addRow(row);
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void IniciarComponentes(){
        JPanel TelaAdmin = new JPanel();
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(PNLTelaAdmin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bem vindo - Orion Bank");
        setVisible(true);
    }
}
