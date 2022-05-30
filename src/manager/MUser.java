/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import entities.User;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isi
 */
public class MUser {
    public static ArrayList<User> getClients() throws IOException{
		ArrayList<User> clients = new ArrayList<>();

		try {
			MDB.connect();
			String query = "SELECT firstName,lastName,email,is_active, id FROM isidrone.user where type_utilisateur='CLIENT'";
			ResultSet rs = MDB.execQuery(query);
			while(rs.next()) {
				clients.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5)));	
			}
			
		} catch (SQLException e) {
		}
		finally {
			MDB.disconnect();	
		}
		
		return clients;
	}
    
    public static User getUserById(int id) {

        User user = new User();

        try {

            MDB.connect();

            String query = "SELECT * FROM `user` WHERE `user`.id = ? ;";

            PreparedStatement ps = MDB.getPS(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            // Une ligne = un item avec qte
            while (rs.next()) {

                

                    user.setId(rs.getInt("id"));                   
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));
                    user.setIs_active(rs.getString("is_active"));
                    
                
            }

            //orderList.add(order);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MDB.disconnect();
        }

        return user;
    }
    
    public static int changeIsActive(int id) throws IOException {
        //nbre de modification effectué
        int retour = 0;
        String isActive="";
        //connexion BD
        MDB.connect();
        
        //requete sql
        String query = "";
        User user = getUserById(id);
        isActive=user.getIs_active();
        if(isActive.equals("ACTIVE")){
            query = "update `user` set is_active = 'DISABLED' where id= ?";
        }
        else{
            query = "update `user` set is_active = 'ACTIVE' where id= ?";
        }
        
        PreparedStatement ps = MDB.getPS(query);
        try {
            
            ps.setInt(1, id);
            retour = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MDB.disconnect();
        }
        //return le nbre de lignes modifiées dans la BD
        return retour;

    }
}
