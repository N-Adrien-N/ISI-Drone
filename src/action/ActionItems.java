package action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MItem;
import manager.MCategory;

public class ActionItems {

    public static void getItems(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("items", MItem.getItems(ActionCategory.getSelectedCategory(request, response)));
    }

    public static void getItemById(int id, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("item", MItem.getItemById(id));
    }

    public static void getItemsByName(String search, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("items", MItem.getItemsByName(search));
    }

    public static void getAllItems(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("items", MItem.getAll());

    }

    public static void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("items", MItem.getItems(1));
        request.setAttribute("categories", MCategory.getCategories());
    }
    
    public static void deleteItem(int idProductToDelete, HttpServletRequest request, HttpServletResponse response){
        MItem.deleteItem(idProductToDelete);
    }
}
