
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
import javax.swing.table.DefaultTableModel;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import task.management.oodp.Task;
import user.management.oodp.UserDTO;

import java.awt.*;
import java.util.*;
import java.text.*;

public class ShowSchedule extends JFrame {
	
	private int i=0;
	private JFrame frame;
	private JTable tableView;
	private StringBuilder sbuilder;
	private JScrollPane scrollList;
	DefaultTableModel model;
	Vector<String> rows;

	public void ShowSchedule(UserDTO user,Group group) {
		JPanel panel = new JPanel();
		BufferedReader logbuff = null;
		ArrayList<Schedule> schedList = new ArrayList<>();
		try {
			logbuff = new BufferedReader(new FileReader("schedule.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String str;
		
		try {
			while((str=logbuff.readLine())!=null){
				//array에 파일이 한줄씩 저장됨 [0]: group / [1] : sched name /..
				String[] array = str.split("/");
				
				//같은 group에 있는 스케줄들이 schedList에 저장됨.
				if(array[0].equals(group.getName())) {
					System.out.println(" Same !! ");
					schedList.add(new Schedule(array[1],array[2],array[3]));
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<String> colNames=new Vector<>();
		colNames.addElement("Group");
		colNames.addElement("Schedule name");
		colNames.addElement("Date");
		colNames.addElement("Content");


		model=new DefaultTableModel(colNames,0);
		tableView=new JTable(model);
		panel.setLayout(new BorderLayout());
		scrollList=new JScrollPane(tableView);
		panel.add(scrollList,BorderLayout.CENTER);

		
		for(int i=0; i<schedList.size(); i++) {
			rows=new Vector<String>();
			rows.addElement(group.getName());
			rows.addElement(schedList.get(i).getSchedule());
			rows.addElement(schedList.get(i).getDate());
			rows.addElement(schedList.get(i).getContent());
			model.addRow(rows);
		}


		JButton j1 = new JButton("뒤로가기");
		add(j1);
		j1.setBounds(200, 380, 100, 30);

		add(panel);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(600,450);
		setTitle("스케줄 확인 페이지");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		//뒤로가기
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	

}
