package com.kjh.wms.location.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Comment("로케이션")
@Table(name = "location")
@Entity
public class Location {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("로케이션 번호")
    private Long locationNo;

    @Column(name = "location_barcode", nullable = false)
    @Comment("로케이션 바코드")
    private String locationBarcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "storage_typegg", nullable = false)
    @Comment("보관 타입")
    private StorageType storageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "usage_purpose", nullable = false)
    @Comment("보관 목적")
    private UsagePurpose usagePurpose;

    public Location(String locationBarcode,
                    StorageType storageType,
                    UsagePurpose usagePurpose) {
        validateConstructor(locationBarcode, storageType, usagePurpose);
        this.locationBarcode = locationBarcode;
        this.storageType = storageType;
        this.usagePurpose = usagePurpose;
    }

    private void validateConstructor(String locationBarcode,
                                     StorageType storageType,
                                     UsagePurpose usagePurpose) {
        Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다");
        Assert.notNull(storageType, "로케이션 유형은 필수입니다");
        Assert.notNull(usagePurpose, "로케이션 용도는 필수입니다");
    }
}
