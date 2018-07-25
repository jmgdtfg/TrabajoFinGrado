/**
 * 
 */
//Función para el formulario de salida
function sendSpreadSheet(initRow,maxColumn,sheetName){

	requestJSON.output_.push({"@class":"outputComponents.SpreadSheetOutput",
		configuration:{"initRow":initRow.value,"maxColumn":maxColumn.value,"sheetName":sheetName.value}});

	//Mostrar al usuario que se ha añadido correctamente
	document.getElementById("sheetOutputInfo").innerHTML = 
		document.getElementById("sheetOutputInfo").innerHTML + "<br>+ Hoja de calculo agregada: "+sheetName.value;
}