package doeat.doeat.menu.service;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Member;
import doeat.doeat.member.domain.Role;
import doeat.doeat.member.dto.MemberDto;
import doeat.doeat.store.domain.Menu;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.request.CreateMenuRequestDto;
import doeat.doeat.store.dto.request.CreateStoreRequestDto;
import doeat.doeat.store.dto.request.UpdateMenuRequestDto;
import doeat.doeat.store.dto.request.UpdateStoreRequestDto;
import doeat.doeat.store.repository.MenuRepository;
import doeat.doeat.store.service.MenuService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@SpringBootTest
public class MenuServiceTest {
    @Autowired MenuService menuService;
    @Autowired MenuRepository menuRepository;

    @Test
    public void 메뉴등록() throws Exception {
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        CreateMenuRequestDto createMenuRequestDto = CreateMenuRequestDto.builder()
                .store(store)
                .explanation("설명")
                .price(10000)
                .build();

        // when then
        Menu menu = menuService.createMenu(createMenuRequestDto);

    }

    @Test
    public void 메뉴ID로찾기() throws Exception {
        Menu menu = Menu.builder()
                .name("메뉴1")
                .explanation("설명")
                .price(10000)
                .build();

        Menu savedMenu = menuRepository.save(menu);
        Long id = savedMenu.getId();

        //when
        Optional<Menu> findMenu = menuRepository.findById(id);
        Menu menuTest = findMenu.orElseThrow(IllegalArgumentException::new);

        //then
        assertEquals(savedMenu.getName(), menuTest.getName());
        assertEquals(savedMenu.getPrice(), menuTest.getPrice());
    }

    @Test
    public void 메뉴변경() throws Exception {

        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        Menu menu = Menu.builder()
                .store(store)
                .name("메뉴1")
                .explanation("설명")
                .price(10000)
                .build();

        Menu savedMenu = menuRepository.save(menu);

        UpdateMenuRequestDto updatedMenu = UpdateMenuRequestDto.builder()
                .name("메뉴2")
                .explanation("설명2")
                .price(20000)
                .build();

        // when
        menuService.updateMenu(savedMenu.getId(), updatedMenu);

        // then
        Optional<Menu> findMenu = menuRepository.findById(savedMenu.getId());
        Menu menuTest = findMenu.orElseThrow(IllegalArgumentException::new);

        assertEquals(Objects.requireNonNullElse(updatedMenu.getName(), menu.getName()), menuTest.getName());
        assertEquals(Objects.requireNonNullElse(updatedMenu.getPrice(), menu.getPrice()), menuTest.getPrice());
        assertEquals(Objects.requireNonNullElse(updatedMenu.getExplanation(), menu.getExplanation()), menuTest.getExplanation());
    }

    @Test
    public void 메뉴삭제() throws Exception{
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        Menu menu = Menu.builder()
                .store(store)
                .name("메뉴1")
                .explanation("설명")
                .price(10000)
                .build();

        Menu savedMenu = menuRepository.save(menu);

        // when
        menuService.deleteMenuById(savedMenu.getId());

        // then
        Optional<Menu> deletedMenuOptional = menuRepository.findById(savedMenu.getId());
        assertFalse(deletedMenuOptional.isPresent());

    }

}
