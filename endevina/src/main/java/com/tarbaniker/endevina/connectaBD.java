/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarbaniker.endevina;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author enric
 */
public class connectaBD {
    
    public static void obreBD() {
        /* Obertura/Creació de la base de dades SQLITE */
        var url = "jdbc:sqlite:endevina.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("El nom del driver és " + meta.getDriverName());
                System.out.println("S'ha obert/creat una nova base de dades.");
      
                /* Creació taula animals */
                var sql = "CREATE TABLE IF NOT EXISTS animals("
                        + "   id INTEGER PRIMARY KEY,"
                        + "   nom text NOT NULL,"
                        + "   pregunta text,"
                        + "   apuntaId INTEGER"
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
        }
}
    
}
