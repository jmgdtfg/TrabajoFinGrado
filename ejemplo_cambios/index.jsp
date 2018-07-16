<html>
<head>
<!--  BOOTSTRAP -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="css/bootstrapCss/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="css/bootstrapCss/bootstrap-grid.min.css"
	type="text/css">
<link rel="stylesheet" href="css/bootstrapCss/bootstrap-reboot.min.css"
	type="text/css">
<script src="js/bootstrapJs/bootstrap.min.js" type="text/javascript"></script>
<script src="js/bootstrapJs/bootstrap.bundle.min.js"
	type="text/javascript"></script>


<!-- JAVASCRIPT -->

<script src="js/inputs.js" type="text/javascript"></script>
<script src="js/process.js" type="text/javascript"></script>
<script src="js/outputs.js" type="text/javascript"></script>
<script>


function ajaxRequestSync(url, request) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('POST', url, true);
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    xmlhttp.send(request);
    return xmlhttp.responseText;
};


function pruebaSend(){
	var url = "http://localhost:7084/sendRequest";
	var request = "{\"input_\":[{\"@class\":\"inputComponents.RssInput\",\"configuration\":{\"intervalEnd\":\"1\",\"author\":\"abc\",\"limit\":\"90\",\"intervalStart\":\"20\",\"rssUrl\":\"http://estaticos.marca.com/rss/futbol/primera-division.xml\"}}],\"process_\":[{\"@class\":\"processingComponents.TimeIntervalRssProcess\",\"configuration\":{\"gistDescription\":\"Gist de prueba...\",\"gistName\":\"---GIST---\",\"intervalEnd\":\"1\",\"author\":\"jm\",\"localPath\":\"C:/Users/jmgd_/Desktop/descarga\",\"minSize\":\"1\",\"maxSize\":\"150\",\"searchLanguage\":\"Java\",\"intervalStart\":\"200\",\"word\":\"la\",\"repositoryUrl\":\"https://github.com/jmgdtfg/TFG/\"}},{\"@class\":\"processingComponents.GetNumberOfMatchesGenericProcess\",\"configuration\":{\"top\":\"10\",\"key\":\"a\"}}],\"output_\":[{\"@class\":\"outputComponents.SlackChannelMessageOutput\",\"configuration\":{\"top\":\"5\",\"slackUserMail\":\"jmgd_3@outlook.com\",\"channelName\":\"general\"}}]}";
	var response = ajaxRequestSync(url, request);
	alert(response);
}

	function prueb(){
		var data = document.getElementById("emailForm");
		var x = data.user;
		var x1 = data.password.value;
		
	}

	function ocultar() {
		document.getElementById("emailForm").style.visibility = "hidden";
	};

	function handleClick(clickedId) {
		if (clickedId == "customerId")
			document.getElementById('tableTextId').value = "customer";
		else
			document.getElementById('tableTextId').value = "company";
	};

	// Example starter JavaScript for disabling form submissions if there are invalid fields
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
</script>
<!-- AJAX -->

</head>

<!-- HTML -->
<body>

	<h2 align=center>Cliente Web TFG</h2>
	<br>

	<div class="row">
		<div class="col-md-1"></div>
		<!-- COMIENZO COLUMNA INPUT -->
		<div class="col-md-5" style="background-color: #C5FFBD">


			<h5 align=center>
				<img src="icons/input.png" style="width: 18px; height: 18px;">
				Fuentes de información
			</h5>

			<div class="form-group">
				<label for="exampleFormControlSelect1">Example select</label> <select
					class="form-control" id="exampleFormControlSelect1">
					<option>Componente entrada 1</option>
					<option>Componente entrada 2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>

		</div>
		<!-- FIN COLUMNA INPUT -->

		<!-- COMIENZO COLUMNA PROCESS -->
		<div class="col-md-5" style="background-color: #FFFE9E">
			<h5 align=center>Procesamiento</h5>

		</div>
		<!-- FIN COLUMNA PROCESS -->
	</div>
	<!-- FIN CLASS-ROW -->
	<br>
	<div class="row">
		<!-- COMIENZO COLUMNA OUTPUT -->
		<div class="col-md-1"></div>
		<div class="col-md-10" style="background-color: lavender;">
			<hr>
			<h5 align=center>Salidas disponibles para la información</h5>
			<hr>

			<!-- INICIO Collapse -->

			<div id="accordion">
				<div class="card">
					<div class="card-header" id="headingOne">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne">Email</button>
						</h5>
					</div>

					<div id="collapseOne" class="collapse"
						aria-labelledby="headingOne" data-parent="#accordion">

						<!-- INICIO FORMULARIO EMAIL -->
						<form class="needs-validation" id="emailForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br><br> <br> <img src="icons/icono-email.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <label for="user">Email emisor</label> <input
										type="email" class="form-control" id="user"
										placeholder="Email válido" required>
									<div class="valid-feedback">Correcto</div>
									<hr>
									<label for="emailList">Lista de destinatarios</label> <input type="text"
										class="form-control" id="emailList"
										placeholder="a@gmail.com,b@outlook.com" required>
									<div class="valid-feedback">Correcto</div>
									<br>
									<h6 align="right">Servidor:</h6>
								</div>

								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <label for="password">Contraseña</label> <input
										type="password" class="form-control" id="password"
										placeholder="Contraseña" required>
									<div class="valid-feedback">Correcto</div>
									<hr>
									<label for="subject">Asunto del mensaje</label> <input
										type="text" class="form-control" id="subject"
										placeholder="Asunto" required>
									<div class="valid-feedback">Correcto</div>
									<br> <label class="radio-inline"><input
										type="radio" name="optradio" id="gmail">gmail</label> <label
										class="radio-inline"><input type="radio"
										name="optradio" id="hotmail" checked>hotmail</label>
								</div>
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br> <br> <br>
									<button class="btn btn-success" type="submit" onClick="prueb()">Añadir</button>
								</div>
							</div>


						</form>
						<!-- FIN FORMULARIO EMAIL -->
					</div>
				</div>

				<div class="card">
					<div class="card-header" id="heading2">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapse2" aria-expanded="true"
								aria-controls="collapse2">Mensaje directo Slack</button>
						</h5>
					</div>

					<div id="collapse2" class="collapse"
						aria-labelledby="heading2" data-parent="#accordion">

						<!-- INICIO FORMULARIO MD SLACK -->
						<form class="needs-validation" id="slackMdForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br><img src="icons/icono-slack.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-8 mb-3" style="background-color: #B5C2FF">
									<br> <label for="slackUserMail">Slack-Email del destinatario</label> <input
										type="email" class="form-control" id="slackUserMail"
										placeholder="Email válido" style="width: 500px;" required>
									<div class="valid-feedback">Correcto</div>
									<br>
									
								</div>

								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br> 
									<button class="btn btn-success" type="submit">Añadir</button>
								</div>
								
							</div>
							


						</form>
						<!-- FIN FORMULARIO MD SLACK -->
					</div>
				</div>

				<div class="card">
					<div class="card-header" id="heading3">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapse3" aria-expanded="true"
								aria-controls="collapse3">Grupo (channel) Slack</button>
						</h5>
					</div>

					<div id="collapse3" class="collapse"
						aria-labelledby="heading3" data-parent="#accordion">

						<!-- INICIO FORMULARIO CHANNEL SLACK -->
						<form class="needs-validation" id="slackChannelForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br><img src="icons/icono-slack.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-8 mb-3" style="background-color: #B5C2FF">
									<br><label for="channelName">Nombre del grupo</label> <input
										type="text" class="form-control" id="channelName"
										placeholder="Nombre del grupo" required>
									<div class="valid-feedback">Correcto</div>
									<br>
								</div>

								
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br>
									<button class="btn btn-success" type="submit">Añadir</button>
								</div>
							</div>


						</form>
						<!-- FIN FORMULARIO SLACK -->
					</div>
				</div>


				<div class="card">
					<div class="card-header" id="heading4">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapse4" aria-expanded="true"
								aria-controls="collapse4">Github GIST</button>
						</h5>
					</div>

					<div id="collapse4" class="collapse"
						aria-labelledby="heading4" data-parent="#accordion">

						<!-- INICIO FORMULARIO GIST -->
						<form class="needs-validation" id="gistForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br><br> <img src="icons/icono-github.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <label for="gistDescription">Descripción</label> <input
										type="text" class="form-control" id="gistDescription"
										placeholder="Descripción" required>
									<div class="valid-feedback">Correcto</div>
									<br>
		
								</div>

								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <label for="gistName">Nombre</label> <input
										type="text" class="form-control" id="gistName"
										placeholder="Nombre" required>
									<div class="valid-feedback">Correcto</div>
									<hr>
									<h6>
									<label for="user">El resultado será publicado <a href="https://gist.github.com/jmgdtfg">AQUÍ</a></label> 
									</h6>
									<br>
									
								</div>
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br><br><br>
									<button class="btn btn-success" type="submit">Añadir</button>
								</div>
							</div>


						</form>
						<!-- FIN FORMULARIO GIST -->
					</div>
				</div>



				<div class="card">
					<div class="card-header" id="heading5">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapse5" aria-expanded="true"
								aria-controls="collapse5">Google Sheet</button>
						</h5>
					</div>

					<div id="collapse5" class="collapse"
						aria-labelledby="heading5" data-parent="#accordion">

						<!-- INICIO FORMULARIO SHEET -->
						<form class="needs-validation" id="sheetForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br> <img src="icons/icono-sheet.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <label for="sheetName">Nombre de la hoja de cálculo</label> <input
										type="email" class="form-control" id="sheetName"
										placeholder="Nombre" required>
									<div class="valid-feedback">Correcto</div>
									<hr>
									<label for="initRow">Fila inicial</label> <input type="number"
										class="form-control" id="initRow"
										placeholder="1" required>
									<div class="valid-feedback">Correcto</div>
									<br>
								</div>

								<div class="col-md-4 mb-3" style="background-color: #B5C2FF">
									<br> <br><br><br><hr>
									<label for="maxColumn">Número de columnas</label> <input
										type="number" class="form-control" id="maxColumn"
										placeholder="5" required>
									<div class="valid-feedback">Correcto</div>
									<br> 
								</div>
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br> <br> <br>
									<button class="btn btn-success" type="submit">Añadir</button>
								</div>
							</div>


						</form>
						<!-- FIN FORMULARIO SHEET -->
					</div>
				</div>

				<div class="card">
					<div class="card-header" id="heading6">
						<h5 class="mb-0 text-center">
							<button class="btn btn-info" data-toggle="collapse"
								data-target="#collapse6" aria-expanded="true"
								aria-controls="collapse6">Twitter</button>
						</h5>
					</div>

					<div id="collapse6" class="collapse"
						aria-labelledby="heading6" data-parent="#accordion">

						<!-- INICIO FORMULARIO TWITTER -->
						<form class="needs-validation" id="twitterForm" novalidate>
							<div class="form-row">
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <img src="icons/icono-twitter.png"
										style="width: 80px; height: 80px;">
								</div>
								<div class="col-md-8 mb-3 text-center" style="background-color: #B5C2FF">
								<h6>
									<br><br><br> <label for="user">El resultado será publicado <a href="https://twitter.com/jmgd_tfg">AQUÍ</a> (https://twitter.com/jmgd_tfg)</label> 
									</h6>
									<div class="valid-feedback">Correcto</div>
									<br>
									
								</div>

								
								<div class="col-md-2 mb-3 text-center"
									style="background-color: #B5C2FF">
									<br> <br>
									<button class="btn btn-success" type="submit">Añadir</button>
								</div>
							</div>


						</form>
						<!-- FIN FORMULARIO TWITTER -->
					</div>
				</div>

				<!-- FIN COLLAPSE -->
			</div>











		</div>

		<!-- FIN COLUMNA OUTPUT -->
		<div class="col-md-1"></div>
	</div>
	<!-- FIN CLASS-ROW -->
	<br>
	<div class=row>

		<div class="col-md-12 text-center">
			<button id="singlebutton" name="singlebutton" class="btn btn-success" onClick="pruebaSend()">¡Enviar
				información!</button>
		</div>

	</div>
	<br>
	<div class="col-md-12 text-center">
		<h4 align=center>Resultado</h4>
		<br>
		<p class="lead">Resultado de la ejecución</p>

	</div>



</body>
</html>
