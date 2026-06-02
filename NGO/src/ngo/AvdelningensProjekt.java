/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo;
import java.util.ArrayList;
import java.util.HashMap;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author nikla
 */
public class AvdelningensProjekt extends javax.swing.JFrame {
    private InfDB idb;
    private String aid;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AvdelningensProjekt.class.getName());

    
    public AvdelningensProjekt(InfDB idb, String aid) {
        this.idb = idb;
        this.aid = aid;
        initComponents();
        laddaProjekt();
        // Fyll comboboxen med statusalternativ
        cmbStatus.addItem("Alla");
        cmbStatus.addItem("Pågående");
        cmbStatus.addItem("Planerat");
        cmbStatus.addItem("Avslutat");
    }

    //Fyller tabell med alla projekt på den inloggades avdelning
    private void laddaProjekt() 
    {
        try 
        {
            // Hämta först avdelnings-id för den inloggade användaren
            String sqlAvd = "SELECT avdelning FROM anstalld WHERE aid = " + aid;
            String avdelningId = idb.fetchSingle(sqlAvd);
        
            // Hämta alla projekt för avdelningen
            String sql = "SELECT DISTINCT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                + "p.kostnad, p.status, p.prioritet, "
                + "CONCAT(a.fornamn, ' ', a.efternamn) AS projektchef, "
                + "l.namn AS land "
                + "FROM projekt p "
                + "JOIN ans_proj ap ON p.pid = ap.pid "
                + "JOIN anstalld a ON p.projektchef = a.aid "
                + "JOIN land l ON p.land = l.lid "
                + "JOIN anstalld ans ON ap.aid = ans.aid "
                + "WHERE ans.avdelning = " + avdelningId;

            ArrayList<HashMap<String, String>> projekt = idb.fetchRows(sql);

            String[] kolumner = {"ID", "Projektnamn", "Beskrivning", "Startdatum",
            "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land"};

            String[][] data = new String[projekt.size()][10];

            for (int i = 0; i < projekt.size(); i++) {
                data[i][0] = projekt.get(i).get("pid");
                data[i][1] = projekt.get(i).get("projektnamn");
                data[i][2] = projekt.get(i).get("beskrivning");
                data[i][3] = projekt.get(i).get("startdatum");
                data[i][4] = projekt.get(i).get("slutdatum");
                data[i][5] = projekt.get(i).get("kostnad");
                data[i][6] = projekt.get(i).get("status");
                data[i][7] = projekt.get(i).get("prioritet");
                data[i][8] = projekt.get(i).get("projektchef");
                data[i][9] = projekt.get(i).get("namn");
            }

            tblProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return false;
                }
            });
        } 
        catch (InfException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
        
    //Fyller tabell beroende på sökt datum och avdelning
    private void sokProjektPaDatum(String datum) 
    {
        try 
        {
            // Hämta avdelnings-id för inloggad användare
            String sqlAvd = "SELECT avdelning FROM anstalld WHERE aid = " + aid;
            String avdelningId = idb.fetchSingle(sqlAvd);
        
            // Hämta alla aktiva projekt på avdelningen där datumet ligger inom projektets tidsspann
            String sql = "SELECT DISTINCT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                + "p.kostnad, p.status, p.prioritet, "
                + "CONCAT(a.fornamn, ' ', a.efternamn) AS projektchef, "
                + "l.namn AS land "
                + "FROM projekt p "
                + "JOIN ans_proj ap ON p.pid = ap.pid "
                + "JOIN anstalld a ON p.projektchef = a.aid "
                + "JOIN land l ON p.land = l.lid "
                + "JOIN anstalld ans ON ap.aid = ans.aid "
                + "WHERE ans.avdelning = " + avdelningId
                + " AND p.startdatum <= '" + datum + "'"
                + " AND p.slutdatum >= '" + datum + "'";

            ArrayList<HashMap<String, String>> projekt = idb.fetchRows(sql);

            String[] kolumner = {"ID", "Projektnamn", "Beskrivning", "Startdatum",
            "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land"};

            String[][] data = new String[projekt.size()][10];

            for (int i = 0; i < projekt.size(); i++) {
                data[i][0] = projekt.get(i).get("pid");
                data[i][1] = projekt.get(i).get("projektnamn");
                data[i][2] = projekt.get(i).get("beskrivning");
                data[i][3] = projekt.get(i).get("startdatum");
                data[i][4] = projekt.get(i).get("slutdatum");
                data[i][5] = projekt.get(i).get("kostnad");
                data[i][6] = projekt.get(i).get("status");
                data[i][7] = projekt.get(i).get("prioritet");
                data[i][8] = projekt.get(i).get("projektchef");
                data[i][9] = projekt.get(i).get("namn");
            }

            tblProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return false;
                }
            });
        } 
        catch (InfException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }   
    
    //Fyller tabell beroende på vilken status som projekten har på avdelningen
    private void laddaProjektMedFilter(String status) 
    {
        try 
        {
            // Hämta avdelnings-id för inloggad användare
            String sqlAvd = "SELECT avdelning FROM anstalld WHERE aid = " + aid;
            String avdelningId = idb.fetchSingle(sqlAvd);
        
            // Hämta projekt filtrerade på status
            String sql = "SELECT DISTINCT p.pid, p.projektnamn, p.beskrivning, p.startdatum, p.slutdatum, "
                + "p.kostnad, p.status, p.prioritet, "
                + "CONCAT(a.fornamn, ' ', a.efternamn) AS projektchef, "
                + "l.namn AS land "
                + "FROM projekt p "
                + "JOIN ans_proj ap ON p.pid = ap.pid "
                + "JOIN anstalld a ON p.projektchef = a.aid "
                + "JOIN land l ON p.land = l.lid "
                + "JOIN anstalld ans ON ap.aid = ans.aid "
                + "WHERE ans.avdelning = " + avdelningId
                + " AND p.status = '" + status + "'";

            ArrayList<HashMap<String, String>> projekt = idb.fetchRows(sql);

            String[] kolumner = {"ID", "Projektnamn", "Beskrivning", "Startdatum",
            "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land"};

            String[][] data = new String[projekt.size()][10];

            for (int i = 0; i < projekt.size(); i++) {
                data[i][0] = projekt.get(i).get("pid");
                data[i][1] = projekt.get(i).get("projektnamn");
                data[i][2] = projekt.get(i).get("beskrivning");
                data[i][3] = projekt.get(i).get("startdatum");
                data[i][4] = projekt.get(i).get("slutdatum");
                data[i][5] = projekt.get(i).get("kostnad");
                data[i][6] = projekt.get(i).get("status");
                data[i][7] = projekt.get(i).get("prioritet");
                data[i][8] = projekt.get(i).get("projektchef");
                data[i][9] = projekt.get(i).get("namn");
            }

            tblProjekt.setModel(new javax.swing.table.DefaultTableModel(data, kolumner) 
            {
                @Override
                public boolean isCellEditable(int row, int column) 
                {
                    return false; // Gör så att inga celler kan redigeras
                }
            });
        } 
        catch (InfException ex) 
        {
            System.out.println(ex.getMessage());
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
        tblProjekt = new javax.swing.JTable();
        cmbStatus = new javax.swing.JComboBox<>();
        btnFiltrera = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfDatum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSokDatum = new javax.swing.JButton();
        lblFelmeddelandeDatum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblProjekt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProjekt);

        btnFiltrera.setText("Filtrera");
        btnFiltrera.addActionListener(this::btnFiltreraActionPerformed);

        jLabel1.setText("Sök på datum:");

        tfDatum.setMinimumSize(new java.awt.Dimension(100, 22));
        tfDatum.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel2.setText("YYYY-MM-DD");

        btnSokDatum.setText("Sök");
        btnSokDatum.addActionListener(this::btnSokDatumActionPerformed);

        lblFelmeddelandeDatum.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSokDatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFelmeddelandeDatum)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFiltrera)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrera)
                    .addComponent(jLabel1)
                    .addComponent(tfDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnSokDatum)
                    .addComponent(lblFelmeddelandeDatum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 249, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //När filtreraknappen trycks så körs kommando beroende på vald status
    private void btnFiltreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltreraActionPerformed
        String valtStatus = cmbStatus.getSelectedItem().toString();
    
        if(valtStatus.equals("Alla"))
        {
            laddaProjekt();
        }
        else 
        {
            laddaProjektMedFilter(valtStatus);
        }
    }//GEN-LAST:event_btnFiltreraActionPerformed

    //När sökknapp trycks valideras texten som står och sokProjektPaDatum körs 
    private void btnSokDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokDatumActionPerformed
        String datum = tfDatum.getText();
    
        // Validera datumet
        if (Validering.arGiltigtDatum(datum)) 
        {
            // Datumet är giltigt - rensa felmeddelande och sök
            lblFelmeddelandeDatum.setText("");
            sokProjektPaDatum(datum);
        }
        else 
        {
            // Datumet är ogiltigt - visa felmeddelande
            lblFelmeddelandeDatum.setText("Ogiltigt datum. Använd formatet YYYY-MM-DD.");
        }
    }//GEN-LAST:event_btnSokDatumActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new AvdelningensProjekt().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrera;
    private javax.swing.JButton btnSokDatum;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFelmeddelandeDatum;
    private javax.swing.JTable tblProjekt;
    private javax.swing.JTextField tfDatum;
    // End of variables declaration//GEN-END:variables
}
