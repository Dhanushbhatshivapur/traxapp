package com.traxcrm.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.traxcrm.entities.Contact;
import com.traxcrm.entities.Lead;
import com.traxcrm.services.ContactService;
import com.traxcrm.services.LeadService;





@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/")
	public String viewLeadPage() {
		return "view_lead_page";
	}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead") Lead lead , ModelMap model) {
		leadService.saveLead(lead);
		model.addAttribute("lead", lead); // Modelmap - model is for displays the lead info with convert button.
		
		return "lead_info";
	}
	
	@RequestMapping(value="/convertLead", method = RequestMethod.POST)
	public String convertLead(@RequestParam("id") long id, ModelMap model) {

		System.out.println(id); // checking if it is  coming to the backend
    		Lead lead = leadService.getLeadById(id); // this will give us the lead Object
	
    		Contact contact = new Contact();// we are taking the data from the lead , initializing contact object
    		contact.setFirstName(lead.getFirstName());
    		contact.setLastName(lead.getLastName());
    		contact.setEmail(lead.getEmail());
    		contact.setLeadSource(lead.getLeadSource());
    		contact.setMobile(lead.getMobile());
    		contact.setGender(lead.getGender());
    		contactService.saveContact(contact); ////its taking data from the lead putting in contact and saving in contact
    						// after saving the contact we will delete the lead which will develop a method next
    		leadService.deleteOnelead(id); // to delete the lead after saving in contact
    		
    		List<Contact> contacts = contactService.listAll(); // this will call teh contact page to get the contact details
    		model.addAttribute("contacts", contacts);
    		return "contact_search_result"; // need to create the page
	}
	
	@RequestMapping("/listall")
	public String getAllLeads(Model map){
		List<Lead> leads = leadService.listall();
		map.addAttribute("leads", leads);
		return "lead_search_result";
	} 

	@RequestMapping("/getLeadById")
	public String getLeadById(@RequestParam("id") long id , ModelMap model) {
		Lead lead = leadService.getLeadById(id);  //give us the lead -- which we developed it earlier
		model.addAttribute("lead", lead); // Modelmap - model is for displays the lead info with convert button.
		return "lead_info";
	}
	
}
