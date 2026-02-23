package se.replyto;

import io.prometheus.metrics.exporter.httpserver.HTTPServer;
import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;

import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws InterruptedException, IOException {
        JvmMetrics.builder().register();

        HTTPServer server = HTTPServer.builder()
                .port(9400)
                .buildAndStart();

        System.out.println("HTTPServer listening on http://localhost:" + server.getPort() + "/metrics");

        while (true) {
            System.out.println("Log entry generated at: " + java.time.LocalDateTime.now());

            Thread.sleep(5000);
        }
    }
}
