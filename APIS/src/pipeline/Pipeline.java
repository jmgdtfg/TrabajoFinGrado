package pipeline;

import java.util.ArrayList;
import java.util.List;

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

	//Getters y setters necesarios para poder serializar y deserializar
	public List<InputComponent> getInput_() {
		return input_;
	}
	public void setInput_(List<InputComponent> input_) {
		this.input_ = input_;
	}
	public List<ProcessComponent> getProcess_() {
		return process_;
	}
	public void setProcess_(List<ProcessComponent> process_) {
		this.process_ = process_;
	}
	public List<OutputComponent> getOutput_() {
		return output_;
	}
	public void setOutput_(List<OutputComponent> output_) {
		this.output_ = output_;
	}


	/**
	 * Función que añade un componente de entrada
	 * @param input
	 */
	public void addInput(InputComponent... input) {

		for (InputComponent in : input){
			InputComponent i = InputComponent.class.cast(in);
			input_.add(i);
		}
	}
	/**
	 * Función que añade un componente de procesamiento
	 * @param process
	 */
	public void addProcess(ProcessComponent... process) {
		//Puede haber varios procesos para la misma información
		for (ProcessComponent pr : process){
			ProcessComponent p = ProcessComponent.class.cast(pr);
			process_.add(p);
		}
	}
	/**
	 * Función que añade un componente de salida
	 * @param output
	 */
	public void addOutput(OutputComponent... output) {
		//Puede haber varias salidas de información
		for (OutputComponent out : output){
			OutputComponent o = OutputComponent.class.cast(out);
			output_.add(o);
		}
	}

	/**
	 * Función que ejecuta el flujo de datos
	 */
	public Response execute() {
		//Devolverá los resultados al servicio web
		Response response = new Response();
		StringBuilder logs = new StringBuilder();
		
		//FLUJO DE DATOS: ENTRADA(S) -> PROCESAMIENTO(S) -> SALIDA(S)

		//Declaración de List<Document> en los que se unificará la información.
		List<Document> inputData = new ArrayList<Document>();
		List<Document> processData = new ArrayList<Document>();

		//1) ENTRADA: Se unifican los inputs en uno solo:

		for (InputComponent input : input_){
			List<Document> newList = input.execute();
			for (Document doc : newList){
				inputData.add(doc);
			}
		}

		//2) PROCESAMIENTO:  Se realiza todo el procesamiento de la información
		//Caso en el que NO hay elementos de procesamiento
		if (process_ == null){
			logs.append("No hay elementos de procesamiento\n");
		}
		else{
			//Caso en el que SI hay elementos de procesamiento
			int flag = 0;

			//***COMPROBACIÓN DE TIPOS DE DATOS VALIDOS***
			for (ProcessComponent process : process_){
				for (Document doc : inputData){
					if (process.isCompatibleWith(doc)){
						logs.append(doc.getClass().getName());
						logs.append(" es COMPATIBLE con ");
						logs.append(process.getClass().getName());
						logs.append("\n");

					}
					else{
						//System.out.println("ERROR");
						logs.append(doc.getClass().getName());
						logs.append(" es INCOMPATIBLE con ");
						logs.append(process.getClass().getName());
						logs.append("\n");
						response.setResult_("ERROR: Se ha producido un error en el procesamiento");
						return response;
					}
				}
			}
			//*********************************************
			for (ProcessComponent process : process_){
				if (flag == 0){
					processData = process.execute(inputData, process.getConfiguration());
					flag = 1;
				}else{
					processData = process.execute(processData, process.getConfiguration());
				}
			}
		}

		//3) SALIDA: Se envía la información a las distintas salidas
		//Caso en el que NO hay elementos de procesamiento
		if ( process_ == null){
			for (OutputComponent output: output_){
				output.execute(inputData, output.getConfiguration());
			}
		}else{
			//Caso en el que SI hay elementos de procesamiento
			for (OutputComponent output: output_){
				output.execute(processData, output.getConfiguration());
			}
		}
		logs.append("El procesamiento se realizó con ÉXITO\n");
		response.setLogs_(logs.toString());
		response.setResult_("Status OK: Procesamiento realizado con éxito");
		return response;
	}
}//Fin clase Pipeline
