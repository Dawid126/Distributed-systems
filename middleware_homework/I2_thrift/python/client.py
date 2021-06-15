import time

from serialization import SendingService
from serialization import ttypes

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TJSONProtocol
from thrift.protocol import TCompactProtocol
from thrift.protocol import TBinaryProtocol
from thrift import TSerialization

def send_simple_struct(client):
    struct = ttypes.SimpleStruct(300,  150.5, 'text')

    start = time.time_ns()
    for _ in range(1000):
        # r = TSerialization.serialize(struct, TBinaryProtocol.TBinaryProtocolFactory())
        # r = TSerialization.serialize(struct, TJSONProtocol.TJSONProtocolFactory())
        r = TSerialization.serialize(struct, TCompactProtocol.TCompactProtocolFactory())
    end = time.time_ns()
    print((end - start)/1000000)
    client.sendSimpleStruct(struct)


def send_collection_struct(client):

    numbers = []
    decimals = set()

    num_of_elem = 10

    for i in range(num_of_elem):
        numbers.append(i)
        decimals.add(i + 0.1)

    struct = ttypes.CollectionStruct(numbers, decimals)

    start = time.time_ns()
    for _ in range(1000):
        # r = TSerialization.serialize(struct, TBinaryProtocol.TBinaryProtocolFactory())
        # r = TSerialization.serialize(struct, TJSONProtocol.TJSONProtocolFactory())
        r = TSerialization.serialize(struct, TCompactProtocol.TCompactProtocolFactory())
    end = time.time_ns()
    print((end - start)/1000000)
    client.sendCollectionStruct(struct)


def send_complex_struct(client):

    nested = []
    integer_to_nested = {}

    num_of_elem = 100
    for i in range(5):

        words = []
        integer_to_decimal = {}

        for j in range(num_of_elem):
            words.append(str(j))
            integer_to_decimal[j] = (j + 0.1)

        nested.append(ttypes.NestedStruct(words, integer_to_decimal))
        integer_to_nested[i] = ttypes.NestedStruct(words, integer_to_decimal)

    struct = ttypes.ComplexStruct(nested, integer_to_nested)

    start = time.time_ns()
    for _ in range(1000):
        # r = TSerialization.serialize(struct, TBinaryProtocol.TBinaryProtocolFactory())
        # r = TSerialization.serialize(struct, TJSONProtocol.TJSONProtocolFactory())
        r = TSerialization.serialize(struct, TCompactProtocol.TCompactProtocolFactory())
    end = time.time_ns()
    print((end - start)/1000000)
    client.sendComplexStruct(struct)


transport = TSocket.TSocket('127.0.0.1', 40004)
transport = TTransport.TBufferedTransport(transport)

# protocol = TBinaryProtocol.TBinaryProtocol(transport)
# protocol = TJSONProtocol.TJSONProtocol(transport)
protocol = TCompactProtocol.TCompactProtocol(transport)

client = SendingService.Client(protocol)

transport.open()

# send_simple_struct(client)
# send_collection_struct(client)
send_complex_struct(client)

transport.close()

