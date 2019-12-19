    
	var n0="<span id=\"hhome\" class=\"active\" href=\"\">Home</span>\n" + 
    "      <form id=\"myForm\" action=\"logout\"><span id=\"hlogout\" style=\"float:right\" href=\"\">Logout</span></form>";        
    
    var n1="<span id=\"hhome\" class=\"active\" href=\"\">Home</span>\n" + 
		"      <span id=\"hclassroom\" href=\"\">CS-101</span>\n" + 
		"      <span id=\"hblog\" href=\"\">Blog</span>\n" + 
		"      <form id=\"myForm\" action=\"logout\"><span id=\"hlogout\" style=\"float:right\" href=\"\">Logout</span></form>";
    
    var p0= "<center><h1 style=\"margin-top: 35px\">Year: 2018 Semester: 2</h1></center>\n" + 
            "<div id=\"courses\" class=\"courses\">\n" +  
            "</div>\n";
    
    var p2=	"   <div class=\"grid-container\">\n" + 
    		"  <div class=\"item1\">\n" + 
    		"  </div>\n" + 
    		"  <div class=\"item1\" style=\"text-align:center;\"><br><h1 id=\"prof\" >Bernanadas</h1></div>\n" + 
    		"  <div class=\"item1\" style=\"text-align:center;\"><br><h1>Thread Members</h1></div>\n" + 
    		"  <div id =\"asd\" class=\"item3\">\n" + 
    		"</div>\n" + 
    		"  <div id=\"threadroll\" class=\"item2\"></div>\n" + 
    		"  <div id =\"item3\" class=\"item3\">\n" + 
            "</div>\n" ;
    
    var p3="<div class=\"grid-container\">\n" + 
		"<div class=\"item1\"></div>\n" + 
		"<div class=\"item1\" style=\"text-align:center;\"><br><h1 id=\"prof\">dsjhgjfghf</h1></div>\n" + 
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
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var res= JSON.parse( this.responseText )["data"];
	    	console.log(res);
	    	var s="";
	    	for(var i=0;i<res.length;i++)
	    	{
	    		s=s+"<div class=\"course\" onclick=\"openclass("+"'"+res[i].course_id+"'"+","+"'"+res[i].sec_id+"'"+","+"'"
	    		+res[i].year+"'"+","+"'"+res[i].semester+"'"+
	    		")\">" + "<span style=\"float:left;padding-left: 10px;\">"
	    		+res[i].title+"<br>"+res[i].name+"</span></div>\n";
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

function openclass(course_id,sec_id,year,semester){
	scourse=course_id;
	ssection=sec_id;
	syear=year;
	ssemester=semester;
	
	$("#mnav").html(n1);;
	$("#css").attr("href","1.css");
	$("#hhome").click(function (){
	        home();
	});
	  $("#hblog").click(function (){
	        $("#mbody").html(p3);
	        $("#css").attr("href","3.css");
	    });
	  $("#hlogout").click(function (){
	    	$("#myForm").submit();
	    }); 
	setblog(uuid);
	
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var r=JSON.parse(this.responseText);
	    	s="<br><br><center>";i=0;
	    	for(i=0;i<r.length;i++){
	    		s+="<div class=\"img\" onclick=\"opencourse("+"'"+scourse+"'"+","+"'"+ssection+"'"+","+"'"
	    		+syear+"'"+","+"'"+ssemester+"'"+","+"'"+r[i].uid+"'"+",'"+r[i].name+"'"+
	    		")\" ><div><img src=\"user.png\" class=\"rounded-circle\" width=\"100\" height=\"100\"></div>"
	    			+ "<div style=\"height: 24px; overflow: hidden; text-align: center\">"+r[i].name+"</div></div>";
	    	}
	    	s+="</center>";
	    	console.log(s);
	    	$("#mbody").html(s);
	    	$("#hclassroom").html(course_id);
	    	 $("#hclassroom").click(function (){
	    	        openclass(course_id,sec_id,year,semester);
	    	    });
	    }
	    
	  };
	  
	  xhttp.open("GET", "classroom?course_id="+course_id+"&sec_id="+sec_id+"&year="+year+"&semester="+semester, true);
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


function opencourse(course_id,sec_id,year,semester,uid,name)
{
	scourse=course_id;
	ssection=sec_id;
	syear=year;
	ssemester=semester;
	suid=uid;
	
	$("#mbody").html(p2);
	$("#css").attr("href","2.css");
	$("#hhome").click(function (){
	        home();
	});
	 $("#hlogout").click(function (){
	    	$("#myForm").submit();
	    });
	 $("#prof").html(name);
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
		    		y=y+"<div class=\"msg\" onclick=\"openquestions("+res[i].thread_id+","+res[i].type+")\">"+res[i].subject+"</div>";
		    	}
		    	document.getElementById("asd").innerHTML = y;
	    	}
	    }
	    
	  };
	  
	  xhttp.open("GET", "AllThreads?course_id="+course_id+"&sec_id="+sec_id+"&year="+year+"&semester="+semester+"&uid="+uid, true);
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
	    		    		y=y+" <div class=\"msg\" onclick=\"bopenquestions('"+res[i].thread_id+"')\">"+res[i].subject+"</div>";
	    		    		
	    		    	}
	    		    	document.getElementById("asd").innerHTML = y;
	    	    	}
	    			
	    	    }
	    	    
	    	  };
	    	  
	    	  xhttp.open("GET", "AllThreads?course_id="+scourse+"&sec_id="+ssection+"&year="+syear+"&semester="+ssemester+"&uid="+uuid, true);
	    	  xhttp.send();
	    	  
	    });
}

function bopenquestions(thread_id)
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
	    	   
	    	    innerHTML+="</div>";
	    	}
	    	
	    	$("#threadroll").html(innerHTML);

	    }
	  };
	  xhttp.open("GET", "open_qa?thread_id="+thread_id, true);
	  xhttp.send();
}


function openquestions(thread_id,type)
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

	    	    	innerHTML+="<br>\n" + 
	    	    	"    <div style=\"border-radius: 10px; margin: 10px;background-color: #AAA\">\n" + 
	    	    	"      <textarea style=\"resize: none;padding: 5px;border-radius: 10px;width: 100%;\" onfocus=\"expand(this)\" onblur=\"retract(this)\" placeholder=\"Enter answer ...\"></textarea></div>\n" + 
	    	    	"    <input style=\"width: 10%;margin-left: 89%\" onMouseDown=\"addanswer(this,"+thread_id+","+q_id+")\" type=\"button\" value=\"send\">";
	    	    	
	    	    innerHTML+="</div>";
	    	    
	    	    if(i==ques.length-1){
	    	    	if(type==0){
	    	    	innerHTML+="<center><button onclick=\"addtoblog("+thread_id+")\" >Add to Blog</button></center> ";
	    	    	}else{
	    	    		innerHTML+="<center>Added to Blog</center> ";	
	    	    	}
	    	    	if(type==0){
		    	    	innerHTML+="<center><button onclick=\"givepoints("+thread_id+")\" >Give Points</button></center> ";
	    	    	}
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


function addtoblog(thread_id){
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	opencourse(scourse,ssection,syear,ssemester,suid);
	    	openquestions(thread_id);
	    }
	  };
	  
	  xhttp.open("GET",  "AddtoBlog?thread_id="+thread_id, true);
	  xhttp.send();
}

function addanswer(button,thread_id,q_id){
	var msg = button.previousElementSibling.firstElementChild.value;
	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    }
	  };
	  
	  xhttp.open("GET",  "AddAnswer?thread_id="+thread_id+"&text="+msg+"&q_id="+q_id, true);
	  xhttp.send();
}

function tcreate(){
	var xhttp;
	xhttp = new XMLHttpRequest();
	console.log("hellquestions");
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var modal = document.getElementById("myModal");
	    	modal.style.display = "none";
	    }
	  };
	  var subject=$("#stcreate").val();
	  var question=$("#qtcreate").val();
	  xhttp.open("GET",  "create_thread?course_id="+scourse+"&sec_id="+ssection+"&year="+syear+"&semester="+ssemester+"&subject="+subject+"&question="+question, true);
	  xhttp.send();
}


function expand(x) {
x.style.height = "100px";
}


function retract(x) {
  x.style.height = "auto";
}

function givepoints(thread_id)
{
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	openquestions(thread_id,0);
	    }
	  };
	  
	  xhttp.open("GET",  "givepoints?thread_id="+thread_id, true);
	  xhttp.send();

}


