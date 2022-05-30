<%-- 
    Document   : editConfirm
    Created on : 10 mai 2022, 13:08:02
    Author     : yacine
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
	  		Catégorie modifiée avec succés !
	  	</div>

        <a href="listCategories" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Liste catégories</a>
    </div>
<!-- Footer -->
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>

