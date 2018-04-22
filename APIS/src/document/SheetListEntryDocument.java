package document;

import com.google.gdata.data.spreadsheet.ListEntry;

public class SheetListEntryDocument extends Document{

	@Override
	public String getDataAsString() {
		ListEntry list = (ListEntry) getRawData();
		String data = "<Columna,Valor> | "+list.getPlainTextContent()+"\n";
		
		return data;
	}

}
