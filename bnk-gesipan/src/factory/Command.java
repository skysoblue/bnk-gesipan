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
		this.start = (Integer.parseInt(pageNo)-1)*PAGESIZE+1;
		this.end = Integer.parseInt(pageNo)*PAGESIZE;
		this.view = "/view/"+directory+"/"+action+".jsp";
	}
	
	public int getPageNo() {
		return pageNo;
	}


	public int getStart() {
		return start;
	}


	public int getEnd() {
		return end;
	}


	public String getKeyword() {
		return keyword;
	}


	public String getKeyField() {
		return keyField;
	}


	public String getDirectory() {
		return directory;
	}


	public String getAction() {
		return action;
	}


	public String getView() {
		return view;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
