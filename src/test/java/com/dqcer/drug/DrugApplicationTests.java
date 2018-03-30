package com.dqcer.drug;

import com.dqcer.drug.web.service.UserService;
import com.dqcer.drug.web.vo.UserVo;
import com.dqcer.drug.web.vo.common.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugApplicationTests {

	@Autowired
	private UserService userService;

	/**
	 * 测试UserDao接口
	 */
	@Test
	public void testUserDao(){
		UserVo vo = new UserVo();
		vo.setUsername("admin");
		Result admin = userService.findUser(vo);
		System.out.println(admin.toString());
	}



}
