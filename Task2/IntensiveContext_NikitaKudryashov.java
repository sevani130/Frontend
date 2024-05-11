
package intensive_61.Task2;
import java.util.List;
/**
 * The IntensiveContext_NikitaKudryashov class represents a context for managing intensive components.
 */
public class IntensiveContext_NikitaKudryashov {
    private final String packageName;

    /**
     * Constructs a new IntensiveContext_NikitaKudryashov with the specified package name.
     *
     * @param packageName the package name to search for intensive components.
     */
    public IntensiveContext_NikitaKudryashov(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets an object of the specified type from the intensive context.
     *
     * @param type the type of object to retrieve.
     * @param <T>  the generic type of the object.
     * @return an object of the specified type from the intensive context.
     * @throws Exception if an error occurs during object retrieval.
     */
    public <T> T getObject(Class<T> type) throws Exception {
        List<Class<?>> annotatedClasses = SearchService.findAnnotatedClasses(packageName, IntensiveComponent_NikitaKudryashov.class);
        List<Class<?>> filteredClasses = annotatedClasses.stream()
                .filter(clazz -> type.isAssignableFrom(clazz) && clazz != type).toList();

        if (filteredClasses.size() == 0) {
            throw new Exception("No implementation found for " + type.getName());
        } else if (filteredClasses.size() > 1) {
            throw new Exception("Multiple implementations found for " + type.getName());
        }

        Class<?> implementationClass = filteredClasses.get(0);
        Object implementation = DependencyFactory.createInstance(implementationClass);
        InjectionService.injectDependencies(implementation);
        return type.cast(implementation);
    }
}