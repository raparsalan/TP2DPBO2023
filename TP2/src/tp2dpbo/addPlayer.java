/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tp2dpbo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author binta
 */
public class addPlayer extends javax.swing.JFrame {
    private Home menu;
    private dbConnection db;
    private Boolean isUpdate = false;
    private int selectedID;
    private File fileImage;
    private String filePath;
    private String fileName;
    /**
     * Creates new form addGundam
     */
    public addPlayer(Home menu) {
        initComponents();
        db = new dbConnection();
        this.menu = menu;
        asalTim.setSelectedItem(null);
        String sql = "SELECT * FROM tim";
        java.sql.ResultSet res = db.selectQuery(sql);
        try {
            while(res.next())
            {
                asalTim.addItem(res.getString("nama_tim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public addPlayer(Home menu,int id, String  name, String image, String role, String tim)
     {
        initComponents();
        db = new dbConnection();
        this.menu = menu;
        asalTim.setSelectedItem(null);
         String sql = "SELECT * FROM tim";
        java.sql.ResultSet res = db.selectQuery(sql);
        try {
            while(res.next())
            {
                asalTim.addItem(res.getString("nama_tim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.menu = menu;
        this.selectedID = id;
        this.isUpdate=true;
        this.fileName=image;
        btnAdd.setText("Update");
        namaField.setText(name);
        roleField.setText(role);
        asalTim.setSelectedItem(tim);
        gambarField.setText(image);
        titleLabel.setText("Update Player");
    }
      public void resetForm() {
        // Set All Value Form to Empty
        roleField.setText("");
        gambarField.setText("");
        
    }
      public void insertData() throws IOException
    {        
        String name = namaField.getText();
        String role = roleField.getText();
        int idTim = 0;
        String tim = asalTim.getSelectedItem().toString();
        
        String query = "SELECT * FROM tim WHERE nama_tim = '"+tim+"'";
        
        java.sql.ResultSet res = db.selectQuery(query);
        try {
            if(res.next())
            {
                idTim = res.getInt("id_tim");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            copyImage(this.fileName);
            String sqlin = "INSERT INTO player VALUES (NULL, '"+idTim+"', '"+name+"', '"+fileName+"', '"+role+"')";       
            db.updateQuery(sqlin);
            resetForm();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
            this.menu.setPanel();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to add data!");
        }
        
       
    }
    public void updateData() throws IOException
    {        
        String name = namaField.getText();
        String role = roleField.getText();
        int idTim = 0;
        String tim = asalTim.getSelectedItem().toString();
        
        String query = "SELECT * FROM tim WHERE nama_tim = '"+tim+"'";
        
        java.sql.ResultSet res = db.selectQuery(query);
        try {
            if(res.next())
            {
                idTim = res.getInt("id_tim");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(this.fileImage!= null)
        {
            copyImage(this.fileName);
        }
        String sqlup = "UPDATE player SET nama_player='"+name+"', foto_player='" + fileName + "', roles = '"+role+"' WHERE id_player =" + selectedID ;
        int affectedRow = db.updateQuery(sqlup);
        
        if (affectedRow > 0) {
            // Show Information
            this.menu.setPanel();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } 
        else {
            JOptionPane.showMessageDialog(null, "Data gagal diubah!");
        }
    }
     public void copyImage(String img)
    {
        try
        {
            String newPath = "src/tp2dpbo/img/";
            File directory = new File(newPath);
            if(!directory.exists())
            {
                directory.mkdir();
            }
            
            Path copy = Paths.get(newPath + img);
            Path originalPath = Paths.get(this.filePath);
            Files.copy(originalPath, copy, StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to upload file!");
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

        titleLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        roleField = new javax.swing.JTextField();
        gambarField = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        asalTim = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        titleLabel.setText("Tambah Player");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("Asal Tim :");

        btnAdd.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpload.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setText("Nama Player");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Foto Player : ");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setText("Role :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(asalTim, 0, 313, Short.MAX_VALUE)
                                    .addComponent(namaField)
                                    .addComponent(roleField, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asalTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(roleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btnUpload)
                    .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(isUpdate == false)
            try {
                insertData();
        } catch (IOException ex) {
            Logger.getLogger(addPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        else {
            try {
                updateData();
            } catch (IOException ex) {
                Logger.getLogger(addPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         this.dispose();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        try
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            this.fileImage = chooser.getSelectedFile();
            this.fileName = this.fileImage.getName();
            this.filePath = this.fileImage.getAbsolutePath();
            gambarField.setText(this.filePath);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to browse file!");
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> asalTim;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpload;
    private javax.swing.JTextField gambarField;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField roleField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
