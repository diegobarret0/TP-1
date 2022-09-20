package Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite implements ComunicacionDB{
    protected Connection conexion = null;
    protected  Statement consulta = null;
    protected ResultSet resultado = null;

    private void conectar(){
        try{
            conexion = DriverManager.getConnection("jdbc:sqlite:DatosUsuarios.db");
            System.out.println("Conectado.");
        }
        catch(SQLException e){
            System.out.println("Error al conectar a base de datos: " + e.getMessage());
        }
    }

    @Override
    public void desconectar() throws SQLException{
        conexion.close();
        System.out.println("Desconectado.");
    }

    @Override
    public void consultaUID(String sql) throws SQLException {
        conectar();

        consulta = conexion.createStatement();
        consulta.execute(sql);

        desconectar();
    }

    @Override
    public ResultSet obtenerDatos(String sql) throws SQLException {
        conectar();
        consulta = conexion.createStatement();
        resultado = consulta.executeQuery(sql);
        return resultado;
    }

    @Override
    public List<Object> obtenerRegistros(String sql) throws SQLException {
        List<Object> registros = new ArrayList<>();

        conectar();
        consulta = conexion.createStatement();
        resultado = consulta.executeQuery(sql);
        if(resultado.next()){
            for (int i=1; i <= resultado.getMetaData().getColumnCount(); i++) {
                registros.add(resultado.getObject(i));
            }
        }
        desconectar();
        return registros;
    }
}
