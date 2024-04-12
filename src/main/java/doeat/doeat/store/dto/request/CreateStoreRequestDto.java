package doeat.doeat.store.dto.request;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.StoreCategories;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateStoreRequestDto {

    private String businessNumber;
    private String name;
    private Address address;
    private List<StoreCategories> categories = new ArrayList<>();

    @Builder
    public CreateStoreRequestDto(String businessNumber, String name, Address address, List<StoreCategories> categories) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.address = address;
        this.categories = categories;
    }
}
