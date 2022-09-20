package Interface;

import Servicios.ServiciosInterface;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteAdmInternalFrame extends javax.swing.JInternalFrame {
    private int idCliente;

    ButtonGroup grupoBotonesGenero;

    public ClienteAdmInternalFrame() {
        initComponents();
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        grupoBotonesGenero = new ButtonGroup();

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        txtPesoCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        maleButton = new javax.swing.JRadioButton();
        femaleButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateFechaNacCliente = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEdadCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        buttonGuardarCliente = new javax.swing.JButton();
        buttonVerCliente = new javax.swing.JButton();
        txtCantidadHijosCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        txtCorreoCliente = new javax.swing.JTextField();
        txtNombreUsuarioCliente = new javax.swing.JTextField();
        txtClaveUsuarioCliente = new javax.swing.JPasswordField();
        adminButton = new javax.swing.JRadioButton();
        buttonUpdateCliente = new javax.swing.JButton();
        buttonDeleteCliente = new javax.swing.JButton();
        grupoBotonesGenero.add(femaleButton);
        grupoBotonesGenero.add(maleButton);

        setClosable(true);
        setTitle("Administrar Clientes");
        setPreferredSize(new java.awt.Dimension(1280, 697));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Datos Cliente");

        jLabel2.setText("Nombre Usuario");

        jLabel3.setText("Clave");

        jLabel4.setText("Nombre");

        jLabel5.setText("Apellido");

        jLabel6.setText("Sexo");

        maleButton.setText("Masculino");

        femaleButton.setText("Femenino");

        jLabel7.setText("Peso");

        jLabel8.setText("Correo");

        jLabel9.setText("Fecha de nacimiento");

        jLabel11.setText("Edad");

        jLabel12.setText("Cantidad de hijos");

        buttonGuardarCliente.setText("Guardar Cliente");
        buttonGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarClienteActionPerformed(evt);
            }
        });

        buttonVerCliente.setText("Ver Clientes");
        buttonVerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerClienteActionPerformed(evt);
            }
        });

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Nombre Usuario", "Clave", "Nombre", "Apellido", "Edad", "Sexo", "Peso", "Correo", "Fecha de nacimiento", "Cant. de hijos", "Admin"
                }
        ));
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        adminButton.setText("Admin");

        buttonUpdateCliente.setText("Actualizar Cliente");
        buttonUpdateCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateCleinteActionPerformed(evt);
            }
        });

        buttonDeleteCliente.setText("Borrar Cliente");
        buttonDeleteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel11))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtApellidoCliente)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(maleButton)
                                                                .addGap(63, 63, 63)
                                                                .addComponent(femaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 83, Short.MAX_VALUE))
                                                        .addComponent(txtNombreCliente)
                                                        .addComponent(txtEdadCliente)
                                                        .addComponent(txtNombreUsuarioCliente)
                                                        .addComponent(txtClaveUsuarioCliente))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(418, 418, 418)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel7)
                                                                                        .addComponent(jLabel12)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(jLabel8)))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(dateFechaNacCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(txtPesoCliente)
                                                                                        .addComponent(txtCorreoCliente)
                                                                                        .addComponent(txtCantidadHijosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(adminButton)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(171, 171, 171)
                                                                .addComponent(buttonDeleteCliente)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonUpdateCliente)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonGuardarCliente)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonVerCliente)))
                                                .addGap(39, 39, 39)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(txtNombreUsuarioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(txtClaveUsuarioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel12))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(txtEdadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(adminButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(maleButton)
                                                        .addComponent(femaleButton)
                                                        .addComponent(buttonGuardarCliente)
                                                        .addComponent(buttonVerCliente)
                                                        .addComponent(buttonUpdateCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(buttonDeleteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(txtPesoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addComponent(dateFechaNacCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addComponent(txtCantidadHijosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>


    //*************************************Eventos************************************************//
    private void buttonGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {
        ServiciosInterface serviciosInterface = new ServiciosInterface();
        HashMap<String, String> nuevoCliente = new HashMap<>();

        nuevoCliente.put("Nombre Usuario", txtNombreUsuarioCliente.getText());
        nuevoCliente.put("Clave", txtClaveUsuarioCliente.getText());
        nuevoCliente.put("Nombre", txtNombreCliente.getText());
        nuevoCliente.put("Apellido", txtApellidoCliente.getText());
        nuevoCliente.put("Edad", txtEdadCliente.getText());
        nuevoCliente.put("Sexo", getSexo());
        nuevoCliente.put("Peso", txtPesoCliente.getText());
        nuevoCliente.put("Correo", txtCorreoCliente.getText());
        nuevoCliente.put("Fecha de nacimiento", getFechaNacimiento());
        nuevoCliente.put("Cant. de hijos", getCantidadHijos());
        nuevoCliente.put("Admin", getAdmin());

        try {
            String validacionCliente = serviciosInterface.validarNuevoCliente(nuevoCliente);

            if(validacionCliente != null)
                JOptionPane.showMessageDialog(null, validacionCliente);
            else {
                JOptionPane.showMessageDialog(null, serviciosInterface.saveCliente(nuevoCliente));
                cargarTabla();
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void buttonVerClienteActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTabla();
    }

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaClientes.getSelectedRow();
        idCliente = Integer.parseInt(String.valueOf(tablaClientes.getModel().getValueAt(fila, 0)));

        txtNombreUsuarioCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila,1)));
        txtClaveUsuarioCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 2)));
        txtNombreCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 3)));
        txtApellidoCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 4)));
        txtEdadCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 5)));
        setGeneroButton(String.valueOf(tablaClientes.getModel().getValueAt(fila, 6)));
        txtPesoCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 7)));
        txtCorreoCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 8)));
        dateFechaNacCliente.setCalendar(setFechaNacimiento(String.valueOf(tablaClientes.getModel().getValueAt(fila, 9))));
        txtCantidadHijosCliente.setText(String.valueOf(tablaClientes.getModel().getValueAt(fila, 10)));
        setAdminButton(String.valueOf(tablaClientes.getModel().getValueAt(fila, 11)));
    }

    private void buttonDeleteClienteActionPerformed(java.awt.event.ActionEvent evt) {
        ServiciosInterface serviciosInterface = new ServiciosInterface();
        String deleteCliente = serviciosInterface.deleteCliente(idCliente);
        JOptionPane.showMessageDialog(null, deleteCliente);
        cargarTabla();
    }

    private void buttonUpdateCleinteActionPerformed(java.awt.event.ActionEvent evt) {
        ServiciosInterface serviciosInterface = new ServiciosInterface();
        HashMap<String, String> cliente = new HashMap<>();

        cliente.put("Nombre Usuario", txtNombreUsuarioCliente.getText());
        cliente.put("Clave", txtClaveUsuarioCliente.getText());
        cliente.put("Nombre", txtNombreCliente.getText());
        cliente.put("Apellido", txtApellidoCliente.getText());
        cliente.put("Edad", txtEdadCliente.getText());
        cliente.put("Sexo", getSexo());
        cliente.put("Peso", txtPesoCliente.getText());
        cliente.put("Correo", txtCorreoCliente.getText());
        cliente.put("Fecha de nacimiento", getFechaNacimiento());
        cliente.put("Cant. de hijos", getCantidadHijos());
        cliente.put("Admin", getAdmin());

        JOptionPane.showMessageDialog(null, serviciosInterface.updateCliente(idCliente, cliente));
        cargarTabla();
    }

    //******************************Metodos soporte para evetos***********************************//
    private void cargarTabla(){
        ServiciosInterface srvDB = new ServiciosInterface();
        try {
            tablaClientes.setModel(srvDB.getGrilla());
        } catch (SQLException e) {
            Logger.getLogger(ClienteAdmInternalFrame.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String getSexo() {
        if(femaleButton.isSelected())
            return "F";
        if(maleButton.isSelected())
            return "M";
        return "";
    }

    private String getAdmin() {
        if(adminButton.isSelected())
            return "1";
        return "0";
    }

    private String getFechaNacimiento(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        return formatoFecha.format(dateFechaNacCliente.getCalendar().getTime());
    }

    private String getCantidadHijos(){
        String cantidadHijos = txtCantidadHijosCliente.getText();
        if(cantidadHijos.isEmpty())
            return "0";
        return cantidadHijos;
    }

    private void setGeneroButton(String genero){
        if (Objects.equals(genero, "F"))
            femaleButton.doClick();
        else
            maleButton.doClick();
    }

    private Calendar setFechaNacimiento(String fechaNacimiento) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        try {
            cal.setTime(formatoFecha.parse(fechaNacimiento));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return cal;
    }

    private void setAdminButton(String admin){
        adminButton.setSelected(Objects.equals(admin, "true"));
    }

    private javax.swing.JButton buttonUpdateCliente;
    private javax.swing.JButton buttonDeleteCliente;
    private javax.swing.JButton buttonGuardarCliente;
    private javax.swing.JButton buttonVerCliente;
    private com.toedter.calendar.JDateChooser dateFechaNacCliente;
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton adminButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtCantidadHijosCliente;
    private javax.swing.JPasswordField txtClaveUsuarioCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtEdadCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreUsuarioCliente;
    private javax.swing.JTextField txtPesoCliente;
}
