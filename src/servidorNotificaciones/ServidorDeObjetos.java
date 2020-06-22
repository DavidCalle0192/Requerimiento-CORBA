/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import servidorNotificaciones.sop_corba.Notificaciones;
import servidorNotificaciones.sop_corba.NotificacionesImpl;
import servidorNotificaciones.sop_corba.NotificacionesPOATie;
import servidorNotificaciones.vista.VistaNotificaciones;

/**
 *
 * @author JhonMZ
 */
public class ServidorDeObjetos extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public ServidorDeObjetos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextField_direccion_ip = new javax.swing.JTextField();
        Label_direccion_ip = new javax.swing.JLabel();
        Label_puerto = new javax.swing.JLabel();
        Button_establecer_conexion = new javax.swing.JButton();
        TextField_puerto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextField_direccion_ip.setText("localhost");
        TextField_direccion_ip.setToolTipText("");
        TextField_direccion_ip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TextField_direccion_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_direccion_ipActionPerformed(evt);
            }
        });

        Label_direccion_ip.setText("Dirección IP");
        Label_direccion_ip.setToolTipText("Ingrese la dirección ip donde desea establecer la conexión");

        Label_puerto.setText("Puerto");
        Label_puerto.setToolTipText("Ingrese el puerto donde desea establecer la conexión");

        Button_establecer_conexion.setText("Establecer conexión");
        Button_establecer_conexion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Button_establecer_conexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_establecer_conexionActionPerformed(evt);
            }
        });

        TextField_puerto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_puerto)
                            .addComponent(Label_direccion_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextField_direccion_ip)
                            .addComponent(TextField_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(Button_establecer_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextField_direccion_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_direccion_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_puerto)
                    .addComponent(TextField_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button_establecer_conexion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextField_direccion_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_direccion_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_direccion_ipActionPerformed

    private void Button_establecer_conexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_establecer_conexionActionPerformed

        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            vec[1] = TextField_direccion_ip.getText();
            vec[2] = "-ORBInitialPort";
            vec[3] = TextField_puerto.getText();
            
            System.out.println("1. Crea e inicia el orb");
            ORB orb = ORB.init(vec, null);
            
            System.out.println("2. Obtiene la referencia al poa raiz, por medio del orb ");
            org.omg.CORBA.Object objPOA = null;
            objPOA=orb.resolve_initial_references("RootPOA");
            POA rootPOA = POAHelper.narrow(objPOA);
            
            System.out.println("3. Activa el POAManager");
            rootPOA.the_POAManager().activate();
            
            System.out.println("4. Crea el objeto servant");
            
            NotificacionesImpl ObjServant = new NotificacionesImpl();
            
            System.out.println("5. Crea el objeto tie y se registra una referencia al objeto servant mediante el contructor");
            NotificacionesPOATie objTIE= new NotificacionesPOATie(ObjServant);
            
            System.out.println("6. Obtiene la referencia al orb ");
            Notificaciones referenciaORB = objTIE._this(orb);
            
            System.out.println("7. Obtiene una referencia al n_s del orb");
            org.omg.CORBA.Object objRefNameService = orb.resolve_initial_references("NameService");
            
            System.out.println("8. Convierte la ref genérica a ref de NamingContextExt");
            NamingContextExt refContextoNombrado = NamingContextExtHelper.narrow(objRefNameService);
            
            System.out.println("9.Construir el identificador del servant");
            String name = "objNotificaciones";
            NameComponent path[] = refContextoNombrado.to_name( name );

            System.out.println("10.Realiza el binding de la referencia de objeto en el N_S");
            refContextoNombrado.rebind(path, referenciaORB);
            this.setVisible(false);
            System.out.println("El Servidor esta listo y esperando ...");
            orb.run();
        }catch (Exception e){
            
        }
    }//GEN-LAST:event_Button_establecer_conexionActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorDeObjetos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_establecer_conexion;
    private javax.swing.JLabel Label_direccion_ip;
    private javax.swing.JLabel Label_puerto;
    private javax.swing.JTextField TextField_direccion_ip;
    private javax.swing.JTextField TextField_puerto;
    // End of variables declaration//GEN-END:variables
}
