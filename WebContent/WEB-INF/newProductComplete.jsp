<%-- 
    Document   : newProductComplete
    Created on : 10 mai 2022, 11 h 05 min 53 s
    Author     : Adrien
--%>

<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<!-- Page Content -->
<div class="container">
    <form action="newProduct" method="post" style="float: unset; margin: auto;">
        <div class="alert alert-success" role="alert">
            Produit ajouté avec succès.
        </div>
        <div>
            <a href="home" id="return_link" class="btn btn-default">Retour à l'accueil</a>
        </div>
    </form>
</div>

<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>