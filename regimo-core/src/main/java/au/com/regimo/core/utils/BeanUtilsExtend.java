package au.com.regimo.core.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsExtend {

	public static void copyPropertiesWithoutNull(Object source, Object target, String... ignoreFields) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());

			// Iterate over all the attributes
			for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {

				String name = descriptor.getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                
                if(ignoreFields!=null && Arrays.asList(ignoreFields).contains(name)){
                	continue;
                }
				
				// Only copy readable attributes
				if (descriptor.getReadMethod() != null) {
					Object newValue = descriptor.getReadMethod().invoke(source);

					// Only copy value values where the source value is not null
					if (newValue != null) {
						BeanUtils.copyProperty(target, name, newValue);

					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
