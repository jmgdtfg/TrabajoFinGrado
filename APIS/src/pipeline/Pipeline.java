package pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import inputComponents.InputComponent;
import outputComponents.OutputComponent;
import processingComponents.ProcessComponent;


//Clase que define la arquitetura del flujo de datos
public class Pipeline {
	//Variables privadas de la clase Pipeline
	private List<InputComponent> input_ = new ArrayList<InputComponent>();		//Obligatorio
	private List<ProcessComponent> process_ = new ArrayList<ProcessComponent>();//Opcional
	private List<OutputComponent> output_ = new ArrayList<OutputComponent>(); 	//Obligatorio

	//Función para añadir elementos de entrada
	public void addInput(Object... input) {
		//Se pueden usar varios inputs siempre que sean del mismo tipo
		//FIXME: Podrían ser de tipos distintos para los procesos genéricos (?)
		String type = input[0].getClass().getName();
		for (Object in : input){
			if (in.getClass().getName().equals(type) && in instanceof InputComponent){
				InputComponent i = InputComponent.class.cast(in);
				input_.add(i);
			}
		}
	}
	//Función para añadir componentes de procesamiento
	public void addProcess(Object... process) {
		//Puede haber varios procesos para la misma información
		for (Object pr : process){
			if (pr instanceof ProcessComponent){
				ProcessComponent p = ProcessComponent.class.cast(pr);
				process_.add(p);
			}
		}
	}
	//Función para añadir elementos de salida
	public void addOutput(Object... output) {
		//Puede haber varias salidas de información
		for (Object out : output){
			if (out instanceof OutputComponent){
				OutputComponent o = OutputComponent.class.cast(out);
				output_.add(o);
			}
		}
	}
	//Función que ejecuta el flujo de datos.
	@SuppressWarnings("unchecked")
	public void execute(Map<String, String>... configurations) {
		/*
		 * Se define la lista de configuraciones
		 * Esta lista se irá vaciando de forma ordenada (FIFO)
		 * Cada listConfiguration.get(0) [primer elemento]
		 * Irá acompañado de listConfiguration.remove(0) [elimina primer elemento]
		 * */
		List<Map<String,String>> listConfigurations = new ArrayList<Map<String,String>>();
		for (Map<String, String> config : configurations){
			listConfigurations.add(config);
		}
		//-----------------------------
		//FLUJO DE DATOS: ENTRADA(S) -> PROCESAMIENTO(S) -> SALIDA(S)

		//Declaración de List<Document> en los que se unificará la información.
		List<Document> inputData = new ArrayList<Document>();
		List<Document> processData = new ArrayList<Document>();

		//1) ENTRADA: Se unifican los inputs en uno solo:

		for (InputComponent input : input_){
			List<Document> newList = input.execute(listConfigurations.get(0));
			listConfigurations.remove(0);
			for (Document doc : newList){
				inputData.add(doc);
			}
		}

		//2) PROCESAMIENTO:  Se realiza todo el procesamiento de la información
		//Caso en el que NO hay elementos de procesamiento
		if (process_ == null){}
		else{
			//Caso en el que SI hay elementos de procesamiento
			int flag = 0;

			for (ProcessComponent process : process_){
				if (flag == 0){
					processData = process.execute(inputData, listConfigurations.get(0));
					listConfigurations.remove(0);
					flag = 1;
				}else{
					processData = process.execute(processData, listConfigurations.get(0));
					listConfigurations.remove(0);
				}
			}
		}

		//3) SALIDA: Se envía la información a las distintas salidas
		//Caso en el que NO hay elementos de procesamiento
		if ( process_ == null){
			for (OutputComponent output: output_){
				output.execute(inputData, listConfigurations.get(0));
				listConfigurations.remove(0);
			}
		}else{
			//Caso en el que SI hay elementos de procesamiento
			for (OutputComponent output: output_){
				output.execute(processData, listConfigurations.get(0));
				listConfigurations.remove(0);
			}
		}
	}
}//Fin clase Pipeline
