<%-- 
    Document   : confirmDeleteProduct
    Created on : May 16, 2022, 9:46:40 AM
    Author     : Administrator
--%>

<%@page import="entities.Category"%>
<%@page import="util.Const"%>
<%@page import="entities.Item"%>
<%
    @SuppressWarnings(
    
    "unchecked")
        Item item = (Item) request.getAttribute("item");
        action.ActionCategory.getCategory(request, response, item.getCategory());
        Category category = (Category)request.getAttribute("category");
        item.setCategoryName(category.getName());
        
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    <form action="confirmDeleteProduct" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title">Confirmer la suppression</h3>
        </div>
        <div class="panel-body">
            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <legend>Informations sur l'item</legend>
                <div class="form-group">
                    <label for="item">Nom: </label>
                    <input disabled type="text" id="productId" class="form-control" name="nameProduct" placeholder="Ce nom doit être un nom non-existant" value=<%=item.getName()%> />
                </div>
                <div class="form-group">
                    <label for="Catégorie">Catégorie: </label>
                    <input disabled type="text" id="description" class="form-control" name="categorieName" value=<%=item.getCategoryName()%> />
                </div>
                <div class="form-group">
                    <label for="prix">Prix: </label>
                    <input disabled type="text" id="position" class="form-control" value=<%=item.getPrice()%>  />
                </div>
                <div class="form-group">
                    <label for="serialNumber">Numéro de série: </label>
                    <input disabled type="text" id="position" class="form-control" value=<%=item.getSerial()%>  />
                </div>
                <div class="form-group">
                    <label for="stock">Stock: </label>
                    <input disabled type="text" id="position" class="form-control" value=<%=item.getStock()%>  />
                </div>
                <input type="hidden" id="id" class="form-control" name="id" value=<%=item.getId()%> />
                <button type="submit" class="btn btn-default">Confirmer</button>
                <a href="listproducts" class="btn btn-default">Annuler</a>
            </fieldset>
        </div>
    </form>
</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>

