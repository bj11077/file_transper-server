package com.ex.mtp.filetr;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @ref : https://stackoverflow.com/questions/11546917/sending-a-byte-array-in-json-using-jackson
 */

public class CustomByteArraySerializer extends JsonSerializer<byte[]> {


    @Override
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (byte b : value) {
            gen.writeNumber(unsignedToBytes(b));
        }
        gen.writeEndArray();
    }
    private static int unsignedToBytes(byte b) {
        return b & 0xFF;
    }
}
