/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bhavik
 */
public class NewGoErie {

    ArrayList Address = new ArrayList();
    ArrayList<Date> datePicker = new ArrayList();
    GrabHTML gh = new GrabHTML();
    ArrayList Offense = new ArrayList();
    ArrayList Situation = new ArrayList();
    ArrayList mapit = new ArrayList();
    ArrayList Address1 = new ArrayList();
    ArrayList Location = new ArrayList();

    int index = 40;
     static Boolean s = true;

    public void getData() {
        try {
            URL goerie = new URL("http://www.goerie.com/local/crime/map/");
            URLConnection yc = goerie.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc
                    .getInputStream()));
            String strLine;

            //Address
            while ((strLine = in.readLine()) != null) {
                System.out.println(strLine);
                Matcher m1 = Pattern.compile(
                        Pattern.quote("<p style=\"margin:0\"><strong>Where: </strong> ")
                        + "(.*?)"
                        + Pattern.quote("</p>")
                ).matcher(strLine);
                while (m1.find()) {
                    String match1 = m1.group(1);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match1);
                    Address.add(match1);
                    //here you insert 'match' into the list
                }

                //Date
//               ------------------------------------------------- -----
                Matcher m = Pattern.compile(
                        Pattern.quote("<p style=\"margin:0\"><strong>Date/Time: </strong> ")
                        + "(.*?)"
                        + Pattern.quote("</p>")
                ).matcher(strLine);
                while (m.find()) {

                    String match = m.group(1);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match + "<");

                    DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, HH:mm");
                    String dateInString = match;

                    try {

                        Date date = formatter.parse(dateInString);
                        Date convertedDate = gh.convertJavaDateToSqlDate(date);
                        datePicker.add(convertedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //--------------------------------------------------------------------------------------  
                Matcher m3 = Pattern.compile(
                        Pattern.quote("<p style=\"margin:0\"><strong>Offense: </strong> ")
                        + "(.*?)"
                        + Pattern.quote("</p>")
                ).matcher(strLine);
                while (m3.find()) {

                    String match3 = m3.group(1);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match3 + "<");
                    Offense.add(match3);
                    //here you insert 'match' into the list

                }
        //-----------------------------------------------------
                //--------------------------------------------------------------------------------------  
                Matcher m4 = Pattern.compile(
                        Pattern.quote("title: '")
                        + "(.*?)"
                        + Pattern.quote("',")
                ).matcher(strLine);
                while (m4.find()) {

                    String match4 = m4.group(1);
                    System.out.println("<><><>" + match4 + "<");
                    Situation.add(match4);
                    //here you insert 'match' into the list

                }
                //-----------------------------------------------------
                Matcher m5 = Pattern.compile(
                        Pattern.quote("position: new google.maps.LatLng(")
                        + "(.*?)"
                        + Pattern.quote("),")
                ).matcher(strLine);
                while (m5.find()) {

                    String match5 = m5.group(1);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match5 + "<");
                    mapit.add(match5);
                    //here you insert 'match' into the list

                }
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void locationAddress() 
    {
        String[] s;
        for (int i = 0; i < Address.size(); i++) {
//           System.out.println(Address.get(i));
            s = Address.get(i).toString().split(",");
            Address1.add(s[0]);
            Location.add(s[1].trim());

        }
        System.out.println("this is size of Address" + Address1.size());
        System.out.println("this is size of Address" + mapit.size());
        System.out.println("this is size of Address" + Situation.size());
        System.out.println("this is size of Address" + Location.size());
        System.out.println("this is size of Address" + datePicker.size());
        System.out.println("this is size of Address" + Offense.size());

        
//        Collections.sort(Address1, Collections.reverseOrder());
        
        for(int i = 40-1 ; i >= 0  ; i--)
        {
            System.out.println("this is size of the reversser order+ <" + Address1.get(i));
        }
    }

    public void insertData() 
    {
        String url1 = "jdbc:mysql://localhost:3306/";
        String dbName1 = "goerie";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";

        
//        
        ArrayList s_address = new ArrayList();
        ArrayList<Date> s_Date = new ArrayList<>();
        int id = 0;
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
            String selectSQL = "SELECT * FROM raw_data ORDER BY id DESC LIMIT 40";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            System.out.println("Object " + rs);

            String add;
            String location;
            Timestamp lastDate2 = null;

            while (rs.next()) 
            {
//                System.out.println(rs.getString("Adress"));
                s_address.add(rs.getString("Adress"));
                s_Date.add(rs.getTimestamp("DateOfCrime"));
            }

            System.out.println("size of Date " + s_Date.size());
            System.out.println("size of address " + s_address.size());
           
            
            for (int i = 0; i < datePicker.size(); i++) 
            {
                add = (String) Address1.get(i);
                location = (String) Location.get(i);
                lastDate2 = (Timestamp) datePicker.get(i);

                System.out.println("address from webpage   " + add);
                System.out.println("address from databasr   " + s_address.get(i).toString());
                
                for(int j = 0 ; j <  datePicker.size() ; j++)
                {
                    
                    System.out.println("this is web inside + " + add);
                    System.out.println("this is wdata inside+ " + s_address.get(j).toString());
                    
                    if(add.equalsIgnoreCase(s_address.get(j).toString()) && lastDate2.equals(s_Date.get(j)))
                    {
                        System.out.println("this is <<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>update ++" + i);
                        index = i;
                        s = false;
                        inserDataAfterCheck();
                        break;
                    }
                   break;
                
                
                }
//                break;
//                if (add.equalsIgnoreCase(s_address.get(i).toString()) && lastDate2.equals(s_Date.get(i))) 
//                {
//                    if (i == 0) 
//                    {
//
//                        System.out.println("You don't have any update right now");
//                        s = false;
//                        break;
//                    } 
//                    else 
//                    {
//                        
//                        System.out.println("this is index of update data <<<<<<<<<<<<<>>>>>>>>>>" + index);
////                        inserDataAfterCheck();
//                        index = i-1;
//                        
//                        s = false;
//                        break;
//                    }
//                }
//                else
//                {
//                    index = i;
//                    System.out.println("index full index" +index);
//                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
//        try 
//        {
//            int count = 0;
//            Class.forName(driver).newInstance();
//            Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
//            String insertTableSQL = "INSERT INTO raw_data"
//                    + "(DateOfCrime,Adress,Location,Situation,Offense,LangiAndLatitude,status1) VALUES"
//                    + "(?,?,?,?,?,?,'NEW')";
//            for (int i = 0; i < datePicker.size(); i++) 
//            {
////                if(Location1.equals((String) Location.get(i)) && Situation1.equals((String) Situation.get(i)) && Adress1.equals((String) Address.get(i))
////                        && Offense1.equals(Offense.get(i))) 
//                if(Location1.equals((String) Location.get(i)) && 
//                        Adress1.equalsIgnoreCase((String) Address.get(i)) && 
//                        Situation1.equals((String) Situation.get(i)) && 
//                        lastDate1.equals(datePicker.get(i)) &&
//                        Offense1.equals(Offense.get(i))) 
//                {
//                    System.out.println("latest : " + Address.get(i));
//                    System.out.println("latest : " + Location.get(i));
//                    System.out.println("latest : " + Situation.get(i));
//                    System.out.println("latest : " + datePicker.get(i));
//                    System.out.println("latest : " + Offense.get(i));
//                   
//                    System.out.println("this is index of new data >>>>>>>>>>>>>>" + i);
//                    
//                }
//                else
//                {
//                    
//                    count++;
//                    System.out.println("count, it descrie how many new update we have got it right now...  " +count );
//                }
//            }
//        if(count  > 0)
//        {
//             String updateSQL = "UPDATE raw_data SET status1='OLD' where status1 = 'NEW'";
//             PreparedStatement preparedStatement1 = conn.prepareStatement(updateSQL);
//             preparedStatement1.executeUpdate();
//            
//             for(int i = 0 ; i < count ;i++)
//            {
//                PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
//                preparedStatement.setTimestamp(1, (java.sql.Timestamp) datePicker.get(i));
//                preparedStatement.setString(2, (String) Address1.get(i));
//                preparedStatement.setString(3, (String) Location.get(i));
//                preparedStatement.setString(4, (String) Situation.get(i));
//                preparedStatement.setString(5, (String) Offense.get(i));
//                preparedStatement.setString(6, (String) mapit.get(i));
//                
//                preparedStatement.executeUpdate();
//            }
//            conn.close();
//        }
//       }
//        catch(Exception e)
//        {
//        
//        }
//                
//   
//   

    }
    
    public void inserDataAfterCheck()
    {
        try
        {
        String url1 = "jdbc:mysql://localhost:3306/";
        String dbName1 = "goerie";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
            Class.forName(driver).newInstance();
           Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
          
           String updateSQL = "UPDATE raw_data SET status1='OLD' where status1 = 'NEW'";
           PreparedStatement preparedStatement1 = conn.prepareStatement(updateSQL);
           preparedStatement1.executeUpdate();
           
           String insertTableSQL = "INSERT INTO raw_data"
                    + "(DateOfCrime,Adress,Location,Situation,Offense,LangiAndLatitude,status1) VALUES"
                    + "(?,?,?,?,?,?,'NEW')";
            System.out.println("index"+Address1.size());
               for(int i= index-1 ;i>=0 ; i--)
            {
                PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
                preparedStatement.setTimestamp(1, (java.sql.Timestamp) datePicker.get(i));
                preparedStatement.setString(2, (String) Address1.get(i));
                preparedStatement.setString(3, (String) Location.get(i));
                preparedStatement.setString(4, (String) Situation.get(i));
                preparedStatement.setString(5, (String) Offense.get(i));
                preparedStatement.setString(6, (String) mapit.get(i));
                
                preparedStatement.executeUpdate();
            }
            conn.close();
        
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        datePicker.clear();
        Address.clear();
        Location.clear();
        Situation.clear();
        Offense.clear();
        mapit.clear();     

       
    }

    public Timestamp convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static void main(String[] args) {
        NewGoErie nge = new NewGoErie();
        nge.getData();
        nge.locationAddress();
        nge.insertData();
        
        if(s == true)
        {
            nge.inserDataAfterCheck();
        }
        
        System.out.println("THIS IS BOOLEAN VARIABLE  " + s);
    }

}
