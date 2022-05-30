
<%@page import="entities.User"%>
<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    @SuppressWarnings(
            "unchecked")
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("clients");
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    
    <div style="display: inline-flex; float: right;">
                <form class="navbar-form" role="search" action="clients?" style="top:0">
                            <div id="auto-search" class="form-group"  style="padding-right:0;">  
                                <input class="form-control biginput" placeholder="Rechercher client" id="autocomplete" type="text" name="search">
                            </div>  
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </form>
            </div>
    <form action="listeClients" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        
        <div class="panel-heading" >
            <div>
                <h3 class="panel-title"><strong>Liste des Clients (<%=users.size()%>)</strong></h3>
            </div>
            
            
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <td class="text-center"><strong>Nom</strong></td>
                            <td class="text-center"><strong>Prénom</strong></td>
                            <td class="text-center"><strong>Courriel</strong></td>
                            <td class="text-center"><strong>Actions</strong></td>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (users != null && users.size() > 0) {
                                System.out.print("taille: " + users.size());
                                for (User u : users) {
                        %>
                        <tr>
                            <td class="text-center"><%=u.getLastName()%></td>
                            <td class="text-center"><%=u.getFirstName()%></td>
                            <td class="text-center"><%=u.getEmail()%></td>
                            <td class="text-center">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Actions
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="#">Edit</a>
                                        <a class="dropdown-item" href="#">Delete</a>
                                        <%
                                    if (u.getIs_active().equals("ACTIVE")) {
                                %>
                                <a class="dropdown-item" href="confirmEditIsActive?id=<%=u.getId()%>">Disable</a>
                                <%
                                } else {
                                %>
                                <a class="dropdown-item" href="confirmEditIsActive?id=<%=u.getId()%>">Activate</a>
                                <%
                                    }
                                %>
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
