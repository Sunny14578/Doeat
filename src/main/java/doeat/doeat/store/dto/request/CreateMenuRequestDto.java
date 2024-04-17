package doeat.doeat.store.dto.request;

import doeat.doeat.store.domain.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMenuRequestDto {
    private String name;
    private Store store;
    private String explanation;
    private float price;

    @Builder
    public CreateMenuRequestDto(String name, Store store, String explanation, float price) {
        this.name = name;
        this.store = store;
        this.explanation = explanation;
        this.price = price;
    }
}
