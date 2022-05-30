/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.MUser;

/**
 *
 * @author isi
 */
public class ActionUser {
    public static void getClients(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("clients", MUser.getClients());
	}
    
    public static void getUserById(HttpServletRequest request, HttpServletResponse response,int id) throws IOException {
		request.setAttribute("user", MUser.getUserById(id));
	}
}
