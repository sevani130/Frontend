package intensive_61.Task2;

import java.lang.reflect.Field;

/**
 * The InjectionService class provides methods for injecting dependencies into objects.
 */
public class InjectionService {

    /**
     * Injects dependencies into the specified object.
     *
     * @param object the object into which dependencies should be injected.
     * @throws Exception if an error occurs during dependency injection.
     */
    public static void injectDependencies(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IntensiveComponent_NikitaKudryashov.class)) {
                field.setAccessible(true);
                Object dependency = DependencyFactory.createInstance(field.getType());
                field.set(object, dependency);
            }
        }
    }
}

