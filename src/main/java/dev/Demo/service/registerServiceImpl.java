package dev.Demo.service;

import dev.Demo.exception.RegisterCollectionException;
import dev.Demo.model.register;
import dev.Demo.repository.registerRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class registerServiceImpl implements registerService{
    @Autowired
    private registerRepository registerRepo;

    @Override
    public void createRegister (register Register) throws ConstraintViolationException, RegisterCollectionException {
       Optional<register> registerOptional = registerRepo.findByUserName(Register.getUserName());
       if(registerOptional.isPresent()){
           throw new RegisterCollectionException(RegisterCollectionException.RegisterAlreadyExists());
       }
       else {
           Register.setCreatedAt(new Date(System.currentTimeMillis()));
           registerRepo.save(Register);
       }

    }

    @Override
    public List<register> getAllRegisters(){
        List<register> Registers = registerRepo.findAll();
        if(Registers.size()>0){
            return Registers;
        }else{
            return new ArrayList<register>();
        }
    }

    @Override
    public register getSingleRegister(String id) throws RegisterCollectionException {
        Optional<register> optionalRegister = registerRepo.findById(id);
        if(!optionalRegister.isPresent()){
            throw new RegisterCollectionException(RegisterCollectionException.NotFoundException(id));
        }else{
            return optionalRegister.get();
        }
    }

    @Override
    public void updateRegister(String id, register Register) throws RegisterCollectionException {
        Optional<register> RegisterWithId = registerRepo.findById(id);
        Optional<register> RegisterWithSameName = registerRepo.findByUserName(Register.getUserName());
        if(RegisterWithId.isPresent()){
            if(RegisterWithSameName.isPresent() && !RegisterWithSameName.get().getId().equals(id)){
                    throw new RegisterCollectionException(RegisterCollectionException.RegisterAlreadyExists());
            }
            register RegisterToUpdate = RegisterWithId.get();
            RegisterToUpdate.setUserName(Register.getUserName());
            RegisterToUpdate.setEmail(Register.getEmail());
            RegisterToUpdate.setPassword(Register.getPassword());
            RegisterToUpdate.setConfirm_password(Register.getConfirm_password());
            RegisterToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            registerRepo.save(RegisterToUpdate);
        }
        else {
            throw  new RegisterCollectionException(RegisterCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteRegisterById(String id) throws RegisterCollectionException {
        Optional<register> registerOptional = registerRepo.findById(id);
        if(!registerOptional.isPresent()){
            throw new RegisterCollectionException(RegisterCollectionException.NotFoundException(id));
        }else{
            registerRepo.deleteById(id);
        }
    }
}
