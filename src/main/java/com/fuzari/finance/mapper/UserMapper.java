package com.fuzari.finance.mapper;

import com.fuzari.finance.domain.User;
import com.fuzari.finance.dtos.auth.request.UserLoginRequest;
import com.fuzari.finance.dtos.auth.request.UserRegisterRequest;
import com.fuzari.finance.dtos.user.response.UserGetResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserMapper {

  UserGetResponse userToUserGetResponse(User user);

  List<UserGetResponse> userListToUserGetResponseList(List<User> user);

  User userGetResponseToUser(UserGetResponse userGetResponse);

  User userPostRequestToUser(UserRegisterRequest userRegisterRequest);

  User userLoginRequestToUser(UserLoginRequest userLoginRequest);
}
