package cn.jiyun.mapper;

import cn.jiyun.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpMapper {
    //查询全部
    List<Emp> queryAllEmp();
    //删除
    void  deleteEmp(@Param(value="eid")Integer eid);
    //添加
    void addEmp(@Param(value="emp")Emp emp);
    //根据id查询
    Emp findById(@Param(value="eid")Integer eid);
    //修改
    void update (@Param(value="emp")Emp  emp);
}
