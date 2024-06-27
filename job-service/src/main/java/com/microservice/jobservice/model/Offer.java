package com.microservice.jobservice.model;

import lombok.*;

import javax.persistence.*;

import com.microservice.jobservice.enums.OfferStatus;

@Entity(name = "offers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer extends BaseEntity {
    private String userId;
    private int offeredPrice;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advert_id")
    private Advert advert;
}
