package api;

public class SplunkResponse {

	private String text;
	private int code;
	public SplunkResponse(String text, int code) {
		super();
		this.text = text;
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
