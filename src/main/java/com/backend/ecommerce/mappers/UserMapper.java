package com.backend.ecommerce.mappers;

import com.backend.ecommerce.dtos.user.UserCreateDto;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.shared.utilities.Constants;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
    @Mapping(source = "birthDate", target = "birthDate", dateFormat = Constants.SERVER_DATE_FORMAT)
    User toUser(UserDto source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = Constants.SERVER_DATE_FORMAT)
    UserDto toUserDto(User source);

    List<UserDto> toUserDtos(List<User> userDto);
}
