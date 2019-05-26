package com.smolenskyi.springmvc.dao;

import java.util.List;

import com.smolenskyi.springmvc.entity.Part;

public interface PartDAO {

	public List<Part> getParts();

	public List<Part> getParts(String search, String sort, String filter);

	public void savePart(Part thePart);

	public Part getPart(int theId);

	public void deletePart(int theId);
	
}
