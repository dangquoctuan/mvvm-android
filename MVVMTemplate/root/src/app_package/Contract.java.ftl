package ${packageName};

import com.htstudio.core.mvvm.interf.IController;
import com.htstudio.core.mvvm.interf.IModel;
import com.htstudio.core.mvvm.interf.IView;

public interface ${className}Contract {

    interface Controller extends IController<View, Model>{
	
    }
	
    interface View extends IView<Controller>{
      
    }

    interface Model extends IModel<Controller>{
       
    }
}



