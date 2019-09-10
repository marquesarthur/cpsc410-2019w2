package cs.ubc.ca.ast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JavaFile {

    private final String sourcePath;

    private String source;

    public JavaFile(String sourcePath) {
        this.sourcePath = sourcePath;

        this.loadSource();
    }

    private void loadSource() {
        try {
            File file = new File(this.sourcePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            this.source = new String(data, "UTF-8");

        } catch (IOException e) {
            String message = String.format("Could not load file:", this.sourcePath);
            throw new RuntimeException(message, e);
        }
    }

    public String getSource() {
        return this.source;
    }
}
