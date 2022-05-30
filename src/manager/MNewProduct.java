/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import entities.Item;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Hash;

/**
 *
 * @author Adrien
 */
public class MNewProduct {

    /*   * -1 : Problème de connexion
	 *  0 : Le nom (Name) est déjà présent dans la base de données
	 *  1 : L'ajout c'est fait sans problème
	 * */
    public static int newProduct(Item item) {
        int code = isExist(item);

        if (code == 1) {

            try {
                MDB.connect();

                // Ajoute le produit a la BD
                String query = "INSERT INTO `isidrone`.`product`\n" +
"(`category`,\n" +
"`name`,\n" +
"`description`,\n" +
"`price`,\n" +
"`serialNumber`,\n" +
"`imgName`,\n" +
"`stockQty`,\n" +
"`isActive`)\n" +
"VALUES\n" +
"(?,?,?,?,?,'19.jpg',?,?);";

                PreparedStatement ps = MDB.getPS(query);
                ps.setInt(1, item.getCategory());
                ps.setString(2, item.getName());
                ps.setString(3, item.getDescription());
                ps.setDouble(4, item.getPrice());
                ps.setString(5, item.getSerial());
                ps.setInt(6, item.getStock());
                ps.setBoolean(7, item.isActive());
                

                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MNewProduct.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                MDB.disconnect();
            }

        }
        return code;
    }

    /*   * -1 : Problème de connexion
	 *  0 : Le nom est déjà présent dans la base de données
	 *  1 : Le nom n'est pas déjà présent dans la base de données
	 * */
    private static int isExist(Item item) {
        int isExist = 1;

        try {
            MDB.connect();
            String query = "SELECT * FROM isidrone.product WHERE name = ?";
            PreparedStatement ps = MDB.getPS(query);

            ps.setString(1, item.getName());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                isExist = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }

        return isExist;
    }
}
