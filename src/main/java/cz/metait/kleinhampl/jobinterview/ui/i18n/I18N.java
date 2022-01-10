package cz.metait.kleinhampl.jobinterview.ui.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;


public class I18N {

    private final ResourceBundle bundle;
    private final String prefix;


    public I18N(Class<?> clazz){
        var packagePath = clazz.getPackageName().replace(".", "/") + '/';
        bundle = ResourceBundle.getBundle(packagePath + "i18n");
        prefix = clazz.getSimpleName() + ".";
    }

    public String getString(String key){
        return bundle.getString(prefix + key);
    }

}
