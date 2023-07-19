import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KeyManagerGUI extends JFrame {

    private KeyManager keyManager;
    private JTextField keyValueField;
    private JTextArea output;

    public KeyManagerGUI() throws IOException {
        keyManager = new KeyManager();

        setTitle("Key Manager KepDoktor Feladat");
        setSize(700, 400);
        BufferedImage image = ImageIO.read(new File ("src/main/resources/icon_chat.png"));
        setIconImage (image);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainLogicComponents();
        setVisible(true);
    }

    private void mainLogicComponents() {
        keyValueField = new JTextField();
        keyValueField.setEditable(true);

        JButton getKeyButton = new JButton("Get Key");
        getKeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int key = keyManager.getKey();
                output.append("Key with number " + key + " received" + "\n");
            }
        });

        JButton releaseKeyButton = new JButton("Release Key");
        releaseKeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyString = keyValueField.getText();
                try {
                    int key = Integer.parseInt(keyString);
                    boolean released = keyManager.releaseKey(key);
                    if (released) {
                        output.append("Key with number " + key + " has been released" + "\n");
                    } else {
                        output.append("Invalid key with number " + key + "\n");
                    }
                } catch (NumberFormatException ex) {
                    if ( keyString.isBlank () ){
                        output.append ( "Please introduce a valid key!"+"\n" );
                    }
                    else{
                    output.append("Invalid key: " + keyString + "\n");}
                }
                keyValueField.setText("");
            }
        });

        JLabel keyLabel = new JLabel("Please enter a valid Key number");
        output = new JTextArea();
        output.setLineWrap(true);
        output.setEditable(true);
        output.setPreferredSize ( new Dimension (600,5000) );

        JPanel keyInputPanel = new JPanel();
        keyInputPanel.setLayout(new BorderLayout());
        keyInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        keyInputPanel.add(keyLabel, BorderLayout.NORTH);
        keyInputPanel.add(keyValueField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds ( 10,10,10,10 );
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(getKeyButton);
        buttonPanel.add(releaseKeyButton);

        JScrollPane scroll= new JScrollPane(output);
        scroll.setPreferredSize (  new Dimension (600,200));

        JPanel mainPanel = new JPanel ();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(keyInputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scroll, BorderLayout.SOUTH);
        setContentPane(mainPanel);


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new KeyManagerGUI();
                } catch (IOException e) {
                    throw new RuntimeException ( e );
                }
            }
        });
    }
}









































