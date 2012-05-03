package au.com.regimo.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	@SuppressWarnings({ "rawtypes" })
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Class<T> getSuperClassGenricType(final Class clazz, final int index) {

		ParameterizedType paramType = null;
		Type genType = clazz.getGenericSuperclass();

		if (genType instanceof ParameterizedType) {
			paramType = (ParameterizedType) genType;
		}
		else{
			paramType = (ParameterizedType) clazz.getSuperclass().getGenericSuperclass();
		}

		Type[] params = paramType.getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return (Class) Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return (Class) Object.class;
		}

		return (Class) params[index];
	}

}
