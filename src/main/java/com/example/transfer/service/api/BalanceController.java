package com.example.transfer.service.api;

import com.example.transfer.service.dto.Response;
import com.example.transfer.service.exception.NotFoundException;
import com.example.transfer.service.model.Balance;
import com.example.transfer.service.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping
    public ResponseEntity<Response> getBalance(@RequestParam String accountId){
        try {
            return ResponseEntity
                    .ok(new Response(true, "Success", balanceService.getBalance(accountId)));
        }
        catch (NotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, ex.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getBalances(){
        return ResponseEntity
                .ok(new Response(true, "Success", balanceService.getAllBalances()));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestParam String name){
        return ResponseEntity
                .ok(new Response(true, "Success", balanceService.createAccount(name)));
    }

    @PostMapping("/credit")
    public ResponseEntity<Response> creditBalance(@RequestParam String accountId, @RequestParam long amount){
        try {
            return ResponseEntity
                    .ok(new Response(true, "Success", balanceService.credit(accountId, amount)));
        }
        catch (NotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(false, ex.getMessage(), null));
        }
    }
}
