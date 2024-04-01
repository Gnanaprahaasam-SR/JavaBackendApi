package dev.Demo.controller;

import dev.Demo.exception.RegisterCollectionException;
import dev.Demo.repository.registerRepository;
import dev.Demo.model.register;
import dev.Demo.service.registerService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.lang.String;

@RestController
public class registerController {
    @Autowired
    private registerRepository registerRepo;
    @Autowired
    private registerService RegisterService;
    @GetMapping(path = "/")
    public ResponseEntity<?> getAllRegister(){
        List <register> Registers = RegisterService.getAllRegisters();
        return new ResponseEntity<>(Registers,Registers.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> createRegister(@RequestBody register Register){
        try{
//            Register.setCreatedAt(new Date(System.currentTimeMillis()));
//            registerRepo.save(Register);
              RegisterService.createRegister(Register);
              return new ResponseEntity<register>(Register,HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (RegisterCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping( path = "/register/{id}")
    public ResponseEntity<?> getSingleRegister(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(RegisterService.getSingleRegister(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
//        Optional<register> registerOptional = registerRepo.findById(id);
//        if(registerOptional.isPresent()){
//            return new ResponseEntity<>(registerOptional.get(), HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("Register not found with id"+id,HttpStatus.NOT_FOUND);
//        }
    }

    @PutMapping(path = "/updateRegister/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody register Register){
//        Optional<register> registerOptional = registerRepo.findById(id);
//        if(registerOptional.isPresent()){
//            register RegisterToSave = registerOptional.get();
//            RegisterToSave.setUserName(Register.getUserName() != null ? Register.getUserName() : RegisterToSave.getUserName());
//            RegisterToSave.setEmail(Register.getEmail() != null ? Register.getEmail() : RegisterToSave.getEmail());
//            RegisterToSave.setPassword(Register.getPassword() != null ? Register.getPassword() : RegisterToSave.getPassword());
//            RegisterToSave.setConfirm_password(Register.getConfirm_password() != null ? Register.getConfirm_password():RegisterToSave.getConfirm_password());
//            RegisterToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
//            registerRepo.save(RegisterToSave);
//            return new ResponseEntity<>(RegisterToSave, HttpStatus.OK) ;
//        }
//        else {
//            return new ResponseEntity<>("Register not found with id"+id,HttpStatus.NOT_FOUND);
//        }
        try{
            RegisterService.updateRegister(id, Register);
            return new ResponseEntity<>("Update Register with id"+id, HttpStatus.OK);
        }catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (RegisterCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/deleteRegister/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable("id") String id){
//        try {
//            registerRepo.deleteById(id);
//            return new ResponseEntity<>("successfully deleted with id"+id, HttpStatus.OK);
//        }catch (Exception e)
//        {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
        try{
            RegisterService.deleteRegisterById(id);
            return new ResponseEntity<>("Successfully deleted with id"+id,HttpStatus.OK);
        }
        catch (RegisterCollectionException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

