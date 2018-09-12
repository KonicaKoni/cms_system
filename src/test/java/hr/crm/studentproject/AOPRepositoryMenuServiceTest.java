package hr.crm.studentproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import hr.crm.studentproject.service.MenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AOPRepositoryMenuServiceTest {
	
	@Autowired
	private MenuService repositoryMenuService;

	@Test
	public void get_all_test() {
		repositoryMenuService.getAll();
	}
	
	@Test
	public void get_by_id_test() {
		repositoryMenuService.getById(0);
	}
}
