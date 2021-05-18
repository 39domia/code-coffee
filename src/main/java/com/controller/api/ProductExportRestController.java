package com.controller.api;

import com.model.dto.BillDateDTO;
import com.model.ProductExport;
import com.service.ProductExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/productExports")
public class ProductExportRestController {

    @Autowired
    ProductExportService productExportService;

    @PostMapping(value = "/dateExport",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestBody BillDateDTO billDateDTO) {
        List<ProductExport> productExportList = productExportService.findAllByDateExport(billDateDTO.getDateIn(),billDateDTO.getDateOut());
        if (!productExportList.isEmpty()) {
            return new ResponseEntity<>(productExportList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductExport productExport){
        try{
            productExportService.save(productExport);
            return new ResponseEntity<>(productExport,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{idProduct}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody ProductExport productExport){
        try{
            productExportService.update(productExport);
            return new ResponseEntity<>(productExport,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
