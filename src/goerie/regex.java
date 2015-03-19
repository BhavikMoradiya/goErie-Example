/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goerie;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bhavik Moradiya
 */
public class regex {

    public static void main(String args[]) {
        String str = "its a string with pattern1 aleatory pattern2 things between pattern1 and pattern2 and sometimes pattern1 pattern2 nothing";
        Matcher m = Pattern.compile(
                Pattern.quote("pattern1")
                + "(.*?)"
                + Pattern.quote("pattern2")
        ).matcher(str);
        while (m.find()) {

            String match = m.group(1);
            System.out.println(">" + match + "<");
    //here you insert 'match' into the list

        }
        String match = "Sep 29, 2012 "; 
        boolean am = match.matches("((Sep)(/s)(0[1-9][1-2][0-9]3[01]),(/s)((19|20[0-9])))");
        System.out.println("sfsf"+am);
        if(am == true)
        {
            System.out.println("heyy bhaivk you got it");
        }
//String s = "Nov 3, 2014,<br />20:04 pm";
//s = s.replaceAll(",<br />", " ");
//        System.out.println("<><> " +s);
//        
//        	SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm");
//	String dateInString = s;		
// 
//	try {
// 
//		Date date = formatter.parse(dateInString);
//                 regex s2 = new regex();
//       Date s1 = s2.convertJavaDateToSqlDate(date);
//            System.out.println("sdfsdflksdfjlksdf" + s1);
//		System.out.println(date);
//		System.out.println(formatter.format(date));
// 
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//      
//}
// public Timestamp convertJavaDateToSqlDate(java.util.Date date) {
//    return new java.sql.Timestamp(date.getTime());
//}
//        String url1 = "jdbc:mysql://localhost:3306/";
//        String dbName1 = "goerie";
//        String driver = "com.mysql.jdbc.Driver";
//        String userName = "root";
//        String password = "root";
//         String Adress = null;
//         String Location = null;
//         String Situation = null;
//         String Offense = null;
//         Timestamp lastDate = null;
//         int id = 0;
//        try {
//            Class.forName(driver).newInstance();
//           Connection conn = DriverManager.getConnection(url1 + dbName1, userName, password);
//            String selectSQL = "select * from raw_data order by id DESC limit 1";
//            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
//            ResultSet rs = preparedStatement.executeQuery(selectSQL);
//          
//            while (rs.next()) 
//            {
//                id  = rs.getInt("id");
//               Location = rs.getString("Location");
//               Situation = rs.getString("Situation");
//               Offense = rs.getString("Offense");
//               lastDate = rs.getTimestamp("DateOfCrime");
//               Adress = rs.getString("Adress");
//            }
//            
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("this is adress of last row" + Adress );
//        System.out.println("this is adress of last row" + id );
//        System.out.println("this is adress of last row" + Location );
//        System.out.println("this is adress of last row" + Situation );
//        System.out.println("this is adress of last row" + Offense );
//        System.out.println("this is adress of last row" + lastDate );
//    }
}
}
