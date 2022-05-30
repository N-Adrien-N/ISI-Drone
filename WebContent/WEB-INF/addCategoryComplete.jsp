<%-- 
    Document   : addCategoryComplete
    Created on : 9 mai 2022, 08:30:39
    Author     : ORNELLA
--%>
<%@page import="java.util.HashMap"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>
<!-- Page Content -->
<div class="container">
    <div class="alert alert-success" role="alert">
        Enregistrement de la catégorie complété avec succès.
    </div>
    <div>
        <a href="listCategories" class="btn btn-default" role="button" aria-pressed="true">Retour à la liste des catégories</a>
    </div>
</div>
<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
