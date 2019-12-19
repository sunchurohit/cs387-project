    var n0="<span id=\"hhome\" class=\"active\" href=\"\">Home</span>\n" + 
    "      <form id=\"myForm\" action=\"logout\"><span id=\"hlogout\" style=\"float:right\" href=\"\">Logout</span></form>";
        
    var n1="<span id=\"hhome\" class=\"active\" href=\"\">Home</span>\n" + 
		"      <span id=\"hclassroom\" href=\"\">CS-101</span>\n" + 
		"      <span id=\"hblog\" href=\"\">Blog</span>\n" + 
		"      <span id=\"hgroup\" href=\"\">Group</span>\n" + 
		"      <form id=\"myForm\" action=\"logout\"><span id=\"hlogout\" style=\"float:right\" href=\"\">Logout</span></form>";

    var p0= "<center><h1 style=\"margin-top: 35px\">Year: 2018 Semester: 2</h1></center>\n" + 
            "<div id=\"courses\" class=\"courses\">\n" +  
            "</div>\n";
    var p2=	"   <div class=\"grid-container\">\n" + 
    		"  <div class=\"item1\">\n" + 
    		"    <input style=\"width: 100%;height: 10vh\" type=\"button\" id=\"myBtn\" value=\"Create Thread\">\n" + 
    		"    <div id=\"myModal\" class=\"modal\">\n" + 
    		"\n" + 
    		"    <!-- Modal content -->\n" + 
    		"    <div class=\"modal-content\">\n" + 
    		"      <span class=\"close\">&times;</span>\n" + 
    		"      <textarea id=\"stcreate\" class=\"summary\" placeholder=\"Thread Subject ...\"></textarea>\n" + 
    		"      <br>\n" + 
    		"      <textarea id=\"qtcreate\" class=\"question\" placeholder=\"Enter Question ...\"></textarea>\n" + 
    		"      <br>\n" + 
    		"      <input id=\"btcreate\" style=\"width: 10%;margin-left: 85%\" type=\"button\" value=\"send\">\n" + 
    		"    </div>\n" + 
    		"  </div>\n" + 
    		"    <script>\n" + 
    		"// Get the modal\n" + 
    		"var modal = document.getElementById('myModal');\n" + 
    		"\n" + 
    		"// Get the button that opens the modal\n" + 
    		"var btn = document.getElementById(\"myBtn\");\n" + 
    		"\n" + 
    		"// Get the <span> element that closes the modal\n" + 
    		"var span = document.getElementsByClassName(\"close\")[0];\n" + 
    		"\n" + 
    		"// When the user clicks the button, open the modal \n" + 
    		"\n" + 
    		"// When the user clicks on <span> (x), close the modal\n" + 
    		"span.onclick = function() {\n" + 
    		"    modal.style.display = \"none\";\n" + 
    		"}\n" + 
    		"\n" + 
    		"// When the user clicks anywhere outside of the modal, close it\n" + 
    		"window.onclick = function(event) {\n" + 
    		"    if (event.target == modal) {\n" + 
    		"        modal.style.display = \"none\";\n" + 
    		"    }\n" + 
    		"}\n" + 
    		"</script>\n" + 
    		"  </div>\n" + 
    		"  <div class=\"item1\" style=\"text-align:center;\"><br><h1 id=\"prof\" >Bernanadas</h1></div>\n" + 
    		"  <div class=\"item1\" style=\"text-align:center;\"><br><h1>Thread Members</h1></div>\n" + 
    		"  \n" + 
    		"  <div id =\"asd\" class=\"item3\">\n" + 
    		"\n" + 
    		"</div>\n" + 
    		"  \n" + 
    		"  <div id=\"threadroll\" class=\"item2\">\n" + 
    		"  </div>\n" + 
    		"  \n" + 
    		"  <div id =\"item3\" class=\"item3\">\n" + 
            "</div>\n" ;

    var p3="<div class=\"grid-container\">\n" + 
		"<div class=\"item1\"></div>\n" + 
		"<div class=\"item1\" style=\"text-align:center;\"><br><h1 id=\"prof\" >dsjhgjfghf</h1></div>\n" + 
		"<div id =\"asd\" class=\"item3\"></div>\n" + 
		"\n" + 
		"<div id=\"threadroll\" class=\"item2\"></div>\n" + 
		"</div>\n";
    
var ssection;
var scourse;
var syear;
var ssemester;
var suid;
var uuid;
var uname;


function home(){
	
    $("#mnav").html(n0);
    $("#mbody").html(p0);
    $("#css").attr("href","0.css");
    $("#hhome").click(function (){
        home();
    });
    $("#hlogout").click(function (){
    	$("#myForm").submit();
    });
    
    var xhttp;
	xhttp = new XMLHttpRequest();
	console.log("hello");
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var res= JSON.parse( this.responseText )["data"];
	    	console.log(res);
	    	var s="";
	    	for(var i=0;i<res.length;i++)
	    	{
	    		
	    		s=s+"<div class=\"course\" onclick=\"suid='"+res[i].uid+"';opencourse("+"'"+res[i].course_id+"'"+","+"'"+res[i].sec_id+"'"+","+"'"
	    		+res[i].year+"'"+","+"'"+res[i].semester+"'"+","+"'"+res[i].name+"'"+
	    		");\">" + "<span style=\"float:left;padding-left: 10px;\">"+
	    		res[i].title+"<br>"+res[i].name+"</span><span style=\"float: right;padding-right: 10px;\">"+
	    		res[i].brownie_points+"</span></div>\n";
	    	}
	    	document.getElementById("courses").innerHTML = s;
	    }
	  };
	  xhttp.open("GET", "AllCourses", true);
	  xhttp.send();
	  
	  var xhttp;
		xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	var res= JSON.parse( this.responseText );
		    	uname=res["name"];
		    	uuid=res["uid"];
		    }
		  };
		  
		  xhttp.open("GET", "details", true);
		  xhttp.send();
	  
}


function x(){
     $("#hclassroom").click(function (){
        $("#mbody").html(p0);
        $("#css").attr("href","1.css");
    });
    $("#hblog").click(function (){
        $("#mbody").html(p3);
        $("#css").attr("href","3.css");
    });
    $("#hgroup").click(function (){
        $("#mbody").html(p3);
        $("#css").attr("href","3.css");
    });
}


$(document).ready(function(){
    home();
});


function opencourse(course_id,sec_id,year,semester,name)
{
	scourse=course_id;
	ssection=sec_id;
	syear=year;
	ssemester=semester;
	$("#mnav").html(n1);
	$("#hhome").click(function (){
	        home();
	});
	$("#hgroup").click(function (){
        opengroup();
    });
	$("#mbody").html(p2);
	$("#prof").html(name);
	$("#css").attr("href","2.css");
	var btn = document.getElementById("myBtn");
	btn.onclick = function() { 
	    modal.style.display = "block"; 
	    $("#btcreate").click(function(){
			tcreate(0);
		});
	} 
	$("#hlogout").click(function (){
	    	$("#myForm").submit();
	});
	$("#hclassroom").html(course_id);
	 $("#hclassroom").click(function (){
	        opencourse(course_id,sec_id,year,semester,name);
	    });
	setblog(suid);
	var xhttp;
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var r=JSON.parse(this.responseText);
	    	if(r!=null){
	    		var res=r["data"];
		    	console.log(res);
		    	var y="";
		    	for(var i=0;i<res.length;i++)
		    	{
		    		console.log(res[i]);
		    		y=y+" <div class=\"msg\" onclick=\"openquestions('"+res[i].thread_id+"')\">"+res[i].subject+"</div>";		
		    	}
		    	document.getElementById("asd").innerHTML = y;
	    	}
	    }
	  };
	  
	  xhttp.open("GET", "AllThreads?course_id="+course_id+"&sec_id="+sec_id+"&year="+year+"&semester="+semester+"&uid="+uuid, true);
	  xhttp.send();
	  
	
}

function opengroup(){
	$("#mbody").html(p2);
    $("#css").attr("href","2.css");
    $("#prof").html("Classroom Group");
    var btn = document.getElementById("myBtn");
	btn.onclick = function() { 
	    modal.style.display = "block"; 
	    $("#btcreate").click(function(){
			tcreate(2);
		});
	} 
	var xhttp;
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var r=JSON.parse(this.responseText);
	    	if(r!=null){
	    		var res=r["data"];
		    	console.log(res);
		    	var y="";
		    	for(var i=0;i<res.length;i++)
		    	{
		    		y=y+" <div class=\"msg\" onclick=\"gopenquestions('"+res[i].thread_id+"')\">"+res[i].subject+"</div>";
		    	}
		    	document.getElementById("asd").innerHTML = y;
	    	}
			
	    }
	    
	  };
	  
	  xhttp.open("GET", "GroupThreads?course_id="+scourse+"&sec_id="+ssection+"&year="+syear+"&semester="+ssemester, true);
	  xhttp.send();
}


function setblog(uid){
	  $("#hblog").click(function (){
	        $("#mbody").html(p3);
	        $("#css").attr("href","3.css");
	        $("#prof").html("Professor Blog");
	        var xhttp;

	    	xhttp = new XMLHttpRequest();
	    	  xhttp.onreadystatechange = function() {
	    	    if (this.readyState == 4 && this.status == 200) {
	    	    	var r=JSON.parse(this.responseText);
	    	    	if(r!=null){
	    	    		var res=r["data"];
	    		    	console.log(res);
	    		    	var y="";
	    		    	for(var i=0;i<res.length;i++)
	    		    	{
	    		    		console.log(res[i]);
	    		    		y=y+" <div class=\"msg\" onclick=\"bopenquestions('"+res[i].thread_id+"',"+res[i].type+")\">"+res[i].subject+"</div>";
	    		    		
	    		    	}
	    		    	document.getElementById("asd").innerHTML = y;
	    	    	}
	    			
	    	    }
	    	    
	    	  };
	    	  
	    	  xhttp.open("GET", "BlogThreads?course_id="+scourse+"&sec_id="+ssection+"&year="+syear+"&semester="+ssemester+"&uid="+suid, true);
	    	  xhttp.send();
	    	  
	    });
}



function bopenquestions(thread_id,type)
{
	var xhttp;
	xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log(this.responseText);
	    	var r=JSON.parse(this.responseText);
	    	var ques=r["ques"];
	    	var ans=r["ans"];
	    	i=0;j=0;
	    	
	    	threadroll=document.getElementById("threadroll");
	    	innerHTML="";
	    	
	    	for (i = 0; i < ques.length; i++) {
	    		q_id=ques[i].q_id;
	    		innerHTML+="<div class=\"threadmsg\"><div class=\"threadques\">"+ques[i].text+"</div>";
	    	    while(j<ans.length && ans[j].q_id == q_id){
	    	    	innerHTML+="<div class=\"threadans\">"+ans[j].text+"</div>";
	    	    	j++;
	    	    }
	    	    if(i==ques.length-1){
	    	    	if(type==0){
	    	    	innerHTML+="<center><button id=\"myBtn\">Add a Question</button></center>";
	    	    	innerHTML+=	"<div id=\"myModal\" class=\"modal\">\n" + 
	    			"\n" + 
	    			" <!-- Modal content -->\n" + 
	    			" <div class=\"modal-content\">\n" + 
	    			"   <span class=\"close\">&times;</span>\n" + 
	    			"\n" + 
	    			"   <textarea id=\"tquestion\" class=\"question\" placeholder=\"Enter Question ...\"></textarea>\n" + 
	    			"   <br>\n" + 
	    			"   <input style=\"width: 10%;margin-left: 85%\" onclick=\"tjoin("+thread_id+")\" type=\"button\" value=\"send\">\n" + 
	    			" </div>\n" + 
	    			"\n" + 
	    			"\n" + 
	    			"</div>\n" + 
	    			"\n" + 
	    			"<script>\n" + 
	    			"//Get the modal\n" + 
	    			"var modal = document.getElementById('myModal');\n" + 
	    			"\n" + 
	    			"//Get the button that opens the modal\n" + 
	    			"var btn = document.getElementById(\"myBtn\");\n" + 
	    			"\n" + 
	    			"//Get the <span> element that closes the modal\n" + 
	    			"var span = document.getElementsByClassName(\"close\")[0];\n" + 
	    			"\n" + 
	    			"//When the user clicks the button, open the modal \n" + 
	    			"btn.onclick = function() {\n" + 
	    			" modal.style.display = \"block\";\n" + 
	    			"}\n" + 
	    			"\n" + 
	    			"//When the user clicks on <span> (x), close the modal\n" + 
	    			"span.onclick = function() {\n" + 
	    			" modal.style.display = \"none\";\n" + 
	    			"}\n" + 
	    			"\n" + 
	    			"//When the user clicks anywhere outside of the modal, close it\n" + 
	    			"window.onclick = function(event) {\n" + 
	    			" if (event.target == modal) {\n" + 
	    			"     modal.style.display = \"none\";\n" + 
	    			" }\n" + 
	    			"}\n" + 
	    			"</script>";
	    	    	}else{
	    	    		innerHTML+="<center><button onclick=\"$('#hclassroom').click();openquestions("+thread_id+",0);\">Go to thread</button></center>";
	    	    	}
	    	    }
	    	    innerHTML+="</div>";
	    	}
	    	
	    	$("#threadroll").html(innerHTML);

	    }
	  };
	  xhttp.open("GET", "open_qa?thread_id="+thread_id, true);
	  xhttp.send();
	  
	  var xhttp2;
		xhttp2 = new XMLHttpRequest();
		console.log("hellmembers");
		xhttp2.onreadystatechange = function() {
			console.log("thread member function mein aaya");
		    if (this.readyState == 4 && this.status == 200) {
		    	console.log("thread member ready state mein aaya");
		    	var obj = JSON.parse( this.responseText );
		    	var res=obj["data"];
		    	var s="";
		    	console.log(res);
		    	for(var i=0;i<res.length;i++)
	    		{
		    		s=s+"<div class=\"msg\">"+res[i].name+"</div> ";
	    		}
		    	document.getElementById("item3").innerHTML = s;
		    }
		};
	  xhttp2.open("GET", "ThreadMembers?thread_id="+thread_id, true);
	xhttp2.send();
	
}

function openquestions(thread_id)
{
	var xhttp;
	xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log(this.responseText);
	    	var r=JSON.parse(this.responseText);
	    	var ques=r["ques"];hclassroom
	    	var ans=r["ans"];
	    	i=0;j=0;
	    	
	    	threadroll=document.getElementById("threadroll");
	    	innerHTML="";
	    	
	    	for (i = 0; i < ques.length; i++) {
	    		q_id=ques[i].q_id;
	    		innerHTML+="<div class=\"threadmsg\"><div class=\"threadques\">"+ques[i].text+"</div>";
	    	    while(j<ans.length && ans[j].q_id == q_id){
	    	    	innerHTML+="<div class=\"threadans\">"+ans[j].text+"</div>";
	    	    	j++;
	    	    }
	    	    innerHTML+="</div>";
	    	    if(i==ques.length-1){
	    	    	innerHTML+="<br>\n" + 
	    	    	"    <div style=\"border-radius: 10px; margin: 10px;background-color: #AAA\">\n" + 
	    	    	"      <textarea style=\"resize: none;padding: 5px;border-radius: 10px;width: 100%;\" onfocus=\"expand(this)\" onblur=\"retract(this)\" placeholder=\"Enter question ...\"></textarea></div>\n" + 
	    	    	"    <input style=\"width: 10%;margin-left: 89%\" onMouseDown=\"addquestion(this,"+thread_id+",0)\" type=\"button\" value=\"send\">";
	    	    }
	    	}
	    	
	    	$("#threadroll").html(innerHTML);

	    }
	  };
	  xhttp.open("GET", "open_qa?thread_id="+thread_id, true);
	  xhttp.send();
	  
	  var xhttp2;
		xhttp2 = new XMLHttpRequest();
		console.log("hellmembers");
		xhttp2.onreadystatechange = function() {
			console.log("thread member function mein aaya");
		    if (this.readyState == 4 && this.status == 200) {
		    	console.log("thread member ready state mein aaya");
		    	var obj = JSON.parse( this.responseText );
		    	var res=obj["data"];
		    	var s="";
		    	console.log(res);
		    	for(var i=0;i<res.length;i++)
	    		{
		    		s=s+"<div class=\"msg\">"+res[i].name+"</div> ";
	    		}
		    	document.getElementById("item3").innerHTML = s;
		    }
		};
	  xhttp2.open("GET", "ThreadMembers?thread_id="+thread_id, true);
	xhttp2.send();
	
}

function gopenquestions(thread_id)
{
	var xhttp;
	xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	console.log(this.responseText);
	    	var r=JSON.parse(this.responseText);
	    	var ques=r["ques"];hclassroom
	    	var ans=r["ans"];
	    	i=0;j=0;
	    	
	    	threadroll=document.getElementById("threadroll");
	    	innerHTML="";
	    	
	    	for (i = 0; i < ques.length; i++) {
	    		q_id=ques[i].q_id;
	    		innerHTML+="<div class=\"threadmsg\"><div class=\"threadques\">"+ques[i].text+"</div>";
	    	    while(j<ans.length && ans[j].q_id == q_id){
	    	    	innerHTML+="<div class=\"threadans\">"+ans[j].text+"</div>";
	    	    	j++;
	    	    }
	    	    
	    	    	innerHTML+="<br>\n" + 
	    	    	"    <div style=\"border-radius: 10px; margin: 10px;background-color: #AAA\">\n" + 
	    	    	"      <textarea style=\"resize: none;padding: 5px;border-radius: 10px;width: 100%;\" onfocus=\"expand(this)\" onblur=\"retract(this)\" placeholder=\"Enter answer ...\"></textarea></div>\n" + 
	    	    	"    <input style=\"width: 10%;margin-left: 89%\" onMouseDown=\"addanswer(this,"+thread_id+","+q_id+")\" type=\"button\" value=\"send\">";
	    	  
	    	    innerHTML+="</div>";
	    	    if(i==ques.length-1){
	    	    	innerHTML+="<br>\n" + 
	    	    	"    <div style=\"border-radius: 10px; margin: 10px;background-color: #AAA\">\n" + 
	    	    	"      <textarea style=\"resize: none;padding: 5px;border-radius: 10px;width: 100%;\" onfocus=\"expand(this)\" onblur=\"retract(this)\" placeholder=\"Enter question ...\"></textarea></div>\n" + 
	    	    	"    <input style=\"width: 10%;margin-left: 89%\" onMouseDown=\"addquestion(this,"+thread_id+",2)\" type=\"button\" value=\"send\">";
	    	    }
	    	}
	    	
	    	$("#threadroll").html(innerHTML);

	    }
	  };
	  xhttp.open("GET", "open_qa?thread_id="+thread_id, true);
	  xhttp.send();
	  
	  var xhttp2;
		xhttp2 = new XMLHttpRequest();
		console.log("hellmembers");
		xhttp2.onreadystatechange = function() {
			console.log("thread member function mein aaya");
		    if (this.readyState == 4 && this.status == 200) {
		    	console.log("thread member ready state mein aaya");
		    	var obj = JSON.parse( this.responseText );
		    	var res=obj["data"];
		    	var s="";
		    	console.log(res);
		    	for(var i=0;i<res.length;i++)
	    		{
		    		s=s+"<div class=\"msg\">"+res[i].name+"</div> ";
	    		}
		    	document.getElementById("item3").innerHTML = s;
		    }
		};
	  xhttp2.open("GET", "ThreadMembers?thread_id="+thread_id, true);
	xhttp2.send();
	
}

function addanswer(button,thread_id,q_id){
	var msg = button.previousElementSibling.firstElementChild.value;
	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	gopenquestions(thread_id);
	    }
	  };
	  
	  xhttp.open("GET",  "AddAnswer?thread_id="+thread_id+"&text="+msg+"&q_id="+q_id, true);
	  xhttp.send();
}

function addquestion(button,thread_id,type){
	var msg = button.previousElementSibling.firstElementChild.value;
	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	if(type==0)
	    		openquestions(thread_id);
	    	else
	    		gopenquestions(thread_id);
	    }
	  };
	  
	  xhttp.open("GET",  "AddQuestion?thread_id="+thread_id+"&text="+msg, true);
	  xhttp.send();
	
}


function tcreate(type){
	
	var xhttp;
	xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var modal = document.getElementById("myModal");
	    	modal.style.display = "none";
	    	if(type==0)
	    		$("#hclassroom").click();
	    	else
	    		$("#hgroup").click();
	
	    }
	  };
	  
	  var subject=$("#stcreate").val();
	  var question=$("#qtcreate").val();
	  xhttp.open("GET",  "create_thread?course_id="+scourse+"&sec_id="+ssection+"&year="+syear+"&semester="+ssemester+"&subject="+subject+"&question="+question+"&type="+type, true);
	  xhttp.send();

}


function tjoin(thread_id){
	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var modal = document.getElementById("myModal");
	    	modal.style.display = "none";
	    	bopenquestions(thread_id,1);
	    }
	  };

	  var question=$("#tquestion").val();
	  xhttp.open("GET",  "join_thread?thread_id="+thread_id+"&question="+question, true);
	  xhttp.send();
	  
}


function expand(x) {
x.style.height = "100px";
}


function retract(x) {
  x.style.height = "auto";
}



