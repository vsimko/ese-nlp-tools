package reprotool.dmodel.tools.phases.elicit;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import com.google.common.collect.Maps;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import opennlp.model.MaxentModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import reprotool.dmodel.api.classifiers.MaxentClassifier;

@Component
@SuppressWarnings("all")
public class ElicitationPhaseModelLoader {
  private final List<ServiceRegistration> registeredServices = new Function0<List<ServiceRegistration>>() {
    public List<ServiceRegistration> apply() {
      ArrayList<ServiceRegistration> _newArrayList = CollectionLiterals.<ServiceRegistration>newArrayList();
      return _newArrayList;
    }
  }.apply();
  
  @Activate
  public void activate(final BundleContext context) {
    ServiceRegistration _loadAndRegisterModel = ElicitationPhaseModelLoader.loadAndRegisterModel(context, "linktype");
    this.registeredServices.add(_loadAndRegisterModel);
    ServiceRegistration _loadAndRegisterModel_1 = ElicitationPhaseModelLoader.loadAndRegisterModel(context, "roleinlink");
    this.registeredServices.add(_loadAndRegisterModel_1);
    ServiceRegistration _loadAndRegisterModel_2 = ElicitationPhaseModelLoader.loadAndRegisterModel(context, "relcl");
    this.registeredServices.add(_loadAndRegisterModel_2);
  }
  
  @Deactivate
  public void deactivate(final BundleContext context) {
    final Procedure1<ServiceRegistration> _function = new Procedure1<ServiceRegistration>() {
        public void apply(final ServiceRegistration it) {
          it.unregister();
        }
      };
    IterableExtensions.<ServiceRegistration>forEach(this.registeredServices, _function);
  }
  
  private static ServiceRegistration loadAndRegisterModel(final BundleContext context, final String modelName) {
    try {
      String _name = MaxentModel.class.getName();
      Bundle _bundle = context.getBundle();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("reprotool/predict/models/dm/");
      _builder.append(modelName, "");
      _builder.append(".maxent.gz");
      URL _resource = _bundle.getResource(_builder.toString());
      InputStream _openStream = _resource.openStream();
      MaxentModel _loadMaxentModel = MaxentClassifier.loadMaxentModel(_openStream);
      Map<String,String> _xsetliteral = null;
      Map<String,String> _tempMap = Maps.<String, String>newHashMap();
      _tempMap.put("model", modelName);
      _xsetliteral = Collections.<String, String>unmodifiableMap(_tempMap);
      Hashtable<String,String> _hashtable = new Hashtable<String,String>(_xsetliteral);
      ServiceRegistration _registerService = context.registerService(_name, _loadMaxentModel, _hashtable);
      return _registerService;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
