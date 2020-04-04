package com.start.stockdata.identity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 'callSuper = false' --- means dont't take into consideration parent fields
@EqualsAndHashCode(callSuper = false)
@Data
@MappedSuperclass
public abstract class AbstractRemovableEntity extends AbstractEntity {

    @Column(name = "removal_date")
    private LocalDateTime removalDate;

}
