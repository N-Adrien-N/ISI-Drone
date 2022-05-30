package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Item;
import java.io.IOException;

public class MItem {

    public static ArrayList<Item> getItems(int category) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            MDB.connect();
            String query;
            PreparedStatement ps;
            ResultSet rs;

            if (category == 1) {
                query = "SELECT * FROM product";
                ps = MDB.getPS(query);
            } else {
                query = "SELECT * FROM product WHERE category = ?";
                ps = MDB.getPS(query);
                ps.setInt(1, category);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                items.add(getItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        return items;
    }

    public static Item getItemById(int id) {
        Item item = null;
        try {
            MDB.connect();
            String query = "SELECT * FROM product WHERE id = ?";

            PreparedStatement ps = MDB.getPS(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                item = getItemFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }

        return item;
    }

    public static ArrayList<Item> getItemsByName(String name) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            MDB.connect();
            String test = "%" + name + "%";

            String query = "SELECT * FROM product WHERE UPPER(name) like UPPER(?) OR UPPER(description) like UPPER(?) ";

            PreparedStatement ps = MDB.getPS(query);
            ps.setString(1, test);
            ps.setString(2, test);
            // Item item = new Item ();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                items.add(getItemFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        System.out.println(items);
        return items;

    }

    public static ArrayList<Item> getFeaturedItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            MDB.connect();
            String query;
            ResultSet rs;

            query = "SELECT * FROM product WHERE id IN (SELECT product FROM featured_product)";

            rs = MDB.execQuery(query);

            while (rs.next()) {
                items.add(getItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        return items;
    }

    private static Item getItemFromResultSet(ResultSet rs) {

        Item item = new Item();

        try {
            item.setId(rs.getInt("id"));
            item.setCategory(rs.getInt("category"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getDouble("price"));
            item.setSerial(rs.getString("serialNumber"));
            item.setImage(rs.getString("imgName"));
            item.setStock(rs.getInt("stockQty"));
            item.setActive(rs.getBoolean("isActive"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void deleteItem(int id) {
        try {
            MDB.connect();
            String query = "DELETE FROM product WHERE id = ?";

            PreparedStatement ps = MDB.getPS(query);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
    }

    public static ArrayList<Item> getAll() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            MDB.connect();
            String query;
            PreparedStatement ps;
            ResultSet rs;

            query = "SELECT * FROM product";
            ps = MDB.getPS(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                items.add(getItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }
        return items;

    }
}
