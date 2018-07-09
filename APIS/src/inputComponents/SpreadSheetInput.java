package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.util.ServiceException;

import document.Document;
import document.SheetListEntryDocument;
import google.SpreadSheetManager;

public class SpreadSheetInput implements InputComponent{
	private Document document_ = new SheetListEntryDocument();
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
	public Document getDocument() {
		return document_;
	}

	//Función que devuelve los valores de una hoja de cálculo
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();

		List<Document> listDocument = new ArrayList<Document>();
		try {
			SpreadSheetManager sm = new SpreadSheetManager();


			for (ListEntry data : sm.readSheet(configuration.get("sheetName"))) {
				SheetListEntryDocument document = new SheetListEntryDocument();
				document.setRawData(data);
				listDocument.add(document);
			}

		} catch (IOException | ServiceException e) {
			e.printStackTrace();
		}

		return listDocument;



	}

}
