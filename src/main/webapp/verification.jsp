<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,javax.servlet.*,java.util.*,java.net.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
 <link id="css" href="captcha.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="banner">
    <h1>Grow More</h1></div>
    <div id="main">
        <h2>Verifiction</h2>
        <form action="./verification" method="">
        <input id="otp" type="number" placeholder="Enter Otp here">
      <h3 id="captcha"></h3>
      <input id="userInput" type="text" placeholder="Captcha here">
      <br><br>
      <input type="submit" value="Submit" onclick="submit1()">
      </form>
      
      
      
    </div>
    
    <script>
    otpGenerate();
    var captcha="";
    captchaGen()
    function captchaGen(){

      
    var n=7;
	
	var ch="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@";
	
	while(n>0) {
		var index=Math.floor(Math.random()*63);
		captcha+=ch.charAt(index);
		n-=1;
	}
    document.getElementById("captcha").innerText=captcha;
}
function submit1(){
	
	 if(document.getElementById("userInput").value==captcha && captcha!=""){
		 console.log(document.getElementById("userInput").value);
		 console.log(captcha);
		 submit()
	 }
	 else{
		console.log("reload");
		//location.reload();
	 }
}
function submit(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4){
			if(this.status==200){
				
				var response=this.responseText;
				console.log(response);
				var res=JSON.parse(response);
				console.log(res);
				if(res.message.includes("success")){
					alert("your account created!!")
					window.location.href="login.html";
				}
				else{
					alert("your account was not created!!");
					window.location.href="login.html";
				}
				console.log(response);
			}
		}
	}
var reqObject={};
	
	reqObject.otp=document.getElementById("otp").value;
	
	
	xhr.open("POST","./otpCheck");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//xhr.setRequestHeader("Content-Type","application/json");
	console.log("second");
	xhr.send("otp="+reqObject.otp);
}
function otpGenerate(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		
	}

	xhr.open("POST","./otpTrigger");
	//xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//xhr.setRequestHeader("Content-Type","application/json");
	//
	xhr.send();
}
    
    </script>
  <% if (session.getAttribute("otp")==null) {
	response.sendRedirect("login.html");

}
	
%>  

</body>
</html>