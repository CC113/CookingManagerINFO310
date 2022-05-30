/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProfileDataInterface;
import domain.Profile;
import gui.helpers.SimpleListModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import password.PasswordHash;

/**
 *
 * @author Hyunsun
 */
public class ChangePassword extends javax.swing.JDialog {

	private ProfileDataInterface dao;
	private SimpleListModel myModel = new SimpleListModel();
	private Profile profile;
	private final MainMenu mm;

	/**
	 * Creates new form CreateProfile
	 *
	 */
	public ChangePassword(java.awt.Window parent, boolean modal, Profile profile, ProfileDataInterface dao,
			  MainMenu mm) {
		
		super(parent);
		super.setModal(modal);
		initComponents();
		this.dao = dao;
		this.profile = profile;
		this.mm = mm;
		txtUsername.setText(profile.getUsername());
		txtUsername.setEditable(false);
		txtPassword.setEditable(true);
		txtPasswordValid.setEditable(true);
                this.setResizable(false);
                Color backgroundColor = new Color(229, 214, 201);
                this.getContentPane().setBackground(backgroundColor);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        profileCloseButton = new javax.swing.JButton();
        profileSaveButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        txtPasswordValid = new javax.swing.JTextField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BAT");
        setBackground(new java.awt.Color(239, 240, 234));
        setIconImage(null);

        jLabel2.setBackground(new java.awt.Color(239, 240, 234));
        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(75, 68, 41));
        jLabel2.setText("C H A N G E  P A S S W O R D");

        jLabel6.setBackground(new java.awt.Color(239, 240, 234));
        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(139, 191, 166));
        jLabel6.setText("Bone");

        jLabel7.setBackground(new java.awt.Color(239, 240, 234));
        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 167, 167));
        jLabel7.setText("Apple");

        jLabel8.setBackground(new java.awt.Color(239, 240, 234));
        jLabel8.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(139, 191, 166));
        jLabel8.setText("Tea");

        profileCloseButton.setBackground(new java.awt.Color(75, 68, 41));
        profileCloseButton.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        profileCloseButton.setForeground(new java.awt.Color(255, 255, 255));
        profileCloseButton.setText("CLOSE");
        profileCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileCloseButtonActionPerformed(evt);
            }
        });

        profileSaveButton.setBackground(new java.awt.Color(75, 68, 41));
        profileSaveButton.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        profileSaveButton.setForeground(new java.awt.Color(255, 255, 255));
        profileSaveButton.setText("SAVE");
        profileSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileSaveButtonActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(239, 240, 234));
        jLabel10.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(56, 133, 96));
        jLabel10.setText("Username:");

        txtUsername.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(239, 240, 234));
        jLabel11.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(56, 133, 96));
        jLabel11.setText("Password:");

        jLabel13.setBackground(new java.awt.Color(239, 240, 234));
        jLabel13.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(56, 133, 96));
        jLabel13.setText("Re-Enter");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        txtPasswordValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordValidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profileSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(profileCloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword)
                            .addComponent(txtUsername)
                            .addComponent(txtPasswordValid))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPasswordValid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileSaveButton)
                    .addComponent(profileCloseButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileCloseButtonActionPerformed
		dispose();
		 EditProfile editor = new EditProfile(this, true, profile, dao, mm);
        editor.setLocationRelativeTo(this);
        editor.setVisible(true);
        
    }//GEN-LAST:event_profileCloseButtonActionPerformed

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed
		String userID = profile.getUserID();
		String valid1 = txtPassword.getText();
		String valid2 = txtPasswordValid.getText();
		
		if (!valid1.equals(valid2)){
			JOptionPane.showMessageDialog(this, "Password fields do not match", "Error",
						JOptionPane.WARNING_MESSAGE);
		}else{
			profile.setUserID(userID);
			profile.setPassword(PasswordHash.hash(txtPassword.getText()));
			 dao.changePassword(profile);
			 mm.signIn(profile);
			 dispose();
		}
    }//GEN-LAST:event_profileSaveButtonActionPerformed

   private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
   }//GEN-LAST:event_txtUsernameActionPerformed

   private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_txtPasswordActionPerformed

   private void txtPasswordValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordValidActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_txtPasswordValidActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CreateProfile dialog = new CreateProfile(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton profileCloseButton;
    private javax.swing.JButton profileSaveButton;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordValid;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
