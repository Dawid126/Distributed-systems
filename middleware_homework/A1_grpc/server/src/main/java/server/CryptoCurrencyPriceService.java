package server;

import gen.generated.CryptoCurrencyPriceInfo;
import gen.generated.CryptoCurrencyPriceRequest;
import gen.generated.CryptoCurrencyPriceServiceGrpc.CryptoCurrencyPriceServiceImplBase;
import io.grpc.stub.StreamObserver;
import server.cryptocurrencydatamock.CryptoCurrencyStocksAPI;
import server.subscriber.CryptoCurrencyPriceSubscriber;

public class CryptoCurrencyPriceService extends CryptoCurrencyPriceServiceImplBase {

    private final CryptoCurrencyStocksAPI stocksAPI;

    public CryptoCurrencyPriceService(CryptoCurrencyStocksAPI stocksAPI) {
        this.stocksAPI = stocksAPI;
    }

    @Override
    public void subscribeOnCryptoCurrencyPriceInfo(CryptoCurrencyPriceRequest request,
                                                   StreamObserver<CryptoCurrencyPriceInfo> responseObserver) {

        final CryptoCurrencyPriceSubscriber subscriber =
                new CryptoCurrencyPriceSubscriber(
                        request.getCryptoName(),
                        request.getClientID(),
                        0,
                        responseObserver);

        stocksAPI.subscribeOnCryptoCurrencyData(subscriber);

    }
}
