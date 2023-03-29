import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame{
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton próximoButton;
    private JPasswordField passwordField1;
    private JPanel PNLTelaCadastro;

    public TelaCadastro() {
        AddListeners();
        IniciarComponentes();
        //Conecta();
    }
        public void AddListeners () {

            próximoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }

        public void IniciarComponentes () {
            JPanel TelaCadastro = new JPanel();
            setSize(1000, 750);
            setContentPane(PNLTelaCadastro);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Cadastro de Cliente");
            setVisible(true);
        }
    }


