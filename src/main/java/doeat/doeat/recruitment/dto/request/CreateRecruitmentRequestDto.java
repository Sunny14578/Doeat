package doeat.doeat.recruitment.dto.request;

import doeat.doeat.store.domain.Store;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateRecruitmentRequestDto {

    private Store store;
    private LocalDateTime startTime;
    private boolean status;

    @Builder
    public CreateRecruitmentRequestDto(Store store, LocalDateTime startTime, boolean status) {
        this.store = store;
        this.startTime = startTime;
        this.status = status;
    }
}
