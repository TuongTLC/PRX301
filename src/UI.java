import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class UI {
    private JPanel panel1;
    private JTextField searchTxt;
    private JButton searchBtn;
    private JTable inforTable;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton exitBtn;
    private JButton showBtn;

    private String[] headers = {"ID", "Name", "Gender", "Phone", "Email", "Address"};
    private int[] indexs = {0, 1, 2, 3, 4, 5};
    public CustomTableModelPerson modelPerson = new CustomTableModelPerson(headers, indexs);
    private CustomTableModelPerson searchPerson = new CustomTableModelPerson(headers, indexs);
    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    public UI() {
        loadData();
        inforTable.setModel(modelPerson);
        inforTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        inforTable.setEnabled(true);
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchTxt.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Please enter an ID to search!!!");
                    return;
                }
                String id = searchTxt.getText();
                searchPerson.getlist().clear();
                for (int i = 0; i < modelPerson.getlist().size(); i++) {
                    if (modelPerson.getlist().get(i).getID().equalsIgnoreCase(id)) {
                        searchPerson.getlist().add(modelPerson.getlist().get(i));
                    }
                }
                if (searchPerson.getlist().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Can not find any ID match " + searchTxt.getText()+" !");
                    return;
                } else {
                    inforTable.setModel(searchPerson);
                }
                inforTable.updateUI();
            }
        });
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddNew addNew = new AddNew();
                addNew.addBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int answer = JOptionPane.showConfirmDialog(null, "Add this new person?");
                        if (answer == JOptionPane.YES_OPTION) {
                            if (addNew.getNewPerson() == null) {
                                return;
                            }
                            modelPerson.getlist().add(addNew.getNewPerson());
                            inforTable.setModel(modelPerson);
                            inforTable.updateUI();
                            writeData(modelPerson);
                        }
                    }
                });
                addNew.dispose();


            }
        });
        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = inforTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a person to update!");
                    return;
                }
                AddNew addNew = new AddNew();
                addNew.idTxt.setEnabled(false);
                addNew.idTxt.setText(modelPerson.getlist().get(row).getID());
                addNew.nameTxt.setText(modelPerson.getlist().get(row).getName());
                addNew.genderTxt.setText(modelPerson.getlist().get(row).getGender());
                addNew.phoneTxt.setText(modelPerson.getlist().get(row).getPhone());
                addNew.emailTxt.setText(modelPerson.getlist().get(row).getEmail());
                addNew.addressTxt.setText(modelPerson.getlist().get(row).getAddress());
                addNew.addBtn.setText("Update");
                addNew.addBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int answer = JOptionPane.showConfirmDialog(null, "Update this person?");
                        if (answer == JOptionPane.YES_OPTION) {
                            Person updatePerson = addNew.getNewPerson();
                            if (updatePerson == null) {
                                return;
                            }
                            for (Person p : modelPerson.getlist()
                            ) {
                                if (p.getID().equalsIgnoreCase(updatePerson.getID())) {
                                    p.setName(updatePerson.getName());
                                    p.setGender(updatePerson.getGender());
                                    p.setPhone(updatePerson.getPhone());
                                    p.setEmail(updatePerson.getEmail());
                                    p.setAddress(updatePerson.getAddress());
                                }
                            }
                            inforTable.setModel(modelPerson);
                            inforTable.updateUI();
                            writeData(modelPerson);
                        }

                    }
                });
            }
        });
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "Delete person?");
                if (answer == JOptionPane.YES_OPTION) {
                    int row = inforTable.getSelectedRow();
                    modelPerson.getlist().remove(row);
                    inforTable.setModel(modelPerson);
                    inforTable.updateUI();
                    writeData(modelPerson);
                }
            }
        });
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        showBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inforTable.setModel(modelPerson);
                inforTable.updateUI();
            }
        });
    }

    public void loadData() {
        try {
            modelPerson.getlist().clear();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            File file = new File("person.xml");
            saxParser.parse(file, handler);
            for (Person p : handler.personList
            ) {
                modelPerson.getlist().add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeData(CustomTableModelPerson model) {
        StaxIterator fileParser = new StaxIterator();
        fileParser.writeFileByStaxIterator(model.getlist(), "person.xml");
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Person Manager");
        jFrame.setContentPane(new UI().panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        centerWindow(jFrame);
    }
}
