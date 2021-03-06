package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BanService;

import com.example.demo.model.Ban;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TableController {
    @Autowired
	BanService repositoryBan;

	// LAY ALL BAN
	@RequestMapping(path = "/GetAllBan", produces = MediaType.APPLICATION_JSON_VALUE)
	public java.util.List<Ban> GetAllBans() {
		// This returns a JSON or XML with the users
		return repositoryBan.GetAllBans();
	}

	// LAY 1 BAN
	@RequestMapping(value = "/Ban/{id}", method = RequestMethod.GET)
	public Ban FindBanByID(@PathVariable("id") long id) {
		return repositoryBan.GetBan(id);
	}

	// THEM BAN
	@RequestMapping(value = "/InsertBan", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public boolean InsertBan(Ban banForm) {

		try {
			return repositoryBan.InsertBan(banForm);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	// CAP NHAT BAN
	@RequestMapping(value = "/UpdateBan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public boolean UpdateBan(@Valid Ban banForm) {
		try {
			return repositoryBan.UpdateBan(banForm);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	// XOA BAN
	@RequestMapping(value = "/DeleteBan/{id}", method = RequestMethod.POST)
	public boolean DeleteBan(@PathVariable(value = "id") Long id) {
		try {
			return repositoryBan.DeleteBan(id);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// TIM KIEM
	@RequestMapping(value = "/SearchBans/{key}", method = RequestMethod.GET)
	public List<Ban> SearchBans(@PathVariable(value = "key") String key) {
		try {
			return repositoryBan.SearchBans(key);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	// CAP NHAT TRANG THAI BAN

	@RequestMapping(value = "/UpdateStatusBan", method = RequestMethod.GET)
	public void UpdateStatusBan() {
		repositoryBan.CapNhatTrangThaiBan("aaa", "Bàn 1");
	}
}