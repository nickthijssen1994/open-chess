package nl.nickthijssen.restshared.messages;

public abstract class BaseResult {

	private boolean success;
	private String message;

	public BaseResult() {
	}

	public BaseResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
