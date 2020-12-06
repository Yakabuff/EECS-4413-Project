<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script type="text/javascript">

function send(){
    $.ajax({
        type: "POST",
        url: "TopBooks",
        success:function(data)
        {
            //console.log the response
			var d = new Date();
            document.getElementById("books").innerHTML =  data + " "+d;
            //Send another request in 10 seconds.
            setTimeout(function(){
                send();
            }, 1000);
        }
    });
}

 $(document).ready(function() {
//      $('#btn').click(function() {
//       $.ajax({
//              type:"POST",
//              url:"TopBooks"
//          })
//          .done(function (data) {
//       alert(data);//data object represents the response
//          });
//      });
	 send();
 });
</script>
</head>
<body>
<!--  <input type="button" value="Call Servlet" id="btn"/> -->
 
 <h1>Top 10 books sold</h1>
 <h1 id="books"></h1>
</body>
</html>

















