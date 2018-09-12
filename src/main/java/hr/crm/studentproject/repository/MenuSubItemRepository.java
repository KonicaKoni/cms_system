package hr.crm.studentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.crm.studentproject.model.MenuSubItem;

@Repository
public interface MenuSubItemRepository extends JpaRepository<MenuSubItem, Integer> {

}
