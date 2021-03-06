package com.example.demo.serviceImpl;

import com.example.demo.dao.NhanVienDao;
import com.example.demo.model.NhanVien;
import com.example.demo.model.NhanVienDto;
import com.example.demo.service.NhanVienService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "nhanvienService")
public class NhanVienServiceImp  implements UserDetailsService, NhanVienService {
	
	@Autowired
	private NhanVienDao nhanvienDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NhanVien nhanvien = nhanvienDao.findByUsername(username);
		if(nhanvien == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(nhanvien.getUsername(), nhanvien.getPassword(), getAuthority(nhanvien.getLoai()));
	}	
	
	private List<SimpleGrantedAuthority> getAuthority(int loainv) {
		return Arrays.asList(new SimpleGrantedAuthority(loainv+""));
	}
	
	@Override
	public List<NhanVien> findAll() {
		List<NhanVien> list = new ArrayList<>();
		nhanvienDao
		.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public NhanVien findById(long id) {
		Optional<NhanVien> optionalNhanVien = nhanvienDao.findById((int) id);
		return optionalNhanVien.isPresent() ? optionalNhanVien.get() : null;
	}
	
	@Override
	public NhanVien findOne(String username) {
		return nhanvienDao.findByUsername(username);
	}
	
	
	@Override
	public void delete(int id) {
		nhanvienDao.deleteById(id);
	}

	@Override
    public NhanVienDto updateNhanVien(NhanVienDto nhanvienDto) {
        NhanVien nhanvien = findById(nhanvienDto.getId());
        if(nhanvien != null) {
            BeanUtils.copyProperties(nhanvienDto, nhanvien, "password");
            nhanvienDao.save(nhanvien);
        }
        return nhanvienDto;
    }
	
	 @Override
	    public NhanVien saveNhanVien(NhanVienDto nhanvien) {
		    NhanVien newNhanVien = new NhanVien();
		    newNhanVien.setNo(nhanvien.getNo());
		    newNhanVien.setFullname(nhanvien.getFullname());
		    newNhanVien.setPhone(nhanvien.getPhone());
		    newNhanVien.setUsername(nhanvien.getUsername());
		    newNhanVien.setPassword(bcryptEncoder.encode(nhanvien.getPassword()));
			newNhanVien.setLoai(nhanvien.getLoai());
			newNhanVien.setImg(nhanvien.getImg());
	        return nhanvienDao.save(newNhanVien);
	    }
	 
}
