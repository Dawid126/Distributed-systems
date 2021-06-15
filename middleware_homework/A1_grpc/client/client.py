import threading
import time
import uuid

import grpc

from gen.crypto_currency_price_info_pb2 import CryptoCurrencyPriceRequest, \
    _CRYPTOCURRENCYPRICEINFO_CRYPTOPRICEVOLATILITY, _CRYPTOCURRENCYPRICEINFO_RECOMMENDATION
from gen.crypto_currency_price_info_pb2_grpc import CryptoCurrencyPriceServiceStub
from properties import config

listOfCryptos = ["Bitcoin", "Ethereum", "Cardano", "Dogecoin"]

def print_info(result):
    for item in result:
        print(f'Crypto name: {item.cryptoName} '
              f' -- Price volatility: {_CRYPTOCURRENCYPRICEINFO_CRYPTOPRICEVOLATILITY.values_by_number[item.priceVolatility].name}'
              f' -- Recommendation: {_CRYPTOCURRENCYPRICEINFO_RECOMMENDATION.values_by_number[item.recommendation].name}')

        for priceOnStocks in item.priceOnStockExchange:
            print(f'Exchange: {priceOnStocks.stockExchangeName} - price: {priceOnStocks.price}')

        print('------------------------------------------------------')


def run():

    while(True):
        crypto_name = input("Choose crypto name that you want to subscribe for price info: ")
        if crypto_name not in listOfCryptos:
            print("Wrong crypto name. Available cryptos:")
            print(listOfCryptos)
        else:
            break

    channel = grpc.insecure_channel(
        config.IP + ':' + config.PORT,
        options=[
            ('grpc.keepalive_time_ms', 5000),
            ('grpc.keepalive_timeout_ms', 2000),
            ('grpc.keepalive_permit_without_calls', True)
        ]
    )

    client_id = str(uuid.uuid1())
    request = CryptoCurrencyPriceRequest(
        cryptoName=crypto_name,
        clientID=client_id)

    print("Client's connecting...")

    crypto_currency_price_service_stub = CryptoCurrencyPriceServiceStub(channel)

    while True:
        try:
            result = crypto_currency_price_service_stub.SubscribeOnCryptoCurrencyPriceInfo(request)
            print_info(result)

        except:
            print("Connection problems...")
            time.sleep(1)


run()

