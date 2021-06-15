from serialization import SendingService

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.protocol import TJSONProtocol
from thrift.protocol import TCompactProtocol
from thrift.server import TServer


class SenderHandler:
    def __init__(self):
        self.log = {}

    def sendSimpleStruct(self, struct):
        return struct.integer > 300

    def sendCollectionStruct(self, struct):
        return len(struct.numbers) > 1

    def sendComplexStruct(self, struct):
        return len(struct.nestedStructList) > 1


if __name__ == '__main__':
    handler = SenderHandler()
    processor = SendingService.Processor(handler)
    transport = TSocket.TServerSocket(host='127.0.0.1', port=40004)
    tfactory = TTransport.TBufferedTransportFactory()
    # pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    # pfactory = TJSONProtocol.TJSONProtocolFactory()
    pfactory = TCompactProtocol.TCompactProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    print('Starting the server...')
    server.serve()
    print('done.')
