package factory;

public class CommandFactory {
	Command command;
	String directory, action, keyword, keyField, view;
	
	public  Command createCommand(
			String directory,
			String action, 
			String keyword,
			String keyField,
			String view){
		
		return new Command(directory, action, keyword,keyField,view);
	}
}
