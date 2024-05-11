package intensive_61.Task2;
import java.lang.reflect.Constructor;

/**
 * Implementation of {@link DependencyFactory} interface to create instances of classes using
 * reflection.
 */
public class DependencyFactory {

    /**
     * Creates an instance of the specified class using reflection.
     *
     * @param clazz the class of which an instance is to be created.
     * @param <T>   the type of the class.
     * @return an instance of the specified class.
     * @throws RuntimeException if an error occurs while creating the instance.
     */
    public static <T> T createInstance(Class<T> clazz) {
        try {
            // Get all declared constructors
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // Select a constructor (for simplicity, let's choose the first one)
            Constructor<?> constructor = constructors[0];
            constructor.setAccessible(true);
            // Create instance using the selected constructor
            return clazz.cast(constructor.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error creating instance of class " + clazz.getName(), e);
        }
    }
}
