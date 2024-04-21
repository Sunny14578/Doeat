package doeat.doeat.store.dto;

import doeat.doeat.store.domain.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuDto {
    private Store store;
    private String name;
    private String explanation;
    private float price;
    private boolean status;

    @Builder
    public MenuDto(Store store, String name, String explanation, float price, boolean status) {
        this.store = store;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.status = status;
    }
}
