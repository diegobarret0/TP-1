package Servicios;

import Datos.*;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class ServiciosInterface {
    ComunicacionDB db = new SQLite();

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String NUMERO_PATTERN = "\\d+";

    private static final String TEXTO_PATTERN = "^[a-zA-Z]*$";

    private boolean isNumbero(String numero) {
        return Pattern.matches(NUMERO_PATTERN, numero);
    }

    private boolean isEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    private boolean isTexto(String texto) {
        return Pattern.matches(TEXTO_PATTERN, texto);
    }

    /**
     *Metodo para obtener la <code/>grilla</code> con los registros de la base de datos.
     * @return </code>DefaultTableModel</code>
     * @throws SQLException
     */
    public DefaultTableModel getGrilla() throws SQLException {
        String[] columnas = { //String con nombres de las columnas de la grilla
                "ID",
                "Nombre Usuario",
                "Clave",
                "Nombre",
                "Apellido",
                "Edad",
                "Sexo",
                "Peso",
                "Correo",
                "Fecha de nacimiento",
                "Cant. de hijos",
                "Admin"
        };
        DefaultTableModel grilla = new DefaultTableModel(null, columnas);
        ResultSet datos = db.obtenerDatos("SELECT * FROM Clientes");
        String[] registro = new String[12];
        while (datos.next()) {
            registro[0] = String.valueOf(datos.getInt("id"));
            registro[1] = datos.getString("nombre_usuario");
            registro[2] = datos.getString("password");
            registro[3] = datos.getString("nombre");
            registro[4] = datos.getString("apellido");
            registro[5] = String.valueOf(datos.getInt("edad"));
            registro[6] = datos.getString("sexo");
            registro[7] = String.valueOf(datos.getInt("peso"));
            registro[8] = datos.getString("correo");
            registro[9] = datos.getString("fecha_nacimiento");
            registro[10] = String.valueOf(datos.getInt("cant_hijos"));
            registro[11] = String.valueOf(datos.getBoolean("admin"));
            grilla.addRow(registro);
        }
        db.desconectar();
        return grilla;
    }

    /**
     * Metodo para validad un nuevo cliente que se quiera registrar en la base de datos.
     * @param datosCliente
     * @return </code>null</code> si no se encotro ningun imperfecto
     * @throws SQLException
     */
    public String validarNuevoCliente(HashMap<String, String> datosCliente) throws SQLException {

        for (Map.Entry<String,String> par: datosCliente.entrySet()) {
            switch (par.getKey()) {
                case "Nombre Usuario":
                    if (par.getValue().isEmpty()) {
                        return "Ingrese un nombre de usuario.";
                    } else if (!db.obtenerRegistros(String.format("SELECT * FROM Clientes WHERE nombre_usuario='%s';", par.getValue())).isEmpty())
                        return "Nombre de Usuario ya existe.";
                    break;

                case "Nombre":
                    if (!isTexto(par.getValue()) || par.getValue().isEmpty())
                        return "Nombre invalido.";
                    break;

                case "Apellido":
                    if (!isTexto(par.getValue()) || par.getValue().isEmpty())
                        return "Apellido invalido.";
                    break;

                case "Edad":
                    if (!isNumbero(par.getValue()) || par.getValue().isEmpty())
                        return "Edad invalida.";
                    break;

                case "Peso":
                    if (!isNumbero(par.getValue()) || par.getValue().isEmpty())
                        return "Peso invalido.";
                    break;

                case "Correo":
                    if (!isEmail(par.getValue()) || par.getValue().isEmpty())
                        return "Correo invalido.";
                    break;

                case "Cant. de hijos":
                    if (!isNumbero(par.getValue()))
                        return "Cant. de hijos invalida.";
                    break;

                case "Sexo":
                    if(par.getValue().isEmpty())
                        return "Seleccione un genero";
                    break;

                default:
                    break;
            }
        }
        return null;
    }

    /**
     * Metodo para registrar un nuevo cliente en la base de datos.
     * @param nuevoCliente
     * @return </code>"Cliente registrado correctamente."</code> si se pudo registrar correctamente.<p>
     *     De lo contrario devolvera el error.
     */
    public String saveCliente(HashMap<String, String> nuevoCliente) {
        String sql = String.format("INSERT INTO Clientes" +
                        "(nombre_usuario, password, nombre, apellido, edad, sexo, peso, correo, fecha_nacimiento, cant_hijos, admin) " +
                        "VALUES ('%s', '%s', '%s', '%s', %s, '%s', %s, '%s', '%s', %s, %s);",
                        nuevoCliente.get("Nombre Usuario"), nuevoCliente.get("Clave"), nuevoCliente.get("Nombre"), nuevoCliente.get("Apellido"),
                        nuevoCliente.get("Edad"), nuevoCliente.get("Sexo"), nuevoCliente.get("Peso"), nuevoCliente.get("Correo"),
                        nuevoCliente.get("Fecha de nacimiento"),  nuevoCliente.get("Cant. de hijos"), nuevoCliente.get("Admin"));
        try {
            db.consultaUID(sql);
            return "Cliente registrado correctamente.";
        } catch (SQLException e) {
            return "Error al registrar Cliente:\n"+e.getMessage();
        }
    }

    /**
     * Metodo para relizar una actualizacion sobre un registro de la base de datos.
     * @param id
     * @param nuevoCliente
     * @return </code>"Cliente actualizado correctamente."</code> si la actualizacion se realiza correctamente.<p>
     *     De lo contrario devolvera el error.
     */
    public String updateCliente(int id, HashMap<String, String> nuevoCliente) {
        String sql = String.format("UPDATE Clientes SET " +
                        "nombre_usuario='%s', password='%s', nombre='%s', apellido='%s', edad=%s, sexo='%s', peso=%s, correo='%s', fecha_nacimiento='%s', cant_hijos=%s, admin=%s " +
                        "WHERE id=%d;",
                        nuevoCliente.get("Nombre Usuario"), nuevoCliente.get("Clave"), nuevoCliente.get("Nombre"), nuevoCliente.get("Apellido"),
                        nuevoCliente.get("Edad"), nuevoCliente.get("Sexo"), nuevoCliente.get("Peso"), nuevoCliente.get("Correo"),
                        nuevoCliente.get("Fecha de nacimiento"),  nuevoCliente.get("Cant. de hijos"), nuevoCliente.get("Admin"), id);
        try {
            db.consultaUID(sql);
            return "Cliente actualizado correctamente.";
        } catch (SQLException e) {
            return "Error al actualizar Cliente:\n"+e.getMessage();
        }
    }

    /**
     * Metodo para relizar la eliminacion de un registro en la base de datos.
     * @param id
     * @return </code>"Cliente borrado correctamente."</code> si se realiza correctamente la eliminacion del registro.<p>
     *     De lo contrario devolvera el error.
     */
    public String deleteCliente(int id) {
        String sql = "DELETE FROM Clientes WHERE id=" + id + ";";
        try {
            db.consultaUID(sql);
            return "Cliente borrado correctamente.";
        } catch (SQLException e) {
            return "Error al borrar cliente:\n" + e.getMessage();
        }
    }
}
