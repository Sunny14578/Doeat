package doeat.doeat.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateRecruitmentRequestDto {
    private boolean status;

    @Builder
    public UpdateRecruitmentRequestDto(boolean status) {
        this.status = status;
    }
}
