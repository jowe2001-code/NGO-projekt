/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nikla
 */
public class AdminProjekt extends javax.swing.JFrame {
    private InfDB idb;
    private boolean arAdmin;
    private boolean arProjektledare;
    private String aid;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminProjekt.class.getName());

    /**
     * Creates new form AdminProjekt
     */
    public AdminProjekt(InfDB idb, boolean arAdmin, boolean arProjektledare, String aid) {
        this.idb = idb;
        this.arAdmin = arAdmin;
        this.arProjektledare = arProjektledare;
        this.aid = aid;
        
        initComponents();
        
        fyllTabell(arAdmin, arProjektledare);
    }

    private void fyllTabell(boolean arAdmin, boolean arProjektledare)
    {
        String[] kolumner = {"ID", "Projektnamn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land"};
        DefaultTableModel model = new DefaultTableModel(kolumner, 0);
        tblAdminProjekt.setModel(model);
        
        if(arAdmin)
        {
            try 
            {
                String projekt = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                + "p.kostnad, p.status, p.prioritet, "
                + "CONCAT(a.fornamn, ' ', a.efternamn) AS projektchef, "
                + "l.namn AS land "
                + "FROM projekt p "
                + "JOIN anstalld a ON p.projektchef = a.aid "
                + "JOIN land l ON p.land = l.lid ";
                
                ArrayList<HashMap<String, String>> allaProjekt = idb.fetchRows(projekt);
                
                String[][] data = new String[allaProjekt.size()][10];

                for (int i = 0; i < allaProjekt.size(); i++) 
                {
                    data[i][0] = allaProjekt.get(i).get("pid");
                    data[i][1] = allaProjekt.get(i).get("projektnamn");
                    data[i][2] = allaProjekt.get(i).get("beskrivning");
                    data[i][3] = allaProjekt.get(i).get("startdatum");
                    data[i][4] = allaProjekt.get(i).get("slutdatum");
                    data[i][5] = allaProjekt.get(i).get("kostnad");
                    data[i][6] = allaProjekt.get(i).get("status");
                    data[i][7] = allaProjekt.get(i).get("prioritet");
                    data[i][8] = allaProjekt.get(i).get("projektchef");
                    data[i][9] = allaProjekt.get(i).get("namn");
                }
                tblAdminProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner));
            }
            catch(InfException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        else if(arProjektledare)
        {
            try 
            {
                String projekt = "SELECT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                + "p.kostnad, p.status, p.prioritet, "
                + "CONCAT(a.fornamn, ' ', a.efternamn) AS projektchef, "
                + "l.namn AS land "
                + "FROM projekt p "
                + "JOIN anstalld a ON p.projektchef = a.aid "
                + "JOIN land l ON p.land = l.lid "
                + "WHERE p.projektchef = '" + aid + "'";
                
                ArrayList<HashMap<String, String>> allaProjekt = idb.fetchRows(projekt);
                
                String[][] data = new String[allaProjekt.size()][10];

                for (int i = 0; i < allaProjekt.size(); i++) 
                {
                    data[i][0] = allaProjekt.get(i).get("pid");
                    data[i][1] = allaProjekt.get(i).get("projektnamn");
                    data[i][2] = allaProjekt.get(i).get("beskrivning");
                    data[i][3] = allaProjekt.get(i).get("startdatum");
                    data[i][4] = allaProjekt.get(i).get("slutdatum");
                    data[i][5] = allaProjekt.get(i).get("kostnad");
                    data[i][6] = allaProjekt.get(i).get("status");
                    data[i][7] = allaProjekt.get(i).get("prioritet");
                    data[i][8] = allaProjekt.get(i).get("projektchef");
                    data[i][9] = allaProjekt.get(i).get("namn");
                }
                tblAdminProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner));
            }
            catch(InfException ex) 
            {
                System.out.println(ex.getMessage());
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdminProjekt = new javax.swing.JTable();
        btnVisaHandläggare = new javax.swing.JButton();
        btnSparaÄndringar = new javax.swing.JButton();
        btnTaBort = new javax.swing.JButton();
        btnNyttProjekt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblAdminProjekt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAdminProjekt);

        btnVisaHandläggare.setText("Visa handläggare");

        btnSparaÄndringar.setText("Spara");

        btnTaBort.setText("Ta bort projekt");

        btnNyttProjekt.setText("Nytt projekt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVisaHandläggare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNyttProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaBort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSparaÄndringar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNyttProjekt, btnTaBort});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisaHandläggare)
                    .addComponent(btnSparaÄndringar)
                    .addComponent(btnTaBort)
                    .addComponent(btnNyttProjekt))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new AdminProjekt().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNyttProjekt;
    private javax.swing.JButton btnSparaÄndringar;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnVisaHandläggare;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdminProjekt;
    // End of variables declaration//GEN-END:variables
}
