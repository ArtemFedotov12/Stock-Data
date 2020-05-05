package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "asset")
    String asset;

    @Column(name = "display_name")
    String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Company company;

}
