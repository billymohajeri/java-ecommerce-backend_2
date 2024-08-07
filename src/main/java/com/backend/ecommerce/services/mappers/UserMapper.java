package com.backend.ecommerce.services.mappers;

import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.services.dtos.UserDto;
import com.backend.ecommerce.services.utilities.Constants;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
    @Mapping(source = "birthDate", target = "birthDate", dateFormat = Constants.SERVER_DATE_FORMAT)
    User toUser(UserDto source);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = Constants.SERVER_DATE_FORMAT)
    UserDto toUserDto(User source);


    List<UserDto> toUserDtos(List<User> userDto);
}
