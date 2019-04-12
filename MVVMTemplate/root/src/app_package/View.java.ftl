package ${packageName};

import com.htstudio.core.mvvm.BaseView;
import ${applicationPackage}.R;

public class ${className}View extends BaseView<${className}Contract.Controller>
implements ${className}Contract.View {
	
	@Override
	public int getLayoutId(){
		return R.layout.${layoutName};
	}
}

