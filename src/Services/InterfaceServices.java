package Services;

import Data.*;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class InterfaceServices {
    DBComunication db = new SQLite();

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String NUMERO_PATTERN = "\\d+";

    private static final String TEXTO_PATTERN = "^[a-zA-Z]*$";

    private boolean isNumber(String numero) {
        return Pattern.matches(NUMERO_PATTERN, numero);
    }

    private boolean isEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    private boolean isTex(String texto) {
        return Pattern.matches(TEXTO_PATTERN, texto);
    }

    /**
     *Metodo para obtener la <code/>grilla</code> con los registros de la base de datos.
     * @return </code>DefaultTableModel</code>
     * @throws SQLException
     */
    public DefaultTableModel getGrid() throws SQLException {
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
        ResultSet datos = db.getData("SELECT * FROM Clientes");
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
        db.disconnect();
        return grilla;
    }

    /**
     * Metodo para validad un nuevo cliente que se quiera registrar en la base de datos.
     * @param clientDates
     * @return </code>null</code> si no se encotro ningun imperfecto
     * @throws SQLException
     */
    public String newClientValidation(HashMap<String, String> clientDates) throws SQLException {

        for (Map.Entry<String,String> par: clientDates.entrySet()) {
            switch (par.getKey()) {
                case "Nombre Usuario":
                    if (par.getValue().isEmpty()) {
                        return "Ingrese un nombre de usuario.";
                    } else if (!db.getRecords(String.format("SELECT * FROM Clientes WHERE nombre_usuario='%s';", par.getValue())).isEmpty())
                        return "Nombre de Usuario ya existe.";
                    break;

                case "Nombre":
                    if (!isTex(par.getValue()) || par.getValue().isEmpty())
                        return "Nombre invalido.";
                    break;

                case "Apellido":
                    if (!isTex(par.getValue()) || par.getValue().isEmpty())
                        return "Apellido invalido.";
                    break;

                case "Edad":
                    if (!isNumber(par.getValue()) || par.getValue().isEmpty())
                        return "Edad invalida.";
                    break;

                case "Peso":
                    if (!isNumber(par.getValue()) || par.getValue().isEmpty())
                        return "Peso invalido.";
                    break;

                case "Correo":
                    if (!isEmail(par.getValue()) || par.getValue().isEmpty())
                        return "Correo invalido.";
                    break;

                case "Cant. de hijos":
                    if (!isNumber(par.getValue()))
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
     * @param newClient
     * @return </code>"Cliente registrado correctamente."</code> si se pudo registrar correctamente.<p>
     *     De lo contrario devolvera el error.
     */
    public String saveClient(HashMap<String, String> newClient) {
        String sql = String.format("INSERT INTO Clientes" +
                        "(nombre_usuario, password, nombre, apellido, edad, sexo, peso, correo, fecha_nacimiento, cant_hijos, admin) " +
                        "VALUES ('%s', '%s', '%s', '%s', %s, '%s', %s, '%s', '%s', %s, %s);",
                        newClient.get("Nombre Usuario"), newClient.get("Clave"), newClient.get("Nombre"), newClient.get("Apellido"),
                        newClient.get("Edad"), newClient.get("Sexo"), newClient.get("Peso"), newClient.get("Correo"),
                        newClient.get("Fecha de nacimiento"),  newClient.get("Cant. de hijos"), newClient.get("Admin"));
        try {
            db.queryUID(sql);
            return "Cliente registrado correctamente.";
        } catch (SQLException e) {
            return "Error al registrar Cliente:\n"+e.getMessage();
        }
    }

    /**
     * Metodo para relizar una actualizacion sobre un registro de la base de datos.
     * @param id
     * @param newClient
     * @return </code>"Cliente actualizado correctamente."</code> si la actualizacion se realiza correctamente.<p>
     *     De lo contrario devolvera el error.
     */
    public String updateClient(int id, HashMap<String, String> newClient) {
        String sql = String.format("UPDATE Clientes SET " +
                        "nombre_usuario='%s', password='%s', nombre='%s', apellido='%s', edad=%s, sexo='%s', peso=%s, correo='%s', fecha_nacimiento='%s', cant_hijos=%s, admin=%s " +
                        "WHERE id=%d;",
                        newClient.get("Nombre Usuario"), newClient.get("Clave"), newClient.get("Nombre"), newClient.get("Apellido"),
                        newClient.get("Edad"), newClient.get("Sexo"), newClient.get("Peso"), newClient.get("Correo"),
                        newClient.get("Fecha de nacimiento"),  newClient.get("Cant. de hijos"), newClient.get("Admin"), id);
        try {
            db.queryUID(sql);
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
    public String deleteClient(int id) {
        String sql = "DELETE FROM Clientes WHERE id=" + id + ";";
        try {
            db.queryUID(sql);
            return "Cliente borrado correctamente.";
        } catch (SQLException e) {
            return "Error al borrar cliente:\n" + e.getMessage();
        }
    }
}
