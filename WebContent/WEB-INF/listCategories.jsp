<%-- 
    Document   : listCategories
    Created on : May 3, 2022, 12:26:58 p.m.
    Author     : j-etienne
--%>

<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    @SuppressWarnings(
            "unchecked")
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    <form action="listCategories" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title"><strong>Liste des catégories (<%=categories.size()%>)</strong></h3>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <td class="text-center"><strong>Nom</strong></td>
                            <td class="text-center"><strong>Ordre d'affichage</strong></td>
                            <td class="text-center"><strong>Action</strong></td>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (categories != null && categories.size() > 0) {
                                System.out.print("taille: " + categories.size());
                                for (Category c : categories) {
                        %>
                        <tr>
                            <% if (c.getIs_active() != false) {%>
                            <td class="text-center"><%=c.getName()%></td>
                            <% } else {%>

                            <td class="text-center" text-align="right"><span><%=c.getName()%></span><span>&emsp;</span><span>(désactivé)</span></td>

                            <% }%>

                            <td class="text-center"><%=c.getOrder()%></td>
                            <td class="text-center">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Actions
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="editCategory?id=<%=c.getId()%>">Edit</a>
                                        <a class="dropdown-item" href="confirmDeleteCategory?id=<%=c.getId()%>">Delete</a>
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
