package meeting.management.oopd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import meeting.management.oopd.MeetingDTO;

public class MeetingDAO {
	public MeetingDTO getMeetingDTO (String name) {
		MeetingDTO meet = new MeetingDTO();
		String str;
		String[] arr;
		try {
			BufferedReader meetingbuff = new BufferedReader(new FileReader("MeetingRecord.txt"));
			while((str=meetingbuff.readLine()) != null) {
				arr = str.split("/");
				if(name.equals(arr[0])) {
					meet.setName(arr[0]);
					meet.setDate(arr[1]);
					meet.setPlace(arr[2]);
					meet.setParti(arr[3]);
					meet.setResult(arr[4]);
					meetingbuff.close();
					return meet;
				}
			}
			meetingbuff.close();
		} catch (IOException E2) {
			E2.printStackTrace();
		}
		return null;
	}
	
	public Vector getMeetingList() {
		Vector meetingList = new Vector();
		String str;
		String[] arr;
		try {
			BufferedReader meetingbuff = new BufferedReader(new FileReader("MeetingRecord.txt"));
			while((str=meetingbuff.readLine()) != null) {
				arr = str.split("/");
				String name = arr[0];
				String date = arr[1];
				String place = arr[2];
				String parti = arr[3];
				String result = arr[4];
				
				Vector row = new Vector();
				row.add(name);
				row.add(date);
				row.add(place);
				row.add(parti);
				row.add(result);
				
				meetingList.add(row);
			}
			meetingbuff.close();
		} catch (IOException E2) {
			E2.printStackTrace();
		}
		return meetingList;
	}

	public boolean insertMeeting(MeetingDTO meet) {
		boolean ok = false;
	    
		try{  
	         
			if(r>0){
                System.out.println("가입 성공");   
                ok=true;
            }else{
                System.out.println("가입 실패");
            }

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	       
		return ok;
}


public boolean updateMember(MemberDTO vMem){
    System.out.println("dto="+vMem.toString());
    boolean ok = false;
    Connection con = null;
    PreparedStatement ps = null;
    try{
       
        con = getConn();           
        String sql = "update tb_member set name=?, tel=?, addr=?, birth=?, job=?, gender=?" +
                ", email=?,intro=? "+ "where id=? and pwd=?";
       
        ps = con.prepareStatement(sql);
       
        ps.setString(1, vMem.getName());
        ps.setString(2, vMem.getTel());
        ps.setString(3, vMem.getAddr());
        ps.setString(4, vMem.getBirth());
        ps.setString(5, vMem.getJob());
        ps.setString(6, vMem.getGender());
        ps.setString(7, vMem.getEmail());
        ps.setString(8, vMem.getIntro());
        ps.setString(9, vMem.getId());
        ps.setString(10, vMem.getPwd());
       
        int r = ps.executeUpdate(); //실행 -> 수정
        // 1~n: 성공 , 0 : 실패
       
        if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경
       
    }catch(Exception e){
        e.printStackTrace();
    }
   
    return ok;
}

public boolean deleteMember(String id, String pwd){
    
    boolean ok =false ;
    Connection con =null;
    PreparedStatement ps =null;
   
    try {
        con = getConn();
        String sql = "delete from tb_member where id=? and pwd=?";
       
        ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, pwd);
        int r = ps.executeUpdate(); // 실행 -> 삭제
       
        if (r>0) ok=true; //삭제됨;
       
    } catch (Exception e) {
        System.out.println(e + "-> 오류발생");
    }      
    return ok;
}


