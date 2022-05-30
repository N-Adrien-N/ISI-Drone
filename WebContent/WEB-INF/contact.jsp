<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<jsp:include page="<%=Const.PATH_HEAD_JSP%>"/>
<jsp:include page="<%=Const.PATH_MENU_JSP%>"/>
	<!-- /.container -->
    <!-- Page Content -->
    <div class="container">
    	<div class="col-md-5">
		    <div class="form-area">  
		        <form role="form" action="contact" method="post">
                    <h3 style="margin-bottom: 25px; text-align: center;">Formulaire de contact</h3>
    				<div class="form-group">
						<input type="text" class="form-control" id="name" name="name" placeholder="Nom" required>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="subject" name="subject" placeholder="Sujet" required>
					</div>
                    <div class="form-group">
                    <textarea class="form-control" id="message" placeholder="Message" rows="7"></textarea>                  
                    </div>
		        	<button type="submit" id="submit" class="btn btn-default pull-right">Envoyer</button>
				</form>
    		</div>
    	</div>
   		<div class="col-md-5">
   			 <div class="span4">
	    		<h2>ISI Drone</h2>
	    		<address>
	    			2100 Boulevard Maisonneuve Est<br>
	    			Montréal (Québec)<br>
	    			H2K 4S1<br>
	    			<abbr title="Courriel">Courriel :</abbr> <a href="mailto:info@isi-mtl.com">info@isi-mtl.com</a> <br>
	    			<abbr title="Téléphone">Téléphone :</abbr> <a href="tel:514-842-2426">514-842-2426</a>
	    		</address>
	    	</div>
   			 <div class="span8">
	        	<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11180.907572282!2d-73.5512917!3d45.5256395!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x647a6b6d7cb681a7!2sISI%2C%20l&#39;Institut%20sup%C3%A9rieur%20d&#39;informatique!5e0!3m2!1sfr!2sca!4v1651245050501!5m2!1sfr!2sca" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
	    	</div>
   		</div>
	</div>
<jsp:include page="<%=Const.PATH_FOOTER_JSP%>"/>