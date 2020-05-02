package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 'callSuper = false' --- means dont't take into consideration parent fields
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractRemovableEntity extends AbstractEntity {

    @Column(name = "removal_date")
    private LocalDateTime removalDate;

}
