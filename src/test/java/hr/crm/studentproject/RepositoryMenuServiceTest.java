package hr.crm.studentproject;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import hr.crm.studentproject.exception.MenuNotFoundException;
import hr.crm.studentproject.model.Menu;
import hr.crm.studentproject.repository.MenuRepository;
import hr.crm.studentproject.service.MenuService;
import hr.crm.studentproject.service.RepositoryMenuService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RepositoryMenuServiceTest {

	private MenuService repositoryMenuService;

	private MenuRepository menuRepositoryMock;

	private static final Integer MENU_ID = 1;
	private static final String MENU_NAME = "Menu";
	private static final String MENU_NAME_UPDATED = "MenuUpdated";
	private static final String MENU_DESC = "Desc";
	private static final String MENU_DESC_UPDATED = "DescUpdated";

	@Before
	public void setUp() {
		menuRepositoryMock = mock(MenuRepository.class);
		repositoryMenuService = new RepositoryMenuService(menuRepositoryMock);
	}

	@Test
	public void create_new_entity_test() {
		Menu created = new Menu();
		created.setName(MENU_NAME);
		created.setDesc(MENU_DESC);

		Menu persisted = new Menu();
		persisted.setId(MENU_ID);
		persisted.setName(MENU_NAME);
		persisted.setDesc(MENU_DESC);

		when(menuRepositoryMock.save(any(Menu.class))).thenReturn(persisted);

		Menu returned = repositoryMenuService.save(created);

		ArgumentCaptor<Menu> menuArgumentCaptor = ArgumentCaptor.forClass(Menu.class);
		verify(menuRepositoryMock, times(1)).save(menuArgumentCaptor.capture());
		verifyNoMoreInteractions(menuRepositoryMock);

		assertMenu(created, menuArgumentCaptor.getValue());
		assertEquals(persisted, returned);
	}

	@Test
	public void update_existing_entity_test() throws MenuNotFoundException {
		Menu created = new Menu();
		created.setName(MENU_NAME);
		created.setDesc(MENU_DESC);
		
		Menu persisted = new Menu();
		persisted.setName(MENU_NAME);
		persisted.setDesc(MENU_DESC);
		
		Menu updated = new Menu();
		updated.setId(MENU_ID);
		updated.setName(MENU_NAME_UPDATED);
		updated.setDesc(MENU_DESC_UPDATED);
		
		when(menuRepositoryMock.save(any(Menu.class))).thenReturn(persisted);

		Menu returned = repositoryMenuService.save(created);

		ArgumentCaptor<Menu> menuArgumentCaptor = ArgumentCaptor.forClass(Menu.class);
		verify(menuRepositoryMock, times(1)).save(menuArgumentCaptor.capture());
		verifyNoMoreInteractions(menuRepositoryMock);

		assertMenu(created, menuArgumentCaptor.getValue());
		assertEquals(persisted, returned);
		
		when(menuRepositoryMock.save(updated)).thenReturn(updated);
		
		Menu returnedUpdate = repositoryMenuService.save(updated);
		
		verify(menuRepositoryMock, times(1)).save(updated);
		verifyNoMoreInteractions(menuRepositoryMock);

		assertEquals(updated, returnedUpdate);
	}

	@Test
	public void get_entity_by_id_test() {
		Menu menu = new Menu();
		menu.setId(MENU_ID);
		menu.setName(MENU_NAME);
		menu.setDesc(MENU_DESC);
		
		when(menuRepositoryMock.getOne(MENU_ID)).thenReturn(menu);
		
		Menu returned = repositoryMenuService.getById(MENU_ID);
		
		verify(menuRepositoryMock, times(1)).getOne(MENU_ID);
		verifyNoMoreInteractions(menuRepositoryMock);
		
		assertMenu(menu, returned);
	}

	@Test
	public void get_all_entities_test() {
        List<Menu> menus = new ArrayList<Menu>();
        when(menuRepositoryMock.findAll()).thenReturn(menus);

        List<Menu> returned = repositoryMenuService.getAll();
        
        verify(menuRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(menuRepositoryMock);
        
        assertEquals(menus, returned);
	}

	@Test
	public void delete_existing_entity_test() {
		Menu deleted = new Menu();
		deleted.setId(MENU_ID);
		deleted.setName(MENU_NAME);
		deleted.setDesc(MENU_DESC);

		when(menuRepositoryMock.getOne(MENU_ID)).thenReturn(deleted);

		repositoryMenuService.delete(MENU_ID);

		verify(menuRepositoryMock, times(1)).getOne(MENU_ID);
		verify(menuRepositoryMock, times(1)).delete(deleted);
		verifyNoMoreInteractions(menuRepositoryMock);
		
		Menu returned = menuRepositoryMock.getOne(MENU_ID);

		assertEquals(deleted, returned);
	}

	private void assertMenu(Menu expected, Menu actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDesc(), expected.getDesc());
	}

}
