package com.retreat.shebuel.spitraining;

import android.icu.text.PluralRules;
import android.support.annotation.Keep;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shebuel on 14-06-2017 at 14:01.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

@Keep
public class Employee {
    private String EmployeeCode,Password,EmployeeName,DOJ,Department,Designation,Site,Gender,DOB;

    public Employee(){}

    public Employee(String EmployeeCode, String Password, String EmployeeName, String DOJ, String Department, String Designation, String Site, String Gender, String DOB) {
        this.EmployeeCode = EmployeeCode;
        this.Password = Password;
        this.EmployeeName = EmployeeName;
        this.DOJ = DOJ;
        this.Department = Department;
        this.Designation = Designation;
        this.Site = Site;
        this.Gender = Gender;
        this.DOB = DOB;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        //private String EmployeeCode,Password,EmployeeName,DOJ,Department,Designation,Site,Gender,DOB;
        //result.put("EmployeeCode", EmployeeCode);
        result.put("Password", Password);
//        result.put("EmployeeName", EmployeeName);
//        result.put("DOJ", DOJ);
//        result.put("Department", Department);
//        result.put("Designation", Designation);
//        result.put("Site", Site);
//        result.put("Gender", Gender);
//        result.put("DOB", DOB);
        return result;
    }
}
