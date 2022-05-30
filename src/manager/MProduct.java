/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import entities.Item;
import entities.ItemCart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author isi
 */
public class MProduct {

    public static Item getProductById(int id) {
        int orderId = 0;
        Item item = new Item();
        try {
            MDB.connect();
            String query = "SELECT * FROM product WHERE id = ?";
            PreparedStatement ps = MDB.getPS(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCategory(rs.getInt("category"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setSerial(rs.getNString("serialNumber"));
                item.setStock(rs.getInt("stockQty"));
                item.setActive(rs.getInt("isActive") == 1 ? true : false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        return item;
    }

    public static void editProduct(Item item) {
        try {
            // TODO Faire une transaction
            MDB.connect();

            // Partie 1
            // Creer une commande et récupere le ID
            String query = "UPDATE product set category = ? , name = ? , description = ? , price = ? , serialNumber = ?, stockQty = ?, isActive = ? WHERE id = ? ";

            PreparedStatement ps = MDB.getPS(query);

            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getSerial());
            ps.setInt(6, item.getStock());
            ps.setBoolean(7, item.isActive());
            ps.setInt(8, item.getId());

            ps.executeUpdate();

            //ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }

    }

    // check si le productName existe déjà dans la categorie
    public static int checkProductName(Item produit) {
        int productExist = 0;
        int cpt = 0;
        try {
            MDB.connect();
            String query = "select * from product WHERE name = ? and category = ?";
            PreparedStatement ps = MDB.getPS(query);
            ps.setString(1, produit.getName());
            ps.setInt(2, produit.getCategory());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //cpt incrémente aprés chaque colonne trouvée de la requête
                cpt++;
            }
            //la modification est correcte si le nbre de lignes retournées est égal à 0 ou 1 
            if (cpt < 1) {
                productExist = 1;

            }
        } catch (SQLException e) {
            productExist = -1;
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        return productExist;
    }

}
