package server;

public class ServerRunner {

    public static void main(String[] args) throws InterruptedException {
        final CryptoCurrencyPriceServer server = new CryptoCurrencyPriceServer();
        try {
            server.start();
        } catch (Exception ignored) {
        }

        server.blockUntilShutdown();
    }

}
