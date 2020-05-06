package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Field extends AbstractAttributeEntity{

    @EqualsAndHashCode.Exclude
    @Column(name = "short_name",nullable = false)
    private String shortName;

    @EqualsAndHashCode.Exclude
    @Column(name = "asset",nullable = false)
    private String asset;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;


}
