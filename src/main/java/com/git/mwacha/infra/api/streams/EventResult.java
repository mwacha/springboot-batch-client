package com.git.mwacha.infra.api.streams;

import com.git.mwacha.domain.enums.ImportStatus;
import java.util.UUID;
import lombok.Builder;

@Builder
public record EventResult(UUID id, ImportStatus status) {}
