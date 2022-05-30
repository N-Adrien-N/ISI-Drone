<%-- 
    Document   : editCategory
    Created on : 9 mai 2022, 09:53:20
    Author     : yacine
--%>

<%@page import="manager.MCategory"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Category"%>
<%@page import="util.Const"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>
<%
//recuperation de la categorie à modifier
    Category categorie = (Category) request.getAttribute("categoryToEdit");
//recuperation du message d'erreur
    String msgErreur = (String) request.getAttribute("msgErreur");
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>

<%if (msgErreur != null) {%>
<div class="container">
    <div class="alert alert-danger" role="alert">
        <%=msgErreur%>
    </div>
</div>
<% }
%>

<div class="container">
    <form action="editCategory" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title"><strong>Editer la catégorie <%=categorie.getId()%></strong></h3>
        </div>
        <div class="panel-body">
            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <div class="form-group">
                    <input type="hidden" id="categoryId" class="form-control" name="id" value=<%=categorie.getId()%>  />
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="category" class="col-sm-2" style="width: 200px;">Nom</label>
                        <input type="text" id="categoryId" class="form-control" name="name" placeholder="Ce nom doit être un nom non-existant" value=<%=categorie.getName()%> required />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">  
                        <label for="description" class="col-sm-2" style="width: 200px;">Description</label>
                        <input type="text" id="description" class="form-control" name="description" value=<%=categorie.getDescription()%> required />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">  
                        <label for="position" class="col-sm-2" style="width: 200px;">Position</label>
                        <input type="text" id="position" class="form-control" placeholder="Vous référer au numéro d'ordre de la dernière catégorie ajoutée" name="order" value=<%=categorie.getOrder()%> required />
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <div class="col-sm-10">  
                            <input type="checkbox" name="is_active" id="choice1" value=<%=categorie.getIs_active()%> >
                            <label for="choice1" class="col-sm-2" style="width: 200px;">Activer la catégorie</label>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-default">Editer la catégorie</button>
            </fieldset>
        </div>
    </form>        
</div>

<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
