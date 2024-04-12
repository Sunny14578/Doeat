package doeat.doeat.store.dto.request;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.StoreCategories;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UpdateStoreRequestDto {

    private String businessNumber;
    private String name;
    private Address address;
    private List<StoreCategories> categories = new ArrayList<>();

    @Builder
    public UpdateStoreRequestDto(String name, Address address, List<StoreCategories> categories) {
        this.name = name;
        this.address = address;
        this.categories = categories;
    }
}
