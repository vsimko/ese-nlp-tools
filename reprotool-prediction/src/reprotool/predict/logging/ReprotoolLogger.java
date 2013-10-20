package reprotool.predict.logging;

import org.osgi.service.log.LogService;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(provide=ReprotoolLogger.class)
final public class ReprotoolLogger {

	private LogService logService;
	
	@Reference(dynamic=false, multiple=false, optional=false)
	final public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	final public void error(CharSequence cs) {
		logService.log(LogService.LOG_ERROR, cs.toString());
	}

	final public void warn(CharSequence cs) {
		logService.log(LogService.LOG_WARNING, cs.toString());
	}
	
	final public void info(CharSequence cs) {
		logService.log(LogService.LOG_INFO, cs.toString());
	}
	
	final public void debug(CharSequence cs) {
		logService.log(LogService.LOG_DEBUG, cs.toString());
	}
}
