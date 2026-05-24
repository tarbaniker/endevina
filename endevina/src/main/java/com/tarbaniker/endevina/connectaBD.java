/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarbaniker.endevina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author enric
 */
public class connectaBD {
    

    //private static final String URL = "jdbc:sqlite:endevina.db";
    private static final String URL = "jdbc:sqlite:"+System.getProperty("user.dir")+"/endevina.db";
    
    
    public  void creaBD() {
        
        System.out.printf("%s\n","URL ->"+URL+"<-");

        // register the driver 
        String sDriverName = "org.sqlite.JDBC";
        try {
            Class.forName(sDriverName);
        } catch (ClassNotFoundException ex) {
            System.getLogger(connectaBD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.exit(3);
        }
        
        /* Obertura/Creació de la base de dades SQLITE */
        var url = URL;

        try ( var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("El nom del driver és " + meta.getDriverName());
                System.out.println("S'ha obert/creat la base de dades.");
      
                /* Creació taula animals */
                var sql = "CREATE TABLE IF NOT EXISTS animals("
                        + "   id INTEGER PRIMARY KEY,"
                        + "   nom text NOT NULL,"
                        + "   pregunta text,"
                        + "   idSI INTEGER,"
                        + "   idNO INTEGER"
                        + ");" ;
                try ( var stmt = conn.createStatement() ) {
                      stmt.execute(sql) ;
                      System.out.println("Si no existia, s'ha creat la taula animals");
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }
    
    public  ArrayList<String> llegirAnimal(int id) {
        System.out.printf("id rebut %d\n", id);
        var url = URL;
        var sql = "SELECT nom, pregunta, idSI, idNO FROM animals WHERE id = ?";

        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {
           //Passem com a paràmetre el id rebut
            stmt.setInt(1, id);
            // Recuperem els resultats; id és PK pertant només un
            var rs = stmt.executeQuery() ;
            var resultat = new ArrayList<String>();
            resultat.add(rs.getString("nom"));
            resultat.add(rs.getString("pregunta"));
            resultat.add(Integer.toString(rs.getInt("idSI")));
            resultat.add(Integer.toString(rs.getInt("idNO")));
            return resultat;
              
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public void inserirAnimal(int id, String nom, String pregunta, int idSI, int idNO) {
        System.out.printf("insereriAnimal. id rebut %d\n", id);
        var url = URL;
        var sql = "INSERT INTO animals(id, nom, pregunta, idSI, idNO) VALUES(?,?,?,?,?)";
        
        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {
            
            stmt.setInt(1, id);
            stmt.setString(2, nom);
            stmt.setString(3, pregunta);
            stmt.setInt(4, idSI);
            stmt.setInt(5, idNO);
            
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    
    }
    
     public void inserirAnimal(int id, String nom) {
        System.out.printf("inserirAnimal (parcial). id rebut %d\n", id);
        var url = URL;
        var sql = "INSERT INTO animals(id, nom, pregunta, idSI, idNO) VALUES(?,?,null,null,null)";
        
        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {
            
            stmt.setInt(1, id);
            stmt.setString(2, nom);
            
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    
     }   

     public void inserirAnimal(int id, String nom, String pregunta) {
        System.out.printf("inserirAnimal (parcial). id rebut %d\n", id);
        var url = URL;
        var sql = "INSERT INTO animals(id, nom, pregunta, idSI, idNO) VALUES(?,?,?,null,null)";
        
        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {
            
            stmt.setInt(1, id);
            stmt.setString(2, nom);
            stmt.setString(3, pregunta);
            
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    
     }   

     
    public void actualitzarAnimal(int id, String pregunta, int idSI, int idNO) {
        System.out.printf("actualitzarBD. id rebut %d\n", id);
        var url = URL;
        var sql = "UPDATE animals SET nom = '*' , "
                + "pregunta = ? , "
                + "idSI = ? , idNO = ? "
                + "WHERE id = ?";
        
        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {
            
            stmt.setString(1, pregunta);
            stmt.setInt(2, idSI);
            stmt.setInt(3, idNO);
            stmt.setInt(4, id);
            
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
        
    }
    
    public int quantsAnimals() {
        System.out.println("quantsAnimals.");
        var url = URL;
        var sql = "SELECT max(id) FROM animals ";
        
        try( var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(sql) ) {

            var rs = stmt.executeQuery() ;
            return rs.getInt(1);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage()); 
            return 0;
        }
        
    }
}
