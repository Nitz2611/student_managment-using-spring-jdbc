package com.spring.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.entities.Student;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "my Application started" );
        //spring jdbc=>JdbcTemplate
        //this statement is for xml configuration
       //ApplicationContext context=new ClassPathXmlApplicationContext("com/spring/jdbc/config.xml");
       ApplicationContext context=new AnnotationConfigApplicationContext(JdbcConfig.class);
       StudentDao studentDao = context.getBean("studentDao",StudentDao.class);
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       
       while(true) {
	   System.out.println("PRESS 1 to ADD Student");
	   System.out.println("PRESS 2 to Delete student");
	   System.out.println("PRESS 3 to display student");
	   System.out.println("PRESS 4 to update student");
	   System.out.println("PRESS 5 to display single student  data");
	   System.out.println("PRESS 6 to exit app");
	   
	   int key=Integer.parseInt(br.readLine());
	   
	   //Another approach to execute query and fetch data from database
        //JdbcTemplate template= context.getBean("jdbcTemplate",JdbcTemplate.class);
       //insert query
      // String query="insert into student(id,name,city) values(?,?,?)";
       //fire the query
//       int result = template.update(query,24,"kapur sahab","unnao");
//       System.out.println("number of record inserted.."+result);

        if(key==1) {
    //inserting student
        	Student student=new Student();
        	System.out.println("Enter student id: ");
        	int id=Integer.parseInt(br.readLine());
        	
        	System.out.println("Enter student name: ");
        	String name=br.readLine();
        	
        	System.out.println("Enter student city");
        	String city=br.readLine();
        student.setId(id);
        student.setName(name);
        student.setCity(city);
        
        int result = studentDao.insert(student);
        
        System.out.println("student added "+result);
        }
        
        else if(key==2) {
            //Delete
            	System.out.println("Enter student id to delete");
            	int id=Integer.parseInt(br.readLine());
            int res=studentDao.delete(id);
            System.out.println("student deleted"+res);
            }
        
        else if(key==3) {
            //	select all student
                	System.out.println("showing data of all students");
                	
                List<Student> res=studentDao.getAllStudents();
                for(Student s:res)
                	System.out.println(s);
                }
        
        else if(key==4) {
       // updating data
        	Student student=new Student();
        	
 		   System.out.println("Enter student id to update ");
 		   int userId=Integer.parseInt(br.readLine());
 		   //enter updated details
 		   System.out.println("Enter user name: ");
 		   String name=br.readLine();
 		   
 		   System.out.println("Enter user city: ");
 		   String city=br.readLine();
        student.setId(userId);
        student.setName(name);
        student.setCity(city);
        
        int res = studentDao.change(student);
        System.out.println("student updated"+res);
        }
       
        else if(key==5) {
        //select single student  data
        	System.out.println("Enter student id to show data");
        	int id=Integer.parseInt(br.readLine());
        Student res=studentDao.getStudent(id);
              System.out.println(res);
        }
        
     
        else if(key==6){
 		   //EXIT
 		   break;
 	   }
 	   else {
 		   System.out.println("Entered invalid Number, Please Enter again.");
 	   }
        }
       
       System.out.println("Thank you for using the app");
       System.out.println("Have a nice day");
    }
}
