package inputComponents;

import java.io.IOException;
import java.util.Map;
import com.google.gdata.util.ServiceException;

import google.SpreadSheetManager;

public class SpreadSheetInput implements InputComponent{
	//Funci�n que devuelve los valores de una hoja de c�lculo
	@Override
	public Object execute(Map<String, String> configuration) {

		try {
			SpreadSheetManager sm = new SpreadSheetManager();
			
			//Devuelve el tipo List<ListEntry>
			return sm.readSheet(configuration.get("sheetName"));
			
		} catch (IOException | ServiceException e) {
			// ***
			e.printStackTrace();
			return null;
		}
		
	}

}
