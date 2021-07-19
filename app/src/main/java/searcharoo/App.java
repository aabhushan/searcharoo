package searcharoo;

import searcharoo.data.JSONDataLoader;
import searcharoo.ui.CommandInterface;
import searcharoo.ui.CommandInterfaceSearchProcessor;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

public class App {
    public final static InputStream APP_INPUT_STREAM = System.in;
    public final static PrintStream APP_PRINT_STREAM = System.out;
    public static final String DATA_FOLDER_PATH = "%s/data".formatted(Paths.get(".").toAbsolutePath().normalize().toString());

    public static void main(String[] args) {
        File folder = new File(DATA_FOLDER_PATH);
        JSONDataLoader loader = new JSONDataLoader(folder);
        loader.loadFilesFromFolder();
        CommandInterfaceSearchProcessor searchProcessor = new CommandInterfaceSearchProcessor(APP_PRINT_STREAM);

        CommandInterface commandInterface = new CommandInterface(
                APP_INPUT_STREAM,
                APP_PRINT_STREAM,
                searchProcessor
        );
        commandInterface.startCommandLineInterface();
    }
}
