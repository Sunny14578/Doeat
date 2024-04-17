package doeat.doeat.store.service;

import doeat.doeat.store.domain.Menu;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.MenuDto;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.dto.request.CreateMenuRequestDto;
import doeat.doeat.store.dto.request.UpdateMenuRequestDto;
import doeat.doeat.store.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Menu createMenu(CreateMenuRequestDto requestDto) {

        Menu menu = Menu.builder()
                .store(requestDto.getStore())
                .explanation(requestDto.getExplanation())
                .price(requestDto.getPrice())
                .build();

        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, UpdateMenuRequestDto updateMenuDto) {
        Optional<Menu> findMenu = menuRepository.findById(id);

        Menu menu = findMenu.orElseThrow(IllegalArgumentException::new);

        menu.update(
                updateMenuDto.getName(),
                updateMenuDto.getExplanation(),
                updateMenuDto.getPrice(),
                updateMenuDto.isStatus()
        );

        return menu;
    }

    @Transactional
    public MenuDto findMenuById(Long id){
        Optional<Menu> findMenu = menuRepository.findById(id);
        Menu menu = findMenu.orElseThrow(IllegalArgumentException::new);

        MenuDto menuDto = MenuDto.builder()
                .name(menu.getName())
                .explanation(menu.getExplanation())
                .store(menu.getStore())
                .price(menu.getPrice())
                .status(menu.isStatus())
                .build();

        return menuDto;
    }

    @Transactional
    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }
}
