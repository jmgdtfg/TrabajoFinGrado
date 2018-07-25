/**
 * 
 */

function weatherInput(weatherCity){
	//Comprueba que procesos estan disponibles
	checkAvailabilityJSON.input_.push({"@class":"inputComponents.WeatherInput",
		configuration:{"weatherCity": weatherCity.value}});
	var strResponse = ajaxCheckAvailability(JSON.stringify(checkAvailabilityJSON));
	//arrayResponse guarda un listado de los procesos disponibles
	var arrayResponse = strResponse.split(",");
	//Se guarda el input en la variable global
	requestJSON.input_.push({"@class":"inputComponents.WeatherInput",
		configuration:{"weatherCity": weatherCity.value}});
	//Mostrar al usuario que se ha añadido correctamente
	//Se pasa el listado de procesos disponibles
	createOptionsSelectProcess(arrayResponse);
}