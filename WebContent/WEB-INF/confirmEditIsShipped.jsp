<%-- 
    Document   : confirmEditIsShipped
    Created on : May 11, 2022, 1:02:20 PM
    Author     : isi
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
    <form action="confirmEditIsShipped" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <% if(order.isIs_shipped()){ %>
            <h3 class="panel-title">Voulez vous annuler l'envoie de cette commande ?</h3>
            <%
                } else { 
            %>
            <h3 class="panel-title">voulez vous passer cette commande en envoie ?</h3>
            <% } %>
            <fieldset >
                <br>
                <div class="form-group">
                    <label for="order_id">Identifiant de la commande: </label>
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
            <a class="btn btn-default" href="shippingStatusChanged?id=<%=order.getId()%>">Confirmer</a>

            <a href="listOrders" class="btn btn-default">Annuler</a>
        </div>
    </form>
</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>

