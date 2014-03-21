package reprotool.predict.mloaders

import aQute.bnd.annotation.component.Activate
import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Deactivate
import java.util.Hashtable
import java.util.List
import opennlp.model.MaxentModel
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceRegistration
import reprotool.dmodel.api.classifiers.MaxentClassifier

@Component
class ElicitationPhaseModelLoader {

	val List<ServiceRegistration> registeredServices = newArrayList

	@Activate def void activate(BundleContext context) {
		registeredServices += context.loadAndRegisterModel("linktype")
		registeredServices += context.loadAndRegisterModel("roleInLink")
		registeredServices += context.loadAndRegisterModel("relcl")
	}
	
	@Deactivate def void deactivate(BundleContext context) {
		registeredServices.forEach[unregister]
	}
	
	// helper method
	private def static loadAndRegisterModel(BundleContext context, String modelName) {
		context.registerService(
			MaxentModel.name,
			MaxentClassifier.loadMaxentModel(
				context.bundle.getResource('''reprotool/predict/models/dm/«modelName».maxent.gz''').openStream
			),
			new Hashtable(#{"model" -> modelName})
		)
	}
}