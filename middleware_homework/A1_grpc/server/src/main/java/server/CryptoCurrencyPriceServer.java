package server;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import server.cryptocurrencydatamock.CryptoCurrencyStocksAPI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static properties.Config.PORT;

public class CryptoCurrencyPriceServer {

    private Server server;

    public void start() throws IOException {
        CryptoCurrencyStocksAPI stocksAPI = new CryptoCurrencyStocksAPI();
        server = createServer(stocksAPI).start();
        new Thread(stocksAPI::generateData).start();

        System.out.println("Server has started and is listening on " + PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            CryptoCurrencyPriceServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private Server createServer(CryptoCurrencyStocksAPI stocksAPI) {
        return NettyServerBuilder.forPort(PORT)
                .permitKeepAliveWithoutCalls(true)
                .permitKeepAliveTime(5, TimeUnit.SECONDS)
                .keepAliveTime(5, TimeUnit.SECONDS)
                .keepAliveTimeout(2, TimeUnit.SECONDS)
                .addService(new CryptoCurrencyPriceService(stocksAPI))
                .build();
    }
}
