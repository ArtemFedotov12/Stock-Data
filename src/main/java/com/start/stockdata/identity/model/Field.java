package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Field extends AbstractAttributeEntity{

    @EqualsAndHashCode.Exclude
    @Column(name = "short_name")
    private String shortName;

    @EqualsAndHashCode.Exclude
    @Column(name = "asset")
    private String asset;

    @Column(name = "display_name")
    private String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    @NonNull
    private Company company;


}
