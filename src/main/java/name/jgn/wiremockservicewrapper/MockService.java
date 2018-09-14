package name.jgn.wiremockservicewrapper;

import java.time.Duration;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.boris.winrun4j.AbstractService;

/**
 * An adapter that wraps a WireMock server in a WinRun4J service.
 */
public class MockService extends AbstractService {

    private static final int DEFAULT_PORT = 9090;
    private static final Duration SHUTDOWN_POLL_TIME = Duration.ofSeconds(1);

    private WireMockServer wireMockServer;

    @Override
    public int serviceMain(String[] args) {

        wireMockServer = new WireMockServer(portFrom(args));
        wireMockServer.start();

        try {
            awaitShutdown();
        } finally {
            wireMockServer.stop();
        }
        return 0;
    }

    private static int portFrom(String[] args) {
        if (args.length == 0) return DEFAULT_PORT;

        try {
            return Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            return DEFAULT_PORT;
        }
    }

    private void awaitShutdown() {
        while (!isShutdown()) {
            try {
                Thread.sleep(SHUTDOWN_POLL_TIME.toMillis());
            } catch (InterruptedException e) {
                wireMockServer.stop();
                Thread.currentThread().interrupt();
            }
        }
    }
}
