package com.example.demo.controller;

import javax.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.NguyenLieuService;
import com.example.demo.service.LoaiNguyenLieuService;


import com.example.demo.model.LoaiNguyenLieu;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MaterialTypeController {

    @Autowired
	LoaiNguyenLieuService repositoryLoaiNguyenLieu;
	
	@Autowired
    NguyenLieuService repositoryNguyenLieu;

    // LAY ALL LOAI NGUYEN LIEU
	@RequestMapping(value = "/GetAllLoaiNguyenLieu", method = RequestMethod.GET)
	public ResponseEntity<List<LoaiNguyenLieu>> listAllLoaiNguyenLieu() {
		List<LoaiNguyenLieu> listLoaiNguyenLieu = repositoryLoaiNguyenLieu.findAll();
		if (listLoaiNguyenLieu.isEmpty()) {
			return new ResponseEntity<List<LoaiNguyenLieu>>(HttpStatus.NO_CONTENT);
		}
		// return ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
		return new ResponseEntity<List<LoaiNguyenLieu>>(listLoaiNguyenLieu, HttpStatus.OK);
	}

	// LAY 1 LOAI NGUYEN LIEU
	@RequestMapping(value = "/LoaiNguyenLieu/{id}", method = RequestMethod.GET)

	public LoaiNguyenLieu findLoaiNguyenLieuByID(@PathVariable("id") long id) {
		LoaiNguyenLieu loainguyenlieu = repositoryLoaiNguyenLieu.getOne(id);
		if (loainguyenlieu == null) {
			ResponseEntity.notFound().build();
		}

		return loainguyenlieu;
	}

	// THEM LOAI NGUYEN LIEU
	@RequestMapping(value = "/InsertLoaiNguyenLieu/", method = RequestMethod.POST)
	@ResponseBody
	public LoaiNguyenLieu insertLoaiNguyenLieu(@Valid @RequestBody LoaiNguyenLieu loainguyenlieuForm) {
		// @Valid: kiem tra xem co ton tai object trong body
		LoaiNguyenLieu lnl = repositoryLoaiNguyenLieu.save(loainguyenlieuForm);
		return lnl;
	}

	// CAP NHAT LOAI NGUYEN LIEU
	@RequestMapping(value = "/UpdateLoaiNguyenLieu/", method = RequestMethod.POST)
	public ResponseEntity<LoaiNguyenLieu> updateLoaiNguyenLieu(@Valid @RequestBody LoaiNguyenLieu loainguyenlieuForm) {
		LoaiNguyenLieu lnl = repositoryLoaiNguyenLieu.getOne(loainguyenlieuForm.getId());
		if (lnl == null) {
			return ResponseEntity.notFound().build();
		}

		lnl.setName(loainguyenlieuForm.getName());
		lnl.setUnit(loainguyenlieuForm.getUnit());

		LoaiNguyenLieu updatedLoaiNguyenLieu = repositoryLoaiNguyenLieu.save(lnl);// update trong database
		return ResponseEntity.ok(updatedLoaiNguyenLieu);
	}

	// XOA LOAI NGUYEN LIEU
	@RequestMapping(value = "/DeleteLoaiNguyenLieu", method = RequestMethod.POST)
	public int deleteLoaiNguyenLieu(@Valid @RequestBody LoaiNguyenLieu loainguyenlieu) {
		// @PathVariable(value=""): lay bien tu url
		// @RequestBody: lay object duoc gui trong body
		LoaiNguyenLieu lnl = repositoryLoaiNguyenLieu.getOne(loainguyenlieu.getId());
		if (lnl == null) {
			return 0;
		}
		repositoryLoaiNguyenLieu.delete(lnl);// delete trong database
		return 1;
	}

}