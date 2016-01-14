package pegasus;

/**
 * Created by annafuller on 1/6/16.
 */
public interface CYOAObject {
    String[] getValidCommands();

    String processCommand(String command);

    String getName();

    String getType();
}
