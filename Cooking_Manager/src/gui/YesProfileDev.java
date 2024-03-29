/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProfileDataInterface;
import domain.PlaylistHeader;
import domain.Profile;
import gui.helpers.SimpleListModel;
import java.awt.Color;

/**
 *
 * @author Hyunsun
 */
public class YesProfileDev extends javax.swing.JDialog {
	private SimpleListModel myModel = new SimpleListModel();
        private Profile profile;
    private ProfileDataInterface prodao;
    public MainMenu mm;

    /**
     * Creates new form YesProfile
     */
    public YesProfileDev(java.awt.Frame parent, boolean modal, Profile profile, ProfileDataInterface prodao, MainMenu mm) {
        super(parent, modal);
        initComponents();
        this.profile = profile;
        this.prodao = prodao;
        this.mm = mm;
        txtName.setText(profile.getName());
        txtUsername.setText(profile.getUsername());

        jCheckBoxVegetarian.setSelected(profile.isVegetarian());
        jCheckBoxVegan.setSelected(profile.isVegan());
        jCheckBoxGF.setSelected(profile.isGlutenFree());
        jCheckBoxDF.setSelected(profile.isDairyFree());
        jCheckBoxPaleo.setSelected(profile.isPaleo());
        
        txtName.setEditable(false);
        txtUsername.setEditable(false);
        jCheckBoxVegetarian.setEnabled(false);
        jCheckBoxVegan.setEnabled(false);
        jCheckBoxGF.setEnabled(false);
        jCheckBoxDF.setEnabled(false);
        jCheckBoxPaleo.setEnabled(false);
        
        this.setResizable(false);
        Color backgroundColor = new Color(229, 214, 201);
                this.getContentPane().setBackground(backgroundColor);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        profileCloseButton = new javax.swing.JButton();
        profileEditButton = new javax.swing.JButton();
        jCheckBoxGF = new javax.swing.JCheckBox();
        jCheckBoxDF = new javax.swing.JCheckBox();
        jCheckBoxVegetarian = new javax.swing.JCheckBox();
        jCheckBoxVegan = new javax.swing.JCheckBox();
        jCheckBoxPaleo = new javax.swing.JCheckBox();
        profileAdminButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(239, 240, 234));

        jLabel8.setBackground(new java.awt.Color(239, 240, 234));
        jLabel8.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(139, 191, 166));
        jLabel8.setText("Tea");

        jLabel7.setBackground(new java.awt.Color(239, 240, 234));
        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 167, 167));
        jLabel7.setText("Apple");

        jLabel6.setBackground(new java.awt.Color(239, 240, 234));
        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(139, 191, 166));
        jLabel6.setText("Bone");

        jLabel2.setBackground(new java.awt.Color(239, 240, 234));
        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(75, 68, 41));
        jLabel2.setText("P R O F I L E");

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

        jLabel11.setBackground(new java.awt.Color(239, 240, 234));
        jLabel11.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(56, 133, 96));
        jLabel11.setText("Username:");

        txtUsername.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(239, 240, 234));
        jLabel12.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(56, 133, 96));
        jLabel12.setText("Diet:");

        profileCloseButton.setBackground(new java.awt.Color(75, 68, 41));
        profileCloseButton.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        profileCloseButton.setForeground(new java.awt.Color(255, 255, 255));
        profileCloseButton.setText("CLOSE");
        profileCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileCloseButtonActionPerformed(evt);
            }
        });

        profileEditButton.setBackground(new java.awt.Color(75, 68, 41));
        profileEditButton.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        profileEditButton.setForeground(new java.awt.Color(255, 255, 255));
        profileEditButton.setText("EDIT");
        profileEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileEditButtonActionPerformed(evt);
            }
        });

        jCheckBoxGF.setBackground(new java.awt.Color(229, 214, 201));
        jCheckBoxGF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxGF.setForeground(new java.awt.Color(53, 133, 96));
        jCheckBoxGF.setText("Gluten Free");

        jCheckBoxDF.setBackground(new java.awt.Color(229, 214, 201));
        jCheckBoxDF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxDF.setForeground(new java.awt.Color(53, 133, 96));
        jCheckBoxDF.setText("Dairy Free");

        jCheckBoxVegetarian.setBackground(new java.awt.Color(229, 214, 201));
        jCheckBoxVegetarian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxVegetarian.setForeground(new java.awt.Color(53, 133, 96));
        jCheckBoxVegetarian.setText("Vegetarian");

        jCheckBoxVegan.setBackground(new java.awt.Color(229, 214, 201));
        jCheckBoxVegan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxVegan.setForeground(new java.awt.Color(53, 133, 96));
        jCheckBoxVegan.setText("Vegan");
        jCheckBoxVegan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVeganActionPerformed(evt);
            }
        });

        jCheckBoxPaleo.setBackground(new java.awt.Color(229, 214, 201));
        jCheckBoxPaleo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxPaleo.setForeground(new java.awt.Color(53, 133, 96));
        jCheckBoxPaleo.setText("Paleo");

        profileAdminButton.setBackground(new java.awt.Color(75, 68, 41));
        profileAdminButton.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        profileAdminButton.setForeground(new java.awt.Color(255, 255, 255));
        profileAdminButton.setText("DATABASE ADMIN");
        profileAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileAdminButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profileAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(profileEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(profileCloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxVegetarian)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxVegan)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxGF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxDF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxPaleo))
                            .addComponent(txtUsername))
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jCheckBoxVegetarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxVegan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxGF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxDF)
                        .addComponent(jCheckBoxPaleo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileEditButton)
                    .addComponent(profileCloseButton)
                    .addComponent(profileAdminButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    /****/
    private void profileCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileCloseButtonActionPerformed
        // TODO add your handling code here:
		  dispose();
    }//GEN-LAST:event_profileCloseButtonActionPerformed

    private void profileEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileEditButtonActionPerformed
        EditProfile editor = new EditProfile(this, true, profile, prodao, mm);
        editor.setLocationRelativeTo(this);
        editor.setVisible(true);
        dispose();
    }//GEN-LAST:event_profileEditButtonActionPerformed

   private void jCheckBoxVeganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVeganActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_jCheckBoxVeganActionPerformed

   private void profileAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileAdminButtonActionPerformed
      DevDatabaseAdmin admin = new DevDatabaseAdmin(this, true);
		admin.setLocationRelativeTo(this);
		admin.setVisible(true);
   }//GEN-LAST:event_profileAdminButtonActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

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
//            java.util.logging.Logger.getLogger(YesProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(YesProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(YesProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(YesProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                YesProfile dialog = new YesProfile(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton profileAdminButton;
    private javax.swing.JButton profileCloseButton;
    private javax.swing.JButton profileEditButton;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
