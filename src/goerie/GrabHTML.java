/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goerie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrabHTML {

    public static void Connect() throws Exception {

        ArrayList<Date> datePicker = new ArrayList();
        ArrayList Address = new ArrayList();
        ArrayList Location = new ArrayList();
        ArrayList Situation = new ArrayList();
        ArrayList SituationDone = new ArrayList();
        ArrayList Offense = new ArrayList();
        ArrayList mapit = new ArrayList();
        GrabHTML gh = new  GrabHTML();
        //Set URL3
        URL url = new URL("http://www.goerie.com/apps/pbcs.dll/cce?Site=GE&Module=4&Class=41&Category=crime01&Start=26&Count=3&FromDate=20081231&ToDate=20141007&Range=0&SortOrder=Date%28DESC%29&NavigatorFilter=");
        URLConnection spoof = url.openConnection();

        //Spoof the connection so we look like a web browser
//        spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        spoof.setRequestProperty("User-Agent", "");
        BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
        String strLine = "";
        //Loop through every line in the source
        while ((strLine = in.readLine()) != null) 
        {

            //Prints each line to the console
            Matcher m = Pattern.compile(
                    Pattern.quote("<div class=\"field date-time first left\">")
                    + "(.*?)"
                    + Pattern.quote("</div>")
            ).matcher(strLine);
            while (m.find()) 
            {

                String match = m.group(1);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match + "<");
               
                match = match.replaceAll(",<br />", " ");
        
        	SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
                String dateInString = match;		
 
	try {
 
		Date date = formatter.parse(dateInString);
                Date convertedDate = gh.convertJavaDateToSqlDate(date);
		System.out.println(date);
		System.out.println(formatter.format(date));
                datePicker.add(convertedDate);
 
	} catch (ParseException e) {
		e.printStackTrace();
	}

                
               
                //here you insert 'match' into the list

            }
            Matcher m1 = Pattern.compile(
                    Pattern.quote("<div class=\"field address left\">")
                    + "(.*?)"
                    + Pattern.quote("</div>")
            ).matcher(strLine);
            while (m1.find()) {

                String match1 = m1.group(1);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match1 + "<");
                Address.add(match1);
                //here you insert 'match' into the list

            }
            System.out.println(strLine);
            Matcher m2 = Pattern.compile(
                    Pattern.quote("<div class=\"field location last left\">")
                    + "(.*?)"
                    + Pattern.quote("</div>")
            ).matcher(strLine);
            while (m2.find()) {

                String match2 = m2.group(1);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match2 + "<");
                Location.add(match2);
                //here you insert 'match' into the list

            }
//            System.out.println(strLine);
            Matcher m3 = Pattern.compile(
                    Pattern.quote("<div class=\"field offense-2 left\">")
                    + "(.*?)"
                    + Pattern.quote("</div>")
            ).matcher(strLine);
            while (m3.find()) {

                String match3 = m3.group(1);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match3 + "<");
                Situation.add(match3);
                //here you insert 'match' into the list

            }
//            System.out.println(strLine);
            Matcher m4 = Pattern.compile(
                    Pattern.quote("position: new google.maps.LatLng(")
                    + "(.*?)"
                    + Pattern.quote("),")
            ).matcher(strLine);
            while (m4.find()) {

                String match4 = m4.group(1);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + match4 + "<");
                mapit.add(match4);
                //here you insert 'match' into the list

            }
//            System.out.println(strLine);
        }
//  FileWriter writer = new FileWriter("C:\\Users\\Bhavik Moradiya\\Desktop\\Book2.csv",false);
//   for(int i = 0 ; i<datePicker.size() ; i++)
//   {
//        writer.append((CharSequence) datePicker.get(i));
//        writer.append(',');
//             
//        writer.append((CharSequence) Address.get(i));
//        writer.append(',');
//        writer.append((CharSequence) Location.get(i));
//                    writer.append(',');
//        writer.append((CharSequence) Situation.get(i));
//                    writer.append(',');
//        writer.append((CharSequence) Offense.get(i));
//                    writer.append(',');
//            writer.append('\n');
//       
//       
//       
//       
//       System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" +datePicker.get(i));
//   }
        String s222 = "";
        for (int i = 0; i < datePicker.size(); i++) {
            System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + datePicker.get(i));
        }
        for (int i = 0; i < Address.size(); i++) 
        {
            if(Address.get(i) == "55xx MILL ST")
            {
                System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + Address.get(i));
            }
            else
            {
                System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + Address.get(i));
            }
        }
//        for (int i = 0; i < Location.size(); i++) {
//            System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + Location.get(i));
//        }
        for (int i = 0; i < Situation.size(); i++) {
//            System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + Situation.get(i));
            if (i % 2 == 0) {
//                System.out.println("this is situation : " + Situation.get(i));
                SituationDone.add(Situation.get(i));
            } else {
//                System.out.println("this is offense : " + Situation.get(i));
                Offense.add(Situation.get(i));
            }

        }
//        for (int i = 0; i < Offense.size(); i++) {
//            System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + Offense.get(i));
//        }
//        for (int i = 0; i < mapit.size(); i++) {
//            System.out.println("thi si date picker object<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>" + mapit.get(i));
//        }     

        
        //insert data into database
        
        String url1 = "jdbc:mysql://localhost:3306/";
        String dbName1 = "goerie";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
       
        String Adress1 = null;
        String Location1 = null;
        String Situation1 = null;
        String Offense1 = null;
        Timestamp lastDate1 = null;
         int id = 0;
        try {
            Class.forName(driver).newInstance();
           Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
            String selectSQL = "select * from raw_data order by id DESC limit 1";
//            String selectSQL = "select * from raw_data limit 1";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            System.out.println("Object " +rs);
            while (rs.next()) 
            {
                id  = rs.getInt("id");
               Location1 = rs.getString("Location");
               Situation1 = rs.getString("Situation");
               Offense1 = rs.getString("Offense");
               lastDate1 = rs.getTimestamp("DateOfCrime");
               Adress1 = rs.getString("Adress");
            }
            System.out.println("<><>" +Location1);
            System.out.println("<><>" +Situation1);
            System.out.println("<><>" +Offense1);
            System.out.println("<><>" +lastDate1);
            System.out.println("<><>" +Adress1);
       
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try 
        {
            int count = 0;
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
            String insertTableSQL = "INSERT INTO raw_data"
                    + "(DateOfCrime,Adress,Location,Situation,Offense,LangiAndLatitude,status1) VALUES"
                    + "(?,?,?,?,?,?,'NEW')";
            for (int i = 0; i < datePicker.size(); i++) 
            {
//                if(Location1.equals((String) Location.get(i)) && Situation1.equals((String) Situation.get(i)) && Adress1.equals((String) Address.get(i))
//                        && Offense1.equals(Offense.get(i))) 
                if(Location1.equals((String) Location.get(i)) && 
                        Adress1.equalsIgnoreCase((String) Address.get(i)) && 
                        Situation1.equals((String) SituationDone.get(i)) && 
                        lastDate1.equals(datePicker.get(i)) &&
                        Offense1.equals(Offense.get(i))) 
                {
                    System.out.println("latest : " + Address.get(i));
                    System.out.println("latest : " + Location.get(i));
                    System.out.println("latest : " + SituationDone.get(i));
                    System.out.println("latest : " + datePicker.get(i));
                    System.out.println("latest : " + Offense.get(i));
                   
                    System.out.println("this is index of new data >>>>>>>>>>>>>>" + i);
                    
                }
                else
                {
                    
                    count++;
                    System.out.println("count, it descrie how many new update we have got it right now...  " +count );
                }
            }
        if(count  > 0)
        {
             String updateSQL = "UPDATE raw_data SET status1='OLD' where status1 = 'NEW'";
             PreparedStatement preparedStatement1 = conn.prepareStatement(updateSQL);
             preparedStatement1.executeUpdate();
            
             for(int i = 0 ; i < count ;i++)
            {
                PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
                preparedStatement.setTimestamp(1, (java.sql.Timestamp) datePicker.get(i));
                preparedStatement.setString(2, (String) Address.get(i));
                preparedStatement.setString(3, (String) Location.get(i));
                preparedStatement.setString(4, (String) SituationDone.get(i));
                preparedStatement.setString(5, (String) Offense.get(i));
                preparedStatement.setString(6, (String) mapit.get(i));
                
                preparedStatement.executeUpdate();
            }
            conn.close();
        }
       }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        //generate whatever data you want
    }

    public Timestamp convertJavaDateToSqlDate(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
}
    public static void main(String[] args) {

        try {
            //Calling the Connect method
            Connect();
        } catch (Exception e) {

        }
    }
}
