import ziper.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2;
    private JTextArea summaryArea;
    private JRadioButton r1, r2;
    private JComboBox<String> comboBox;

    public Interface() {
        setBounds(250, 150, 850, 570);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("Winloss");
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("Tahoma", Font.BOLD, 40));
        l1.setBounds(340, 0, 1000, 70);
        contentPane.add(l1);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu CompDecom = new JMenu("Compression-Decompression");
        CompDecom.setForeground(Color.BLACK);
        menuBar.add(CompDecom);
        
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLACK);
        JMenuItem developersInfo = new JMenuItem("Developed by Mohsin Raza and Misbah Hassan");
        about.add(developersInfo);
        menuBar.add(about);

        JLabel l2 = new JLabel("Source");
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l2.setBounds(30, 50, 1000, 70);
        contentPane.add(l2);

        t1 = new JTextField();
        t1.setBounds(155, 80, 200, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        JButton browse1 = new JButton("Browse");
        browse1.setBounds(375, 75, 100, 30);
        browse1.setForeground(Color.BLACK);
        contentPane.add(browse1);
        browse1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(contentPane);
                if (result == JFileChooser.APPROVE_OPTION) {
                    t1.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JButton browse2 = new JButton("Browse");
        browse2.setBounds(375, 225, 100, 30);
        browse2.setForeground(Color.BLACK);
        contentPane.add(browse2);
        browse2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(contentPane);
                if (result == JFileChooser.APPROVE_OPTION) {
                    t2.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JLabel l3 = new JLabel("Procedure");
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l3.setBounds(30, 100, 1000, 70);
        contentPane.add(l3);

        r1 = new JRadioButton("Compress");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBounds(150, 123, 100, 30);
        contentPane.add(r1);

        r2 = new JRadioButton("Decompress");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBounds(265, 123, 150, 30);
        contentPane.add(r2);

        // Use ButtonGroup to ensure only one radio button is selected
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);

        JLabel l4 = new JLabel("Algorithms");
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l4.setBounds(30, 150, 1000, 70);
        contentPane.add(l4);

        comboBox = new JComboBox<>(new String[]{"Huffman", "Ziper"});
        comboBox.setBounds(155, 170, 150, 25);
        contentPane.add(comboBox);

        JLabel l5 = new JLabel("Destination");
        l5.setForeground(Color.BLACK);
        l5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        l5.setBounds(30, 200, 1000, 70);
        contentPane.add(l5);

        t2 = new JTextField();
        t2.setBounds(155, 229, 200, 20);
        contentPane.add(t2);
        t2.setColumns(10);

     summaryArea = new JTextArea();
        summaryArea.setBounds(155, 300, 300, 100);
        contentPane.add(summaryArea);
        summaryArea.setEditable(false);
        
        JButton OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourcePath = t1.getText();
                String destinationPath = t2.getText();
               if (r1.isSelected()) {
                    // Compression


                    String algorithm = (String) comboBox.getSelectedItem();
                    if ("Huffman".equals(algorithm)) {
                        HuffmanEncoder encoder = new HuffmanEncoder(sourcePath, destinationPath );
                        try {
                            encoder.encodeFile();
                            summaryArea.setText("ALgorthim = Huffman \n"+encoder.getSummary());
                            JOptionPane.showMessageDialog(contentPane, "File Compressed Successfully");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if ("Ziper".equals(algorithm)) {
                        ZipEncoder encoder = new ZipEncoder(sourcePath, destinationPath );
                        try {
                            encoder.encodeFile();
                            summaryArea.setText("ALgorthim = Ziper \n"+encoder.getSummary());
                            JOptionPane.showMessageDialog(contentPane, "File Compressed Successfully");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        // Handle other compression algorithms
                    }
                } else if (r2.isSelected()) {
                    // Decompression
                    String algorithm = (String) comboBox.getSelectedItem();
                    if ("Huffman".equals(algorithm)) {
                        HuffmanDecoder decoder = new HuffmanDecoder(sourcePath, destinationPath);
                        try {
                            decoder.decodeFile();
                            summaryArea.setText("ALgorthim = Huffman \n"+decoder.getSummary());
                            JOptionPane.showMessageDialog(contentPane, "File Decompressed Successfully");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Handle other decompression algorithms
                    }
                    
                    if ("Ziper".equals(algorithm)) {
                        ZipDecoder decoder = new ZipDecoder(sourcePath, destinationPath);
                        try {
                            decoder.decodeFile();
                            summaryArea.setText("ALgorthim = Ziper \n"+decoder.getSummary());
                            JOptionPane.showMessageDialog(contentPane, "File Decompressed Successfully");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Handle other decompression algorithms
                    }
                }
            }
        });

        OK.setBounds(300, 450, 100, 30);
        OK.setForeground(Color.BLACK);
        contentPane.add(OK);
        JLabel Status = new JLabel("____Status____");
        Status.setFont(new Font("Tahoma", Font.BOLD, 14));
        Status.setBounds(250, 269, 150, 20);
        contentPane.add(Status);
        setVisible(true);

        JButton clear = new JButton("Clear");
        clear.setBounds(430, 450, 100, 30);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                r1.setSelected(true);
                comboBox.setSelectedIndex(0);
                summaryArea.setText("");
            }
        });
        contentPane.add(clear);

        contentPane.setBackground(new Color(242, 238, 159));
        
       
    }

    public static void main(String[] args) {
        new Interface();
    }
}