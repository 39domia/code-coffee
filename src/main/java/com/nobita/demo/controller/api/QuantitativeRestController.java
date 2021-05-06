package com.nobita.demo.controller.api;

import com.nobita.demo.model.Quantitative;
import com.nobita.demo.service.QuantitativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/quantitatives")
public class QuantitativeRestController {

    @Autowired
    QuantitativeService quantitativeService;

    @GetMapping
    public ResponseEntity<?> list(){
        List<Quantitative> quantitativeList=quantitativeService.findAll();
        if(quantitativeList.size() != 0){
            return new ResponseEntity<>(quantitativeList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/product/{idProduct}/ingredient/{idIngredient}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdProductAndIdIngredient(@PathVariable("idProduct") Long idProduct,@PathVariable("idIngredient") Long idIngredient){
        Quantitative quantitative=quantitativeService.findByIdProductAndIdIngredient(idProduct,idIngredient);
        if(quantitative != null){
            return new ResponseEntity<>(quantitative,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Quantitative quantitative){
        try{
            quantitativeService.save(quantitative);
            return new ResponseEntity(quantitative,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/product/{idProduct}/ingredient/{idIngredient}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateByIdProductAndIdIngredient(@PathVariable("idProduct") Long idProduct,@PathVariable("idIngredient") Long idIngredient,@RequestBody Quantitative quantitative){
        Quantitative quantitative1=quantitativeService.findByIdProductAndIdIngredient(idProduct, idIngredient);
        if(quantitative1 != null){
            quantitativeService.updateByIdProductAndIdIngredient(quantitative,idProduct,idIngredient);
            return new ResponseEntity<>(quantitative,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/product/{idProduct}/ingredient/{idIngredient}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteByIdProductAndIdIngredient(@PathVariable("idProduct") Long idProduct,@PathVariable("idIngredient") Long idIngredient){
        try{
            quantitativeService.deleteByIdProductAndIdIngredient(idProduct,idIngredient);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
