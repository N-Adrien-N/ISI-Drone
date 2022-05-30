/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import action.ActionItems;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.MProduct;

/**
 *
 * @author yacine
 */
@WebServlet(name = "EditProduct", urlPatterns = {"/EditProduct"})
public class EditProduct extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
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
        int productId = Integer.parseInt(request.getParameter("product_id"));
        entities.Item product = MProduct.getProductById(productId);
        request.setAttribute("item", product);
        request.getRequestDispatcher("/WEB-INF/editProduct.jsp").forward(request, response);
        processRequest(request, response);
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
        entities.Item item = new entities.Item();

        //recuperer les nouvelles données du produit à editer 
        int id_produit = Integer.parseInt(request.getParameter("productId"));
        int cat_produit = Integer.parseInt(request.getParameter("productCategorie"));
        String nom_produit = request.getParameter("productName");
        String desc_produit = request.getParameter("productDescription");
        double prix_produit = Double.parseDouble(request.getParameter("productPrice"));
        String serial_produit = request.getParameter("productSerial");
        int stock_produit = Integer.parseInt(request.getParameter("productQty"));
        boolean is_active = request.getParameter("isActif") != null;
        item.setActive(is_active);
        item.setCategory(cat_produit);
        item.setDescription(desc_produit);
        item.setId(id_produit);
        item.setName(nom_produit);
        item.setPrice(prix_produit);
        item.setStock(stock_produit);
        item.setSerial(serial_produit);
        //message d'erreur
        String erreur;
        //check si le nom du produit existe déja dans  la categorie 
        if (MProduct.checkProductName(item) == 1) {
            MProduct.editProduct(item);
            request.getRequestDispatcher("/WEB-INF/editProductConfirm.jsp").forward(request, response);
            erreur = null;
        } //si categorie existe deja return un message d'erreur et reste dans le formulaire avec les anciennes donnees
        else {
            erreur = "Aucune modification ajoutée : produit existe déjà !";
            entities.Item produit = MProduct.getProductById(id_produit);
            request.setAttribute("item", produit);
            request.setAttribute("msgErreur", erreur);
            request.getRequestDispatcher("/WEB-INF/editProduct.jsp").forward(request, response);
        }
        processRequest(request, response);
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
