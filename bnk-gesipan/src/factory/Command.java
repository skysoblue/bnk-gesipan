package factory;

public class Command implements Orderable{
	public final int PAGESIZE = 10;
	private int pageNo, start, end;
	private String directory, action, keyword, keyField, view;
	
	
	public Command(
			String directory, 
			String action, 
			String pageNo, 
			String keyField,
			String keyword) {
		this.directory = directory;
		this.action = action;
		this.pageNo = Integer.parseInt(pageNo);
		this.keyField = keyField;
		this.keyword = keyword;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int pageNo) {
		this.start = (pageNo-1)*PAGESIZE+1;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int pageNo) {
		this.end = pageNo*PAGESIZE;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
