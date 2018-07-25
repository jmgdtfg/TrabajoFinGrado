

//Función para el formulario de salida
function sendEmail(user,password,server,emailList,subject){

	requestJSON.output_.push({"@class":"outputComponents.EmailOutput",
		configuration:{"user": user.value,
			"password": password.value,
			"server":server.value,
			"emailList":emailList.value,
			"subject":subject.value}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("emailOutputInfo").innerHTML = 
		document.getElementById("emailOutputInfo").innerHTML + "<br>+ Email agregado. Se enviara a -> "+ emailList.value;
}