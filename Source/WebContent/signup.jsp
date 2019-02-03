<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Registrazione officina</title>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/signup.css">  
	<!-- Insert javascript code here -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    	<jsp:include page = "menu.jsp"/>
  	
    <div class="container">

      <div class="signup_container">
        <div class="header">
          <span>Registrazione officina</span>
          <div>Alcuni campi inseriti sono errati.Riprovare!</div>
        </div>

        <form action="" method="post">
        
          <div class = "inputBox">
            <input type="text" name="p_iva" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Partita Iva</label>
          </div>
    
          <div class = "inputBox">
            <input type="text" name="account_holder" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Intestatario officina</label>
          </div>
    
          <div class = "inputBox">
            <input type="email" name="email" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Indirizzo Email</label>
          </div>
    
          <div class = "inputBox">
            <input type="text" name="address" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Indirizzo</label>
          </div>
            
          <div class = "inputBox">
              <textarea rows="3" name="description" value ="" onkeyup="this.setAttribute('value', this.value);" required></textarea>
            <label>Descrizione servizi offerti</label>
          </div>
   
          <button type="submit" name="button" value="Signup">
          	REGISTRATI
          </button>
        </form>

        <div class="form_footer">
          <div>Hai gi� un account?<a href="#">Effettua il login</a></div>
        </div>

      </div>
    </div>
  </body>
</html>