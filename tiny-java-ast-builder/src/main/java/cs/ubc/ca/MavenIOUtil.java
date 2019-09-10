package cs.ubc.ca;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MavenIOUtil {

    public String getPath(String resource) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path filepath = Paths.get(classLoader.getResource(resource).toURI());
            return filepath.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Could not load resource", e);
        }
    }
}
