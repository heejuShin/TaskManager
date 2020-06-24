package user.management.oodp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import user.management.oodp.UserDTO;

public class UserDAO {
	public UserDTO getUserUsingId(String id){
		UserDTO user = new UserDTO();
		String str;
		String[] array;
		try {
			BufferedReader logbuff = new BufferedReader(new FileReader("user.txt"));
    		while((str=logbuff.readLine())!=null){
	    			array=str.split("/");
	    			if(id.equals(array[1])) {
		    			user.setName(array[0]);
		    			user.setId(array[1]);
		    			user.setPassword(array[2]);
		    			user.setDepartment(array[3]);
		    			user.setIntro(array[4]);
		    			logbuff.close();
		    			return user;
	    			}
    		}
    		logbuff.close();
		}catch (IOException E10) {
    		E10.printStackTrace();
    	}
		return null;
	}
	
	public void addUser(String name, String id, String password, String department, String intro) {
		try {
			BufferedWriter bos = new BufferedWriter(new FileWriter("user.txt", true));
				bos.write(name+"/");
				bos.write(id+"/");
				bos.write(password+"/");
				bos.write(department+"/");
				bos.write(intro+"\r\n");
				bos.close();
				JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!");
		}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.");
		}
	}
}
