package doeat.doeat.store.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMenuRequestDto {
    private String name;
    private String explanation;
    private float price;
    private boolean status;
    private String a;

    @Builder
    public UpdateMenuRequestDto(String name, String explanation, float price, boolean status) {
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.status = status;
    }

}
