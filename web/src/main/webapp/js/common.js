/**
 * 
 */
function ajaxCheckAvailability(request) {
	var url = "http://localhost:7084/getAvailability";
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('POST', url, false);
    xmlhttp.send(request);
    return xmlhttp.responseText;
};

function ajaxSendRequest() {
	var url = "http://localhost:7084/sendRequest";
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('POST', url, false);
    xmlhttp.send(JSON.stringify(requestJSON));
    document.getElementById("resultInfo").innerHTML = xmlhttp.responseText;
};




function createOptionsSelectProcess(arrayResponse){
	var html = '';
	var arrayLength = arrayResponse.length;
	html += '<option value="'+ "default" +'">' + "Sin seleccion" + '</option>';
	for (var i = 0; i < arrayLength; i++) {
		if (arrayResponse[i] == "processingComponents.GetNumberOfMatchesGenericProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener numero de coincidencias (G)" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopFrecuentWordGenericProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener palabras mas frecuentes (G)" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FeedbackTweetsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener Feedback" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterByRssAuthorProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar resultados por autor" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterByWeatherCloudinessProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar resultados por nubosidad" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterByWeatherConditionsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar segun condicion meteorologica" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterByWeatherHumidityProccess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar segun humedad" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterByWeatherTempProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar por temperatura" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterRepositoriesByAuthorProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar repositorios segun autor" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.FilterRepositoriesBySizeProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar repositorios por tamaño" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.RepositoryInformationProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener informacion del repositorio" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TimeIntervalMailProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar por intervalo de tiempo" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TimeIntervalRepositoryProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar por intervalo de tiempo" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TimeIntervalRssProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar por intervalo de tiempo" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TimeIntervalTweetsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Filtrar por intervalo de tiempo" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopArtistsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Artistas mas escuchados" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopChannelMessagesReactionsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener el top de mensajes con mas reacciones" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopLikesProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener el top de tweets con mas likes" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopMostFrecuentMailSendersProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener top de remitentes mas frecuentes" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopRetweetsProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener top de tweets con mas retweets" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TopTracksProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Obtener top de canciones mas populares" + '</option>';
			
		}else if(arrayResponse[i] == "processingComponents.TranslateGenericProcess"){
			html += '<option value="'+ arrayResponse[i] +'">' + "Traducir (G)" + '</option>';
			
		}
	}
    document.getElementById("processSelect").innerHTML = html;
}

// Indicará si el formato del input del formulario es válido
(function() {
	'use strict';
	window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);
})();

//JQUERY 
$(document).ready(function(){
	//var opt = $('select[name=exampleFormControlSelect]').val()
	//INPUTS
    $("#weatherInputForm").hide();
	$("#forecastInputForm").hide();
	//PROCESS
	$("#filterByCloudinessForm").hide();
	$("#filterByConditionForm").hide();
	$("#filterByTempForm").hide();
	$("#translatorForm").hide();
	$("#mostUsedWordsForm").hide();
	$("#matchesForm").hide();
});


$(document).on('change',function(){
	var opt = $('select[name=exampleFormControlSelect]').val()
	if (opt == "WeatherInput")
    	$("#weatherInputForm").show();
	else if(opt == "ForecastInput")
		$("#forecastInputForm").show();
});



$(document).on('change',function(){
	var opt = $('select[name=processSelect]').val()
	if (opt == "processingComponents.FilterByWeatherCloudinessProcess")
    	$("#filterByCloudinessForm").show();
	else if(opt == "processingComponents.FilterByWeatherConditionsProcess")
		$("#filterByConditionForm").show();
	else if(opt == "processingComponents.FilterByWeatherTempProcess")
		$("#filterByTempForm").show();
	else if(opt == "processingComponents.TranslateGenericProcess")
		$("#translatorForm").show();
	else if(opt == "processingComponents.TopFrecuentWordGenericProcess")
		$("#mostUsedWordsForm").show();
	else if(opt == "processingComponents.GetNumberOfMatchesGenericProcess")
		$("#matchesForm").show();
});
