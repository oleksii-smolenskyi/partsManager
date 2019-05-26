package com.smolenskyi.springmvc.service;

import java.util.List;

import com.smolenskyi.springmvc.dao.PartDAO;
import com.smolenskyi.springmvc.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartDAO partDAO;
	
	@Override
	@Transactional
	public List<Part> getParts() {
		return partDAO.getParts();
	}

	@Override
	@Transactional
	public List<Part> getParts(String search, String sort, String filter) {
		return partDAO.getParts(search, sort, filter);
	}

	@Override
	@Transactional
	public void savePart(Part thePart) {
		partDAO.savePart(thePart);
	}

	@Override
	@Transactional
	public Part getPart(int theId) {
		return partDAO.getPart(theId);
	}

	@Override
	@Transactional
	public void deletePart(int theId) {
		partDAO.deletePart(theId);
	}

	@Override
	@Transactional
	public int getMaxCountPC() {
		List<Part> allParts = getParts();
		int maxCountPC = 0;
		for(Part part : allParts) {
			if(part.isRequirement()) {
				if(part.getCountInStock() == 0) {
					return 0;
				}
				if(maxCountPC == 0 || maxCountPC > part.getCountInStock()) {
					maxCountPC = part.getCountInStock();
				}
			}
		}
		return maxCountPC;
	}
}





