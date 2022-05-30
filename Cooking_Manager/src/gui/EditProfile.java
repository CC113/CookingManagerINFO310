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
public class EditProfile extends javax.swing.JDialog {

	private ProfileDataInterface dao;
	private SimpleListModel myModel = new SimpleListModel();
	private Profile profile;
	private final MainMenu mm;

	/**
	 * Creates new form CreateProfile
	 *
	 */
	public EditProfile(java.awt.Window parent, boolean modal, Profile profile, ProfileDataInterface dao,
			  MainMenu mm) {
		super(parent);
		super.setModal(modal);
		initComponents();
		this.dao = dao;
		this.profile = profile;
		this.mm = mm;
		txtName.setText(profile.getName());
		txtUserID.setText(profile.getUserID());
		txtUsername.setText(profile.getUsername());

		jCheckBoxVegetarian.setSelected(profile.isVegetarian());
		jCheckBoxVegan.setSelected(profile.isVegan());
		jCheckBoxGF.setSelected(profile.isGlutenFree());
		jCheckBoxDF.setSelected(profile.isDairyFree());
		jCheckBoxPaleo.setSelected(profile.isPaleo());

		txtName.setEditable(true);
		txtUsername.setEditable(true);
		txtPassword.setEditable(true);
		jCheckBoxVegetarian.setEnabled(true);
		jCheckBoxVegan.setEnabled(true);
		jCheckBoxGF.setEnabled(true);
		jCheckBoxDF.setEnabled(true);
		jCheckBoxPaleo.setEnabled(true);
                
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
      jLabel9 = new javax.swing.JLabel();
      txtName = new javax.swing.JTextField();
      jLabel12 = new javax.swing.JLabel();
      passwordChange = new javax.swing.JButton();
      profileSaveButton = new javax.swing.JButton();
      jCheckBoxVegetarian = new javax.swing.JCheckBox();
      jCheckBoxVegan = new javax.swing.JCheckBox();
      jCheckBoxGF = new javax.swing.JCheckBox();
      jCheckBoxDF = new javax.swing.JCheckBox();
      jCheckBoxPaleo = new javax.swing.JCheckBox();
      txtUserID = new javax.swing.JTextField();
      jLabel10 = new javax.swing.JLabel();
      txtUsername = new javax.swing.JTextField();
      jLabel11 = new javax.swing.JLabel();
      txtPassword = new javax.swing.JPasswordField();
      profileCloseButton1 = new javax.swing.JButton();

      jPasswordField1.setText("jPasswordField1");

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setBackground(new java.awt.Color(239, 240, 234));

      jLabel2.setBackground(new java.awt.Color(239, 240, 234));
      jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
      jLabel2.setForeground(new java.awt.Color(75, 68, 41));
      jLabel2.setText("P R O F I L E");

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

      jLabel9.setBackground(new java.awt.Color(239, 240, 234));
      jLabel9.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
      jLabel9.setForeground(new java.awt.Color(56, 133, 96));
      jLabel9.setText("Name:");

      txtName.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
      txtName.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtNameActionPerformed(evt);
         }
      });

      jLabel12.setBackground(new java.awt.Color(239, 240, 234));
      jLabel12.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
      jLabel12.setForeground(new java.awt.Color(56, 133, 96));
      jLabel12.setText("Diet:");

      passwordChange.setBackground(new java.awt.Color(75, 68, 41));
      passwordChange.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
      passwordChange.setForeground(new java.awt.Color(255, 255, 255));
      passwordChange.setText("CHANGE PASSWORD");
      passwordChange.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            passwordChangeActionPerformed(evt);
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

      jCheckBoxVegetarian.setBackground(new java.awt.Color(229, 214, 201));
      jCheckBoxVegetarian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBoxVegetarian.setForeground(new java.awt.Color(56, 133, 96));
      jCheckBoxVegetarian.setText("Vegetarian");

      jCheckBoxVegan.setBackground(new java.awt.Color(229, 214, 201));
      jCheckBoxVegan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBoxVegan.setForeground(new java.awt.Color(56, 133, 96));
      jCheckBoxVegan.setText("Vegan");

      jCheckBoxGF.setBackground(new java.awt.Color(229, 214, 201));
      jCheckBoxGF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBoxGF.setForeground(new java.awt.Color(56, 133, 96));
      jCheckBoxGF.setText("Gluten Free");

      jCheckBoxDF.setBackground(new java.awt.Color(229, 214, 201));
      jCheckBoxDF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBoxDF.setForeground(new java.awt.Color(56, 133, 96));
      jCheckBoxDF.setText("Dairy Free");

      jCheckBoxPaleo.setBackground(new java.awt.Color(229, 214, 201));
      jCheckBoxPaleo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBoxPaleo.setForeground(new java.awt.Color(56, 133, 96));
      jCheckBoxPaleo.setText("Paleo");

      txtUserID.setEditable(false);
      txtUserID.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtUserIDActionPerformed(evt);
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

      profileCloseButton1.setBackground(new java.awt.Color(75, 68, 41));
      profileCloseButton1.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
      profileCloseButton1.setForeground(new java.awt.Color(255, 255, 255));
      profileCloseButton1.setText("CLOSE");
      profileCloseButton1.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            profileCloseButton1ActionPerformed(evt);
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
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(profileSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordChange, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profileCloseButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                     .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(jLabel10)
                           .addComponent(jLabel11)
                           .addComponent(jLabel12)
                           .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                              .addComponent(jCheckBoxVegetarian)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jCheckBoxVegan)
                              .addGap(18, 18, 18)
                              .addComponent(jCheckBoxGF)
                              .addGap(18, 18, 18)
                              .addComponent(jCheckBoxDF)
                              .addGap(18, 18, 18)
                              .addComponent(jCheckBoxPaleo))
                           .addComponent(txtUsername)
                           .addComponent(txtName)
                           .addComponent(txtPassword)))))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(18, 18, 18)
                  .addComponent(jLabel2)
                  .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel6)
            .addGap(9, 9, 9)
            .addComponent(jLabel7)
            .addGap(6, 6, 6)
            .addComponent(jLabel8)
            .addGap(94, 94, 94))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(89, 89, 89))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addComponent(jLabel2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel6)
                     .addComponent(jLabel7)
                     .addComponent(jLabel8))
                  .addGap(11, 11, 11)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel9)
               .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel12)
               .addComponent(jCheckBoxVegetarian)
               .addComponent(jCheckBoxVegan)
               .addComponent(jCheckBoxGF)
               .addComponent(jCheckBoxDF)
               .addComponent(jCheckBoxPaleo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel10)
               .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(txtPassword))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(profileSaveButton)
               .addComponent(passwordChange)
               .addComponent(profileCloseButton1))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
		 // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void passwordChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordChangeActionPerformed
		
	dispose();	
	ChangePassword changer = new ChangePassword(this, true, profile, dao, mm);
        changer.setLocationRelativeTo(this);
        changer.setVisible(true);
		
    }//GEN-LAST:event_passwordChangeActionPerformed

    private void profileSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileSaveButtonActionPerformed
		 String username = txtUsername.getText();
		 if (dao.getProfileByUsername(username) != null && !username.equals(profile.getUsername())) {
			 JOptionPane.showMessageDialog(this, "An account with this username already exists.", "Error",
						JOptionPane.WARNING_MESSAGE);
		 } else if(txtPassword.getText().equals("")){
			 JOptionPane.showMessageDialog(this, "Please enter a password.", "Error",
						JOptionPane.WARNING_MESSAGE);
		 } else if (username.length() > 20 ){
			  JOptionPane.showMessageDialog(this, "Please ensure username does not exceed 20 characters.", "Error",
						JOptionPane.WARNING_MESSAGE);
		}else if ((txtName.getText()).length() > 40 ){
			  JOptionPane.showMessageDialog(this, "Please ensure name does not exceed 40 characters.", "Error",
						JOptionPane.WARNING_MESSAGE);
		}else{
			 String name = txtName.getText();
			 profile.setName(name);
			 profile.setUsername(username);
			 profile.setPassword(PasswordHash.hash(txtPassword.getText()));

			 //setting dietary requirments
			 if (jCheckBoxVegetarian.isSelected()) {
				 profile.setVegetarian(true);
			 }
			 if (jCheckBoxVegan.isSelected()) {
				 profile.setVegan(true);
			 }
			 if (jCheckBoxGF.isSelected()) {
				 profile.setGlutenFree(true);
			 }
			 if (jCheckBoxDF.isSelected()) {
				 profile.setDairyFree(true);
			 }
			 if (jCheckBoxPaleo.isSelected()) {
				 profile.setPaleo(true);
			 }

			 dao.edit(profile);
			 mm.signIn(profile);
			 dispose();
		 }
    }//GEN-LAST:event_profileSaveButtonActionPerformed

   private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
   }//GEN-LAST:event_txtUsernameActionPerformed

   private void txtUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserIDActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_txtUserIDActionPerformed

   private void profileCloseButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileCloseButton1ActionPerformed
dispose();      // TODO add your handling code here:
   }//GEN-LAST:event_profileCloseButton1ActionPerformed

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
   private javax.swing.JCheckBox jCheckBoxDF;
   private javax.swing.JCheckBox jCheckBoxGF;
   private javax.swing.JCheckBox jCheckBoxPaleo;
   private javax.swing.JCheckBox jCheckBoxVegan;
   private javax.swing.JCheckBox jCheckBoxVegetarian;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel12;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPasswordField jPasswordField1;
   private javax.swing.JButton passwordChange;
   private javax.swing.JButton profileCloseButton1;
   private javax.swing.JButton profileSaveButton;
   private javax.swing.JTextField txtName;
   private javax.swing.JPasswordField txtPassword;
   private javax.swing.JTextField txtUserID;
   private javax.swing.JTextField txtUsername;
   // End of variables declaration//GEN-END:variables
}
