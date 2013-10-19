package reprotool.dmodel.api;

public interface ITool {
	String getUsage();
	void execute(String[] args);
}
