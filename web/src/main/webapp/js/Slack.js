/**
 * 
 */
//Función para el formulario de salida
function sendSlackMD(slackUserMail){

	requestJSON.output_.push({"@class":"outputComponents.SlackDirectMessageOutput",
		configuration:{"slackUserMail":slackUserMail.value}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("slackMDOutputInfo").innerHTML = 
		document.getElementById("slackMDOutputInfo").innerHTML + "<br>+ MD Slack agregado";
}

//Función para el formulario de salida
function sendSlackChannel(channelName){

	requestJSON.output_.push({"@class":"outputComponents.SlackChannelMessageOutput",
		configuration:{"channelName":channelName.value}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("slackChannelOutputInfo").innerHTML = 
		document.getElementById("slackChannelOutputInfo").innerHTML + "<br>+ Canal Slack agregado";
}