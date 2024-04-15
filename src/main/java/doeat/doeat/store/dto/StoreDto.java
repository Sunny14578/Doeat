package doeat.doeat.store.dto;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.StoreCategories;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreDto {

    private String businessNumber;
    private String name;
    private Address address;
    List<StoreCategories> categories = new ArrayList<>();
    private Long point;

    @Builder
    public StoreDto(String businessNumber, String name, Address address, List<StoreCategories> categories, Long point) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.address = address;
        this.categories = categories;
        this.point = point;
    }
}
