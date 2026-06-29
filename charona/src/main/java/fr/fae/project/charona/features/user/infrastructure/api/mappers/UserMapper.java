package fr.fae.project.charona.features.user.infrastructure.api.mappers;

import fr.fae.project.charona.features.user.infrastructure.api.dto.requests.UserCreateRequest;
import fr.fae.project.charona.features.user.infrastructure.api.dto.requests.UserUpdateRequest;
import fr.fae.project.charona.features.user.infrastructure.api.dto.responses.UserPublicResponse;
import fr.fae.project.charona.features.user.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserFromCreate(UserCreateRequest userCreateRequest);
    User toUserFromUpdate(UserUpdateRequest userUpdateRequest);

    UserPublicResponse toUserResponse(User user);
}
