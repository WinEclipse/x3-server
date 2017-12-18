package com.x3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.x3.core.ResponseState;
import com.x3.core.dto.ParamsBody;
import com.x3.core.dto.ReturnBody;
import com.x3.model.Student;

@RestController
@RequestMapping("/api")
@Service
public class StudentController {

	@RequestMapping(value = "/students", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Student> getAll() {
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setId("id" + i);
			student.setName("name" + i);
			student.setEmail("mail" + i);
			student.setAge(10 + i);
			students.add(student);
		}
		return students;
	}

	@RequestMapping(value = { "/student" }, method = RequestMethod.POST)
	@ResponseBody
	public ReturnBody addStudent(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
		System.out.println("add student......");
		ReturnBody rbody = new ReturnBody();
		try {
			Map<String, Object> body = paramsBody.getBody();
			System.out.println(body.toString());

			Student student = new Student();
			BeanUtils.populate(student, body);
			System.out.println("=======" + student.getEmail());
			rbody.setData(student);
			rbody.setStatus(ResponseState.SUCCESS);
			rbody.setMsg("添加成功");
		} catch (Exception e) {
			rbody.setStatus(ResponseState.FAILED);
			rbody.setMsg("添加失败");
		}
		return rbody;
	}

	@RequestMapping(value = { "/student" }, method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public ReturnBody updateStudent(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
		ReturnBody rbody = new ReturnBody();
		Map params = paramsBody.getBody();
		Student student = new Student();
		System.out.println("student: " + params.toString());
		BeanUtils.populate(student, params);

		rbody.setStatus(ResponseState.SUCCESS);
		rbody.setMsg("修改成功");
		return rbody;
	}

	@RequestMapping(value = { "/delStudent" }, method = RequestMethod.POST)
	@ResponseBody
	public ReturnBody deleteStudent(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
		ReturnBody rbody = new ReturnBody();
		Map params = paramsBody.getBody();
		String studentId = (String) params.get("studentId");

		System.out.println("delete sutdent: " + studentId);
		rbody.setStatus(ResponseState.SUCCESS);
		rbody.setMsg("删除成功");
		return rbody;
	}

}
