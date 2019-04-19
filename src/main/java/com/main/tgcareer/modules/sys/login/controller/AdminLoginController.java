package com.main.tgcareer.modules.sys.login.controller;

import com.main.tgcareer.common.jason.Ajax;
import com.main.tgcareer.common.jason.AjaxEnum;
import com.main.tgcareer.common.jason.AjaxJson;
import com.main.tgcareer.common.redis.RedisServiceImpl;
import com.main.tgcareer.modules.salary.entity.Salary;
import com.main.tgcareer.modules.salary.service.SalaryService;
import com.main.tgcareer.modules.user.entity.Admin;
import com.main.tgcareer.modules.user.entity.User;
import com.main.tgcareer.modules.user.service.AdminService;
import com.main.tgcareer.modules.user.service.UserService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminLoginController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    SalaryService salaryService;

    @Autowired
    RedisServiceImpl redisService;

    private static long Time = 86400;//一天

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView modelAndView,@RequestParam String token){
        if (redisService.isKeyExists(token)){
            redisService.remove(token);
        }
        modelAndView.setViewName("redirect:login");
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
        String token = DigestUtils.md5Hex(admin.getId()+System.currentTimeMillis());
        if (redisService.isKeyExists(admin.getId()) && redisService.isKeyExists(redisService.get(admin.getId()).toString())){
            redisService.remove(redisService.get(admin.getId()).toString());
            redisService.remove(admin.getId());
        }
        redisService.put(token, "1", Time);
        redisService.put(admin.getId(), token, Time);
        modelAndView.addObject("id",admin.getId());
        modelAndView.addObject("token",token);
        modelAndView.setViewName("redirect:index");
        return modelAndView;
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView, @RequestParam String id, @RequestParam String token){
        modelAndView.addObject("userName",adminService.getAdminById(id).getName());
        modelAndView.addObject("token",token);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "salaryPost",method = RequestMethod.GET)
    public ModelAndView salaryPost(ModelAndView modelAndView){
        modelAndView.setViewName("salaryPost");
        return modelAndView;
    }

    @RequestMapping(value = "userDownload",method = RequestMethod.GET)
    public ModelAndView userDownload(ModelAndView modelAndView){
        modelAndView.setViewName("userDownload");
        return modelAndView;
    }

    @RequestMapping(value = "userList",method = RequestMethod.GET)
    public ModelAndView userList(ModelAndView modelAndView, @RequestParam(defaultValue = "1") Integer indexPage){
        Map<String,Integer> params = new HashMap<>();
        params.put("start",indexPage);
        params.put("limit",10);
        modelAndView.addObject("users",userService.getAllUser(params));
        modelAndView.addObject("totalPage",Math.ceil(userService.getAllUser(null).size()/10.0));
        modelAndView.addObject("indexPage",indexPage);
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @RequestMapping(value = "salaryList",method = RequestMethod.GET)
    public ModelAndView salaryList(ModelAndView modelAndView, @RequestParam(defaultValue = "1") Integer indexPage){
        Map<String,Integer> params = new HashMap<>();
        params.put("start",indexPage);
        params.put("limit",10);
        modelAndView.addObject("salaries",salaryService.getSalary(params));
        modelAndView.addObject("totalPage",Math.ceil(salaryService.getSalary(null).size()/10.0));
        modelAndView.addObject("indexPage",indexPage);
        modelAndView.setViewName("salaryList");
        return modelAndView;
    }

    @RequestMapping(value = "download",method = RequestMethod.GET)
    public void download(HttpServletResponse response, Map params){
        // 设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        ,@RequestBody List<User> userList
        List<User> users = new ArrayList<>();
//        for (int i = 0; i < userList.size(); i++) {
//            users.add(i,userService.getUser(userList.get(i).getId()));
//        }
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
            sheet.addCell(new jxl.write.Label(9, 0, "年龄"));
//            sheet.addCell(new jxl.write.Label(9, 0, "是否推送"));
            sheet.addCell(new jxl.write.Label(10, 0, "期望年薪"));
            sheet.addCell(new jxl.write.Label(11, 0, "当前年薪"));
            sheet.addCell(new jxl.write.Label(12, 0, "月薪"));
            sheet.addCell(new jxl.write.Label(13, 0, "微信昵称"));
            sheet.addCell(new jxl.write.Label(14, 0, "更新时间"));
            sheet.addCell(new jxl.write.Label(15, 0, "创建时间"));
            sheet.addCell(new jxl.write.Label(16, 0, "登录次数"));
            sheet.addCell(new jxl.write.Label(17, 0, "查看职位次数"));

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
                sheet.addCell(new jxl.write.Label(9, i, String.valueOf(users.get(i - 1).getAge())));
//                sheet.addCell(new jxl.write.Label(9, i, users.get(i - 1).isPush()? "是":"否"));
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
                sheet.addCell(new jxl.write.Label(16, i, String.valueOf(users.get(i-1).getLogin())));
                sheet.addCell(new jxl.write.Label(16, i, String.valueOf(users.get(i-1).getLooks())));
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

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson upload(MultipartFile file){

        // 设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
//        System.out.println(file.getOriginalFilename());
        Workbook book  = null;
        try {
            book = Workbook.getWorkbook(file.getInputStream());
            //  获得第一个工作表对象
            Sheet sheet  =  book.getSheet( 0 );
            for (int i = 1; i < sheet.getRows(); i++) {
                Salary salary = new Salary();
                    Cell[] cell  =  sheet.getRow(i);
                    salary.setCity(cell[1].getContents());
                    salary.setJob(cell[2].getContents());
                    salary.setCorporation(cell[3].getContents());
                    salary.setContact(cell[4].getContents());
                    salary.setPublicTime(sf.parse(cell[5].getContents()));
                    salaryService.saveSalary(salary);
            }
            book.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            map.put("message",e.toString());
            return Ajax.error(AjaxEnum.UNKOWN_ERROR,map);
        }

        return Ajax.success();
    }
}
