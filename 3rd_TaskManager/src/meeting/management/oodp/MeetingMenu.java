package meeting.management.oodp;

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

public class MeetingMenu {

	public void screen(UserDTO user, Group group, Color color) {
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setTitle("Meeting Menu Page");
		f.getContentPane().setBackground(color);
		
		JLabel welcome = new JLabel(user.getName()+"님, 현재 "+ group.getName() +" 회의 관리에 접속해있습니다.");
		f.add(welcome, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
		
		JButton j1 = new JButton("회의 생성하기");
		JButton j3 = new JButton("회의 리스트 보기 ");
		buttonPanel.add(j1);
		buttonPanel.add(j3);
		
		
		f.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setVisible(true);
		
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MakeMeeting makeSched=new MakeMeeting();
				makeSched.MakeSchedule(user, group);
			}
			
		});
		
		
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowMeeting showMeet=new ShowMeeting();
				showMeet.ShowMeeting(user,group);
			}
			
		});
		
	}
}
