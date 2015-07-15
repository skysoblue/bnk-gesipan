package controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MemberBean;
import factory.CommandFactory;
import factory.Command;
import serviceImpl.MemberServiceImpl;
 
/**
 * @ Date : 2015.06;
 * @ Author : itb-1;
 * @ Story : 회원관리 컨트롤러;
 */
@WebServlet({"/member/join.do","/member/login.do",
    "/member/searchIdForm.do","/member/searchPassForm.do",
    "/member/searchAllMembers.do"})
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    Map<String,Object> map = new HashMap<String,Object>();
    MemberBean bean = new MemberBean();
    
    CommandFactory factory = new CommandFactory();
    String view;
    Command command;
    
    
    public void init(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
    	request.setCharacterEncoding("UTF-8");
    	String directory = request.getServletPath().split("/")[1];
    	String extention = request.getServletPath().split("/")[2];
    	String action = extention.substring(0, extention.indexOf("."));
    	System.out.println("디렉토리 :" + directory);
    	System.out.println("명령어 : " + action);
    	String pageNo = request.getParameter("pageNo");
    	String keyField = request.getParameter("keyField");
    	String keyword = request.getParameter("keyword");
    	if(action==null){action="frame";}
    	if(pageNo==null){pageNo="1";}
    	if(keyField==null){keyField="null";}
    	if(keyword==null){keyword="null";}
    	command = factory.createCommand(directory,action,pageNo,keyField,keyword);
    }
    
    protected void service(HttpServletRequest request, 
    		HttpServletResponse response)
            throws ServletException, IOException {
    	init(request,response);
        switch (command.getAction()) {
        case "joinForm" :
        	dispatcherServlet(request,response,command);
            break;
        case "searchIdForm" :
        	dispatcherServlet(request,response,command);
            break;
        case "searchPassForm": 
        	dispatcherServlet(request,response,command);
            break;
        case "searchAllMembers": 
            request.setAttribute("memberList", MemberServiceImpl.getInstance().memberList(command));
            dispatcherServlet(request,response,command);
            break;
        case "join" : 
            String name = request.getParameter("name");
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String age = request.getParameter("age");
            String email = request.getParameter("email");
            bean.setName(name);
            bean.setEmail(email);
            bean.setMemId(id);
            bean.setPassword(password);
            bean.setAge(age);
            
            MemberServiceImpl.getInstance().joinMember(bean);
            dispatcherServlet(request,response,command);
            break;
        case "login" : 
            
            String loginId = request.getParameter("id");
            String loginPass = request.getParameter("password");
            command.setKeyField(loginId);
            command.setKeyword(loginPass);
            String msg = MemberServiceImpl.getInstance().login(command);
            
            if(msg.equals("환영합니다..")){
               HttpSession session = request.getSession();
               session.setAttribute("loginId", loginId);
               request.setAttribute("loginId", loginId);
                dispatcherServlet(request,response,command);
                break;
            }else{
                request.setAttribute("msg", msg);
                dispatcherServlet(request,response,command);
                break;
            }    
        default:
            break;
        }
    }
    public void dispatcherServlet(HttpServletRequest request, 
    		HttpServletResponse response,
    		Command commmand) throws ServletException, IOException{
    	RequestDispatcher dis = request.getRequestDispatcher(
    			"/views/"+command.getDirectory()
    			+"/"+command.getView()+".jsp");
		dis.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	service(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	service(request,response);
    }
}