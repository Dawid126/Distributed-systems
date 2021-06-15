package server;

import gen.generated.CryptoCurrencyPriceInfo;
import gen.generated.CryptoCurrencyPriceRequest;
import gen.generated.CryptoCurrencyPriceServiceGrpc;
import gen.generated.CryptoCurrencyPriceServiceGrpc.CryptoCurrencyPriceServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Client {
    private static final String host = "localhost";
    private static final int port = 40004;

    public static void main(String[] args) {
        ManagedChannel channel;
        CryptoCurrencyPriceServiceBlockingStub blockingStub;
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        System.out.println("Client started");

        blockingStub = CryptoCurrencyPriceServiceGrpc.newBlockingStub(channel);

        String cryptoName = chooseCryptoName();

        CryptoCurrencyPriceRequest request = CryptoCurrencyPriceRequest
                .newBuilder()
                .setCryptoName(cryptoName)
                .setClientID("LOL")
                .build();

        Iterator<CryptoCurrencyPriceInfo> cryptoCurrencyPriceInfoIterator;

        while (true) {
            try {
                cryptoCurrencyPriceInfoIterator = blockingStub.subscribeOnCryptoCurrencyPriceInfo(request);

                while (cryptoCurrencyPriceInfoIterator.hasNext()) {
                    CryptoCurrencyPriceInfo response = cryptoCurrencyPriceInfoIterator.next();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder
                            .append("Crypto: ")
                            .append(response.getCryptoName())
                            .append(" -- Price volatility: ")
                            .append(response.getPriceVolatility().toString())
                            .append(" -- Recommendation: ")
                            .append(response.getRecommendation().toString())
                            .append("\n");
                    for(CryptoCurrencyPriceInfo.PriceOnStockExchange priceOnStockExchange : response.getPriceOnStockExchangeList()) {
                        stringBuilder
                                .append(priceOnStockExchange.getStockExchangeName())
                                .append(" - ")
                                .append(priceOnStockExchange.getPrice())
                                .append("\n");
                    }
                    stringBuilder.append("\n------------------------\n");
                    System.out.println(stringBuilder.toString());
                }

            } catch (Exception e) {
                System.out.println("Connection issues....");
                try {
                    sleep(1000);
                } catch (Exception ignore) {

                }
            }
        }
    }

    private static String chooseCryptoName() {
        System.out.println("Choose name of the city that you want to subscribe for air quality info: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
