package com.nocountry.docspotback.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professional")
    private UUID idProfessional;

    @Column(name = "name_professional",length = 70, nullable = false)
    private String nameProfessional;

    @Column(length = 4,nullable = false)
    private Double reputation;

    @Column(name = "value_query",length = 8,nullable = false)
    private Double valueQuery;

    @Column(length = 12,nullable = false,unique = true)
    private String mp;

   @OneToOne
   @JsonIgnore
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "professionals_shifts",
    joinColumns = @JoinColumn(name = "id_professional",referencedColumnName = "id_professional"),
    inverseJoinColumns = @JoinColumn(name = "id_shift",referencedColumnName = "id_shift"))
    private List<Shift> shifts;


}
