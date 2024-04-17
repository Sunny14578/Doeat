package doeat.doeat.store.dto.request;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.StoreCategories;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
