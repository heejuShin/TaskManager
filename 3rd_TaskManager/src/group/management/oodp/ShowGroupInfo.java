package group.management.oodp;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Decorate.design.pattern.oodp.DecoName;
import Decorate.design.pattern.oodp.JustName;
import Decorate.design.pattern.oodp.PerfectTopping;
import Decorate.design.pattern.oodp.SuperTopping;
import task.management.oodp.Task;
import task.management.oodp.TaskMenu;
import schedule.management.oodp.ScheduleMenu;
import user.management.oodp.UserDTO;

public class ShowGroupInfo extends JFrame{
	
	
	public void screen(UserDTO user, Color color) {
		
		DefaultTableModel model;
		JTable tableView;
		StringBuilder sbuilder;
		JScrollPane scrollList;
		Vector<String> colNames = new Vector<>();
		colNames.addElement("그룹명");
		colNames.addElement("호스트");
		colNames.addElement("멤버");
		colNames.addElement("그룹 타입");
		BufferedReader logbuff = null;
		ArrayList<GroupDTO> groupList = new ArrayList<>();
		GroupDAO dao = new GroupDAO();
		try {
			logbuff = new BufferedReader(new FileReader("group.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String str;		
		try {
			while ((str = logbuff.readLine()) != null) {
				System.out.println("hello?");
				ArrayList<String> memberList = new ArrayList<>();
				String[] array = str.split("/");
				int j=3;
				while(!array[j].equals("!end!")) {
					System.out.println(array[j]);
					if(array[j].equals(user.getName()))
						groupList.add(dao.getGroupUsingName(array[0]));
					j++;
				}
			}
			logbuff.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model = new DefaultTableModel(colNames, 0);
		tableView = new JTable(model);
		scrollList = new JScrollPane(tableView);
		//add(scrollList, BorderLayout.CENTER);
		add(scrollList);
		scrollList.setBounds(0, 10, 500, 300);

		for (int i = 0; i < groupList.size(); i++) {
			// setting member taskList
			String allUser = "";
			for(int j=0; j<groupList.get(i).getNum()-1; j++)
				allUser += groupList.get(i).getMemberName(j)+" ";
			System.out.println(allUser);
			
			Vector<String> rows;
			rows = new Vector<String>();
			rows.addElement(groupList.get(i).getName());
			rows.addElement(groupList.get(i).getHostName());
			rows.addElement(allUser);
			rows.addElement("hello");
			model.addRow(rows);
		}
		JButton j1 = new JButton("닫기");
		add(j1);
		j1.setBounds(200, 330, 100, 30);
		
		setLayout(null);
		setSize(500,400);
		setTitle("나의 그룹 정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
	
}


//TO DO
/* 그룹 설명 Template method 사용해서 넣기
 * 그룹 수정하면 그룹 정보 저장되게
 * 그룹 삭제 하기
*/