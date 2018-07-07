package pipeline;
/**
 * 
 * @author jmgd_
 *
 */
//Clase que almacena una respuesta
public class Response {

	private String logs_;
	private String result_;
	
	public Response(){
		logs_="";
		result_="";
	}
	//Getters & setters
	public String getLogs_() {
		return logs_;
	}
	public void setLogs_(String logs_) {
		this.logs_ = logs_;
	}
	public String getResult_() {
		return result_;
	}
	public void setResult_(String result_) {
		this.result_ = result_;
	}
}
