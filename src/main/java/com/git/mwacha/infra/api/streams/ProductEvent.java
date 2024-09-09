package com.git.mwacha.infra.api.streams;

import java.util.UUID;

public record ProductEvent(
    UUID id, String code, String productName, String description, UUID importProductId) {}
