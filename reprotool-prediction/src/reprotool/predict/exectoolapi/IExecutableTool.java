package reprotool.predict.exectoolapi;

public interface IExecutableTool {
	String getUsage();
	void execute(String[] args);
}
