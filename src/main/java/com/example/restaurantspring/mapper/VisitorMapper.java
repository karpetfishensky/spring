package com.example.restaurantspring.mapper;

import com.example.restaurant.entity.Visitor;
import com.example.restaurant.web.request.VisitorRequest;
import com.example.restaurant.web.response.VisitorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitorMapper {

    Visitor toEntity(VisitorRequest request);

    VisitorResponse toResponse(Visitor visitor);
}
