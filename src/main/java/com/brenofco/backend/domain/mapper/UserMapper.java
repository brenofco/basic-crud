package com.brenofco.backend.domain.mapper;

import com.brenofco.backend.domain.UserDTO;
import com.brenofco.backend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapDtoToUser(UserDTO dto) {
        return new User(dto.cpf(),dto.fname(),dto.lname(),dto.phone(),dto.mail(),dto.bdate());
    }

    public UserDTO mapUserToDto(User user) {
        return new UserDTO(user.getCpf(),user.getFname(), user.getLname(), user.getPhone(), user.getMail(), user.getBdate());
    }
}
