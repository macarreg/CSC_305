import java.io.File;

public interface Strategy {
    void load(File file);
    void findStatistics();
}
