package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String path = System.getProperty("user.home") 
    + System.getProperty("file.separator") 
    + "output.txt";
    private File file;

    public Controller(){
        this.file = new File(path);
    }

    public File getCurrentFile(){
        return this.file;
    }

    public String getPath(){
        return this.file.getPath();
    }

    public void setCurrentFile(final File newFile){
        final File parentFile = newFile.getParentFile();
        if(parentFile.exists()) {
            this.file = newFile;
        }
        else {
            throw new IllegalArgumentException("No existing folder!");
        }
    }

    public void saveString(final String newsString) throws IOException{
        try(final PrintStream pS = new PrintStream(this.file, StandardCharsets.UTF_8)){
            pS.append(newsString);
        }
    }
}
