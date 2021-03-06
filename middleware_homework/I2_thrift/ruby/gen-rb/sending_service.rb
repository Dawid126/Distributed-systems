#
# Autogenerated by Thrift Compiler (0.14.1)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#

require 'thrift'
require 'serialization_types'

module Serialization
  module SendingService
    class Client
      include ::Thrift::Client

      def sendSimpleStruct(str)
        send_sendSimpleStruct(str)
        return recv_sendSimpleStruct()
      end

      def send_sendSimpleStruct(str)
        send_message('sendSimpleStruct', SendSimpleStruct_args, :str => str)
      end

      def recv_sendSimpleStruct()
        result = receive_message(SendSimpleStruct_result)
        return result.success unless result.success.nil?
        raise ::Thrift::ApplicationException.new(::Thrift::ApplicationException::MISSING_RESULT, 'sendSimpleStruct failed: unknown result')
      end

      def sendCollectionStruct(str)
        send_sendCollectionStruct(str)
        return recv_sendCollectionStruct()
      end

      def send_sendCollectionStruct(str)
        send_message('sendCollectionStruct', SendCollectionStruct_args, :str => str)
      end

      def recv_sendCollectionStruct()
        result = receive_message(SendCollectionStruct_result)
        return result.success unless result.success.nil?
        raise ::Thrift::ApplicationException.new(::Thrift::ApplicationException::MISSING_RESULT, 'sendCollectionStruct failed: unknown result')
      end

      def sendComplexStruct(str)
        send_sendComplexStruct(str)
        return recv_sendComplexStruct()
      end

      def send_sendComplexStruct(str)
        send_message('sendComplexStruct', SendComplexStruct_args, :str => str)
      end

      def recv_sendComplexStruct()
        result = receive_message(SendComplexStruct_result)
        return result.success unless result.success.nil?
        raise ::Thrift::ApplicationException.new(::Thrift::ApplicationException::MISSING_RESULT, 'sendComplexStruct failed: unknown result')
      end

    end

    class Processor
      include ::Thrift::Processor

      def process_sendSimpleStruct(seqid, iprot, oprot)
        args = read_args(iprot, SendSimpleStruct_args)
        result = SendSimpleStruct_result.new()
        result.success = @handler.sendSimpleStruct(args.str)
        write_result(result, oprot, 'sendSimpleStruct', seqid)
      end

      def process_sendCollectionStruct(seqid, iprot, oprot)
        args = read_args(iprot, SendCollectionStruct_args)
        result = SendCollectionStruct_result.new()
        result.success = @handler.sendCollectionStruct(args.str)
        write_result(result, oprot, 'sendCollectionStruct', seqid)
      end

      def process_sendComplexStruct(seqid, iprot, oprot)
        args = read_args(iprot, SendComplexStruct_args)
        result = SendComplexStruct_result.new()
        result.success = @handler.sendComplexStruct(args.str)
        write_result(result, oprot, 'sendComplexStruct', seqid)
      end

    end

    # HELPER FUNCTIONS AND STRUCTURES

    class SendSimpleStruct_args
      include ::Thrift::Struct, ::Thrift::Struct_Union
      STR = 1

      FIELDS = {
        STR => {:type => ::Thrift::Types::STRUCT, :name => 'str', :class => ::Serialization::SimpleStruct}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

    class SendSimpleStruct_result
      include ::Thrift::Struct, ::Thrift::Struct_Union
      SUCCESS = 0

      FIELDS = {
        SUCCESS => {:type => ::Thrift::Types::BOOL, :name => 'success'}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

    class SendCollectionStruct_args
      include ::Thrift::Struct, ::Thrift::Struct_Union
      STR = 1

      FIELDS = {
        STR => {:type => ::Thrift::Types::STRUCT, :name => 'str', :class => ::Serialization::CollectionStruct}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

    class SendCollectionStruct_result
      include ::Thrift::Struct, ::Thrift::Struct_Union
      SUCCESS = 0

      FIELDS = {
        SUCCESS => {:type => ::Thrift::Types::BOOL, :name => 'success'}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

    class SendComplexStruct_args
      include ::Thrift::Struct, ::Thrift::Struct_Union
      STR = 1

      FIELDS = {
        STR => {:type => ::Thrift::Types::STRUCT, :name => 'str', :class => ::Serialization::ComplexStruct}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

    class SendComplexStruct_result
      include ::Thrift::Struct, ::Thrift::Struct_Union
      SUCCESS = 0

      FIELDS = {
        SUCCESS => {:type => ::Thrift::Types::BOOL, :name => 'success'}
      }

      def struct_fields; FIELDS; end

      def validate
      end

      ::Thrift::Struct.generate_accessors self
    end

  end

end
