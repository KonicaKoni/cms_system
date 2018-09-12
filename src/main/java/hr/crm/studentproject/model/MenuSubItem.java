package hr.crm.studentproject.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MenuSubItem extends RootEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5193566188393886921L;
	
	@Embedded
	private Item item;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTMENUITEMID")
	private MenuItem parentMenuItem;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTMENUSUBITEMID")
	private MenuSubItem parentMenuSubItem;
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public MenuItem getParentMenuItem() {
		return parentMenuItem;
	}
	public void setParentMenuItem(MenuItem parentMenuItem) {
		this.parentMenuItem = parentMenuItem;
	}
	public MenuSubItem getParentMenuSubItem() {
		return parentMenuSubItem;
	}
	public void setParentMenuSubItem(MenuSubItem parentMenuSubItem) {
		this.parentMenuSubItem = parentMenuSubItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((parentMenuItem == null) ? 0 : parentMenuItem.hashCode());
		result = prime * result + ((parentMenuSubItem == null) ? 0 : parentMenuSubItem.hashCode());
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
		MenuSubItem other = (MenuSubItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (parentMenuItem == null) {
			if (other.parentMenuItem != null)
				return false;
		} else if (!parentMenuItem.equals(other.parentMenuItem))
			return false;
		if (parentMenuSubItem == null) {
			if (other.parentMenuSubItem != null)
				return false;
		} else if (!parentMenuSubItem.equals(other.parentMenuSubItem))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuSubItem [item=" + item + ", parentMenuItem=" + parentMenuItem + ", parentMenuSubItem="
				+ parentMenuSubItem + "]";
	}
}
