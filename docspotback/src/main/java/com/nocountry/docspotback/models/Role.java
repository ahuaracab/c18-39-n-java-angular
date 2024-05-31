package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_role")
    private UUID idRole = UUID.randomUUID();

    @Column(name = "name_role", length = 50, nullable = false)
    private String nameRole;

    @Column(name = "description_role", length = 120)
    private String descriptionRole;

}
