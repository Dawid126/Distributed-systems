package server.cryptocurrencydatamock;

import gen.generated.CryptoCurrencyPriceInfo.CryptoPriceVolatility;
import gen.generated.CryptoCurrencyPriceInfo.PriceOnStockExchange;
import gen.generated.CryptoCurrencyPriceInfo.Recommendation;
import gen.generated.CryptoCurrencyPriceInfo;
import server.subscriber.CryptoCurrencyPriceSubscriber;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toList;

public class CryptoCurrencyStocksAPI {


    List<String> listOfCryptos = Arrays.stream(CryptoCurrencies.values())
            .map(Enum::toString)
            .collect(Collectors.toUnmodifiableList());

    List<String> listOfStockExchanges = Arrays.stream(Exchanges.values())
            .map(Enum::toString)
            .collect(Collectors.toUnmodifiableList());

    private static final Random random = new Random();
    private final CopyOnWriteArraySet<CryptoCurrencyPriceSubscriber> subscribers = new CopyOnWriteArraySet<>();

    private final Map<String, List<CryptoCurrencyPriceInfo>> bufferedMessages = new HashMap<>();

    public CryptoCurrencyStocksAPI() {
    }

    public void subscribeOnCryptoCurrencyData(CryptoCurrencyPriceSubscriber subscriber) {
        if (!listOfCryptos.contains(subscriber.getCryptoName())) {
            System.out.println(
                    "Crypto currency: " + subscriber.getCryptoName()
                    + " is not available. Subscription request rejected from "
                    + subscriber.getClientID());
            return;
        }

        boolean reconnection = false;
        for (CryptoCurrencyPriceSubscriber sub : subscribers) {
            if (sub.getClientID().equals(subscriber.getClientID())) {
                reconnection = true;
                subscribers.remove(sub);
                System.out.println("Client with ID: " + subscriber.getClientID() + " has reconnected.");
                if(hasBufferedMessage(subscriber)) {
                    resendBufferedMessages(subscriber);
                }
                break;
            }
        }

        subscribers.add(subscriber);
        if(!reconnection) {
            System.out.println("New subscriber: " + subscriber);
        }
    }

    public void generateData() {
        try {
            while (true) {
                String cryptoName = getRandomAvailableCrypto();
                CryptoCurrencyPriceInfo cryptoCurrencyPriceInfo = CryptoCurrencyPriceInfo.newBuilder()
                        .setCryptoName(cryptoName)
                        .setPriceVolatility(getRandomCryptoPriceVolatility())
                        .setRecommendation(getRandomRecommendation())
                        .addAllPriceOnStockExchange(getRandomPricesOnStockExchange(cryptoName))
                        .build();

                sendCryptoCurrencyPriceInfo(cryptoCurrencyPriceInfo);
                removeSubscribersWithLostConnection();
                sleep(500);
            }
        } catch (Exception ignore) {
        }

    }

    private String getRandomAvailableCrypto() {
        return listOfCryptos.get(random.nextInt(listOfCryptos.size()));
    }

    private CryptoPriceVolatility getRandomCryptoPriceVolatility() {
        final CryptoPriceVolatility[] statuses = CryptoPriceVolatility.values();
        return statuses[random.nextInt(statuses.length - 1)];
    }

    private Recommendation getRandomRecommendation() {
        final Recommendation[] statuses = Recommendation.values();
        return statuses[random.nextInt(statuses.length - 1)];
    }

    private List<PriceOnStockExchange> getRandomPricesOnStockExchange(String cryptoName) {
        double cryptoPrice = getRandomPrice(cryptoName);
        return listOfStockExchanges.stream()
                .map(stockName -> PriceOnStockExchange.newBuilder()
                        .setStockExchangeName(stockName)
                        .setPrice(cryptoPrice * (1 + random.nextDouble()/10))
                        .build())
                .collect(toList());
    }

    private double getRandomPrice(String cryptoName) {
        double result = random.nextDouble();
        switch (cryptoName) {
            case "Bitcoin":
                result *= 100000;
                break;
            case "Ethereum":
                result *= 10000;
                break;
            case "Cardano":
                result *= 10;
                break;
        }
        return result;
    }


    private void sendCryptoCurrencyPriceInfo(CryptoCurrencyPriceInfo cryptoCurrencyPriceInfo) {
        for (CryptoCurrencyPriceSubscriber subscriber : subscribers) {
            if (subscriber.getCryptoName().equals(cryptoCurrencyPriceInfo.getCryptoName())) {
                try {
                    subscriber.getResponseObserver().onNext(cryptoCurrencyPriceInfo);
                    subscriber.setErrors(0);

                } catch (Exception e) {
                    bufferMessage(cryptoCurrencyPriceInfo, subscriber);
                    if(subscriber.getErrors() == 0) {
                        System.out.println("Failed to send message to: " + subscriber.getClientID());
                    } else if(subscriber.getErrors() % 10 == 0) {
                        System.out.println("Could not send message to subscriber: " + subscriber.getClientID() +
                                ". Failed attempts: " + subscriber.getErrors());
                    }
                    subscriber.setErrors(subscriber.getErrors() + 1);
                }
            }
        }
    }

    private void bufferMessage(
            CryptoCurrencyPriceInfo cryptoCurrencyPriceInfo,
            CryptoCurrencyPriceSubscriber subscriber) {

        bufferedMessages.computeIfAbsent(subscriber.getClientID(), k -> new ArrayList<>());
        bufferedMessages.get(subscriber.getClientID()).add(cryptoCurrencyPriceInfo);
    }

    private boolean hasBufferedMessage(CryptoCurrencyPriceSubscriber subscriber) {
        return bufferedMessages.get(subscriber.getClientID()) != null;
    }

    private void resendBufferedMessages(CryptoCurrencyPriceSubscriber subscriber){
        bufferedMessages.get(subscriber.getClientID())
                .forEach(message -> subscriber.getResponseObserver().onNext(message));
        bufferedMessages.remove(subscriber.getClientID());
    }

    private void removeSubscribersWithLostConnection() {
        Set<CryptoCurrencyPriceSubscriber> subscribersToRemove = new CopyOnWriteArraySet<>();
        subscribers.stream()
                .filter(CryptoCurrencyPriceSubscriber::hasLostConnection)
                .forEach(subscriber -> {
                    System.out.println("Removing subscriber: " + subscriber.getClientID() + " due to many failed attempts to send message.");
                    subscribersToRemove.add(subscriber);
                    bufferedMessages.remove(subscriber.getClientID());
                });

        subscribers.removeAll(subscribersToRemove);
    }
}
