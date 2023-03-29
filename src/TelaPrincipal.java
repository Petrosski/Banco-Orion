import javax.swing.*;

public class TelaPrincipal extends JFrame{
    private JTextField a00000TextField;
    private JButton transferirButton;
    private JButton depositarButton;
    private JPanel PNLTelaPrincipal;

    public TelaPrincipal() {
       // AddListeners();
        IniciarComponentes();
      //  Conecta();
    }





    public void IniciarComponentes(){
        JPanel TelaPrincipal = new JPanel();
        setSize(1000, 750);
        setContentPane(PNLTelaPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Orion Bank");
        setVisible(true);
    }
}
