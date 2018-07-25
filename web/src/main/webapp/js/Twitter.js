/**
 * 
 */

//Función para el formulario de salida
function sendTwitter(){

	requestJSON.output_.push({"@class":"outputComponents.TwitterOutput",
		configuration:{}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("twitterOutputInfo").innerHTML = 
		document.getElementById("twitterOutputInfo").innerHTML + "<br>+ Twitter agregado";
}