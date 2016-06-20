package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author DDcreo
 */
public class LoginPanel2 extends javax.swing.JPanel {


    private javax.swing.JTextField TEXT;
    private javax.swing.JButton button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private final static String SOURCE = "pic\\backLogin.jpg";
    private Image img;
    private MainFrame MF;
    private JPanel tmp;


    public LoginPanel2(MainFrame MF) {

        this.MF = MF;
        this.MF.add(this);
        initComponents();
        MF.revalidate();
        tmp = this;
        MF.addWindowStateListener(e -> {
                this.setPreferredSize(MF.getSize());
                this.repaint();
                MF.revalidate();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {
        this.setPreferredSize(MF.getSize());
        this.img = new ImageIcon(SOURCE).getImage();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TEXT = new javax.swing.JTextField();
        button = new javax.swing.JButton();

        jLabel1.setText("SOKOBANA");

        jLabel2.setText("TOMASZ JANIEC");

        jLabel3.setText("KACPER KUSTRA");

        button.setText("OK");
        button.addActionListener(evt -> buttonActionPerformed(evt));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(144, 144, 144)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(TEXT, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel3)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel1)
                                                                                .addGap(17, 17, 17)))
                                                                .addGap(11, 11, 11))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(32, 32, 32)
                                .addComponent(TEXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button)
                                .addContainerGap(141, Short.MAX_VALUE))
        );
    }

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        if(!TEXT.getText().isEmpty()) {
            String nick = TEXT.getText().replaceAll("[0-9]","").replace(" ","");
            if(nick.isEmpty())
                JOptionPane.showMessageDialog(null, "Podałeś zły nic. Poprawny nic nie powinien zawierać cyfr i odstępów");
            else {
                MF.setNickname(nick);
                MF.remove(tmp);
                MF.startNewGame();
                MF.revalidate();
            }
        }


    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // draw the image
    }
}
