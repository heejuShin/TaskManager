package user.management.oodp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

import user.management.oodp.UserDAO;

public class SignUp extends JFrame{
	boolean isIdUnique=false;
	public void SingUp() throws FileNotFoundException {
		JPanel panel = new JPanel();
		Label l1 = new Label("이름");
		Label l2 = new Label("아이디");
		Label l3 = new Label("비밀번호");
		Label l4 = new Label("소속");
		Label l5 = new Label("소개");
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		add(t1);
		add(t2);
		add(t3);
		t3.setEchoChar('*');
		add(t4);
		add(t5);
		JButton j1 = new JButton("등록");
		JButton j2 = new JButton("취소");
		JButton j3 = new JButton("중복확인");
		add(j1);
		add(j2);
		add(j3);
		l1.setBounds(40, 10, 40, 40);
		l2.setBounds(40, 50, 40, 40);
		l3.setBounds(40, 90, 60, 40);
		l4.setBounds(40, 130, 40, 40);
		l5.setBounds(40, 170, 60, 40);
		t1.setBounds(120, 10, 200, 30);
		t2.setBounds(120, 50, 200, 30);
		t3.setBounds(120, 90, 200, 30);
		t4.setBounds(120, 130, 280, 30);
		t5.setBounds(120, 180, 280, 120);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30);
		j3.setBounds(330,50,120,35);
		add(panel);
		setSize(500,400);
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO userDAO = new UserDAO();
				if(!isIdUnique) 
					JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.");
				else {
					userDAO.addUser(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
					dispose();
				}
			}
		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		j3.addActionListener(new ActionListener() {
			String str;
			String[] array;
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
			    		BufferedReader logbuff = new BufferedReader(new FileReader("user.txt"));
			    		boolean duplicate = false;
			    		while((str=logbuff.readLine())!=null){
							array=str.split("/");
							if(t2.getText().equals(array[1])) {
								JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
							    duplicate = true;
							    break;
							}else {
								continue;
							}
			    		}
			    		if(!duplicate) {
			    			isIdUnique = true;
			    			JOptionPane.showMessageDialog(null, "사용가능한 ID입니다.");
			    		}
					}
					catch (IOException E10) {
			    		E10.printStackTrace();
			    	}
			}
		});
	}
}
