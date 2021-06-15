$:.push('gen-rb')
$:.unshift '../../lib/rb/lib'

require 'thrift'

require_relative 'gen-rb/sending_service'
include Serialization
include SendingService
require_relative 'gen-rb/serialization_types'
include Serialization

class SenderHandler
  def initialize()
    @log = {}
  end

  def sendSimpleStruct(struct)
    struct.integer > 30
  end

  def sendCollectionStruct(struct)
    struct.numbers.length > 30
  end

  def sendComplexStruct(struct)
    struct.nestedStructList.length > 30
  end
end

handler = SenderHandler.new
processor = SendingService::Processor.new(handler)
transport = Thrift::ServerSocket.new(40004)
transportFactory = Thrift::BufferedTransportFactory.new
# protocolFactory = Thrift::JsonProtocolFactory.new
protocolFactory = Thrift::CompactProtocolFactory.new
# protocolFactory = Thrift::BinaryProtocolFactory.new
server = Thrift::SimpleServer.new(processor, transport, transportFactory, protocolFactory)
puts "Starting the server..."
server.serve
puts "done."
