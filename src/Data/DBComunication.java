package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DBComunication {
    /**
     * Metodo para tarminar la comunicacion con la base de datos.
     */
    void disconnect() throws SQLException;

    /**
     * Metodo para realizar Update, Insert o Delete en la base de datos.
     * @param sql Instruccion SQL a ejecutar.
     * @throws SQLException
     */
    void queryUID(String sql) throws SQLException;

    /**
     * Metodo para obtener el resultado completo de una busqueda, tanto columnas como filas.<p>
     * Quien llame al metodo debe cerrar la conexion con la base de datos.
     * @param sql Instruccion SQL a ejecutar.
     * @return <code>ResultSet</code> con los resultados de la busqueda segun la instruccion SQL dada.
     * @throws SQLException
     */
    ResultSet getData(String sql) throws SQLException;

    /**
     * Metodo para obtener los registros de una busqueda exeptuando el nombre de las columnas.
     * @param sql
     * @return List
     * @throws SQLException
     */
    List<Object> getRecords(String sql) throws SQLException;
}
