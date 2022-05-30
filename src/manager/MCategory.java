package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Category;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MCategory {

    public static ArrayList<Category> getCategories() throws IOException {
        ArrayList<Category> categories = new ArrayList<Category>();


		try {
			MDB.connect();
			String query = "SELECT * FROM category";
			ResultSet rs = MDB.execQuery(query);
			while(rs.next()) {
                            categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		
		return categories;
	}
        
        public static Category getCategory(int id) throws IOException{
		Category category = null;

		try {
			MDB.connect();
			String query = "SELECT * FROM category where id = ?";
                        PreparedStatement ps = MDB.getPS(query);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				category = new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		
		return category;
	}
        
       
        public static void deleteCategory(int id) throws IOException{

		try {
			MDB.connect();
			String query = "DELETE FROM category where id = ?";
                        PreparedStatement ps = MDB.getPS(query);
			
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
	}
	
	public static int isExist(int category) {
		int isExist = 0;		
		try {
			MDB.connect();
			String query = "select * from isidrone.category WHERE name = ?";
			PreparedStatement ps = MDB.getPS(query);
                        
			ps.setInt(1, category);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
                            isExist = 1;
                        }
		} catch (SQLException e) {
			isExist = -1;
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		
		return isExist;
	}
        
        public static int getByName(Category category) {
		int isExist = 0;		
		try {
			MDB.connect();
			String query = "select * from isidrone.category WHERE name = ?";
			PreparedStatement ps = MDB.getPS(query);
                        
			ps.setString(1, category.getName());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
                            isExist = 1;
                        }
		} catch (SQLException e) {
			isExist = -1;
			e.printStackTrace();
		}
		finally {
			MDB.disconnect();	
		}
		
		return isExist;
	}
        
        public static int getByOrder(Category category){
            int isExist = 0;
                      
            
        try {
            MDB.connect();
            String query = "select * from isidrone.category WHERE `order` = ?";
            PreparedStatement ps = MDB.getPS(query);
            ps.setInt(1,category.getOrder());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                isExist = 1;
            }
        } catch (SQLException ex) {
            isExist = -1;
            ex.printStackTrace();
        }finally{
        
            MDB.disconnect();
        }
           return isExist;
        }
        
       

        public static int addCategory(Category nouvelCategory) {
        int nbRowChange = 0;
        int id = 0;
        MDB.connect();
        String query = "insert into category (name, description, `order`, is_active) values(?,?,?,?);";
        PreparedStatement ps = MDB.getPS(query);
        int name_code = MCategory.getByName(nouvelCategory);
        int order_code = MCategory.getByOrder(nouvelCategory);
        if (name_code == 0 && order_code != 1) {
            
            try {

                ps.setString(1, nouvelCategory.getName());
                ps.setString(2, nouvelCategory.getDescription());
                ps.setInt(3, nouvelCategory.getOrder());
                ps.setBoolean(4, nouvelCategory.getIs_active());

                id = ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    nouvelCategory.setId(generatedKeys.getInt(1));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MCategory.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                MDB.disconnect();

            
            
            return id;

        } 
            
            

        }else{}

        return id;
    }

    //Modifier une catégorie existante
    public static int EditCategory(int id_Categorie, Category editCat) throws IOException {
        //nbre de modification effectué
        int retour = 0;
        //connexion BD
        MDB.connect();
        //requete sql
        String query = "UPDATE category SET name = ?, description = ?, `order` = ?, is_active = ? WHERE id = ? ";
        PreparedStatement ps = MDB.getPS(query);
        try {
            ps.setString(1, editCat.getName());
            ps.setString(2, editCat.getDescription());
            ps.setInt(3, editCat.getOrder());
            ps.setBoolean(4, editCat.getIs_active());
            ps.setInt(5, id_Categorie);
            retour = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MDB.disconnect();
        }
        //return le nbre de lignes modifiées dans la BD
        return retour;

    }

}
