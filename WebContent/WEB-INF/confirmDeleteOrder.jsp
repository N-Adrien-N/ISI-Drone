<%-- 
    Document   : confirmDeleteOrder
    Created on : May 16, 2022, 1:48:52 p.m.
    Author     : j-etienne
--%>

<%@page import="entities.Order"%>
<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    @SuppressWarnings(
            
    
    "unchecked")
        Order order = (Order) request.getAttribute("order");
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class = "container"> 
    <form action="confirmDeleteOrder" method="post" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title">Voulez vous supprimer cette commande?</h3>
            <fieldset >
                <br>
                <div class="form-group">
                    <label for="order_id">Identifiant de la commande:</label>
                    <input disabled type="text" id="order_id" class="form-control" name="order_id" value=<%=order.getId()%> />
                </div>
                <div class="form-group">
                    <label for="user_id">Identifiant du client: </label>
                    <input disabled type="text" id="user_id" class="form-control" name="user_id" value=<%=order.getUserId()%> />
                </div>
                <div class="form-group">
                    <label for="date_id">date du paiement: </label>
                    <input disabled type="text" id="date_id" class="form-control" name="date_id" value=<%=order.getDate()%> />
                </div>
                    <input type="hidden" id="id" class="form-control" name="id" value=<%=order.getId()%> />
            </fieldset>
            <button type="submit" class="btn btn-default">Confirmer</button>

            <a href="listOrders" class="btn btn-default">Annuler</a>
        </div>
    </form>
</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>