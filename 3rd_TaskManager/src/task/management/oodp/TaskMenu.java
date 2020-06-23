package task.management.oodp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Decorate.design.pattern.oodp.DecoName;
import Decorate.design.pattern.oodp.JustName;
import Decorate.design.pattern.oodp.IsLeaderTopping;
import Decorate.design.pattern.oodp.InChargeTopping;
import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

public class TaskMenu {
	
	
	public void screen(UserDTO user, Group group, Color color, Font f1) {
		//그냥 이름 
		DecoName justName=new JustName();
		justName.setName(user.getName());
				
		//Decorate Pattern
		DecoName IsLeaderInChargeName = new IsLeaderTopping(new InChargeTopping(justName));
						
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.setVisible(true);
		f.getContentPane().setBackground(color);
		f.setTitle("Task Menu Page");
		
		JLabel welcome = new JLabel(IsLeaderInChargeName.getName()+"님, 현재 "+ group.getName() +" 업무 관리에 접속해있습니다.");
		f.add(welcome, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
		
		JButton j1 = new JButton("과제 등록하기");
		JButton j2 = new JButton("과제 리스트 보기 ");
		buttonPanel.add(j1);
		buttonPanel.add(j2);		
		
		f.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setVisible(true);
		
		welcome.setFont(f1);
		j1.setFont(f1);
		j2.setFont(f1);
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MakeTask makeTask=new MakeTask();
				makeTask.MakeTask(user, group);
				
			}
			
		});
		
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShowTask showTask=new ShowTask();
				showTask.ShowTask(user,group);
			}
			
		});
		
	}

}
