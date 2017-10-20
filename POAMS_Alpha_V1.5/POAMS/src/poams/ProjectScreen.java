/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poams;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.text.DateFormat;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author skyzer
 */

public final class ProjectScreen extends javax.swing.JFrame {

    int prjIDNumber; 
    String userLoginstr; 
    String userIDstr;
    String selectedprojectstr;

    /**
     * Creates new form ProjectScreen
     */
    public ProjectScreen(String selectedProject,String userLogin, String userID) {
        userIDstr = userID;
        userLoginstr = userLogin; 
        selectedprojectstr = selectedProject;
        initComponents();
        
        
        LoadProjectData(selectedProject, userLogin);
        
        

        
        
    }

    public void LoadProjectData(String selectedProject, String userLogin){
        
    lblProjectName.setText(selectedProject.trim());  //Fill the project name label 
    
    lblWelcomeUser.setText("Welcome " + userLogin); //fill welcome label text
    
    // Load or Reload all data in the screen using the entity manger
    projectQuery = POAMSPUEntityManager.createQuery("SELECT p FROM Project p WHERE p.prjName = :prjName");
    projectQuery.setParameter("prjName", selectedProject);
       
      
        //This code fills the project description 
        
        javax.persistence.Query projectdescription = POAMSPUEntityManager.createQuery("SELECT p.prjDescription FROM Project p WHERE p.prjName = :prjName");    // Call project query from entity class
               
        projectdescription.setParameter("prjName", selectedProject.trim());
        
        String projectdescriptionstr = projectdescription.getResultList().toString(); // move result to string
                
        txtProjectDescription.setText(projectdescriptionstr.substring(1, projectdescriptionstr.length()-1));
        
        // This code pulls the project ID which will be used to fill the notes, activity and documents sections
        
        javax.persistence.Query projectIdentifier = POAMSPUEntityManager.createQuery("SELECT p.prjId FROM Project p WHERE p.prjName = :prjName");    // Call project query from entity class
               
        projectIdentifier.setParameter("prjName", selectedProject.trim());
        
        String projectIdentifierstr = projectIdentifier.getResultList().toString(); // move result to string
        
        projectIdentifierstr = projectIdentifierstr.substring(1, projectIdentifierstr.length()-1);
        
         //notes refresh based on project              
                       
        noteQuery1.setParameter("prjId", Integer.parseInt(projectIdentifierstr));    
        
        prjIDNumber = Integer.parseInt(projectIdentifierstr);               
        
        
                
        java.util.Collection data = noteQuery1.getResultList(); //Do this for notes     
        noteList1.clear(); // clear existing data          
        noteList1.addAll(data);
        for (Object entity : data) {
            POAMSPUEntityManager.refresh(entity);            
        }
         // Rebind the table each time and override previous binding elements. 
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, noteList1, jTable2, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteId}"));
        columnBinding.setColumnName("Note Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteDateCreated}"));
        columnBinding.setColumnName("Note Date Created");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteText}"));
        columnBinding.setColumnName("Note Text");
        columnBinding.setColumnClass(String.class); 
        jTableBinding.bind();

        
        
        //Activity Binding 
        
        
        activityQuery.setParameter("prjId", Integer.parseInt(projectIdentifierstr));          
            
                
        java.util.Collection dataActivity = activityQuery.getResultList(); //Do this for notes     
        activityList.clear();
        activityList.addAll(dataActivity);
        for (Object entity : dataActivity) {
            POAMSPUEntityManager.refresh(entity);            
        }
        
        org.jdesktop.swingbinding.JTableBinding jTableBindingActivity = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, activityList, jTable3);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actId}"));
        columnBindingActivity.setColumnName("Act Id");
        columnBindingActivity.setColumnClass(Integer.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actDateCreated}"));
        columnBindingActivity.setColumnName("Act Date Created");
        columnBindingActivity.setColumnClass(String.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actDateDue}"));
        columnBindingActivity.setColumnName("Act Date Due");
        columnBindingActivity.setColumnClass(String.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actResolutionDate}"));
        columnBindingActivity.setColumnName("Act Resolution Date");
        columnBindingActivity.setColumnClass(String.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actNoteUserCreate}"));
        columnBindingActivity.setColumnName("Act Note User Create");
        columnBindingActivity.setColumnClass(String.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actNoteUserAssigned}"));
        columnBindingActivity.setColumnName("Act Note User Assigned");
        columnBindingActivity.setColumnClass(String.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actUserAssignedId}"));
        columnBindingActivity.setColumnClass(Integer.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${userId}"));
        columnBindingActivity.setColumnName("User Id");
        columnBindingActivity.setColumnClass(Integer.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${prjId}"));
        columnBindingActivity.setColumnName("Prj Id");
        columnBindingActivity.setColumnClass(Integer.class);
        columnBindingActivity = jTableBindingActivity.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${actResolutionNotes}"));
        columnBindingActivity.setColumnName("Act Resolution Notes");
        columnBindingActivity.setColumnClass(String.class);
        //bindingGroup.addBinding(jTableBinding);
        jTableBindingActivity.bind();
        
        
        
        
        
        
        
        
         // add new data
       
        //POAMSPUEntityManager.close(); close data connection so other users can utilize
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        POAMSPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("POAMSPU").createEntityManager();
        noteQuery1 = POAMSPUEntityManager.createQuery("SELECT n FROM Note n WHERE n.prjId = :prjId"); noteQuery1.setParameter("prjId", prjIDNumber) ;
        noteList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : noteQuery1.getResultList();
        projectQuery = java.beans.Beans.isDesignTime() ? null : POAMSPUEntityManager.createQuery("Select p FROM Project p");
        activityQuery = java.beans.Beans.isDesignTime() ? null : POAMSPUEntityManager.createQuery("SELECT a FROM Activity a WHERE a.prjId = :prjId");activityQuery.setParameter("prjId", prjIDNumber);
        activityList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : activityQuery.getResultList();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProjectDescription = new javax.swing.JTextPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnl_note = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnl_activity = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lblWelcomeUser = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblProjectName = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuItem1.setText("New");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Edit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem7.setText("jMenuItem7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txtProjectDescription.setEditable(false);
        txtProjectDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProjectDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProjectDescriptionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtProjectDescription);

        jTable2.setColumnSelectionAllowed(true);
        jTable2.setComponentPopupMenu(jPopupMenu1);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, noteList1, jTable2, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteId}"));
        columnBinding.setColumnName("Note Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteDateCreated}"));
        columnBinding.setColumnName("Note Date Created");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noteText}"));
        columnBinding.setColumnName("Note Text");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${userId}"));
        columnBinding.setColumnName("User ID");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout pnl_noteLayout = new javax.swing.GroupLayout(pnl_note);
        pnl_note.setLayout(pnl_noteLayout);
        pnl_noteLayout.setHorizontalGroup(
            pnl_noteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_noteLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_noteLayout.setVerticalGroup(
            pnl_noteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_noteLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Notes", pnl_note);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Document Name", "Document Type", "URL : Location"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTabbedPane1.addTab("Documents", jScrollPane2);

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, activityList, jTable3);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ObjectProperty.create());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout pnl_activityLayout = new javax.swing.GroupLayout(pnl_activity);
        pnl_activity.setLayout(pnl_activityLayout);
        pnl_activityLayout.setHorizontalGroup(
            pnl_activityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_activityLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_activityLayout.setVerticalGroup(
            pnl_activityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_activityLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Activities", pnl_activity);

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setText("Project Description");

        lblWelcomeUser.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        lblWelcomeUser.setText("Welcome USER NAME");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setText("Overall Completion");

        lblProjectName.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lblProjectName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProjectName.setText("Project Name");
        lblProjectName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(lblProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu2.setText("   Project  ");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Open / Create New Project");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Edit Project");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator2);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Connection");
        jMenu2.add(jMenuItem6);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu3.setText("   Reports   ");

        jMenuItem3.setText("User Assignments");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblWelcomeUser))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jButton1))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcomeUser))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(jButton2))
        );

        getAccessibleContext().setAccessibleParent(this);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        if(pnl_activity.isShowing()==true)
        {
                JFrame frame = new JFrame();
                frame.setContentPane(new Activity_CRUD(prjIDNumber, Integer.parseInt(userIDstr)));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            
        } else if (pnl_note.isShowing()==true)         
        { 
                JFrame frame = new JFrame();
                frame.setContentPane(new Note_CRUD(prjIDNumber));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
        }
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        PointerInfo a = MouseInfo.getPointerInfo();
    Point b  = a.getLocation();
    int x = (int)b.getX();
    int y = (int)b.getY();
    if (evt.getClickCount()==2){
        
    //Note_CRUD editdialog = new poams.Note_CRUD();

    //editdialog.setVisible(true);
    
       Note_CRUD Note = new Note_CRUD(prjIDNumber); 

       Note.setVisible(true);
  
    }//GEN-LAST:event_jTable2MouseClicked
    }
        private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
      new ProjectSelect(userIDstr,userLoginstr).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
      new ProjectSelect(userIDstr,userLoginstr).setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
               LoadProjectData(selectedprojectstr, userLoginstr);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtProjectDescriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProjectDescriptionMouseClicked

    }//GEN-LAST:event_txtProjectDescriptionMouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
                                JFrame frame = new JFrame();
                frame.setContentPane(new Project_CRUD());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

         
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
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
              //  new ProjectScreen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager POAMSPUEntityManager;
    private java.util.List activityList;
    private javax.persistence.Query activityQuery;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblProjectName;
    private javax.swing.JLabel lblWelcomeUser;
    private java.util.List<poams.Note> noteList1;
    private javax.persistence.Query noteQuery1;
    private javax.swing.JPanel pnl_activity;
    private javax.swing.JPanel pnl_note;
    private javax.persistence.Query projectQuery;
    private javax.swing.JTextPane txtProjectDescription;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
