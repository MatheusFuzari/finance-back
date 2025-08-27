package com.fuzari.finance.mapper;

import com.fuzari.finance.domain.Wage;
import com.fuzari.finance.dtos.wage.request.WagePostRequest;
import com.fuzari.finance.dtos.wage.request.WagePutRequest;
import com.fuzari.finance.dtos.wage.response.WageGetResponse;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = UserMapper.class)
public interface WageMapper {

  WageGetResponse wageToWageGetResponse(Wage wage);

  @Mapping(target = "user.id", source = "user_id")
  Wage wagePostResponseToWage(WagePostRequest wagePostRequest, UUID user_id);

  @Mapping(target = "user.id", source = "user_id")
  Wage wagePutResponseToWage(WagePutRequest wagePutRequest, UUID user_id);

}
