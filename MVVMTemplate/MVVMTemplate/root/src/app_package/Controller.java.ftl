package ${packageName};

import android.support.v4.app.FragmentActivity;
import com.htstudio.core.mvvm.BaseController;

public class ${className}Controller extends BaseController<${className}Contract.View, ${className}Contract.Model> 
  implements ${className}Contract.Controller {

	public ${className}Controller(FragmentActivity containerActivity, int containerFrame) {
		super(containerActivity, containerFrame);
	}

	@Override
	public ${className}Contract.View onCreateView() {
		return new ${className}View();
	}

	@Override
	public ${className}Contract.Model onCreateModel() {
		return new ${className}Model(this);
	}
}
