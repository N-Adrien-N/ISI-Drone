<%-- 
    Document   : confirmEditIsActive
    Created on : May 17, 2022, 11:38:54 AM
    Author     : isi
--%>

<%@page import="entities.User"%>
<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    @SuppressWarnings(
            
    
    "unchecked")
        User user = (User) request.getAttribute("user");
        String isActive=user.getIs_active();
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class = "container"> 
    <form action="confirmEditIsActive" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <% 
                if(isActive.equals("ACTIVE")){ %>
            <h3 class="panel-title">Voulez vous desactiver ce client ?</h3>
            <%
                } else { 
            %>
            <h3 class="panel-title">voulez vous activer ce client ?</h3>
            <% } %>
            <fieldset >
                <br>
                <div class="form-group">
                    <label for="firstName">Nom: </label>
                    <input disabled type="text" id="firstName" class="form-control" name="firstName" value=<%=user.getFirstName()%> />
                </div>
                <div class="form-group">
                    <label for="lastName">Prenom: </label>
                    <input disabled type="text" id="lastName" class="form-control" name="lastName" value=<%=user.getLastName()%> />
                </div>
                <div class="form-group">
                    <label for="email">Courriel: </label>
                    <input disabled type="text" id="email" class="form-control" name="email" value=<%=user.getEmail()%> />
                </div>
                    <input type="hidden" id="id" class="form-control" name="id" value=<%=user.getId()%> />
            </fieldset>
            <a class="btn btn-default" href="isActiveChanged?id=<%=user.getId()%>">Confirmer</a>

            <a href="listeClients" class="btn btn-default">Annuler</a>
        </div>
    </form>
</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>

