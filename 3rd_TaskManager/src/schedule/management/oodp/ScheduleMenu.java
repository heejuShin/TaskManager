package schedule.management.oodp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

public class ScheduleMenu {

	public void screen(UserDTO user, Group group, Color color) {
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setTitle("Schedule Menu Page");
		f.getContentPane().setBackground(color);
		
		JLabel welcome = new JLabel(user.getName()+"님, 현재 "+ group.getName() +" 스케줄 관리에 접속해있습니다.");
		f.add(welcome, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
		
		JButton j1 = new JButton("스케줄 생성하기");
		JButton j2 = new JButton("스케줄 수정하기");
		JButton j3 = new JButton("스케줄 리스트 보기 ");
		buttonPanel.add(j1);
		buttonPanel.add(j2);
		buttonPanel.add(j3);
		
		
		f.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setVisible(true);
		
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MakeSchedule makeSched=new MakeSchedule();
				makeSched.MakeSchedule(user, group);
				
			}
			
		});
		
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditSchedule editSched=new EditSchedule();
				editSched.EditSchedule(user,group);
			}
			
		});
		
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSchedule showSched=new ShowSchedule();
				showSched.ShowSchedule(user,group);
			}
			
		});
		
	}

}
