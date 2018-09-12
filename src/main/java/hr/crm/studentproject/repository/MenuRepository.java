package hr.crm.studentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.crm.studentproject.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
