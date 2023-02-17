package com.ex.mtp.filetr;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class FileTradeController {


    private final FileTradeService fileTradeService;


    @PostMapping("/upload-response")
    public String fileResponse(@RequestParam("file")MultipartFile file) throws IOException {
        fileTradeService.fileUploadResponse(file);
        return "ok";
    }

    @GetMapping(value = "download-response")
    public TradeDto fileDownloadResponse() throws IOException {
        TradeDto result = fileTradeService.fileDownloadResponse();
        return result;
    }
}
