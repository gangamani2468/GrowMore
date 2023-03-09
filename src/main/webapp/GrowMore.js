/**
 * 
 */
dashboard();
  dashboardData()
   
  notify2()
    function dashboard(){
		 document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="none";
      document.getElementById("dashboard").style.display="block";
      document.getElementById("holdings").style.display="none";
      document.getElementById("funds").style.display="none";
      document.getElementById("chartBox").style.display="none";
      document.getElementById("profile").style.display="none";
      
    }
    function holdingsDis(){
      document.getElementById("dashboard").style.display="none";
      document.getElementById("holdings").style.display="block";
      document.getElementById("funds").style.display="none";
      document.getElementById("chartBox").style.display="none";
      document.getElementById("profile").style.display="none";     
     }
    function funds(){
      document.getElementById("dashboard").style.display="none";
      document.getElementById("holdings").style.display="none";
      document.getElementById("funds").style.display="block";
      document.getElementById("chartBox").style.display="none";
      document.getElementById("profile").style.display="none";      
    }
    function profile(){
      document.getElementById("dashboard").style.display="none";
      document.getElementById("holdings").style.display="none";
      document.getElementById("funds").style.display="none";
      document.getElementById("chartBox").style.display="none";
      document.getElementById("profile").style.display="block";      
    }
    function notify(){
      
      document.getElementById('notify').setAttribute("onclick","notify2()");
      var box=document.getElementById("notifyBox");
       var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		 
		  if(this.readyState==4){

			  if(this.status==200){
				var res1=this.responseText;
				
				  if(res1.includes("div")){
					box.innerHTML=res1;
					document.getElementById("notifyBox").style.display="flex";
					document.getElementById("dot").style.display="none";
					document.getElementById("dot").setAttribute("id","notShow");
					
				  }
				  else{
					  document.getElementById("notifyBox").style.display="flex";
					  alert("request failure");
				  }
				  console.log("flex")
				  
				
			  }
		  }
	  }
	  xhr.open("POST","./notificationServlet");
	  
	  xhr.send("");
		 
    }
    function notify2(){
      document.getElementById('notifyBox').style.display="none";
      document.getElementById('notify').setAttribute("onclick","notify()");
    }
    var log;
  var chartProperties;
  var domElement;
  var chart;
  
  function nifty() {
  log=console.log;
  chartProperties={
   width:500,
   height:280,
  layout:{background: { type: 'solid', color: 'aliceblue' }}
  }
  domElement=document.getElementById("chart1");
  domElement.innerHTML="";
  chart=LightweightCharts.createChart(domElement,chartProperties);
  const lineSeries=chart.addLineSeries();
  var xhr=new XMLHttpRequest();
  xhr.onreadystatechange=function(){
  console.log(this.readyState);
  if(this.readyState==4){
    if(this.status==200){
      var response=this.responseText;
            
      var res=JSON.parse(response);
             
              
      if(response.includes("candles")){
        
        const cdata=res.candles.map(d=>{
          
          return {time:d[0],value:parseFloat(d[1])}
        })
        lineSeries.setData(cdata);
      }
      
    }
  }
  }
  xhr.open("POST","./niftyServlet");
  xhr.setRequestHeader("Content-Type","application/json");
  //xhr.setRequestHeader("Content-Type","application/json");
  // console.log("second");
  xhr.send();
  }
  var ChartProperties1;
  var docElement;
  var Chart1;

  //setInterval(nifty, 15000);
  //setInterval(priceData,3500);
  priceData();
  nifty();
  var res;
	  /////////use full below
	 function priceData(){
		 
		
		  
		  var xhr=new XMLHttpRequest();
		  xhr.onreadystatechange=function(){
		  console.log(this.readyState);
		  if(this.readyState==4){
		    if(this.status==200){
		      var response=this.responseText;
		              console.log(response);
		      var res=JSON.parse(response);
		             
		      
		      if(response.includes("ltp")){
				  notification()
		        console.log(res)
		   		 

		        document.getElementById("adaniPower").style.color=res.adaniPower.bullOrBear;
		        document.getElementById("adaniGreen").style.color=res.adaniGreen.bullOrBear;
		        document.getElementById("itc").style.color=res.itc.bullOrBear;
		        document.getElementById("kalyan").style.color=res.kalyan.bullOrBear;
		        document.getElementById("manappuram").style.color=res.manappuram.bullOrBear;
		        document.getElementById("natco").style.color=res.natco.bullOrBear;
		        document.getElementById("tataPower").style.color=res.tataPower.bullOrBear;
		        document.getElementById("tataMotors").style.color=res.tataMotors.bullOrBear;
			     
		        document.getElementById("adaniPowerPriceData").innerHTML="<div> ("+res.adaniPower.changeAmount+")</div><div>("+res.adaniPower.changePercent+"%)</div><div>"+res.adaniPower.arrow+"</div><div>"+res.adaniPower.ltp+"</div>"
		         //document.getElementById("price").innerText="₹"+res.adaniPower.ltp;	  
		        document.getElementById("adaniGreenPriceData").innerHTML="<div> ("+res.adaniGreen.changeAmount+")</div><div>("+res.adaniGreen.changePercent+"%)</div><div>"+res.adaniGreen.arrow+"</div><div>"+res.adaniGreen.ltp+"</div>"
		        document.getElementById("itcPriceData").innerHTML="<div> ("+res.itc.changeAmount+")</div><div>("+res.itc.changePercent+"%)</div><div>"+res.itc.arrow+"</div><div>"+res.itc.ltp+"</div>"
		        document.getElementById("kalyanPriceData").innerHTML="<div> ("+res.kalyan.changeAmount+")</div><div>("+res.kalyan.changePercent+"%)</div><div>"+res.kalyan.arrow+"</div><div>"+res.kalyan.ltp+"</div>"
		        document.getElementById("manappuramPriceData").innerHTML="<div> ("+res.manappuram.changeAmount+")</div><div>("+res.manappuram.changePercent+"%)</div><div>"+res.manappuram.arrow+"</div><div>"+res.manappuram.ltp+"</div>"
		        document.getElementById("natcoPriceData").innerHTML="<div> ("+res.natco.changeAmount+")</div><div>("+res.natco.changePercent+"%)</div><div>"+res.natco.arrow+"</div><div>"+res.natco.ltp+"</div>"
		        document.getElementById("tataPowerPriceData").innerHTML="<div> ("+res.tataPower.changeAmount+")</div><div>("+res.tataPower.changePercent+"%)</div><div>"+res.tataPower.arrow+"</div><div>"+res.tataPower.ltp+"</div>"
		        document.getElementById("tataMotorsPriceData").innerHTML="<div> ("+res.tataMotors.changeAmount+")</div><div>("+res.tataMotors.changePercent+"%)</div><div>"+res.tataMotors.arrow+"</div><div>"+res.tataMotors.ltp+"</div>"
		      }
		      
		    }
		  }
		  }
		  xhr.open("POST","./stocksPriceData");
		  xhr.setRequestHeader("Content-Type","application/json");
		  //xhr.setRequestHeader("Content-Type","application/json");
		  // console.log("second");
		  xhr.send();
		  
		  }
 
 function companyAbout(divElement){
 var company=divElement.getAttribute("id");
 document.getElementById("chartBox").style.display="block";
 
 document.getElementById("dashboard").style.display="none";
 document.getElementById("holdings").style.display="none";
 document.getElementById("funds").style.display="none";
 document.getElementById("profile").style.display="none"; 
 var xhr=new XMLHttpRequest();
 xhr.onreadystatechange=function(){
   console.log(this.readyState);
   if(this.readyState==4){
     if(this.status==200){
       var response=this.responseText;
       console.log(response);
            res=JSON.parse(response);
       if(response.includes("header")){
               
        
     	var image=(company=="adaniPower")?"adaniPower.png":(company=="adaniGreen")?"adaniGreen.png":(company=="itc")?"itcImg.png":(company=="kalyan")?"kalyan.png":(company=="manappuram")?"manappuram.png":(company=="natco")?"Natco.png":(company=="tataPower")?"tataPower.png":(company=="tataMotors")?"tataMotors.png":"";
    	
         document.getElementById("companyImg").src=image;
     		dataAdd();
     
       }
       //console.log(response);
     }
   }
 }
 var object={};
 object.path=company;
console.log(object.path);
 xhr.open("POST","./companyAbout");
 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
 //xhr.setRequestHeader("Content-Type","application/json");

 xhr.send("path="+object.path);
 }
 function stockCandleChart(divId){
	 var company=divId.getAttribute("id");
	 document.getElementById("companyName").setAttribute("data-mani",company);
	 stockCandle();
	 holdings();
 }
 function lineChart(){
	 var company=document.getElementById("companyName").getAttribute("data-mani");
	 element=document.getElementById("determineChart");
	 element.setAttribute("src","candlestick.png");
	 element.setAttribute("onclick","stockCandle()");
	 chartProperties={
			   width:700,
			   height:380,
			    layout:{background: { type: 'solid', color: 'aliceblue' }}
			  }
			  domElement=document.getElementById("candleChart");
			  domElement.innerHTML="";
			  chart=LightweightCharts.createChart(domElement,chartProperties);
			  const lineSeries=chart.addLineSeries();
			  var xhr=new XMLHttpRequest();
			  xhr.onreadystatechange=function(){
			  console.log(this.readyState);
			  if(this.readyState==4){
			    if(this.status==200){
			      var response=this.responseText;
			            
			      var res=JSON.parse(response);
			             
			              
			      if(response.includes("candles")){
			        
			        const cdata=res.candles.map(d=>{
			          
			          return {time:d[0],value:parseFloat(d[1])}
			        })
			        lineSeries.setData(cdata);
			      }
			      
			    }
			  }
			  }
			  var Obj={};
			  Obj.stock=company;
			  console.log(company);
			  xhr.open("POST","./lineChart");
			  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			  //xhr.setRequestHeader("Content-Type","application/json");
			  // console.log("second");
			  xhr.send("stock="+Obj.stock);
		
 }
 function stockCandle(){
	    document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="none";
	 var company=document.getElementById("companyName").getAttribute("data-mani");
	 element=document.getElementById("determineChart");
	 element.setAttribute("src","lineChart.png");
	 element.setAttribute("onclick","lineChart()");
	 

	  ChartProperties1={
	   width:700,
	   height:380,
	    layout:{background: { type: 'solid', color: 'aliceblue' }}
	  }
	  docElement=document.getElementById("candleChart");
	  docElement.innerHTML="";
	
	  Chart1=LightweightCharts.createChart(docElement,ChartProperties1);
	  const lineSeries=Chart1.addCandlestickSeries();
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
	  console.log(this.readyState);
	  if(this.readyState==4){
	    if(this.status==200){
	      var response=this.responseText;
	              console.log(response)
	      var res=JSON.parse(response);
	             
	      
	      if(response.includes("candles")){

	        console.log(res)
	  
	     
	        const cdata=res.candles.map(d=>{
	          
	        
	       
	          
	  return {time:d[0],open:parseFloat(d[1]),high:parseFloat(d[2]),low:parseFloat(d[3]),close:parseFloat(d[4])}
	        })
	        lineSeries.setData(cdata);
	  
	        
	      }
	      
	    }
	  }
	  }
	  var request={};
	  request.name=company;
	  console.log(request.name);
	  xhr.open("POST","./stockCandle");
	  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  //xhr.setRequestHeader("Content-Type","application/json");
	  // console.log("second");
	  xhr.send("name="+request.name);
 }
 
 
 
 
 
 
  function dataAdd(){
	  console.log("ll")
      document.getElementById("companyName").innerText=res.header.displayName;

       // document.getElementById("first").innerHTML=fundamentals[0].shortName+":"+fundamentals[0].value.replace("?","");
        document.getElementById("first").innerHTML="<div>"+res.fundamentals[0].shortName+" : "+res.fundamentals[0].value.replace("?","")+"</div>"+"<div>"+res.fundamentals[2].shortName+" : "+res.fundamentals[2].value+"</div>"+"<div>"+res.fundamentals[4].shortName+" : "+res.fundamentals[4].value+"</div>"+"<div>"+res.fundamentals[6].shortName+" : "+res.fundamentals[6].value+"</div>"+"<div>"+res.fundamentals[8].shortName+" : "+res.fundamentals[8].value+"</div>";
        
        document.getElementById("second").innerHTML="<div>"+res.fundamentals[1].shortName+" : "+res.fundamentals[1].value+"</div>"+"<div>"+res.fundamentals[3].shortName+" : "+res.fundamentals[3].value+"</div>"+"<div>"+res.fundamentals[5].shortName+" : "+res.fundamentals[5].value+"</div>"+"<div>"+res.fundamentals[7].shortName+" : "+res.fundamentals[7].value+"</div>"+"<div>"+res.fundamentals[9].shortName+" : "+res.fundamentals[9].value+"</div>";
  
        document.getElementById("revenue").innerHTML=res.financialStatement[0].title;
        
        document.getElementById("quarterlyFirst").innerHTML="<div> Dec '21 : ₹"+res.financialStatement[0].quarterly["Dec '21"]+"Cr </div>"+"<div> Mar '22 : ₹"+res.financialStatement[0].quarterly["Mar '22"]+"Cr </div>"+"<div> Jun '22 : ₹"+res.financialStatement[0].quarterly["Jun '22"]+"Cr </div>"+"<div> Sep '22 : ₹"+res.financialStatement[0].quarterly["Sep '22"]+"Cr </div>"+"<div> Dec '22 : ₹"+res.financialStatement[0].quarterly["Dec '22"]+"Cr </div>";
        
        document.getElementById("yearSecond").innerHTML="<div> 2018 : ₹"+res.financialStatement[0].yearly[2018]+"Cr </div>"+"<div> 2019 : ₹"+res.financialStatement[0].yearly[2019]+"Cr</div>"+"<div> 2020 : ₹"+res.financialStatement[0].yearly[2020]+"Cr </div>"+"<div> 2021 : ₹"+res.financialStatement[0].yearly[2021]+"Cr </div>"+"<div> 2022 : ₹"+res.financialStatement[0].yearly[2022]+"Cr </div>"
    
        
        document.getElementById("profitState").innerHTML=res.financialStatement[1].title;
        document.getElementById("profitQuarterlyFirst").innerHTML="<div> Dec '21 : ₹"+res.financialStatement[1].quarterly["Dec '21"]+"Cr </div>"+"<div> Mar '22 : ₹"+res.financialStatement[1].quarterly["Mar '22"]+"Cr </div>"+"<div> Jun '22 : ₹"+res.financialStatement[1].quarterly["Jun '22"]+"Cr </div>"+"<div> Sep '22 : ₹"+res.financialStatement[1].quarterly["Sep '22"]+"Cr </div>"+"<div> Dec '22 : ₹"+res.financialStatement[1].quarterly["Dec '22"]+"Cr </div>";
        
        document.getElementById("profitYearSecond").innerHTML="<div> 2018 : ₹"+res.financialStatement[1].yearly[2018]+"Cr </div>"+"<div> 2019 : ₹"+res.financialStatement[1].yearly[2019]+"Cr</div>"+"<div> 2020 : ₹"+res.financialStatement[1].yearly[2020]+"Cr </div>"+"<div> 2021 : ₹"+res.financialStatement[1].yearly[2021]+"Cr </div>"+"<div> 2022 : ₹"+res.financialStatement[1].yearly[2022]+"Cr </div>";
        document.getElementById("netWorthState").innerHTML=res.financialStatement[2].title;
  
        document.getElementById("netWorthQuarterlyFirst").innerHTML="<div> Mar '22 : ₹"+res.financialStatement[2].quarterly["Mar '22"]+"Cr </div>"+"<div> Sep '22 : ₹"+res.financialStatement[2].quarterly["Sep '22"]+"Cr </div>";
       
        document.getElementById("netWorthYearSecond").innerHTML="<div> 2018 : ₹"+res.financialStatement[2].yearly[2018]+"Cr </div>"+"<div> 2019 : ₹"+res.financialStatement[2].yearly[2019]+"Cr</div>"+"<div> 2020 : ₹"+res.financialStatement[2].yearly[2020]+"Cr </div>"+"<div> 2021 : ₹"+res.financialStatement[2].yearly[2021]+"Cr </div>"+"<div> 2022 : ₹"+res.financialStatement[2].yearly[2022]+"Cr </div>";
        document.getElementById("aboutCompany1").innerHTML="<div>Parent organisation : "+res.details.parentCompany+"</div>"+"<div>MD/CEO : "+res.details.managingDirector+"</div>"+"<div>Founded Year : "+res.details.foundedYear+"</div>"+"<div>NSE symbol : "+res.details.fullName+"</div>";
        document.getElementById("shareholdingPattern").innerHTML="<div>Foreign Institutions : "+res.shareHoldingPattern["Dec '22"].foreignInstitutions.percent+"%</div>"+"<div>Mutual Funds : "+res.shareHoldingPattern["Dec '22"].mutualFunds.percent+"%</div>"+"<div> Other DomesticInstitutions: "+(res.shareHoldingPattern["Dec '22"].otherDomesticInstitutions.insurance.percent+res.shareHoldingPattern["Dec '22"].otherDomesticInstitutions.otherFirms.percent)+"%</div>"+"<div>Promoters : "+(res.shareHoldingPattern["Dec '22"].promoters.individual.percent+res.shareHoldingPattern["Dec '22"].promoters.government.percent+res.shareHoldingPattern["Dec '22"].promoters.corporation.percent)+"%</div>"+"<div>Retail :"+res.shareHoldingPattern["Dec '22"].retailAndOthers.percent+"%</div>";
        document.getElementById("expertsRating").innerHTML="";
        document.getElementById("expertsRating").innerHTML="<div> Buy :"+res.expertRating.buyPercent+"%</div><div> Hold :"+res.expertRating.holdPercent+"%</div><div> Sell :"+res.expertRating.sellPercent+"%</div>";
  }
  function dashboardData(){
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  console.log(this.readyState);
		  if(this.readyState==4){
			 
			  if(this.status==200){
				  
				  var response=JSON.parse(this.responseText);
				  if(response.StatusCode==500){
					  window.location.href="login.html";
				  }
				  else{
					  console.log(response);
						document.getElementById("userName").innerText=response.demat.name
				  }
				  if(response.holdArr.length>0){
					    pieChart(response);
				  }
				  userDataAddDashboard(response);
				
			  }
		  }
	  }
	  xhr.open("POST","./userDataServlet");
	//  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("");
  }
 
  function buyPage(){
    document.getElementById("buyPage").style.display="block";
    document.getElementById("sellPage").style.display="none";
    document.getElementById("nameOfShare").innerHTML=document.getElementById("companyName").getAttribute("data-mani");
    document.getElementById("priceOfShare").innerHTML="₹"+document.getElementById("price").getAttribute("data-price");
    document.getElementById("buyBut").setAttribute("onclick","close1()"); 
    
  }
  function sellPage(){
	    document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="block";
	    document.getElementById("nameOfShare1").innerHTML=document.getElementById("companyName").getAttribute("data-mani");
	    document.getElementById("priceOfShare1").innerHTML="₹"+document.getElementById("price").getAttribute("data-price");
	    document.getElementById("sellBut").setAttribute("onclick","close1()"); 
	    
	  }
function close1(){
	
	  document.getElementById("buyPage").style.display="none";
	  document.getElementById("sellPage").style.display="none";
	  document.getElementById("sellBut").setAttribute("onclick","sellPage()"); 
	  document.getElementById("buyBut").setAttribute("onclick","buyPage()"); 
}
 function buyRequest(){
	 var sharePrice=document.getElementById("price").getAttribute("data-price");
	 var company=document.getElementById("companyName").getAttribute("data-mani");
	 var qty=document.getElementById("quantityOfShare").value;
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){

		  if(this.readyState==4){
			  
			  if(this.status==200){
				  var response=this.responseText;
				  console.log(response)
				 	 document.getElementById("buyPage").style.display="none";
				  if(response.includes("success")){
						alert("ordered successful!!");
				  }
				  else{
					  alert("you not have enough money!!")
				  }
				  
				  
			  }
		  }
	  }
	  var buyObj={}
	  buyObj.sharePrice=sharePrice;
	  buyObj.company=company;
	  buyObj.qty=qty;
	  
	  
	  
	  xhr.open("POST","./buyServlet");
	  xhr.setRequestHeader("Content-Type","application/json");
	  xhr.send(JSON.stringify(buyObj));
 }
 function sellRequest(){
	 var sharePrice=document.getElementById("price").getAttribute("data-price");
	 var company=document.getElementById("companyName").getAttribute("data-mani");
	 var qty=document.getElementById("quantityOfShare1").value;
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){

		  if(this.readyState==4){
			  
			  if(this.status==200){
				  var response=this.responseText;
				  document.getElementById("sellPage").style.display="none";
				  console.log(response);
				  if(response.includes("success")){
						alert("ordered successful!!");
				  }
				  else{
					  alert("order failure")
				  }
				  
				  
			  }
		  }
	  }
	  var sellObj={}
	  sellObj.sharePrice=sharePrice;
	  sellObj.company=company;
	  sellObj.qty=qty;
	  
	  
	  
	  xhr.open("POST","./sellServlet");
	  xhr.setRequestHeader("Content-Type","application/json");
	  xhr.send(JSON.stringify(sellObj));
 }
 function holdings(){
	  document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="none";
  var company=document.getElementById("companyName").getAttribute("data-mani");
  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		 
		  if(this.readyState==4){

			  if(this.status==200){

				  var res1= this.responseText;
				  var response=JSON.parse(res1);
				  if(res1.includes("hold")){
					  document.getElementById("userHoldings").innerHTML="";
					  document.getElementById("userHoldings").innerHTML="<div> Avg Price :"+response.holds.shareAvg+"</div>"+"<div> Qty :"+response.holds.quantity+"</div><div> Invested Amount :"+response.holds.InvestedAmount+"</div><div style=\"color:"+response.color+"\"> P&L :"+response.holds.profitAndLoss+"</div>"
				  }
				  document.getElementById("price").innerHTML="₹"+response.ltp;
				  document.getElementById("price").setAttribute("data-price",response.ltp)
				 
			  }
		  }
	  }
	  
	  
	  console.log(company);
	  xhr.open("POST","./ifHoldServlet");
	  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("company="+company);
 }
 function userDataAddDashboard(response){
	 document.getElementById("name").innerHTML="Hi, "+response.demat.name;
	 document.getElementById("countHoldings").innerHTML="Holdings("+response.holdArr.length+")";
	 document.getElementById("amount").innerHTML=response.demat.balance;
	 document.getElementById("profit").innerHTML=response.profitAndLoss;
	 document.getElementById("profit").style.color=response.color;
	 document.getElementById("profitPercent1").innerHTML=response.profitPercent+"%";
	 document.getElementById("profitPercent1").style.color=response.color;
	 var curr=(response.currentAmount)/1000;
	 var invest=response.invested/1000;
	 document.getElementById("currentAmount").innerHTML=curr.toFixed(2)+"k";
	 document.getElementById("invested").innerHTML=invest.toFixed(2)+"k";
	 
 }
 function holdingsData(){
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  console.log(this.readyState);
		  if(this.readyState==4){
			 
			  if(this.status==200){
				  
				  var response=JSON.parse(this.responseText);
				  if(response.StatusCode==500){
					  window.location.href="login.html";
				  }
				  else{
					  console.log(response);
						document.getElementById("userName").innerText=response.demat.name
				  }
				  userDataAddHolding(response);
				  holdScale(response);
			  }
		  }
	  }
	  xhr.open("POST","./userDataServlet");
	//  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("");
  }
 function userDataAddHolding(response){
	 document.getElementById("countHoldings3").innerHTML="Holdings("+response.holdArr.length+")";
	 document.getElementById("holdingsData").innerHTML="";
	 document.getElementById("holdTotalInvest").innerHTML="<div>Total Investment</div><div> ₹"+response.invested+"</div>";
 	 document.getElementById("holdCurrentAmount").innerHTML="<div>Current Amount</div><div> ₹"+response.currentAmount+"</div>";
     document.getElementById("holdTotalP&L").innerHTML="<div>Total P&L</div><div style=\"color:"+response.color+";\"> ₹"+response.profitAndLoss+"</div>";
	 for(var i=0;i<response.holdArr.length;i++){
		 document.getElementById("holdingsData").innerHTML+="<div class=\"headings\" ><h5>"+response.holdArr[i].shareName+"</h5><h5>"+response.holdArr[i].quantity+"</h5><h5>"+response.holdArr[i].shareAvg+"</h5><h5>"+response.holdArr[i].ltp+"</h5><h5>"+response.holdArr[i].currentAmount+"</h5><h5 style=\"color:"+response.holdArr[i].color+"\">"+response.holdArr[i].profitAndLoss+"</h5>"
	 }
	 
		 
 }
 function profileData(){
	  document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="none";
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  console.log(this.readyState);
		  if(this.readyState==4){
			 
			  if(this.status==200){
				  
				  var response=JSON.parse(this.responseText);
				  if(response.StatusCode==500){
					  window.location.href="login.html";
				  }
				  else{
					  console.log(response);
						document.getElementById("userName").innerText=response.demat.name
				  }
				  userDataAddProfile(response);
			  }
		  }
	  }
	  xhr.open("POST","./userDataServlet");
	//  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("");
 }
 function userDataAddProfile(response){
	 console.log("profile")
	 document.getElementById("name1").innerHTML="<div>Name</div><div>"+response.demat.name+"</div";
	 document.getElementById("email").innerHTML="<div>Email</div><div>"+response.demat.mail+"</div>";
	 document.getElementById("pan").innerHTML="<div>Pan Number</div><div>"+response.demat.pan+"</div>";
	 document.getElementById("phone").innerHTML="<div>Phone Number</div><div>"+response.demat.phone+"</div>";
	 document.getElementById("demat").innerHTML="<div>Demat Account Number</div><div>"+response.demat.dematNumber+"</div>";
	 document.getElementById("segments").innerHTML="<div>Segments</div><div>"+"<a target=\"_blank\"href=\"https://www.nseindia.com/\">NSE</a></div>";
	 document.getElementById("bankName").innerHTML="<div>Bank Name</div><div>"+response.bankArr[0].bankName+"</div>"
	  document.getElementById("bankBranch").innerHTML="<div>Bank Branch</div><div>"+response.bankArr[0].bankBranch+"</div>";
	  document.getElementById("bankIfsc").innerHTML="<div>IFSC Code</div><div>"+response.bankArr[0].ifscCode+"</div>";
	   document.getElementById("bankAccountNumber").innerHTML="<div>Account Number</div><div>"+response.bankArr[0].bankAccount+"</div>";

	
 }
 function showBank(){
	 document.getElementById("bankDisplay").style.display="block";
	  document.getElementById("bank1").setAttribute("onclick","hideBank()");
	   document.getElementById("bank1").value="hide";
 }
 function hideBank(){
	  document.getElementById("bankDisplay").style.display="none";
	  document.getElementById("bank1").setAttribute("onclick","showBank()");
	   document.getElementById("bank1").value="show";
 }
 function transaction(id){
	 var amount=document.getElementById("howMuch").value;
	 if(amount==""){
		 alert("invalid input");
	 }else{
		  var eleId=id.getAttribute("data-name");
	 
	 console.log(eleId);
	 var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		 
		  if(this.readyState==4){

			  if(this.status==200){

				  var res1= this.responseText;
				  var response=JSON.parse(res1);
				  if(res1.includes("success")){
					alert("request successfull!")
				  }
				  else{
					  alert("request failure");
				  }
				
			  }
		  }
	  }
	  xhr.open("POST","./transactionServlet");
	  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("action="+eleId+"&amount="+amount);
		 
	 }
	 
	
 }
 function funds1(){
	  document.getElementById("buyPage").style.display="none";
	    document.getElementById("sellPage").style.display="none";
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  console.log(this.readyState);
		  if(this.readyState==4){
			 
			  if(this.status==200){
				  
				  var response=JSON.parse(this.responseText);
				  if(response.StatusCode==500){
					  window.location.href="login.html";
				  }
				  else{
					  console.log(response);
					  console.log("hii")
						document.getElementById("userName").innerText=response.demat.name;
						document.getElementById("availableCash").innerText="₹"+response.demat.balance;
				  }
				  
			  }
		  }
	  }
	  xhr.open("POST","./userDataServlet");
	//  xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xhr.send("");
 }
function pieChart(response){
	 var list=[];
	 for (var i=0;i<response.holdArr.length;i++){
		 var r=Math.random()*255;
	 	 var g=Math.random()*255;
	 	 var b=Math.random()*255;
	 	 list.push("rgb("+r+","+g+","+b+")");
	 }
	 var shareName=[];
	 for(var i=0;i<response.holdArr.length;i++){
		 shareName.push(""+response.holdArr[i].shareName);
	 }
	 var amount=[];
	  for(var i=0;i<response.holdArr.length;i++){
		 amount.push(""+response.holdArr[i].currentAmount);
	 }
	 new Chart("pieChart", {
 type: "pie",
 data: {
   labels: shareName,
   datasets: [{
     backgroundColor: list,
     data: amount
   }]
 },
 options: {
   title: {
     display: true,
     text: "My Holdings"
   }
 }
});
	 	 
}
function sort(element){
	var doc=element.getAttribute("id");
	var parent=element.parentNode;
	 var id;
	 var arrow;
	 if(doc.includes("Desc")){
		 id=doc.replaceAll("Desc","");
				 arrow="<i id=\""+id+"\" onclick=\"sort(this)\" class=\"fa-sharp fa-solid fa-angle-up\"></i>";
	 }else{
		 id=doc+"Desc";
		  arrow="<i id=\""+id+"\" onclick=\"sort(this)\" class=\"fa-solid fa-angle-down\"></i>";
	 }
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		 
		  if(this.readyState==4){

			  if(this.status==200){
				var res1=this.responseText;
				var response=JSON.parse(res1)
				 parent.innerHTML=arrow;
				if(response!=""){
					 document.getElementById("holdingsData").innerHTML="";
					 for(var i=0;i<response.holdArr.length;i++){
						 document.getElementById("holdingsData").innerHTML+="<div class=\"headings\" ><h5>"+response.holdArr[i].shareName+"</h5><h5>"+response.holdArr[i].quantity+"</h5><h5>"+response.holdArr[i].shareAvg+"</h5><h5>"+response.holdArr[i].ltp+"</h5><h5>"+response.holdArr[i].currentAmount+"</h5><h5 style=\"color:"+response.holdArr[i].color+"\">"+response.holdArr[i].profitAndLoss+"</h5>"
	 					}
				}
				 
				
			  }
		  }
	  }
	  var responseObj={};
	  responseObj.action=doc;
	  xhr.open("POST","./sortServlet");
	   xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	  xhr.send("action="+responseObj.action);
	 
}
function holdScale(response){
	var doc=document.getElementById("scaleHold");
	doc.innerHTML="";
	var onePercent=response.currentAmount/100;
	var percent;
	for (var i=0;i<response.holdArr.length;i++){
		 var r=Math.random()*255;
	 	 var g=Math.random()*255;
	 	 var b=Math.random()*255;
	 	 
	 	 percent=response.holdArr[i].currentAmount/onePercent;
	 	 doc.innerHTML+="<div title=\""+response.holdArr[i].shareName+" ₹"+response.holdArr[i].currentAmount+"\" style=\"height:100%;width:"+percent+"%;background:"+"rgb("+r+","+g+","+b+")"+"\">"
	 }

}
 
 function notification(){
	 var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		 
		  if(this.readyState==4){

			  if(this.status==200){
				var res1=this.responseText;
				
				  if(res1==""){
					document.getElementById("dot").style.display="none"
				  }
				  else{
					  document.getElementById("dot").style.display="block"
				  }
				 
				
			  }
		  }
	  }
	  xhr.open("POST","./notificationServlet");
	  xhr.send("");
 }
 
 