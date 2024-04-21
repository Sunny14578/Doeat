package doeat.doeat.recruitment.dto;

import doeat.doeat.store.domain.Store;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecruitmentDto {
    private Store store;
    private LocalDateTime startTime;
    private boolean status;

    @Builder
    public RecruitmentDto(Store store, LocalDateTime startTime, boolean status) {
        this.store = store;
        this.startTime = startTime;
        this.status = status;
    }
}
