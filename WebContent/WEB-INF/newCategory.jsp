<%-- 
    Document   : newCategory.jsp
    Created on : 5 mai 2022, 09:19:52
    Author     : ORNELLA
--%>

<%@page import="java.lang.String"%>
<%@page import="util.Misc"%>
<%@page import="java.util.HashMap"%>
<%@page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<%
    @SuppressWarnings(  "unchecked")
    HashMap<String, String> hm_formParamValue = (HashMap<String, String>) request.getAttribute("hm_formParams");
    @SuppressWarnings(  "unchecked")
    HashMap<String, String> hm_fieldErrorMsg = (HashMap<String, String>) request.getAttribute("hm_fieldErrorMsg");
    String error = (String) request.getAttribute("msgErreur");
%>
<div class = "container"> 

    <%if (error != null) {%>
    <div class="container">
        <div class="alert alert-danger" role="alert">
            <%=error%>
        </div>
    </div>
    <% }
    %>   

    <form action="newCategory" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">

        <div class="panel-heading">
            <h3 class="panel-title"><strong>Ajouter une nouvelle catégorie</strong></h3>
        </div>

        <div class="panel-body">

            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <legend>Informations sur le produit</legend>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("name")) {
                %> 
                <div>
                    <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%= hm_fieldErrorMsg.get("name")%></div>
                </div>
                <%
                    }
                %>
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="category" class="col-sm-2" style="width: 200px;">*Nom </label>
                        <input type="text" id="categoryId" class="form-control" name="name" placeholder="Ce nom doit être un nom non-existant" value="<%=Misc.getOrDefault(hm_formParamValue, "name", "")%>" required />
                    </div>
                </div>

                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("description")) {
                %> 
                <div>
                    <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%= hm_fieldErrorMsg.get("description")%></div>
                </div>
                <%
                    }
                %>
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="description" class="col-sm-2" style="width: 200px;">*Description </label>
                        <input type="text" id="description" class="form-control" placeholder="Description de la catégorie" name="description" value="<%=Misc.getOrDefault(hm_formParamValue, "description", "")%>" required />
                    </div>
                </div>

                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("order")) {
                %> 

                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%= hm_fieldErrorMsg.get("order")%></div>

                <%
                    }
                %>                        
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="position" class="col-sm-2" style="width: 200px;">*Position </label>
                        <input type="text" id="position" class="form-control" placeholder="Vous référer au numéro d'ordre de la dernière catégorie ajoutée" name="order" value="<%=Misc.getOrDefault(hm_formParamValue, "order", "")%>" required />
                    </div>
                </div>

                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("is_active")) {
                %> 
                <div>
                    <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%= hm_fieldErrorMsg.get("is_active")%></div>
                </div>
                <%
                    }
                %>                   
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="checkbox" name="is_active" id="choice1" value="1">
                        <label for="choice1" class="col-sm-2" style="width: 200px;">Activer la catégorie</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-default">Ajouter la catégorie</button>
            </fieldset>

        </div>

    </form>

</div>

<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>
