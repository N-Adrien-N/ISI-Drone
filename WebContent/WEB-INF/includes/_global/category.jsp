<%@page import="entities.User"%>
<%@page import="action.ActionCategory"%>
<%@page import="java.util.ArrayList, entities.Category"%>
<%
    //Vérification si la catégorie sélectionne est valide (Un utilisateur pourrait tenter d'entré une catégorie invalide dans la barre d'adresse)
    @SuppressWarnings(
            
    
    "unchecked")
            User user = (User) session.getAttribute("user");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
    int categorySelected = ActionCategory.getSelectedCategory(request, response);

    if (categories.size() > 0) {
        for (Category category : categories) {
            if (category.getIs_active() == true) {
%>
<a href="items?category=<%=category.getId()%>" class="list-group-item<%=(category.getId() == categorySelected ? " active" : "")%>"><%=category.getName()%></a>
<%
} else {
    if (user != null) {
        if (user.getTypeUtilisateur().equals("ADMIN")) {
%>
<a href="items?category=<%=category.getId()%>" class="list-group-item<%=(category.getId() == categorySelected ? " active" : "")%>"><%=category.getName()%>(désactivé)</a>
<%
                }
            }
        }
    }
} else {
%>
Aucune Catégorie n'est présente pour le moment.
<%
    }
%>