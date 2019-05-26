package com.smolenskyi.springmvc.controller;

import java.util.List;

import com.smolenskyi.springmvc.entity.Part;
import com.smolenskyi.springmvc.service.FilterEntity;
import com.smolenskyi.springmvc.service.PartService;
import com.smolenskyi.springmvc.service.SortEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PartController {
	private int PAGE_SIZE = 10;

	@Autowired
	private PartService partService;

	@GetMapping("/")
	public String index() {
		return "redirect:/list";
	}

	@RequestMapping(value="/list")
	public ModelAndView listCustomers(@RequestParam(required = false) Integer page,
									  @RequestParam(required = false) String search,
									  @RequestParam(required = false) String filter,
									  @RequestParam(required = false) String sort) {
		ModelAndView modelAndView = new ModelAndView("list-parts");
		List<Part> theParts = partService.getParts(search, sort, filter);
		PagedListHolder<Part> pagedListHolder = new PagedListHolder<>(theParts);
		pagedListHolder.setPageSize(PAGE_SIZE);
		modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		modelAndView.addObject("filterList", FilterEntity.values());
		modelAndView.addObject("sortList", SortEntity.values());
		modelAndView.addObject("maxCountPC", partService.getMaxCountPC());
		//check page
		if(page == null || page < 1 || page > pagedListHolder.getPageCount())
			page = 1;
		modelAndView.addObject("page", page);
		if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
			pagedListHolder.setPage(0);
			modelAndView.addObject("parts", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
			modelAndView.addObject("parts", pagedListHolder.getPageList());
		}
		if(search != null && !search.equals("")) {
			modelAndView.addObject("search", search);
		}

		return modelAndView;
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		Part thePart = new Part();
		theModel.addAttribute("part", thePart);
		return "part-form";
	}
	
	@PostMapping("/savePart")
	public String saveCustomer(@ModelAttribute("part") Part thePart) {
		partService.savePart(thePart);
		return "redirect:/list";
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("partId") int theId,
									Model theModel) {
		Part thePart = partService.getPart(theId);
		theModel.addAttribute("part", thePart);
		return "part-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("partId") int theId) {
		partService.deletePart(theId);
		return "redirect:/list";
	}
}
