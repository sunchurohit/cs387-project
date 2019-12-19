
public class html {
	static String login = 
			"<!DOCTYPE html>\n" + 
			"<html>\n" + 
			"<head>\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"<style>\n" + 
			"body {font-family: Arial, Helvetica, sans-serif;}\n" + 
			"form {border: 3px solid #f1f1f1;}\n" + 
			"\n" + 
			"input[type=text], input[type=password] {\n" + 
			"    width: 100%;\n" + 
			"    padding: 12px 20px;\n" + 
			"    margin: 8px 0;\n" + 
			"    display: inline-block;\n" + 
			"    border: 1px solid #ccc;\n" + 
			"    box-sizing: border-box;\n" + 
			"}\n" + 
			"\n" + 
			"button {\n" + 
			"    background-color: #4CAF50;\n" + 
			"    color: white;\n" + 
			"    padding: 14px 20px;\n" + 
			"    margin: 8px 0;\n" + 
			"    border: none;\n" + 
			"    cursor: pointer;\n" + 
			"    width: 100%;\n" + 
			"}\n" + 
			"\n" + 
			"button:hover {\n" + 
			"    opacity: 0.8;\n" + 
			"}\n" + 
			"\n" + 
			".imgcontainer {\n" + 
			"    text-align: center;\n" + 
			"    margin: 24px 0 12px 0;\n" + 
			"}\n" + 
			"\n" + 
			"img.avatar {\n" + 
			"    width: 60%;\n" + 
			"    border-radius: 50%;\n" + 
			"}\n" + 
			"\n" + 
			".container {\n" + 
			"    align: center;\n" + 
			"    width: 80%;\n" + 
			"}\n" + 
			"\n" + 
			"span.psw {\n" + 
			"    padding-top: 16px;\n" + 
			"    padding-bottom: 16px;\n" + 
			"}\n" + 
			"\n" + 
			"/* Change styles for span and cancel button on extra small screens */\n" + 
			"@media screen and (max-width: 300px) {\n" + 
			"    span.psw {\n" + 
			"       display: block;\n" + 
			"       float: none;\n" + 
			"    }\n" + 
			"    .cancelbtn {\n" + 
			"       width: 100%;\n" + 
			"    }\n" + 
			"}\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"<center>\n" + 
			"<h2>Welcome to Doubt-Smash!</h2>\n" + 
			"<form style=\"width: 30%\" action=\"Login\" method=\"post\">\n" + 
			"  <div class=\"imgcontainer\">\n" + 
			"    <img src=\"login_icon.jpeg\" class=\"avatar\">\n" + 
			"  </div>\n" + 
			"\n" + 
			"  <center><div class=\"container\">\n" + 
			"    <label for=\"uid\"><b>User ID</b></label>\n" + 
			"    <input type=\"text\" placeholder=\"Enter User ID\" name=\"uid\" required>\n" + 
			"\n" + 
			"    <label for=\"psw\"><b>Password</b></label>\n" + 
			"    <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" required>\n" + 
			"        \n" + 
			"        <input type=\"submit\" value=\"Login\">\n" + 

			"\n" + 
			"    \n" + 
			"  </div>\n" + 
			"  </center>\n" + 
			"</form>\n" + 
			"</center>\n" + 
			"</body>\n" + 
			"</html>\n";
	
	static String student="<html>\n" + 
			"    <head>\n" + 
			"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" + 
			"        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" + 
			"        <link id=\"css\" rel=\"stylesheet\" href=\"0.css\">\n" + 
			"    </head>\n" + 
			"    \n" + 
			"    <body>\n" + 
			"    \n" + 
			"        \n" + 
			"    <div class=\"topnav\" id=\"mnav\">\n" + 
			"    </div>  \n" + 
			"    \n" + 
			"    <div class=\"main\" id=\"mbody\"></div>  \n" + 
			"        <script src=\"student.js\"></script>\n" + 
			"    </body>\n" + 
			"</html>";
	
	
	static String instructor="<html>\n" + 
			"    <head>\n" + 
			"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" + 
			"        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" + 
			"        <link id=\"css\" rel=\"stylesheet\" href=\"0.css\">\n" + 
			"    </head>\n" + 
			"    \n" + 
			"    <body>\n" + 
			"    \n" + 
			"        \n" + 
			"    <div class=\"topnav\" id=\"mnav\">\n" + 
			"    </div>  \n" + 
			"    \n" + 
			"    <div class=\"main\" id=\"mbody\"></div>  \n" + 
			"        <script src=\"instructor.js\"></script>\n" + 
			"    </body>\n" + 
			"</html>";
	
}
