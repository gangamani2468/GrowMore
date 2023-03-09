/**
 * 
 */
//function validate(){
	//var validationPassed=false;
	//var 
//}
console.log(listCookies());


function init(){
	if(listCookies()!=""){
		window.open("");
	}
}
function signUpPage(){
	document.getElementById("wholeContainer").setAttribute("style","font-size: 3em")
	document.getElementById("css").setAttribute("href","style2.css");
	document.getElementById("loginContainer").innerHTML="<div id=\"loginContainer\"> <h1>SignUp</h1><div style=\"overflow-x: hidden;height: 53vh;width: 35vh;display: flex;flex-direction: column;\"> <div> <span>Name</span><br> <input type=\"text\" id=\"name\" required></div><br><br><div> <span>Date Of Birth</span><br> <input type=\"date\" id=\"date\" required> </div><br><br> <div> <span>Phone Number</span><br> <input type=\"number\" id=\"phone\" required> </div> <br><br><div> <span>Gmail</span><br> <input type=\"text\" id=\"gmail\" required> </div> <br><br><div> <span>Pan Number</span><br> <input oninput=\"this.value=this.value.toUpperCase()\"type=\"text\" id=\"pan\" required> </div><br><br> <div id=\"marryDiv\"> <span>Marital Status</span>: <input name=\"marital\" type=\"radio\" id=\"single\" value=\"Single\" class=\"radio\"> <label for=\"single\" >Single</label> <input name=\"marital\" type=\"radio\" id=\"married\" value=\"Married\" class=\"radio\"> <label for=\"married\" >Married</label> </div><br><br> <div id=\"genderDiv\" style=\"width:100%;\"> <span>Gender</span>: <input name=\"gender\" type=\"radio\" id=\"Marital1\" value=\"Male\" class=\"radio\"> <label for=\"marital1\" >Male</label> <input name=\"gender\" type=\"radio\" id=\"Marital2\" value=\"Female\" class=\"radio\"> <label for=\"marital2\" >Female</label> <input name=\"gender\" type=\"radio\" id=\"Marital3\" value=\"Others\" class=\"radio\"> <label for=\"marital3\" >Others</label> </div><br><br> <div> <span>Password</span><br> <input type=\"password\" id=\"userPass\" required>  <input style=\"height: 2vh;width: 1vw;\" type=\"checkbox\" id=\"check\" onclick=\"showPass()\" >Show Password</div>  <h2> Bank Details</h2> <div>  <span>IFSC Code</span><br> <input oninput=\"this.value=this.value.toUpperCase()\"type=\"text\" id=\"ifsc\" required> </div><br><br> <div> <span>AccountNumber</span><br> <input type=\"number\" id=\"accountNo\" required> </div><br><br></div><div id=\"loginBut\" onclick=\"validation()\">SignUp</div> <div style=\"display: flex;\"><span id=\"signin\">Do you have an account?</span ><span id=\"loginPage\" style=\"color: blueviolet;\" onclick=\"logInPage()\" > login</span></div> </div><script> var check=\"password\"; function showPass(){ let checking = document.getElementById(\"check\"); console.log(checking==true); if(check == \"password\"){ userPass.type = 'text' check=\"\"; } else{ userPass.type = 'password'; check=\"password\"; } } </script>"
}
function logInPage(){
		
		console.log("loo");
		document.getElementById("wholeContainer").setAttribute("style","")
		document.getElementById("css").setAttribute("href","style.css");
		document.getElementById("loginContainer").innerHTML="<div id=\"loginContainer\"> <h1>Login</h1> <div> <span>Phone Number</span><br> <input type=\"number\" id=\"userId\" required> </div> <div> <span>Password</span><br> <input type=\"password\" id=\"userPass\" required><br><br> <input style=\"height: 2vh;width: 1vw;\" type=\"checkbox\" id=\"check\" onclick=\"showPass()\" >Show Password </div> <div id=\"loginBut\" onclick=\"login()\">Login</div> <div style=\"cursor: pointer;\"><span style=\"color: gray;\">Don't have an account? </span><span style=\"color: blue;\" onclick=\"signUpPage()\">SignUp</span></div> <p id=\"forgot\">Forgot password?</p> </div> <div id=\"successMsg\"></div> <script> var check=\"password\"; function showPass(){ let checking = document.getElementById(\"check\");if(check == \"password\"){ userPass.type = 'text'; check=\"\"; } else{ userPass.type = 'password'; check=\"password\"; } } </script>";	
}
function login(){
	console.log("one");
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4){
			if(this.status==200){
				var response=this.responseText;
				console.log(response);
				
				if(response.includes("success")){
					window.location.href="GrowMore.jsp";	
				}
				else{
					alert("invalid input");
				}
				//console.log(response);
			}
		}
	}
	var reqObject={};
	
	reqObject.phone=document.getElementById("userId").value;
	reqObject.pass=document.getElementById("userPass").value;
	xhr.open("POST","./Login");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//xhr.setRequestHeader("Content-Type","application/json");
	console.log("second");
	xhr.send("phone="+reqObject.phone+"&pass="+reqObject.pass);
//  xhr.send("{\"details\":"+JSON.stringify(reqObject)+"}");
	
}
function signUp(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(this.readyState==4){
			if(this.status==200){
				var response=this.responseText;
				console.log(response);
				if(response!="1"){
					//captcha generation
					alert("invalid input");
					signUpPage();
					
				}else{
					console.log("success");
					window.location.href="./verification.jsp";
					
				}
				console.log(response);
			}
		}
	}
	var object={};
	object.name=document.getElementById("name").value;
	object.date=formatDate(document.getElementById("date").value);
	object.phone=document.getElementById("phone").value;
	object.mail=document.getElementById("gmail").value;
	object.pan=document.getElementById("pan").value;
	object.marital=getMarriedValue();
	object.gender=getGenderValue();
	object.password=document.getElementById("userPass").value;
	object.ifsc=document.getElementById("ifsc").value
	object.bankAccountNumber=document.getElementById("accountNo").value;
	console.log(object.marital);
	console.log(object.gender);
	console.log(object.date);
	xhr.open("POST","./SignUp");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(object));
	
	
	
}
function formatDate (input) {
  var datePart = input.match(/\d+/g),
  year = datePart[0].substring(0), // get only two digits
  month = datePart[1], day = datePart[2];

  return day+'/'+month+'/'+year;
}

function getMarriedValue() {
            var ele = document.getElementsByName('marital');
              
            for(i = 0; i < ele.length; i++) {
                if(ele[i].checked){
					return ele[i].value;
				}
            }
 }
function getGenderValue() {
            var ele = document.getElementsByName('gender');
              
            for(i = 0; i < ele.length; i++) {
                if(ele[i].checked){
					return ele[i].value;
				}
            }
 }
function listCookies() {
    var theCookies = document.cookie.split(';');
    var aString = '';
    for (var i = 1 ; i <= theCookies.length; i++) {
        aString += i + ' ' + theCookies[i-1] + "\n";
    }
    return aString;
}


function checkYear(input){
	 var datePart = input.match(/\d+/g),
  year = datePart[0].substring(0)
  return ""+year;
}
function validation(){
	var count=0;
	if(document.getElementById("name").value==""){
		alert("invalid input");
	}
	else{
		count++;
	}
	if( (new Date().getFullYear())-checkYear(document.getElementById("date").value)>=18){
		count++;
	}
	else{
		alert("you can't open demataccount because you age is less than 18");
	}
	if(document.getElementById("phone").value.length==10){
		count++;
	}
	else{
		alert("given phone number is invalid")
	}
	if(document.getElementById("gmail").value.toLowerCase().includes("@gmail.com")&&document.getElementById("gmail").value!="@gmail.com"){
		count++;
	}
	else{
		alert("invalid gmail address")
	}if(document.getElementById("pan").value.length==10){
		count++;
	}
	else{
		alert("invalid pan number");
	}
	if(document.getElementById("userPass").value.length>=8&&document.getElementById("userPass").value.length<=16){
		count++;
	}
	else{
		alert("8-16 characters use");
	}
	if(document.getElementById("ifsc").value.length==11){
		count++;
	}
	else{
		alert("invalid ifsc code")
	}
	
	if(document.getElementById("accountNo").value.length>=10){
		count++
	}else{
		alert("invalid account number");
	}
	if(count==8){
		signUp()
	}
}
function logout(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4){
			if(this.status==200){
				var response=this.responseText;
				//console.log(response);
				if(response.includes("success")){
					window.location.href="login.html";
				}
				//console.log(response);
			}
		}
	}
	
	xhr.open("POST","./logout");
	//xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//xhr.setRequestHeader("Content-Type","application/json");
	//
	xhr.send("");
}
function numberCheck(element){
	if(element.value>=7000000000&&element.value<=9999999999){
		
	}
	else{
		element.value="";
	}
}

 