package com.brenofco.backend.service;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.brenofco.backend.domain.UserDTO;
import com.brenofco.backend.domain.User;
import com.brenofco.backend.domain.mapper.UserMapper;
import com.brenofco.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    public UserDTO getUserByCpf(String cpf) {
        if (checkValidCpf(cpf)) {
            if (checkCpfExistence(cpf)) {
                //logger.info("Outputting user");
                System.out.println("Outputting the user of " + cpf);
                return userMapper.mapUserToDto(userRepository.getReferenceById(cpf));
            } else {
                //logger.warn("User doesn't exist");
                return userMapper.mapUserToDto(new User(cpf));
            }
        } else {
            //logger.warn("Invalid CPF");
            return userMapper.mapUserToDto(new User(cpf));
        }
    }

    public String postUser(UserDTO dto) {
        if (checkValidCpf(dto.cpf())) {
            if (checkCpfExistence(dto.cpf())) {
                return "User already exists!";
            } else {
                userRepository.save(userMapper.mapDtoToUser(dto));
                return "User successfully created!";
            }
        } else {
            return "Error: Invalid CPF!";
        }
    }

    public String editUser(String cpf, UserDTO dto) {
        if (checkCpfExistence(cpf)) {
            if (dto.cpf()==cpf) {
                userRepository.save(userMapper.mapDtoToUser(dto));

                return "User successfully edited!";
            } else {
                return "Error: inconsistent data!";
            }
        } else {
            return "Error: User does not exist!";
        }
    }

    public String deleteUser(String cpf) {
        String message;
        if (checkValidCpf(cpf)) {
            if (checkCpfExistence(cpf)) {
                userRepository.deleteById(cpf);
                message = "User successfully deleted!";
            } else {
                message = "Error: User doesn't exist";
            }
        } else {
            message = "Error: Invalid CPF";
        }
        return message;
    }

    public boolean checkCpfExistence(String cpf) {
        return userRepository.existsById(cpf);
    }

    public boolean checkValidCpf(String cpf) {
        if (cpf.length()==11) {
            int validatorChar1 = 0;
            int validatorChar2 = 0;
            int k = 10;
            //initializing array
            Integer[] doc = new Integer[11];
            //constructing array
            for (int i = 0; i < 11; i++) {
                doc[i] = Character.getNumericValue(cpf.charAt(i));
                System.out.println(doc[i]);
            }
            //first check digit
            for (int j = 0; j < 9; j++) {
                validatorChar1 = validatorChar1 + doc[j]*k;
                k--;
            }
            validatorChar1 = validatorChar1%11;
            if (validatorChar1 < 2) {
                validatorChar1 = 0;
            } else {
                validatorChar1 = 11 - validatorChar1;
            }
            System.out.println(validatorChar1);
            //second check digit
            k = 11;
            for (int j = 0; j < 10; j++) {
                validatorChar2 = validatorChar2 + doc[j]*k;
                k--;
            }
            validatorChar2 = validatorChar2%11;
            if (validatorChar2 < 2) {
                validatorChar2 = 0;
            } else {
                validatorChar2 = 11 - validatorChar2;
            }
            System.out.println(validatorChar2);
            //return results
            if (validatorChar1 == doc[9] && validatorChar2 == doc[10]) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
