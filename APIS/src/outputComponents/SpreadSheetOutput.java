package outputComponents;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gdata.util.ServiceException;

import document.Document;
import google.SpreadSheetManager;

public class SpreadSheetOutput implements OutputComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	} 

	@Override
	public void execute(List<Document> data, Map<String, String> configuration) {
		int initRow = Integer.valueOf(configuration.get("initRow")).intValue();
		int maxColumn = Integer.valueOf(configuration.get("maxColumn")).intValue();
		try {
		
			SpreadSheetManager sm = new SpreadSheetManager();
			//Si no existe la hoja de cálculo la crea. Si ya existe se sobreescribirá.
			sm.createNewSheet(configuration.get("sheetName"));
			int i = 1;
			int j = 0;
			SpreadSheetManager sm2 = new SpreadSheetManager();
			for (Document document : data) {
	
				sm2.setDataInSpecificCell(
						configuration.get("sheetName"),
						initRow+j,
						i,
						document.getDataAsString());
				
				//Control fila-columna
				if (i>=maxColumn){
					i = 1;
					j++;
				}else{
					i++;
				}
				
			}
			
			
		} catch (IOException | ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
