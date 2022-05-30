/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import action.ActionCategory;
import entities.Category;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.MCategory;

/**
 *
 * @author yacine
 */
@WebServlet(name = "EditCategory", urlPatterns = {"/editCategory"})
public class EditCategory extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //recuperation du parametre 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_categorie = Integer.parseInt(request.getParameter("id"));
        //recupére la categorie à editer et affiche les infos recuperés dans le form de la jsp
        Category categorie = MCategory.getCategory(id_categorie);
        request.setAttribute("categoryToEdit", categorie);
        request.getRequestDispatcher("/WEB-INF/editCategory.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //recuperation des nouvelles données de la categorie
            String nom = request.getParameter("name");
            String description = request.getParameter("description");
            boolean is_active = request.getParameter("is_active") != null;
            int order = Integer.parseInt(request.getParameter("order"));
            int id = Integer.parseInt(request.getParameter("id"));
            Category categorie = new Category(id, nom, description, order, is_active);
            //message d'erreur 
            String erreur;
            //verifie si le new nom et l'ordre existent déja dans la bd 
            if (MCategory.getByName(categorie) == 0 && MCategory.getByOrder(categorie) != 1 || is_active) {
                MCategory.EditCategory(id, categorie);
                request.getRequestDispatcher("/WEB-INF/editConfirm.jsp").forward(request, response);
                erreur = null;
            } //si categorie existe deja return un message d'erreur et reste dans le formulaire avec les anciennes donnees
            else {
                erreur = "Aucune modification ajoutée : nom et ou position de catégorie existe déjà !";
                Category cat = MCategory.getCategory(id);
                request.setAttribute("categoryToEdit", cat);
                request.setAttribute("msgErreur", erreur);
                request.getRequestDispatcher("/WEB-INF/editCategory.jsp").forward(request, response);
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
