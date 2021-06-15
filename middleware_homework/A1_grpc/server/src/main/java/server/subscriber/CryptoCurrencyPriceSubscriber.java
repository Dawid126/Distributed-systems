package server.subscriber;

import gen.generated.CryptoCurrencyPriceInfo;
import io.grpc.stub.StreamObserver;

public class CryptoCurrencyPriceSubscriber {

    private final String cryptoName;
    private final String clientID;
    private int errors;
    private final StreamObserver<CryptoCurrencyPriceInfo> responseObserver;

    public CryptoCurrencyPriceSubscriber(String cryptoName,
                                         String clientID,
                                         int errors,
                                         StreamObserver<CryptoCurrencyPriceInfo> responseObserver) {
        this.cryptoName = cryptoName;
        this.clientID = clientID;
        this.errors = errors;
        this.responseObserver = responseObserver;
    }

    public boolean hasLostConnection() {
        return this.errors >= 30;
    }

    @Override
    public String toString() {
        return "Crypto name: " + cryptoName + ", clientID: " + clientID;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public String getClientID() {
        return clientID;
    }

    public int getErrors() {
        return errors;
    }

    public StreamObserver<CryptoCurrencyPriceInfo> getResponseObserver() {
        return responseObserver;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

}
