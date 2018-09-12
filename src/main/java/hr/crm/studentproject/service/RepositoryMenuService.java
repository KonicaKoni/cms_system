package hr.crm.studentproject.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.crm.studentproject.exception.MenuNotFoundException;
import hr.crm.studentproject.model.Menu;
import hr.crm.studentproject.repository.MenuRepository;

@Service
public class RepositoryMenuService implements MenuService {

	@Resource
	private MenuRepository menuRepository;

	public RepositoryMenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public List<Menu> getAll() {
		return menuRepository.findAll();
	}

	@Override
	public Menu getById(int entityId) {
		return menuRepository.getOne(entityId);
	}

	@Transactional
	@Override
	public Menu save(Menu entity) {
		return menuRepository.save(entity);
	}

	@Transactional
	@Override
	public Menu update(Menu entity) {

		Menu menu = menuRepository.getOne(entity.getId());

		if (menu == null) {
			throw new MenuNotFoundException("Menu with id: " + entity.getId() + " not found!");
		}

		return menuRepository.save(menu);
	}

	@Transactional
	@Override
	public Menu delete(int entityId) {

		Menu deleted = menuRepository.getOne(entityId);

		if (deleted == null) {
			throw new MenuNotFoundException("Menu with id: " + entityId + " not found!");
		}

		menuRepository.delete(deleted);

		return deleted;
	}

	@Override
	public JpaRepository<Menu, Integer> getRepository() {
		return menuRepository;
	}
}
