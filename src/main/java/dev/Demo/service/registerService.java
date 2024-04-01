package dev.Demo.service;

import dev.Demo.exception.RegisterCollectionException;
import dev.Demo.model.register;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface registerService {

    public void createRegister(register Register) throws ConstraintViolationException, RegisterCollectionException;

    public List<register> getAllRegisters();

    public register getSingleRegister(String id) throws RegisterCollectionException;

    public void updateRegister(String id, register Register) throws RegisterCollectionException;

    public void deleteRegisterById(String id) throws RegisterCollectionException;

}
