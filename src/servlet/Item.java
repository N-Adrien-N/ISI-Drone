package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Const;
import action.ActionCart;
import action.ActionItems;

/**
 * Servlet implementation class Item
 */
@WebServlet(name = "item", urlPatterns = { "/item" })
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("item"));
		}
		catch(NumberFormatException e) {
			id = -1;
		}
		
		ActionItems.getItemById(id, request, response);
		request.getRequestDispatcher(Const.PATH_PAGE_ITEM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = request.getParameter("itemId");
		String strQty = request.getParameter("qty");
	 
		ActionCart.addItem(request, response, strId, strQty);
		
		doGet(request, response);
	}

}
