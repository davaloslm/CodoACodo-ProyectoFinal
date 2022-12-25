
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Leo
 */
public class Persistencia {
    
    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement sentenciaSQL;
    private ResultSetMetaData rsm;
    
    public String servidor, baseDeDatos, usuario, clave, ejecutar;
    
//    método para conectarse a la base de datos
    public Connection conectarse(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            servidor = "localhost:3306/";
            baseDeDatos = "cacproyectojava";
            usuario = "root";
            clave = "";
            
            conexion = DriverManager.getConnection("jdbc:mysql://" + servidor + baseDeDatos +"?autoReconnect=true&useSSL=false",usuario, clave);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
    }
    
//    método para ejecutar llamados a la base de datos
    
    public ResultSet consultaSQL(String busqueda){
        
        try {
            sentenciaSQL = conectarse().prepareStatement(busqueda);
        
        
            rs = sentenciaSQL.executeQuery();

            rsm = rs.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}
