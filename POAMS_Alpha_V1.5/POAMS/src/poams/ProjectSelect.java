/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poams;

import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFrame;

/**
 *
 * @author ShawnKyzer
 */
public class ProjectSelect extends javax.swing.JFrame {
    String userLogin;
    String userIDstr;
    /**
     * Creates new form ProjectSelect
     */
    public ProjectSelect(String userID, String userLoginName) {
        userIDstr = userID;
        userLogin = userLoginName; //set global to passed Login 

        initComponents();
        
        //Populate the combo box from user's project
        
       
        
        //javax.persistence.Query projectlistquery = entityManager1.createNamedQuery("Project.findByUserId");    // get list 
        
        javax.persistence.Query projectlistquery = entityManager1.createQuery("SELECT p FROM Project p JOIN p.prjId a");    // get list 
        
        // WHERE a.assigneeUserId = :assigneeUserId
        // WHERE a.assigneeUserId = a.userId = :userId
       //projectlistquery.setParameter("assigneeUserId", Integer.parseInt(userID));
        
       List projectlist = projectlistquery.getResultList(); // move result to list  
        
       
       
       StringTokenizer st = new StringTokenizer(projectlist.toString().substring(1,projectlist.toString().length()-1),","); 
       
       while (st.hasMoreTokens()){        
        cmbProjectSelect.addItem(st.nextToken().trim());
       }
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("POAMSPU").createEntityManager();
        lblSelectProject = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        cmbProjectSelect = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSelectProject.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSelectProject.setText("Select Project");

        btnSubmit.setText("Submit");
        btnSubmit.setToolTipText("");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        cmbProjectSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< Create New >" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProjectSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectProject))
                .addGap(131, 131, 131))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(btnSubmit)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(lblSelectProject)
                .addGap(18, 18, 18)
                .addComponent(cmbProjectSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // Open Project with combo box variable
        
        if ("< Create New >".equals(cmbProjectSelect.getSelectedItem().toString())){
                JFrame frame = new JFrame();
                frame.setContentPane(new Project_CRUD());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            
        }
        
        ProjectScreen PS = new ProjectScreen(cmbProjectSelect.getSelectedItem().toString(), userLogin, userIDstr);
        
        PS.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnSubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectSelect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
        //        new ProjectSelect().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cmbProjectSelect;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JLabel lblSelectProject;
    // End of variables declaration//GEN-END:variables
}