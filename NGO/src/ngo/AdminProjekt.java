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
import javax.swing.JOptionPane;

/**
 *
 * @author nikla
 */
public class AdminProjekt extends javax.swing.JFrame {
    private InfDB idb;
    private boolean arAdmin;
    private boolean arProjektledare;
    private String aid;
    private ArrayList<String> bortagnaProjekt = new ArrayList<>();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminProjekt.class.getName());

    /**
     * Creates new form AdminProjekt
     */
    public AdminProjekt(InfDB idb, boolean arAdmin, boolean arProjektledare, String aid) 
    {
        this.idb = idb;
        this.arAdmin = arAdmin;
        this.arProjektledare = arProjektledare;
        this.aid = aid;
        
        initComponents();
        
        fyllTabell(arAdmin, arProjektledare);
        
        if(arAdmin)
        {
            btnNyttProjekt.setVisible(true);
            btnTaBort.setVisible(true);
        }
        else
        {
            btnNyttProjekt.setVisible(false);
            btnTaBort.setVisible(false);
        }
    }

    //Fyll i tabellen som visar projekt på olika sätt beroende på om det är en admin eller projektledare som är inloggad
private void fyllTabell(boolean arAdmin, boolean arProjektledare)
{
    String[] kolumner = {"ID", "Projektnamn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land"};
    
    if(arAdmin)
    {
        try 
        {
            String projekt = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, "
                    + "kostnad, status, prioritet, projektchef, land "
                    + "FROM projekt";

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
                data[i][9] = allaProjekt.get(i).get("land");
            }
            
            // Skrivskydda ID-kolumnen så användaren inte kan ändra den
            tblAdminProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return column != 0; // ID-kolumnen får inte redigeras
                }
            });
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
            String projekt = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, "
                    + "kostnad, status, prioritet, projektchef, land "
                    + "FROM projekt WHERE projektchef = '" + aid + "'";

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
                data[i][9] = allaProjekt.get(i).get("land");
            }
            
            // Skrivskydda ID-kolumnen så användaren inte kan ändra den
            tblAdminProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return column != 0; // ID-kolumnen får inte redigeras
                }
            });
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
        btnVisaPartners = new javax.swing.JButton();

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
        btnVisaHandläggare.addActionListener(this::btnVisaHandläggareActionPerformed);

        btnSparaÄndringar.setText("Spara");
        btnSparaÄndringar.addActionListener(this::btnSparaÄndringarActionPerformed);

        btnTaBort.setText("Ta bort projekt");
        btnTaBort.addActionListener(this::btnTaBortActionPerformed);

        btnNyttProjekt.setText("Nytt projekt");
        btnNyttProjekt.addActionListener(this::btnNyttProjektActionPerformed);

        btnVisaPartners.setText("Visa Partners");
        btnVisaPartners.addActionListener(this::btnVisaPartnersActionPerformed);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisaPartners)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNyttProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaBort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSparaÄndringar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNyttProjekt, btnTaBort});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnVisaHandläggare, btnVisaPartners});

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
                    .addComponent(btnNyttProjekt)
                    .addComponent(btnVisaPartners))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Öppnar ett nytt fönster för att visa det markerade projektets handläggare
    private void btnVisaHandläggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaHandläggareActionPerformed
        int valdRad = tblAdminProjekt.getSelectedRow();

        if (valdRad == -1) {
            JOptionPane.showMessageDialog(this, "Välj ett projekt först");
            return;
        }

        String pid = tblAdminProjekt.getValueAt(valdRad, 0).toString();

        new HandläggareProjekt(idb, pid).setVisible(true);
    }//GEN-LAST:event_btnVisaHandläggareActionPerformed
    
    //Öppnar ett nytt fönster för att visa det markerade projektets partners
    private void btnVisaPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaPartnersActionPerformed
        int valdRad = tblAdminProjekt.getSelectedRow();

        if (valdRad == -1) {
            JOptionPane.showMessageDialog(this, "Välj ett projekt först");
            return;
        }

        String pid = tblAdminProjekt.getValueAt(valdRad, 0).toString();

        new PartnerProjekt(idb, pid).setVisible(true);
    }//GEN-LAST:event_btnVisaPartnersActionPerformed
    
    //Lägger till en ny rad för att skapa ett nytt projekt
    private void btnNyttProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNyttProjektActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblAdminProjekt.getModel();
        model.addRow(new Object[]{"", "", "", "", "", "", "", "", "", ""});
    }//GEN-LAST:event_btnNyttProjektActionPerformed
    
    //Tar bort den markerade raden 
    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        int valdRad = tblAdminProjekt.getSelectedRow();
    
    if (valdRad == -1) {
        JOptionPane.showMessageDialog(this, "Markera ett projekt att ta bort först.");
        return;
    }
    
    DefaultTableModel model = (DefaultTableModel) tblAdminProjekt.getModel();
    
    // Hämta pid för raden (om den redan finns i databasen)
    Object pidVarde = model.getValueAt(valdRad, 0);
    
    if (pidVarde != null && !pidVarde.toString().isEmpty()) {
        bortagnaProjekt.add(pidVarde.toString());
    }
    
    model.removeRow(valdRad);
    }//GEN-LAST:event_btnTaBortActionPerformed

    //Sparar eventuella ändringar som har gjorts till tabellen
    private void btnSparaÄndringarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaÄndringarActionPerformed
       // Spara cellen om man precis skrivit i den
        if (tblAdminProjekt.isEditing()) 
        {
            tblAdminProjekt.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) tblAdminProjekt.getModel();
        int antalRader = model.getRowCount();

        try 
        {
        // Först - radera projekt som markerats för borttagning
            for (String pid : bortagnaProjekt) 
            {
                String sqlTaBort = "DELETE FROM projekt WHERE pid = " + pid;
                idb.delete(sqlTaBort);
            }
            bortagnaProjekt.clear();

            // Gå igenom alla rader
            for (int i = 0; i < antalRader; i++) 
            {
                String pid = model.getValueAt(i, 0).toString();
                System.out.println("Rad " + i + " har pid: '" + pid + "'");
                String projektnamn = model.getValueAt(i, 1).toString();
                String beskrivning = model.getValueAt(i, 2).toString();
                String startdatum = model.getValueAt(i, 3).toString();
                String slutdatum = model.getValueAt(i, 4).toString();
                String kostnad = model.getValueAt(i, 5).toString();
                String status = model.getValueAt(i, 6).toString();
                String prioritet = model.getValueAt(i, 7).toString();
                String projektchef = model.getValueAt(i, 8).toString();
                String land = model.getValueAt(i, 9).toString();

                // VALIDERING - startdatum
                if (!Validering.arGiltigtDatum(startdatum)) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Startdatum. " + "Använd formatet YYYY-MM-DD.");
                    return;
                }

                // VALIDERING - slutdatum
                if (!Validering.arGiltigtDatum(slutdatum)) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Slutdatum. " + "Använd formatet YYYY-MM-DD.");
                    return;
                }

                // VALIDERING - projektchef måste vara en handläggare
                String sqlKollaChef = "SELECT aid FROM handlaggare WHERE aid = " + projektchef;
                String chefFinns = idb.fetchSingle(sqlKollaChef);
                if (chefFinns == null) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Projektchef. " + "Personen med ID " + projektchef + " är inte en handläggare och kan inte vara projektchef.");
                    return;
                }

                // VALIDERING - land måste vara en siffra (lid)
                if (!Validering.arEnbartSiffror(land)) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Land. " + "Ange landets ID-nummer (bara siffror).");
                    return;
                }
            
                // VALIDERING - projektnamn får inte vara tomt
                if (projektnamn.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Projektnamn. " + "Projektnamnet får inte vara tomt.");
                    return;
                }

                   // VALIDERING - kostnad måste vara en siffra (kan ha decimaler)
                try 
                {
                    Double.parseDouble(kostnad);
                }
                catch (NumberFormatException e) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Kostnad. " + "Kostnaden måste vara ett tal.");
                    return;
                }

                // VALIDERING - status måste vara giltigt
                if (!status.equals("Pågående") && !status.equals("Planerat") && !status.equals("Avslutat")) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Status. " + "Status måste vara 'Pågående', 'Planerat' eller 'Avslutat'.");
                    return;
                }

                // VALIDERING - prioritet måste vara giltigt
                if (!prioritet.equals("Hög") && !prioritet.equals("Medel") && !prioritet.equals("Låg")) 
                {
                    JOptionPane.showMessageDialog(this, "Fel i rad " + (i + 1) + ", kolumnen Prioritet. " + "Prioritet måste vara 'Hög', 'Medel' eller 'Låg'.");
                    return;
                }

                // Om pid är tomt är det ett NYTT projekt
                if (pid.isEmpty()) 
                {
                    String nyttPid = idb.getAutoIncrement("projekt", "pid");

                    String sqlNy = "INSERT INTO projekt (pid, projektnamn, beskrivning, startdatum, "
                    + "slutdatum, kostnad, status, prioritet, projektchef, land) VALUES ("
                    + nyttPid + ", '" + projektnamn + "', '" + beskrivning + "', '"
                    + startdatum + "', '" + slutdatum + "', " + kostnad + ", '"
                    + status + "', '" + prioritet + "', " + projektchef + ", " + land + ")";
                    System.out.println("INSERT: " + sqlNy);
                    idb.insert(sqlNy);
                }
                else 
                {
                    // Befintligt projekt - uppdatera
                    String sqlUppdatera = "UPDATE projekt SET "
                    + "projektnamn = '" + projektnamn + "', "
                    + "beskrivning = '" + beskrivning + "', "
                    + "startdatum = '" + startdatum + "', "
                    + "slutdatum = '" + slutdatum + "', "
                    + "kostnad = " + kostnad + ", "
                    + "status = '" + status + "', "
                    + "prioritet = '" + prioritet + "', "
                    + "projektchef = " + projektchef + ", "
                    + "land = " + land + " "
                    + "WHERE pid = " + pid;
                    idb.update(sqlUppdatera);
                }
            }

            JOptionPane.showMessageDialog(this, "Ändringarna har sparats!");
            fyllTabell(arAdmin, arProjektledare);
        } 
        catch (InfException ex) 
        {
            System.out.println("Fel vid sparande: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ett fel uppstod. Kontrollera att alla fält är ifyllda korrekt.");
        }
    }//GEN-LAST:event_btnSparaÄndringarActionPerformed

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
    private javax.swing.JButton btnVisaPartners;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdminProjekt;
    // End of variables declaration//GEN-END:variables
}
