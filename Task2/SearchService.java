package intensive_61.Task2;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * The SearchService class provides methods for finding annotated classes within a specified package.
 */
public class SearchService {

    /**
     * Finds annotated classes within the specified package.
     *
     * @param packageName the name of the package to search for annotated classes.
     * @param annotation  the annotation to search for.
     * @return a list of annotated classes found within the specified package.
     * @throws Exception if an error occurs during the search process.
     */
    public static List<Class<?>> findAnnotatedClasses(String packageName, Class<? extends java.lang.annotation.Annotation> annotation) throws Exception {
        List<Class<?>> annotatedClasses = new ArrayList<>();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String filePath = resource.getFile();
            File directory = new File(filePath);
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            annotatedClasses.addAll(findAnnotatedClasses(packageName + "." + file.getName(), annotation));
                        } else if (file.getName().endsWith(".class")) {
                            String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                            Class<?> clazz = Class.forName(className);
                            if (clazz.isAnnotationPresent(annotation)) {
                                annotatedClasses.add(clazz);
                            }
                        }
                    }
                }
            }
        }
        return annotatedClasses;
    }
}
