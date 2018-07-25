/**
 * 
 */
//Función para el formulario de salida
function sendGist(gistDescription,gistName){

	requestJSON.output_.push({"@class":"outputComponents.GithubGistOutput",
		configuration:{"gistDescription":gistDescription.value,"gistName":gistName.value}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("gistOutputInfo").innerHTML = 
		document.getElementById("gistOutputInfo").innerHTML + "<br>+ Gist agregado";
}