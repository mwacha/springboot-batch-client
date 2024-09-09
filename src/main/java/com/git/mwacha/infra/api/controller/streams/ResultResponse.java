package com.git.mwacha.infra.api.controller.streams;

import com.git.mwacha.domain.enums.AnalysisStatus;

public record ResultResponse(Long clientId, AnalysisStatus status) {}
