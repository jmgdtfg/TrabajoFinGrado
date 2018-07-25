/**
 * 
 */
//Función de procesamiento que traduce
function translateG(language){
	$("#translatorForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.TranslateGenericProcess",
		configuration:{"language": language.value}});
	
	//Mostrar al usuario que se ha añadido correctamente

	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Se traducira a "+language.value;
}


//Función de procesamiento obtiene las palabras más frecuentes
function mostUsedWord(top){
	$("#mostUsedWordsForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.TopFrecuentWordGenericProcess",
		configuration:{"top": top.value}});
	
	//Mostrar al usuario que se ha añadido correctamente

	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Obtener top "+top.value+" de las palabras mas frecuentes";
}

//Función de procesamiento obtiene las palabras más frecuentes
function getMatches(key){
	$("#matchesForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.GetNumberOfMatchesGenericProcess",
		configuration:{"key": key.value}});
	
	//Mostrar al usuario que se ha añadido correctamente

	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Numero de veces que aparece: \""+key.value+"\"";
}