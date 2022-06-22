import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddNew extends JFrame {
    public JTextField nameTxt;
    public JTextField idTxt;
    public JTextField phoneTxt;
    public JTextField addressTxt;
    public JTextField genderTxt;
    public JTextField emailTxt;
    public JButton addBtn;
    public JPanel jpanel;

    public void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    public AddNew() {
        JFrame jFrame = new JFrame("Information");
        jFrame.setContentPane(jpanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        centerWindow(jFrame);

    }

    public Person getNewPerson() {
        if (checkInput() == true) {
            return null;
        }
        return new Person(idTxt.getText(), nameTxt.getText(), genderTxt.getText(), phoneTxt.getText(), emailTxt.getText(), addressTxt.getText());
    }

    public boolean checkInput() {
        if (idTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ID can not be empty!!!");
            return true;
        }
        if (nameTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Name can not be empty!!!");
            return true;
        }
        if (genderTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Gender can not be empty!!!");
            return true;
        }
        if (phoneTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Phone can not be empty!!!");
            return true;
        }
        if (emailTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Email can not be empty!!!");
            return true;
        }
        if (addressTxt.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Address can not be empty!!!");
            return true;
        }
        return false;
    }
}
