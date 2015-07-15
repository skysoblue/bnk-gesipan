package factory;

public class CommandFactory {
	
	public  Command createCommand(
			String directory,
			String action,
			String pageNo,
			String keyField,
			String keyword){
		
		return new Command(directory, action,pageNo, keyField, keyword);
	}
}
