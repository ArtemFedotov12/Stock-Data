package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table//(name = "company_factor")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Factor extends AbstractAttributeEntity {

    @EqualsAndHashCode.Exclude
    @Column(name = "short_name")
    String shortName;

    @EqualsAndHashCode.Exclude
    @Column(name = "asset", nullable = false)
    String asset;

    @Column(name = "display_name", nullable = false)
    String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

}
