package com.nocountry.docspotback.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditableDTO {
    private Instant createdAt;
    private Instant updateAt;
    private Instant deletedAt;
    private boolean isDeleted;

}