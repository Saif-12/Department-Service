package com.departmentx.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.departmentx.dto.DepartmentDto;
import com.departmentx.entity.Department;
import com.departmentx.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	private final ModelMapper modelMapper;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {

		this.departmentRepository = departmentRepository;
		this.modelMapper = modelMapper;
	}

	public DepartmentDto addDepartment(DepartmentDto departmentDto) {

		Department department = modelMapper.map(departmentDto, Department.class);

		Department savedDepartment = departmentRepository.save(department);

		DepartmentDto deDto = modelMapper.map(savedDepartment, DepartmentDto.class);

		return deDto;

	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new RuntimeException("Department Code is not available"));

		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}

}
