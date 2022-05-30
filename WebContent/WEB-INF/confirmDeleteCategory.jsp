<%-- 
    Document   : confirmDeleteCategory
    Created on : May 6, 2022, 3:16:19 p.m.
    Author     : j-etienne
--%>

<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    @SuppressWarnings("unchecked")
        Category category = (Category) request.getAttribute("category");
%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<div class="container">
    <form action="confirmDeleteCategory" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title">Confirmer la suppression</h3>
        </div>
        <div class="panel-body">
            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <legend>Informations sur la categorie</legend>
                <div class="form-group">
                    <label for="category">Nom: </label>
                    <input disabled type="text" id="categoryId" class="form-control" name="newCategory" placeholder="Ce nom doit être un nom non-existant" value=<%=category.getName()%> />
                </div>
                <div class="form-group">
                    <label for="description">Description: </label>
                    <input disabled type="text" id="description" class="form-control" name="descriptionName" value=<%=category.getDescription()%> />
                </div>
                <div class="form-group">
                    <label for="position">Position: </label>
                    <input disabled type="text" id="position" class="form-control" value=<%=category.getOrder()%> placeholder="Vous référer au numéro d'ordre de la dernière catégorie ajoutée" name="positionName" />
                </div>
                    <input type="hidden" id="id" class="form-control" name="id" value=<%=category.getId()%> />
                <button type="submit" class="btn btn-default">Confirmer</button>
            <a href="listCategories" class="btn btn-default">Annuler</a>
            </fieldset>
        </div>
    </form>
</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
