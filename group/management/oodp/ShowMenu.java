package group.management.oodp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ShowMenu extends JFrame implements ActionListener{
	
	private String userName = "���ϼ�";
	
	public void screen() {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.setVisible(true);
		
		JLabel welcome = new JLabel("ȯ���մϴ�.	"+ userName +" ��!!");
		f.add(welcome, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton groupButton = new JButton("�׷� ����/����");
		groupButton.addActionListener(this);
		buttonPanel.add(groupButton);
		
		JButton taskButton = new JButton("���� ����");
		taskButton.addActionListener(this);
		buttonPanel.add(taskButton);
		
		JButton meetingButton = new JButton("ȸ�� ����");
		meetingButton.addActionListener(this);
		buttonPanel.add(meetingButton);
		
		JButton schduleButton = new JButton("������ ����");
		schduleButton.addActionListener(this);
		buttonPanel.add(schduleButton);
		
		JButton optionButton = new JButton("�ɼ� ����");
		optionButton.addActionListener(this);
		buttonPanel.add(optionButton);
		
		f.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�׷� ����/����")) {}
		else if (e.getActionCommand().equals("���� ����")) {}
		else if (e.getActionCommand().equals("ȸ�� ����")) {}
		else if (e.getActionCommand().equals("������ ����")) {}
		else {}
	}

}
