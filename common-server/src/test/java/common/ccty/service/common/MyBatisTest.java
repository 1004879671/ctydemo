package common.ccty.service.common;

import com.alibaba.fastjson.JSON;
import com.ccty.service.common.CommonApplication;
import com.ccty.service.common.dao.SysUserMapper;
import com.ccty.service.common.entity.SysUser;
import com.ccty.service.common.service.SysUserService;
import com.ccty.service.common.util.PageRequest;
import com.ccty.service.common.util.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据元，不加则采用虚拟数据源
@Rollback(false)    //这个是默认是回滚，不会commit入数据库，改成false 则commit

@SpringBootTest(
        classes = CommonApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class MyBatisTest {

    private static Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserService sysUserService;

    @Test
    public void Test(){
        List<SysUser>  userLt =  sysUserMapper.selectPage();
        System.out.println(userLt);
    }

    @Test
    public void Test2(){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(2);
        pageRequest.setPageSize(2);
        PageResult pageResult =  sysUserService.findPage(pageRequest);

        logger.info(JSON.toJSONString(pageResult));


        List<SysUser> list = (List<SysUser>) pageResult.getContent();

        System.out.println(list);

        System.out.println(list.get(0));

        System.out.println(JSON.toJSONString(list));
    }
}
