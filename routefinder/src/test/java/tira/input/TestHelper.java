package tira.input;

import java.net.URL;

/**
 * Utilities for testing.
 */
public class TestHelper {
    
    public static final TestHelper INSTANCE = new TestHelper();
    
    protected TestHelper() {
        // protected to encourage using eagerly initialized instance,
        // but allowing sub-classing should there be need to 
    }
    
    /**
     * Gets the file path to a classpath resource.
     * 
     * @param resourcePath  resource path.
     * 
     * @return  file path to the resource.
     */
    public String getFilePathForClasspathResource(String resourcePath) {
        URL resourceUrl = this.getClass().getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found or not accessible: " + resourcePath);
        }
        return resourceUrl.getFile();
    }

}
