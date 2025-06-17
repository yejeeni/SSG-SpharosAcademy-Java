package test.multicast;

public class FileData implements Content{
	private String id;
	private String fileName;
	private String[] fileContent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(String[] fileContent) {
		this.fileContent = fileContent;
	}
	
	
	
}
