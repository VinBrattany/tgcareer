package com.main.tgcareer.modules.sys.login.controller;

import com.main.tgcareer.modules.user.entity.Admin;
import com.main.tgcareer.modules.user.entity.User;
import com.main.tgcareer.modules.user.service.AdminService;
import com.main.tgcareer.modules.user.service.UserService;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminLoginController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModelAndView login(ModelAndView modelAndView, @Valid Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        }

        admin = adminService.getAdmin(admin);
        if(admin == null){
            modelAndView.addObject("error","账号或密码不正确！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("userName",admin.getName());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "userList",method = RequestMethod.GET)
    public ModelAndView userList(ModelAndView modelAndView, Map params){
        modelAndView.addObject("users",userService.getAllUser(params));
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @RequestMapping(value = "excel",method = RequestMethod.GET)
    public void excel(HttpServletResponse response){
        // 设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        ,@RequestBody List<User> userList
        List<User> users = new ArrayList<>();
//        for (int i = 0; i < userList.size(); i++) {
//            users.add(i,userService.getUser(userList.get(i).getId()));
//        }
        Map params = null;
        users = userService.getAllUser(params);
        // 创建工作表
        WritableWorkbook book = null;
        response.reset();

        response.setCharacterEncoding("UTF-8");// 设置字符集

        // 创建工作流
        OutputStream os = null;
        try {
            // 设置弹出对话框
            response.setContentType("application/DOWLOAD");
            response.setCharacterEncoding("UTF-8");

            // 设置工作表的标题
            String fileName = "用户信息"+sf.format(new Date())+".xls";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);// 设置生成的文件名字
                os = response.getOutputStream();

                // 初始化工作表
                book = Workbook.createWorkbook(os);

        } catch (IOException e1) {

            System.out.println("导出excel出现IO异常"+e1);
//           throw new ServiceException("导出失败", e1);
        }
        try {

                // 以下为excel表格内容
                // int nCount = list.size();
            WritableSheet sheet = book.createSheet("用户信息表", 0);

            // 生成名工作表，参数0表示这是第一页
            // int nI = 1;

            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "序号"));
            sheet.addCell(new jxl.write.Label(1, 0, "姓名"));
            sheet.addCell(new jxl.write.Label(2, 0, "职位"));
            sheet.addCell(new jxl.write.Label(3, 0, "公司"));
            sheet.addCell(new jxl.write.Label(4, 0, "城市"));
            sheet.addCell(new jxl.write.Label(5, 0, "性别"));
            sheet.addCell(new jxl.write.Label(6, 0, "电话"));
            sheet.addCell(new jxl.write.Label(7, 0, "大学"));
            sheet.addCell(new jxl.write.Label(8, 0, "学历"));
            sheet.addCell(new jxl.write.Label(9, 0, "是否推送"));
            sheet.addCell(new jxl.write.Label(10, 0, "期望年薪"));
            sheet.addCell(new jxl.write.Label(11, 0, "年薪"));
            sheet.addCell(new jxl.write.Label(12, 0, "月薪"));
            sheet.addCell(new jxl.write.Label(13, 0, "微信昵称"));
            sheet.addCell(new jxl.write.Label(14, 0, "更新时间"));
            sheet.addCell(new jxl.write.Label(15, 0, "创建时间"));

            // 将数据追加
            for (int i = 1; i < users.size() + 1; i++) {
                sheet.addCell(new jxl.write.Label(0, i, String.valueOf(i)));// 序号从1开始
                sheet.addCell(new jxl.write.Label(1, i, users.get(i - 1).getName()));
                sheet.addCell(new jxl.write.Label(2, i, users.get(i - 1).getJob()));
                sheet.addCell(new jxl.write.Label(3, i, users.get(i - 1).getCorporation()));
                sheet.addCell(new jxl.write.Label(4, i, users.get(i - 1).getCity()));
                sheet.addCell(new jxl.write.Label(5, i, users.get(i - 1).isSex()? "男":"女"));
                sheet.addCell(new jxl.write.Label(6, i, users.get(i - 1).getPhone()));
                sheet.addCell(new jxl.write.Label(7, i, users.get(i - 1).getCollege()));
                sheet.addCell(new jxl.write.Label(8, i, users.get(i - 1).getEduction()));
                sheet.addCell(new jxl.write.Label(9, i, users.get(i - 1).isPush()? "是":"否"));
                sheet.addCell(new jxl.write.Label(10, i, String.valueOf(users.get(i - 1).getExpectedAnnualSalary())));
                sheet.addCell(new jxl.write.Label(11, i, String.valueOf(users.get(i - 1).getAnnualSalary())));
                sheet.addCell(new jxl.write.Label(12, i, String.valueOf(users.get(i - 1).getMonthlySalary())));
                sheet.addCell(new jxl.write.Label(13, i, users.get(i - 1).getPetName()));

                Date updateTime = users.get(i - 1).getUpdateTime();
                String updateStr = sf.format(updateTime);
                sheet.addCell(new jxl.write.Label(14, i, updateStr));// 更新日期
                Date createTime = users.get(i - 1).getCreateTime();
                String createStr = sf.format(createTime);
                sheet.addCell(new jxl.write.Label(15, i, createStr));// 创建日期
            }
            book.write();book.close();
        } catch (Exception e) {
                System.out.println("导出excel出现异常"+e);
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("关流出现异常"+e);
                    e.printStackTrace();
                }
            }
        }
    }
}