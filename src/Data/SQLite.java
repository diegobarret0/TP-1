package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite implements DBComunication {
    protected Connection conexion = null;
    protected  Statement consulta = null;
    protected ResultSet resultado = null;

    private void connect(){
        try{
            conexion = DriverManager.getConnection("jdbc:sqlite:DatosUsuarios.db");
            System.out.println("Conectado.");
        }
        catch(SQLException e){
            System.out.println("Error al conectar a base de datos: " + e.getMessage());
        }
    }

    @Override
    public void disconnect() throws SQLException{
        conexion.close();
        System.out.println("Desconectado.");
    }

    @Override
    public void queryUID(String sql) throws SQLException {
        connect();

        consulta = conexion.createStatement();
        consulta.execute(sql);

        disconnect();
    }

    @Override
    public ResultSet getData(String sql) throws SQLException {
        connect();
        consulta = conexion.createStatement();
        resultado = consulta.executeQuery(sql);
        return resultado;
    }

    @Override
    public List<Object> getRecords(String sql) throws SQLException {
        List<Object> registros = new ArrayList<>();

        connect();
        consulta = conexion.createStatement();
        resultado = consulta.executeQuery(sql);
        if(resultado.next()){
            for (int i=1; i <= resultado.getMetaData().getColumnCount(); i++) {
                registros.add(resultado.getObject(i));
            }
        }
        disconnect();
        return registros;
    }
}
