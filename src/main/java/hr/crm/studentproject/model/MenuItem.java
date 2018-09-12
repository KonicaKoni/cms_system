package hr.crm.studentproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MENUITEM")
public class MenuItem extends RootEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8117497884807671886L;

	@Embedded
	private Item item;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENUID", nullable = false)
	private Menu menu;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMenuItem", cascade = CascadeType.ALL)
	private Set<MenuSubItem> subMenuItems = new HashSet<>(0);
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Set<MenuSubItem> getSubMenuItems() {
		return subMenuItems;
	}
	public void setSubMenuItems(Set<MenuSubItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((subMenuItems == null) ? 0 : subMenuItems.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (subMenuItems == null) {
			if (other.subMenuItems != null)
				return false;
		} else if (!subMenuItems.equals(other.subMenuItems))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuItem [item=" + item + ", menu=" + menu + ", subMenuItems=" + subMenuItems + "]";
	}
}
