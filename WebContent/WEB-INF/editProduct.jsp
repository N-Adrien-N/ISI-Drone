<%-- 
    Document   : editProduct
    Created on : May 10, 2022, 11:48:43 AM
    Author     : isi
--%>
<%@page import="entities.Item"%>
<%
    Item item = (Item) request.getAttribute("item");
    String msgErreur = (String) request.getAttribute("msgErreur");
%>
<%@page import="util.Const"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>


<div class="container">
    <%
        //if (item != null) {
            if (msgErreur != null) {%>
    <div class="container">
        <div class="alert alert-danger" role="alert">
            <%=msgErreur%>
        </div>
    </div>
    <% }
    %>
    <form action="EditProduct" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title">Modifier produit</h3>
        </div>
        <div class="panel-body">
            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <legend>Informations sur le produit</legend>
                <div class="form-group">
                    <label for="productId">id : </label>
                    <input type="text" id="productName" class="form-control" name="productId" placeholder="" value="<%= item.getId()%>" readonly />
                </div>
                <div class="form-group">
                    <label for="productName">Nom : </label>
                    <input type="text" id="productName" class="form-control" name="productName" placeholder="" value="<%= item.getName()%>" required />
                </div>
                <div class="form-group">
                    <label for="productCategorie">Categorie : </label>
                    <input type="text" id="productName" class="form-control" name="productCategorie" placeholder="" value="<%= item.getCategory()%>" required />
                </div>
                <div class="form-group">
                    <label for="productDescription">Description : </label>
                    <input type="text" id="productName" class="form-control" name="productDescription" placeholder="" value="<%= item.getDescription()%>" required />
                </div>
                <div class="form-group">
                    <label for="productPrice">Prix : </label>
                    <input type="text" id="productPrice" class="form-control" name="productPrice" placeholder="" value="<%= item.getPrice()%>" required />
                </div>
                <div class="form-group">
                    <label for="productSerial">Numero de serie : </label>
                    <input type="text" id="productDescription" class="form-control" name="productSerial" placeholder="<%= item.getSerial()%>" value="<%= item.getSerial()%>" required>
                </div>
                <div class="form-group">
                    <label for="productQty">Quantite en stock : </label>
                    <input type="text" id="productDescription" class="form-control" name="productQty" placeholder="" value="<%= item.getStock()%>" required>
                </div>
                <div class="form-group">
                    <input type="checkbox" id="isActif" name="isActif" value="1" <% if (item.isActive()) {%> checked <%} %>>
                    <label for="isActif">Produit actif ou pas  : </label>
                </div>            
                <button type="submit" class="btn btn-default">Valider la modification</button>
                <a href="listproducts" class="btn btn-default" role="button" aria-pressed="true">Annuler</a>
            </fieldset>
        </div>
    </form>
   
</div>

<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
