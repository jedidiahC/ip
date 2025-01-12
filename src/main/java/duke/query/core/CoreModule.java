package duke.query.core;

import java.util.HashMap;

import duke.exception.DukeException;
import duke.query.QueryHandler;
import duke.query.QueryModule;

/**
 * The class CoreModule represents core query features.
 */
public class CoreModule extends QueryModule {
    public static final String GREET_QUERY_TYPE = "hi";
    public static final String HELP_QUERY_TYPE = "help";
    public static final String BYE_QUERY_TYPE = "bye";
    private final Help help;

    public CoreModule(Help help) {
        this.help = help;
    }

    /**
     * @throws DukeException
     */
    @Override
    public void init() throws DukeException {
        // No initialization required.
    }

    /**
     * @param commandToQueryHandler
     */
    @Override
    public void installQueryHandlers(HashMap<String, QueryHandler> commandToQueryHandler) {
        commandToQueryHandler.put(GREET_QUERY_TYPE, new GreetQueryHandler());
        commandToQueryHandler.put(HELP_QUERY_TYPE, new HelpQueryHandler(help));
    }
}
