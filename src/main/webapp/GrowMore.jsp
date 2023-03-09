<%@page import="org.apache.tomcat.util.http.ServerCookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.util.logging.Logger" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="growmore.css">
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<script src="script.js"></script>
 <script src="https://kit.fontawesome.com/e9663b22be.js" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/lightweight-charts@4.0.0/dist/lightweight-charts.standalone.production.js"></script>
    <%
   
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    
    if(session.getAttribute("SESSION-ID")==null && session.getAttribute("phone")==null){
    	
     	response.sendRedirect("login.html");
     }
   
    
    %>
    
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      
      <div id="header">
      <img src="logo.png"/>
      <div id="topNavDiv">
        <h3  class="header" onclick="dashboard(),dashboardData()">Dashboard</h3>
        <h3 class="header" onclick="holdingsDis(),holdingsData()">Holdings</h3>
        <h3 class="header" onclick="funds(),funds1()">Funds</h3>
        <!-- <h3>Profile</h3> -->
        <div class="header" style="position:relative" id="notify" onclick="notify()">
          <div id="dot" style="background:red;
          height:10px;
          width:10px;
          position:absolute;
          top: 1px;
          left: 15px;
          border-radius: 6px;display:none;"></div>
          
           <i class="fa-solid fa-bell"></i>
        </div>
        <div class="header" id="profileDiv" onclick="profile(),profileData()">
          <div></div>
          <i class="fa-solid fa-user"></i>
          <div class="header" style="font-size: 2vh;line-height: 0%;">
            <p id="userName" ></p>
          </div>
          
        </div>
        <h2 class="header" onclick="logout()">Logout</h2>
      </div>
    </div>
    <div id='stock'></div>
  
  
    <!-- <section id="about">
      <h2>About Us</h2>
      <p>Learn about our company and our mission to provide top-quality brokerage services.</p>
    </section>
    <section id="services">
      <h2>Our Services</h2>
      <p>Discover the wide range of services we offer, including stock trading and investment advice.</p>
    </section>
    <section id="contact">
      <h2>Contact Us</h2>
      <p>Get in touch with us to learn more about our services or to ask any questions you may have.</p>
    </section> -->
  <!-- TradingView Widget BEGIN -->
  <div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>
  <div class="tradingview-widget-copyright"> 
  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-ticker-tape.js" async>
  {
  "symbols": [
  {
    "proName": "FOREXCOM:SPXUSD",
    "title": "S&P 500"
  },
  {
    "proName": "FOREXCOM:NSXUSD",
    "title": "US 100"
  },
  {
    "description": "DOLLAR",
    "proName": "FX_IDC:USDINR"
  },
  {
    "description": "BSE",
    "proName": "BSE:SENSEX"
  }
  ],
  "showSymbolLogo": true,
  "colorTheme": "light",
  "isTransparent": false,
  "displayMode": "adaptive",
  "locale": "in"
  }
  </script>
  </div></div>
  <!-- TradingView Widget END -->
  <div id='container'>
  <div id="space"></div>
  <div id='watchList' style="border-bottom: 1px solid gray;
  ">
  <h2 class="watchList1" style="display:flex;justify-content:center;">Watchlist</h2>
  <div class="watchList1" id="adaniPower" onclick="companyAbout(this);stockCandleChart(this)"><span>ADANI POWER</span><span id="adaniPowerPriceData" style="
   display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;"></span></div>
  <div class="watchList1" id="adaniGreen" onclick="companyAbout(this);stockCandleChart(this)" ><span>ADANI GREEN ENERGY</span><span id="adaniGreenPriceData" style="
    display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" id="itc" onclick="companyAbout(this);stockCandleChart(this)"><span>ITC</span><span id="itcPriceData" style="
   display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" onclick="companyAbout(this);stockCandleChart(this)" id="kalyan"><span>KALYANKJIL</span><span id="kalyanPriceData" style="
    display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" onclick="companyAbout(this);stockCandleChart(this)" id="manappuram"><span>MANAPPURAM</span><span id="manappuramPriceData" style="
    display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" onclick="companyAbout(this);stockCandleChart(this)" id="natco"><span>NATCO PHARMA</span><span id="natcoPriceData" style="
   display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" onclick="companyAbout(this);stockCandleChart(this)" id="tataPower"><span>TATA POWER</span><span id="tataPowerPriceData" style="
   display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  <div class="watchList1" onclick="companyAbout(this);stockCandleChart(this)" id="tataMotors"><span>TATA MOTORS</span><span id="tataMotorsPriceData" style="
    display: grid;
    grid-template-columns: repeat(4,1fr);
    width: 50%;
"></span></div>
  
  
  </div>
  <div id="dashboard" class="display">
  <h3 id="name" style="align-items:center;width:25%"></h3>
  <div id="equity">
    <br> 
    <span><i class="fa-solid fa-chart-pie-simple"></i><span style="font-size:30px;">Equity</span></span>
    
    <br>
    <span id="amount" style="font-size:22px;"></span>
    <h4>Available amount</h4>
  </div>
  <br>
  <br>
  
  
  <div id="holdingsPart">
  <div id="welcomeUser"></div>
    <div id="portfolio">
    <span><h2 id="countHoldings"><h2></span>
      <h3 id="profit"></h3>
      <span id="profitPercent1"></span>
      <h3 id="pandL">P&L</h3>
    </div>
    <div id="amountPart">
     
      <div style="
    color: gray;
    font-size: 0.8em;
"><span >Current value</span> <span id="currentAmount"></span></div>
     
      <div style="
    color: gray;
    font-size: 0.8em;
"><span>Investment</span> <span id="invested"></span></div>
    </div>
    <span></span>
  </div>
  <span><i style="font-size:35px;" class="fa-sharp fa-solid fa-arrow-trend-up"></i>
  
 <b> <span style="font-size:35px;">Market review</span></b></span>
  <h4>Nifty 50</h4>
  <div style="display:flex;
  justify-content:space-between;">
  <span id="chart1" style="margin-top: 40px;"></span>
  <canvas id="pieChart" style="width:100%;max-width:600px"></canvas>
  </div>
  </div>
  <div id="holdings" class="display">
  <h2 id="countHoldings3"><h2>
  <div class="headings">
    <h5><div>Stocks</div><div><i id="sortName" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
   <h5><div>Qty</div><div><i id="sortQty" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
  <h5><div>Avg.cost</div><div><i id="sortAvg" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
    <h5><div>Ltp</div><div><i id="sortLtp" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
    <h5><div>Curr.value</div><div><i id="sortCurrent" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
   <h5><div>P&L</div><div><i id="sortPAndL" onclick="sort(this)" class="fa-sharp fa-solid fa-angle-up"></i></div></h5>
  </div>
  <div id="holdingsData" style="overflow:scroll;height:274px;border-bottom:1px solid rgb(150,150,150);
  border-left:1px solid rgb(150,150,150);border-right:1px solid rgb(150,150,150) ">
    
  </div>
      <div id="holdingPortfolio" style="height:97px;display:flex;
      justify-content:space-between;" >
      <div id="holdTotalInvest" style="display:flex;flex-direction:column;"></div>
      <div id="holdCurrentAmount" style="display:flex;flex-direction:column;"></div>
      <div id="holdTotalP&L" style="display:flex;flex-direction:column;"></div>
      </div>
      <div id="scaleHold" style="width: 86%;display: flex;height:76px;"></div>
      
  
  
  
  </div>
  <div id="funds"  class="display">
  <h2 style="
    height: 80px;
    font-size: 51px;
">Equity</h2>
  
  <h3 id="usedmargin"></h3>
  <h3 id="availableCash" style="
    font-size: 40px;
"></h3>
  <br>
  <br>
  <br>
  <br>
 
   <div id="paymentPage" style="
    font-size: 40px;
">
  Enter a Amount:
  <br>
  <input id="howMuch" type="number" value="" placeholder="Enter a amount"></div>
  <br>
  <button data-name="addMoney" type="submit" onclick="transaction(this)" style="
	background-color: green;
  height: 81px;
  color: white;
  width: 234px;
  border-radius: 7px;
  margin-right:6vh;
" >Add funds</button>
  <button data-name="withdraw" type="submit" onclick="transaction(this)" style="
    background-color: deepskyblue;
    height: 81px;
    color: white;
    width: 234px;
    border-radius: 7px;
">Withdraw</button>
    <br><br>
  <br>
  <br>
  
 
  </div>
  <div id="profile" class="display">
  <h2>Profile</h2>
  <div>
    <div class="profileDetails" id="name1"></div>
    <div class="profileDetails" id="email"></div>
    <div class="profileDetails" id="pan"></div>
    <div class="profileDetails" id="phone"></div>
    <div class="profileDetails" id="demat"></div>
    <div class="profileDetails" id="segments"></div>
  </div>
  <div id="bankAccount" style="display:flex;
  justify-content:space-between;width:58%;">
  <h2>Bank account</h2>
  <input id="bank1" type="submit" onclick="showBank()" value="show"/>
  </div>
  <div id="bankDisplay">
  <div class="profileDetails" id="bankName"></div>
 <div class="profileDetails" id="bankBranch"></div>
 <div class="profileDetails" id="bankIfsc"></div>
 <div class="profileDetails" id="bankAccountNumber"></div>
 </div>
  </div>
  <div id="chartBox" class="display">
    <div style="height:10vh;width:35vh;display:flex;justify-content:space-evenly"><img id="companyImg" style="border-radius: 51px;
    height: 61%;"> 
    
    <div style="height:100%;width:59%;display:flex;flex-direction:column; font-size:25px">
    <div data-mani="" id="companyName">
    </div>
    <div id="price"></div>
    </div>
    <img id="determineChart" onclick="lineChart()" style="height:40%" src="">
    </div>
    <div style="
    height: 51%;
    width: 100%;
    display: flex;
    justify-content: space-around;
    border-bottom: 1px solid gray;
  ">
  <div id="candleChart"></div>
  <div id="buyAndSell" style="
  height: 100%;
  width: 32%;
  margin-left: -4vw;
  ">
  <div id="butsDiv">
    <div id="sellBut" class="buts" onclick="sellPage()">SELL</div>
    <div id="buyBut" class="buts" onclick="buyPage()">BUY</div>
  </div>
  <div id="userHoldings">
  
  </div>
  </div>
  </div>
  <div id="aboutcompany" style="overflow: scroll;height: 34vh;width: 114vh;display: flex;flex-direction: column;">
  <h2 style="font-size:35px;">Fundamentals</h2>
    <div id="fundamentals" style="height: 30vh;width:114vh;display: flex;justify-content: space-evenly;font-size:30px;    border-bottom: 1px solid gray;
    ;">
      <div id="first">
      </div>
      <div id="second">
      </div>
    </div>
    <h2 style="font-size:35px;"yearSecond>Financials</h2>
    <h4 id="revenue" style="font-size:33px"></h4>
      <div id="Quertely" style="height: 30vh;width:114vh;display: flex;justify-content: space-evenly;font-size:30px;
      ;">
      <div id="quarterlyFirst">
      </div>
      <div id="yearSecond">
      </div>
    </div>
    <h4 id="profitState" style="font-size:33px"></h4>
      <div id="profitQuertely" style="height: 30vh;width:114vh;display: flex;justify-content: space-evenly;font-size:30px;">
      <div id="profitQuarterlyFirst">
      </div>
      <div id="profitYearSecond">
      </div>
    </div>
     <h4 id="netWorthState" style="font-size:33px"></h4>
      <div id="netWorthQuertely" style="height: 30vh;width:114vh;display: flex;justify-content: space-evenly;font-size:30px;border-bottom: 1px solid gray;
      ;">
      <div id="netWorthQuarterlyFirst">
      
      </div>
      <div id="netWorthYearSecond">
      </div>
    </div>
    <div id="companyDetails" style="height: 30vh;width:114vh;display: flex;justify-content: space-evenly;font-size:30px;border-bottom: 1px solid gray;
    ;">
      <div><h2>About Company</h2>
      <div id="aboutCompany1" >
      
      </div>
      </div>
      <div>
        <h2>Shareholding Pattern</h2>
      <div id="shareholdingPattern">
      </div>
      </div>
    </div>
    <h2>Experts Rating</h2>
    <div id="expertsRating" style="height: 30vh;width:26vh;display: flex;text-align:center;flex-direction:column;font-size:30px;    border-bottom: 1px solid gray;
    ;">
    </div>
    
    
  </div>
  
  </div>
  <div id="notifyBox" style="display:flex;
  flex-direction:column">
  
  </div>
  <div id="buyPage">
    <div id="nameOfShare"></div>
    <div id="priceOfShare"></div>
   Buy Quantity  :<input id="quantityOfShare" type="number" placeholder="Enter quantity of shares"/>
   <input type="submit" onclick="buyRequest()"/>
  </div>
  <div id="sellPage">
    <div id="nameOfShare1"></div>
    <div id="priceOfShare1"></div>
   Sell Quantity  :<input id="quantityOfShare1" type="number" placeholder="Enter quantity of shares"/>
   <input type="submit" onclick="sellRequest()"/>
  </div>
  
    <!-- TradingView Widget END -->
  
  
 <script src="GrowMore.js"></script>
  </body>
  </html>