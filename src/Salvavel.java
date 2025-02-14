import java.io.IOException;
import java.io.FileWriter;

public interface Salvavel {
    public abstract void saveArq(FileWriter fw) throws IOException;
}
