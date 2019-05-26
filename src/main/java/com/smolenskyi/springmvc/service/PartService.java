package com.smolenskyi.springmvc.service;

import java.util.List;

import com.smolenskyi.springmvc.entity.Part;

public interface PartService {

	public List<Part> getParts();

	public List<Part> getParts(String search, String sort, String filter);

	public void savePart(Part thePart);

	public Part getPart(int theId);

	public void deletePart(int theId);

	public int getMaxCountPC();
}
