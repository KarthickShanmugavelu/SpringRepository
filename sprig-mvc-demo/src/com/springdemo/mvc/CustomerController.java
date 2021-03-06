package com.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//code to preprocess each eb request and remove leading and trailing white spaces
	@InitBinder
	public void removeSpace(WebDataBinder dataBinder) {
		
		StringTrimmerEditor  stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,BindingResult theBindingResult) {
		
		System.out.println("FirstName:|"+theCustomer.getFirstName()+"|");
		System.out.println("LastName:|"+theCustomer.getLastName()+"|");
		
		System.out.println("*********");
		System.out.println(theBindingResult);
		System.out.println("*********");
		
		if(theBindingResult.hasErrors())
			return "customer-form";
		else
			return "customer-confirmation";
	}

}
