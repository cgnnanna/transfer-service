package com.example.transfer.service.api;

import com.example.transfer.service.dto.Response;
import com.example.transfer.service.dto.TransferDto;
import com.example.transfer.service.exception.BadRequestException;
import com.example.transfer.service.exception.NotFoundException;
import com.example.transfer.service.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<Response> transfer(@Validated @RequestBody TransferDto transfer){
        try {
            return ResponseEntity
                    .ok(new Response(true, "Success", transferService.transfer(transfer)));
        }
        catch (NotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, ex.getMessage(), null));
        }
        catch (BadRequestException ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, ex.getMessage(), null));

        }
    }
}
