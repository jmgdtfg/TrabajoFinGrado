/**
 * 
 */
//Función de añadir (INPUT)
function forecastInput(weatherCity){
	$("#exampleFormControlSelect").hide();
	//Comprueba que procesos estan disponibles
	checkAvailabilityJSON.input_.push({"@class":"inputComponents.ForecastInput",
		configuration:{"weatherCity": weatherCity.value}});
	var strResponse = ajaxCheckAvailability(JSON.stringify(checkAvailabilityJSON));
	//arrayResponse guarda un listado de los procesos disponibles
	var arrayResponse = strResponse.split(",");
	//Se guarda el input en la variable global
	requestJSON.input_.push({"@class":"inputComponents.ForecastInput",
		configuration:{"weatherCity": weatherCity.value}});
	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("inputInfo").innerHTML = 
		document.getElementById("inputInfo").innerHTML + "<br>+ Prediccion meteorologica " + weatherCity.value;
	//Se pasa el listado de procesos disponibles
	createOptionsSelectProcess(arrayResponse);
}
//Función de procesamiento que filtra por nubosidad
function filterByCloudiness(min,max){
	$("#filterByCloudinessForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.FilterByWeatherCloudinessProcess",
		configuration:{"cloudinessMin": min.value, "cloudinessMax": max.value}});
	
	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Filtrar por nubosidad [ " + min.value+ " , " + max.value + " ]";
}

//Función de procesamiento que filtra por temperatura
function filterByTemp(min,max){
	$("#filterByTempForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.FilterByWeatherTempProcess",
		configuration:{"tempMin": min.value, "tempMax": max.value}});
	
	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Filtrar por temperatura [ " + min.value+ "º , " + max.value + "º ]";
}

//Función de procesamiento que filtra por nubosidad
function filterByCondition(condition){
	$("#filterByConditionsForm").hide();
	requestJSON.process_.push({"@class":"processingComponents.FilterByWeatherConditionsProcess",
		configuration:{"weatherCondition": condition.value}});
	
	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("processInfo").innerHTML = 
		document.getElementById("processInfo").innerHTML + "<br>+ Filtrar por condicion meteorologica: "+condition.value;
}