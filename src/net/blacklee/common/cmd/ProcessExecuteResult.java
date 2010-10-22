package net.blacklee.common.cmd;


/**
 * @author lihr
 * @created Oct 18, 2010 3:34:56 PM
 */
public class ProcessExecuteResult {
	public ProcessExecuteResult() {
		super();
	}
	public ProcessExecuteResult(int value, int exitValue) {
		super();
		this.value = value;
		this.exitValue = exitValue;
	}
	public ProcessExecuteResult(int value, int exitValue,
			String standardOutput, String standardErrorOutput) {
		super();
		this.value = value;
		this.exitValue = exitValue;
		this.standardOutput = standardOutput;
		this.standardErrorOutput = standardErrorOutput;
	}
	private int value;
	private int exitValue;
	private String standardOutput;
	private String standardErrorOutput;
	public String getStandardOutput() {
		return standardOutput;
	}
	public void setStandardOutput(String standardOutput) {
		this.standardOutput = standardOutput;
	}
	public String getStandardErrorOutput() {
		return standardErrorOutput;
	}
	public void setStandardErrorOutput(String standardErrorOutput) {
		this.standardErrorOutput = standardErrorOutput;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getExitValue() {
		return exitValue;
	}
	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}
}
