<%-- 
    Document   : listProducts
    Created on : May 2, 2022, 12:59:27 PM
    Author     : Administrator
--%>
<%@page import="entities.Item"%>
<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    @SuppressWarnings(
              "unchecked")
    ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
    for (Item item : items) {
        Category c = manager.MCategory.getCategory(item.getCategory());
        item.setCategoryName(c.getName());
        System.out.println("" + item.getName());
    }
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    <form action="listProducts" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title"><strong>Liste des produits (<%=items.size()%>)</strong></h3>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <td class="text-center"><strong>Nom</strong></td>
                            <td class="text-center"><strong>Catégorie </strong></td>
                            <td class="text-center"><strong>Prix </strong></td>
                            <td class="text-center"><strong>Quantité en stock </strong></td>
                            <td class="text-center"><strong>Actions</strong></td>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (items != null && items.size() > 0 && categories != null && categories.size() > 0) {
                                for (Item item : items) {
                        %>
                        <tr>
                            <td class="text-center"><%=item.getName()%></td>
                            <td class="text-center"><%=item.getCategoryName()%></td>
                            <td class="text-center"><%=item.getPrice()%></td>
                            <td class="text-center"><%=item.getStock()%></td>
                            <td class="text-center">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Actions
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="EditProduct?product_id=<%=item.getId()%>">Edit</a>
                                        <a class="dropdown-item" href="confirmDeleteProduct?idProduct=<%=item.getId()%>">Delete</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div> 
<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
