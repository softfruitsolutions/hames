<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hames - Softfruit</title>
	
	<!-- Jquery -->
	<script src="resources/endless/js/jquery.min.js" type="text/javascript" ></script>
	
	<!-- Font Awesome -->
	<link href="resources/endless/css/font-awesome.min.css" rel="stylesheet">
	
	<script type="text/javascript">
		/* $( ".input" ).focusin(function() {
		  $( this ).find( "span" ).animate({"opacity":"30"}, 200);
		});

		$( ".input" ).focusout(function() {
		  $( this ).find( "span" ).animate({"opacity":"1"}, 300);
		}); */

		$(".login").submit(function(){
		  $(this).find(".submit i").removeAttr('class').addClass("fa fa-check").css({"color":"#fff"});
		  $(".submit").css({"background":"#2ecc71", "border-color":"#2ecc71"});
		  $(".feedback").show().animate({"opacity":"1", "bottom":"-80px"}, 400);
		  $("input").css({"border-color":"#2ecc71"});
		  return false;
		});
	</script>
	
	<style type="text/css">
	  * {
		  -ms-box-sizing: border-box;
		  -moz-box-sizing: border-box;
		  -webkit-box-sizing: border-box;
		  box-sizing: border-box;
		  margin: 0;
		  padding: 0;
		  border: 0;
		}
		html,
		body {
		  width: 100%;
		  height: 100%;
		  background: url('resources/images/sativa.png') repeat fixed;
		  font-family: 'Open Sans', sans-serif;
		  font-weight: 200;
		}
		.login {
		  position: relative;
		  top: 50%;
		  width: 350px;
		  display: table;
		  margin: -150px auto 0 auto;
		  background: #fff;
		  border-radius: 4px;
		}
		.legend {
		  position: relative;
		  width: 100%;
		  display: block;
		  background: #FF7052;
		  padding: 15px;
		  color: #fff;
		  font-size: 20px;
		}
		.legend:after {
		  content: "";
		  background-image: url('resources/images/multy-user.png');
		  background-size: 100px 100px;
		  background-repeat: no-repeat;
		  background-position: 230px -16px;
		  opacity: 0.06;
		  top: 0;
		  left: 0;
		  bottom: 0;
		  right: 0;
		  position: absolute;
		}
		.input {
		  position: relative;
		  width: 90%;
		  margin: 15px auto;
		}
		.input span {
		  position: absolute;
		  display: block;
		  color: #d4d4d4;
		  left: 10px;
		  top: 8px;
		  font-size: 20px;
		}
		.input input {
		  width: 100%;
		  padding: 10px 5px 10px 40px;
		  display: block;
		  border: 1px solid #EDEDED;
		  border-radius: 4px;
		  transition: 0.2s ease-out;
		  color: #a1a1a1;
		}
		.input input:focus {
		  padding: 10px 5px 10px 35px;
		  outline: 0;
		  border-color: #FF7052;
		}
		.submit {
		  width: 45px;
		  height: 45px;
		  display: block;
		  margin: 0 auto -15px auto;
		  background: #fff;
		  border-radius: 100%;
		  border: 1px solid #FF7052;
		  color: #FF7052;
		  font-size: 24px;
		  cursor: pointer;
		  box-shadow: 0px 0px 0px 7px #fff;
		  transition: 0.2s ease-out;
		}
		.submit:hover,
		.submit:focus {
		  background: #FF7052;
		  color: #fff;
		  outline: 0;
		}
		.feedback {
		  position: absolute;
		  bottom: -70px;
		  width: 100%;
		  text-align: center;
		  color: #fff;
		  background: #2ecc71;
		  padding: 10px 0;
		  font-size: 12px;
		  display: none;
		  opacity: 0;
		}
		.feedback:before {
		  bottom: 100%;
		  left: 50%;
		  border: solid transparent;
		  content: "";
		  height: 0;
		  width: 0;
		  position: absolute;
		  pointer-events: none;
		  border-color: rgba(46, 204, 113, 0);
		  border-bottom-color: #2ecc71;
		  border-width: 10px;
		  margin-left: -10px;
		}
	</style>
	
	
</head>

<body>
<%
  if (request.getAttribute("shiroLoginFailure")!=null) {
%>
Username or password incorrect
<%
  }
%>

	
	<form class="login" action="" method="POST">
	  <fieldset>
	  	<legend class="legend"><b>HAMES</b></legend>
	    <div class="input">
	    	<input type="text" placeholder="Username" id="username" name="username" required />
	      <span><i class="fa fa-envelope-o"></i></span>
	    </div>
	    
	    <div class="input">
	    	<input type="password" placeholder="Password" id="password" name="password" required />
	      <span><i class="fa fa-lock"></i></span>
	    </div>
	    
	    <button type="submit" class="submit"><i class="fa fa-long-arrow-right"></i></button>
	    
	  </fieldset>
	  
	   <div class="feedback">
	  	login successful <br />
	    redirecting...
	  </div>
	 
	</form>
	
</body>
</html>