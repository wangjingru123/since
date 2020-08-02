package cn.jiyun.service;

import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    //查询全部
    public List<Emp> queryAllEmp(){
        return empMapper.queryAllEmp();
    }
    //删除
    public void deleteEmp(Integer eid){
        empMapper.deleteEmp(eid);
    }

    //添加
   public void addEmp(Emp emp){
       empMapper.addEmp(emp);
   }
   //根据id查询
    public Emp findById(Integer eid){
       return empMapper.findById(eid);
    }
    //修改
    public void update(Emp emp){
        empMapper.update(emp);

    }
}
