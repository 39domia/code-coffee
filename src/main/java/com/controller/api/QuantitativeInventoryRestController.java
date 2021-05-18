package com.controller.api;

import com.model.QuantitativeInventory;
import com.model.dto.BillDateDTO;
import com.service.QuantitativeInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/quantitativeInventories")
public class QuantitativeInventoryRestController {
    @Autowired
    QuantitativeInventoryService quantitativeInventoryService;

    @PostMapping(value = "/dateExport")
    public ResponseEntity<?> list(@RequestBody BillDateDTO billDateDTO){
        List<QuantitativeInventory> quantitativeInventoryList=quantitativeInventoryService.findAllByDate(billDateDTO.getDateIn(),billDateDTO.getDateOut());
        return new ResponseEntity<>(quantitativeInventoryList, HttpStatus.OK);
    }


}
