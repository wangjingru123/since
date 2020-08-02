package cn.jiyun.controller;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Emp;
import cn.jiyun.service.DeptService;
import cn.jiyun.service.EmpService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {
    @Value("${file.upload.path}")
    private String filePath;


    @Autowired
    private EmpService empService;
    @Autowired
    private DeptService deptService;

    @GetMapping("show")
    @ResponseBody
    public String  show(){
        return "hello word";
    }

  //查询全部
    @GetMapping("/queryAllEmp")
    public String  queryAllEmp(Model model){
        List<Emp> elist= empService.queryAllEmp();
        model.addAttribute("elist",elist);
        return "empList";
    }

    //删除
    @GetMapping("deleteEmp")
    public String deleteEmp(@RequestParam(value="eid") Integer eid){
        empService.deleteEmp(eid);
        return "redirect:/emp/queryAllEmp";
    }
    //添加跳转
    @GetMapping("toAdd")
    public  String toAdd(Model model){
        List<Dept> dlist=deptService.findAll();
        model.addAttribute("dlist",dlist);
        return "toAdd";
    }

    @PostMapping("addEmp")
    public String addEmp(@ModelAttribute(value="emp")Emp emp,@RequestParam(value="file")MultipartFile  file) throws IOException{
      String fileName= file.getOriginalFilename();
      File photoFile=new File(filePath,fileName);
      //判断当前系统是否在上传路径，如果不存在则新建
        if(!photoFile.getParentFile().exists()){
            photoFile.getParentFile().mkdirs();
        }
            file.transferTo(new File(filePath+File.separator+fileName));
        emp.setPhoto("/photo/"+fileName);
        empService.addEmp(emp);
        //System.out.println("aaa"+emp);
        return "redirect:/emp/queryAllEmp";

    }
     @GetMapping("findById")
    public String findById(Integer eid,Model model){
        Emp emp=empService.findById(eid);
        model.addAttribute("emp",emp);
        List<Dept> dlist=deptService.findAll();
        model.addAttribute("dlist",dlist);
        return "update";
     }

    @PostMapping("update")
    public String update(@ModelAttribute(value="emp")Emp emp,@RequestParam(value="file")MultipartFile  file) throws IOException{
        String fileName= file.getOriginalFilename();
        File photoFile=new File(filePath,fileName);
        //判断当前系统是否在上传路径，如果不存在则新建
        if(!photoFile.getParentFile().exists()){
            photoFile.getParentFile().mkdirs();
        }
        file.transferTo(new File(filePath+File.separator+fileName));
        emp.setPhoto("/photo/"+fileName);
        empService.update(emp);
        System.out.println("aaa"+emp);
        return "redirect:/emp/queryAllEmp";

    }
}
