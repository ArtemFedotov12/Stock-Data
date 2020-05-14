package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.stream.events.Comment;

@Entity(name = "Factor")
@Table(name = "factor")
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
    @JoinColumn(name = "company_id")
    private Company company;

}
