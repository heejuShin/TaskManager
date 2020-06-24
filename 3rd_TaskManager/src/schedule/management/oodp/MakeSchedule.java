package schedule.management.oodp;

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

public class MakeSchedule extends JFrame {
	
	public void MakeSchedule(UserDTO user,Group group) {
		JPanel panel = new JPanel();
		Label l1 = new Label("일시");
		Label l2 = new Label("스케줄 이름");
		Label l3 = new Label("스케줄 내용");
		add(l1);
		add(l2);
		add(l3);
		l1.setBounds(40, 10, 60, 30);
		l2.setBounds(40, 50, 100, 30);
		l3.setBounds(40, 80, 100, 30);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		add(t1);
		add(t2);
		t1.setBounds(160, 50, 150, 30);
		t2.setBounds(160, 80, 150, 60);
		
		
		BufferedReader logbuff = null;
		try {
			logbuff = new BufferedReader(new FileReader("user.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 날짜와 시간 스피너
		Calendar calendar = Calendar.getInstance(); //갤린더 객체 얻기
		Date value = calendar.getTime(); //현재 시간 얻기
		
		//50년 전의 날짜를 최소 날짜로 지정
		calendar.add(Calendar.YEAR, -50); 
		Date start = calendar.getTime(); 
		
		//50년 뒤의 날짜를 최대 날짜로 지정
		calendar.add(Calendar.YEAR, 100);
		Date end = calendar.getTime(); 

		SpinnerDateModel dateModel = new SpinnerDateModel(value, start, end, Calendar.YEAR);
		JSpinner spinner1 = new JSpinner(dateModel);
		spinner1.setEditor(new JSpinner.DateEditor(spinner1, "yyyy-MM-dd HH시mm분"));
		panel.add(spinner1);
		
		JButton j1 = new JButton("등록");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30);
		
		add(panel);
		setSize(500,400);
		setTitle("스케줄 생성");
		setVisible(true);
		
		
		
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bos = new BufferedWriter(new FileWriter("schedule.txt", true));
				    	Object timeval = spinner1.getModel().getValue();
				    	bos.write(group.getName()+"/");
				    	bos.write(t1.getText()+"/");
						bos.write(timeval+"/");
					    bos.write(t2.getText()+"/");
					    bos.write("!end!\n");
						bos.close();
						JOptionPane.showMessageDialog(null, t1.getText()+" 스케줄이 생성되었습니다.");
						dispose();
				}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "스케줄 생성에 실패했습니다.");
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
