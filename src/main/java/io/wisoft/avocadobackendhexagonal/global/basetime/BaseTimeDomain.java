package io.wisoft.avocadobackendhexagonal.global.basetime;

import java.time.LocalDateTime;

public class BaseTimeDomain {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public void createDomain() {
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void updateDomain() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}
