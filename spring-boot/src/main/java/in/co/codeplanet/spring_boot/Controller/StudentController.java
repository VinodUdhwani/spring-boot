package in.co.codeplanet.spring_boot.Controller;

import in.co.codeplanet.spring_boot.bean.EmailDetails;
import in.co.codeplanet.spring_boot.bean.Student;
import in.co.codeplanet.spring_boot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;


@RestController
public class StudentController {

    @Autowired
    private EmailService emailService;

    @GetMapping("rollno")
    public int rollNo(@RequestParam String name,String fatherName){
        try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","vinod123")){
            String query="select rollNo from student_info where name=? and fatherName=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,fatherName);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            else{
                return -1;
            }
        }
        catch (Exception e){
           return 0;
        }
    }

    @GetMapping("result")
    public String result(@RequestParam int rollNo){
        try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","vinod123")){
            String query="select percentage from student_info where rollNo=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,rollNo);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getDouble(1)>=60)
                    return "First Division";
                else if (resultSet.getDouble(1)>=48) {
                    return "Second Division";
                }
                else if(resultSet.getDouble(1)>=33){
                    return "Third Division";
                }
                else{
                    return "Fail";
                }
            }
            else{
                return "Invalid RollNo";
            }
        }
        catch (Exception e){
            return "Something went wrong.Try again";
        }
    }


    @GetMapping("profile")
    public Student profile(@RequestParam int rollNo){
        try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","vinod123")){
            String query="select * from student_info where rollNo="+rollNo;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                return new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getString(5));
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @GetMapping("profile1")
    public String profile1(@RequestParam  int id,String name){
        return "your id is "+id+" and name is "+name;
    }


    @PostMapping("create")
    public String create(@RequestBody Student student){
        try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","vinod123")){
            String query="insert into student_info values(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,student.getRollNo());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getCollageName());
            preparedStatement.setDouble(4,student.getPercentage());
            preparedStatement.setString(5, student.getFatherName());
            preparedStatement.executeUpdate();
            return "your details has been submitted";
        }
        catch (Exception e){
            return "Something went wrong.Try again";
        }
    }

    @PostMapping("sendmail")
    public String send(@RequestBody EmailDetails emailDetails){
       return emailService.sendMail(emailDetails);
    }
}
