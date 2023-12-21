package com.kjh.wms.location.feature;

import com.kjh.wms.location.domain.Location;
import com.kjh.wms.location.domain.LocationRepository;
import com.kjh.wms.location.domain.StorageType;
import com.kjh.wms.location.domain.UsagePurpose;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterLocation {

    private final LocationRepository locationRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/locations")
    public void request(@RequestBody @Valid Request request) {
        Location location = request.toDomain();
        locationRepository.save(location);
    }

    public record Request(
            @NotBlank(message = "로케이션 바코드는 필수입니다.")
            String locationBarcode,

            @NotNull(message = "로케이션 유형은 필수입니다.")
            StorageType storageType,

            @NotNull(message = "로케이션 용도는 필수입니다.")
            UsagePurpose usagePurpose) {

        public Location toDomain() {
            return new Location(locationBarcode, storageType, usagePurpose);
        }
    }
}
