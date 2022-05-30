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
import util.Const;

/**
 *
 * @author ORNELLA
 */
@WebServlet(name = "NewCategory", urlPatterns = {"/newCategory"})
public class NewCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public NewCategory() {
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        request.getRequestDispatcher("/WEB-INF/newCategory.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        //Category category = new Category(10, request.getParameter("name"), request.getParameter("description"), Integer.parseInt(request.getParameter("order")), Boolean.parseBoolean(request.getParameter("is_active")));
        String error;

        //MCategory.addCategory(category);
        if (ActionCategory.addCategory(request, response)) {
            error = null;
            request.getRequestDispatcher(Const.PATH_PAGE_NEWCATEGORY_COMPLETE).forward(request, response);
        } else {
            error = "Cette catégorie existe déjà ! Vérifier le nom et la position";
            request.setAttribute("msgErreur", error);
            request.getRequestDispatcher(Const.PATH_PAGE_NEWCATEGORY).forward(request, response);
        }

        //request.getRequestDispatcher("/listCategories").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
