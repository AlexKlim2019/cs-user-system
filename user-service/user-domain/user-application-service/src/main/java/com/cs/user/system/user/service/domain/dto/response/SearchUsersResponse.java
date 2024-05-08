package com.cs.user.system.user.service.domain.dto.response;

import com.cs.user.system.user.service.domain.entity.User;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchUsersResponse(List<User> users) {}
