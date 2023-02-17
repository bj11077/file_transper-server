package com.ex.mtp.filetr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto {
    private String fileName;
    private byte[] file;
    public String getFileName() {
        return fileName;
    }

    /**
     * 1.jackson은 기본적으로 byte[]를 base64로 말아서 반환시킴
     * 2. 이게 싫으면 serializer 직접 지정해줘야됨
     * 3. 적용시 base64가 아닌 byte[] 로 값반환
     */
    @JsonSerialize(using = CustomByteArraySerializer.class)
    public byte[] getFile() {
        return file;
    }
}