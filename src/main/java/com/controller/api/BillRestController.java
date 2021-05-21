package com.controller.api;

import com.model.dto.BillDateDTO;
import com.model.Bill;
import com.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/bills")
public class BillRestController {

    @Autowired
    BillService billService;

    @PostMapping(value = "/dateExport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody BillDateDTO billDateDTO) {
        List<Bill> bills = billService.findByDateExport(billDateDTO.getDateIn(), billDateDTO.getDateOut());
        if (!bills.isEmpty()) {
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Bill bill) {
        try {
            billService.save(bill);
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/totalRevenue")
    public ResponseEntity<?> totalRevenue(){
        Bill bill = billService.sumTotalPriceAll();
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/countBills")
    public ResponseEntity<?> countBills(){
        Bill bill = billService.countBills();
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
