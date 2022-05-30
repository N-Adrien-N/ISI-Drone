<%-- 
    Document   : listOrders
    Created on : May 4, 2022, 12:41:13 PM
    Author     : isi
--%>
<%@page import="util.Const"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.OrderComponent"%>
<%
    @SuppressWarnings(
            "unchecked")
    ArrayList<OrderComponent> orders = (ArrayList<OrderComponent>) request.getAttribute("orders");
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    <form action="listOrders" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title"><strong>Liste des commandes  (<%=orders.size()%>)</strong></h3>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <td class="text-center"><strong>Nom</strong></td>
                            <td class="text-center"><strong>Order date</strong></td>
                            <td class="text-center"><strong>Statuts d'envoie</strong></td>
                            <td class="text-center"><strong>Action</strong></td>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (orders != null && orders.size() > 0) {
                                System.out.print("taille: " + orders.size());
                                for (OrderComponent o : orders) {
                        %>
                        <tr>
                            <td class="text-center"><%= o.getClientName()%></td>
                            <td class="text-center"><%= o.getDate()%></td>
                            <td class="text-center">
                                <%
                                    if (o.isIs_shipped() == false) {
                                %>
                                <a href="confirmEditIsShipped?id=<%=o.getId()%>">
                                    <div class="button">
                                        <button class="btn btn-large" type="button" id="expedie"  aria-haspopup="true" aria-expanded="false">
                                            Non expédié
                                        </button>                                               
                                    </div>
                                </a>
                                <%
                                } else {
                                %>
                                <a href="confirmEditIsShipped?id=<%=o.getId()%>">
                                    <div class="button">
                                        <button class="btn btn-large" type="button" id="non_expedie"  aria-haspopup="true" aria-expanded="false">
                                            Expédié
                                        </button>                                               
                                    </div>
                                </a>
                                <%
                                    }
                                %>
                            </td>
                            <td class="text-center">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Actions
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="#">Edit</a>
                                        <%
                                            if (o.isIs_shipped() == false) {
                                        %>
                                        <a class="dropdown-item" href="confirmDeleteOrder?id=<%=o.getId()%>">Delete</a>
                                        <%} else if (o.isIs_shipped() == true) {%>
                                        <a class="dropdown-item" hidden href="confirmDeleteOrder?id=<%=o.getId()%>">Delete</a>
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
