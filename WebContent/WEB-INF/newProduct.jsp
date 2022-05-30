<%-- 
    Document   : newProduct
    Created on : 5 mai 2022, 10 h 55 min 24 s
    Author     : Adrien
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.Category"%>
<%@page import="util.Misc"%>
<%@page import="java.util.HashMap"%>
<%@page import="util.Const"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>

<%
    @SuppressWarnings(
            
    
    "unchecked")
    HashMap<String, String> hm_formParamValue = (HashMap<String, String>) request.getAttribute("hm_formParamValue");
    @SuppressWarnings(
            
    
    "unchecked")
    HashMap<String, String> hm_fieldErrorMsg = (HashMap<String, String>) request.getAttribute("hm_fieldErrorMsg");
    String error = (String) request.getAttribute("error");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>

<div class="container">
    <form action="newProduct" method="post" class="panel panel-primary form-horizontal" style="float: unset; margin: auto;">
        <div class="panel-heading">
            <h3 class="panel-title"><strong>Nouveau produit</strong></h3>
        </div>
        <div class="panel-body">
            <fieldset class="col-sm-10 col-lg-10 col-md-10">
                <legend>Informations sur le produit</legend>

                <!-- NOM -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productName" class="col-sm-2" style="width: 200px;">*Nom</label>
                        <input type="text" id="productName" class="form-control" name="productName" placeholder="Saisir le nom du produit" value="<%=Misc.getOrDefault(hm_formParamValue, "productName", "")%>" required />
                    </div>
                </div>

                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productName")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productName")%></div>
                <%
                    }
                    if (error != null) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=error%></div>
                <%
                    }
                %>

                <!-- PRIX -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productPrice" class="col-sm-2" style="width: 200px;">*Prix</label>
                        <input type="text" id="productPrice" class="form-control" name="productPrice" placeholder="Saisir le prix du produit" value="<%=Misc.getOrDefault(hm_formParamValue, "productPrice", "")%>" required />
                    </div>
                </div>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productPrice")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productPrice")%></div>
                <%
                    }
                %>
                <!-- CATEGORIE -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productCategory" class="col-sm-2" style="width: 200px;">*Categorie</label>
                        <select id="productCategory" class="form-select" aria-label="Default select example" name="productCategory" required>
                            <%
                                if (categories != null) {
                                    if (categories.size() > 0) {
                                        for (Category c : categories) {
                            %>
                            <option value="<%=c.getId()%>">
                                <%=c.getName()%>
                            </option>
                            <%
                                        }
                                    }
                                }

                            %>
                        </select>
                            <!-- <input type="text" id="productCategory" class="form-control" name="productCategory" placeholder="Saisir l'id du categorie" value="<%=Misc.getOrDefault(hm_formParamValue, "productCategory", "")%>" required />-->
                    </div>
                </div>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productCategory")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productCategory")%></div>
                <%
                    }
                %>
                <!-- SERIAL -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productSerial" class="col-sm-2" style="width: 200px;">*Numéro de série</label>
                        <input type="text" id="ProductSerial" class="form-control" name="productSerial" placeholder="Saisir le numéro de série du produit" value="<%=Misc.getOrDefault(hm_formParamValue, "productSerial", "")%>" required />
                    </div>
                </div>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productSerial")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productSerial")%></div>
                <%
                    }
                %>
                <!-- STOCK -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productStock" class="col-sm-2" style="width: 200px;">*Quantité en stock</label>
                        <input type="text" id="ProductStock" class="form-control" name="productStock" placeholder="Saisir la Quantité dans le stock" value="<%=Misc.getOrDefault(hm_formParamValue, "productStock", "")%>" required />
                    </div>
                </div>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productStock")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productStock")%></div>
                <%
                    }
                %>
                
                <!-- DESCRIPTION -->
                <div class="form-group">
                    <div class="col-sm-10">
                        <label for="productDescription" class="col-sm-2" style="width: 200px;">*Description</label>
                        <textarea type="text" id="lastName" class="form-control" name="productDescription" placeholder="Saisir la description du produit" value="<%=Misc.getOrDefault(hm_formParamValue, "productDescription", "")%>" rows="5" required><%=Misc.getOrDefault(hm_formParamValue, "productDescription", "")%></textarea>
                    </div>
                </div>
                <!-- ISACTIVE -->
                <div class="form-group">
                    <div class="col-sm-10">        
                        <input type="checkbox" id="productIsActive" name="productIsActive" value="1" margin>
                        <label for="productIsActive" class="col-sm-2" style="width: 200px;">*Produit actif ou pas</label>
                    </div>
                </div>
                <%
                    if (hm_fieldErrorMsg != null && hm_fieldErrorMsg.containsKey("productDescription")) {
                %>
                <div class="alert alert-warning" style="margin-bottom: 0px; white-space: pre-line;"><%=hm_fieldErrorMsg.get("productDescription")%></div>
                <%
                    }
                %>
                <!-- BOUTON DE VALIDATION -->
                <button type="submit" class="btn btn-default" margin-left="200px">Valider la création</button>

            </fieldset>
        </div>
    </form>
</div>

<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>