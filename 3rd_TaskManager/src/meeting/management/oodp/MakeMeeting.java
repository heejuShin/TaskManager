package meeting.management.oodp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

import java.awt.*;
import java.util.*;
import java.text.*;

public class MakeMeeting extends JFrame {
	public void MakeSchedule(UserDTO user,Group group) {
		JPanel panel = new JPanel();
		Label l1 = new Label("회의 이름");
		Label l2 = new Label("회의 날짜");
		Label l3 = new Label("회의 장소");
		Label l4 = new Label("회의 참여 인원");
		Label l5 = new Label("회의 내용");
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		
		l1.setBounds(40, 10, 60, 30);
		l2.setBounds(40, 50, 100, 30);
		l3.setBounds(40, 80, 100, 30);
		l4.setBounds(40, 120, 100, 30);
		l5.setBounds(40, 180, 100, 30);
		
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		
		add(t1);
		add(t2);
		add(t3);
		add(t4);
		add(t5);
		
		t1.setBounds(160, 10, 150, 30);
		t2.setBounds(160, 50, 150, 30);
		t3.setBounds(160, 80, 150, 30);
		t4.setBounds(160, 120, 200, 30);
		t5.setBounds(160, 180, 200, 80);
		
		
		BufferedReader logbuff = null;
		try {
			logbuff = new BufferedReader(new FileReader("user.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton j1 = new JButton("등록");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30);
		
		add(panel);
		setSize(500,400);
		setTitle("회의 기록 생성");
		setVisible(true);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bos = new BufferedWriter(new FileWriter("Meeting.txt", true));
				    	
				    	bos.write(group.getName()+"/");
				    	bos.write(t1.getText()+"/");
					    bos.write(t2.getText()+"/");
					    bos.write(t3.getText()+"/");
					    bos.write(t4.getText()+"/");
					    bos.write(t5.getText()+"/");
					    bos.write("!end!\n");
						bos.close();
						JOptionPane.showMessageDialog(null, t1.getText()+" 회의 기록이 생성되었습니다.");
						dispose();
				}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "회의 기록 작성에 실패했습니다.");
				}
			}
		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	
	}
	
}
