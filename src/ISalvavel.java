import java.io.IOException;
import java.io.FileWriter;

public interface ISalvavel {
    public abstract void saveArq(FileWriter fw) throws IOException;
}
