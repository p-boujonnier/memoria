package fr.fae.project.charona.account.user.api.mappers;

import fr.fae.project.charona.account.user.api.dto.requests.UserCreateRequest;
import fr.fae.project.charona.account.user.api.dto.requests.UserUpdateRequest;
import fr.fae.project.charona.account.user.api.dto.responses.UserPublicResponse;
import fr.fae.project.charona.account.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserFromCreate(UserCreateRequest userCreateRequest);
    User toUserFromUpdate(UserUpdateRequest userUpdateRequest);

    UserPublicResponse toUserResponse(User user);
}
