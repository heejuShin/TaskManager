package meeting.management.oodp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import group.management.oodp.Group;
import group.management.oodp.GroupDTO;
import user.management.oodp.UserDTO;

import observer.design.pattern.oodp.*;

public class ShowMeeting extends JFrame {
	private int i = 0;
	private JFrame frame;
	private JTable tableView;
	private StringBuilder sbuilder;
	private JScrollPane scrollList;
	DefaultTableModel model;
	Vector<String> rows;

	public void ShowMeeting(UserDTO user, Group group) {

		BufferedReader logbuff = null;
		ArrayList<MeetingDTO> meetList = new ArrayList<>();
		try {
			logbuff = new BufferedReader(new FileReader("Meeting.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String str;

		try {
			while ((str = logbuff.readLine()) != null) {
				ArrayList<String> memberList = new ArrayList<>();
				// array에 파일이 한줄씩 저장됨 [0]: group / [1] : task name /..
				String[] array = str.split("/");

				// 같은 group에 있는 meeting들이 meetingList에 저장됨.
				if (array[0].equals(group.getName())) {
					System.out.println(" Same !! ");
					for (int i = 4; i < array.length - 1; i++) {
						memberList.add(array[i]);
					}
					meetList.add(new MeetingDTO(array[1], array[2], array[3], array[4], memberList));

				}
			}
			logbuff.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<String> colNames = new Vector<>();
		colNames.addElement("회의 이름");
		colNames.addElement("회의 날짜");
		colNames.addElement("회의 장소");
		colNames.addElement("회의 참여 인원");
		colNames.addElement("회의 내용");

		model = new DefaultTableModel(colNames, 0);
		tableView = new JTable(model);
		scrollList = new JScrollPane(tableView);
		add(scrollList, BorderLayout.CENTER);

		for (int i = 0; i < meetList.size(); i++) {
			// setting member taskList
			String allUser = "";
			for (String users : meetList.get(i).getUserList()) {
				allUser += users + " ";
			}
			System.out.println(allUser);
			rows = new Vector<String>();
			rows.addElement(group.getName());
			rows.addElement(meetList.get(i).getName());
			rows.addElement(meetList.get(i).getDate());
			
			model.addRow(rows);
		}

		JPanel panel = new JPanel();
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));

		JTextField tfName = new JTextField(10);
		JTextField tfMember = new JTextField(10);
		JTextField tfStartday = new JTextField(8);
		JTextField tfEndday = new JTextField(8);
		panel.add(new JLabel("Meeting"));
		panel.add(tfName);
		panel.add(new JLabel("Member(member1/member2 입력)"));
		panel.add(tfMember);
		panel.add(new JLabel("StartDay"));
		panel.add(tfStartday);
		panel.add(new JLabel("EndDay"));
		panel.add(tfEndday);
		bottomPanel.add(panel);

		JPanel panel2 = new JPanel();
		JButton btnMod = new JButton("수정");
		JButton btnDel = new JButton("Delete");
		JButton btnBack = new JButton("뒤로가기");
		panel2.add(btnMod);
		panel2.add(btnDel);
		panel2.add(btnBack);
		bottomPanel.add(panel2);

		add(bottomPanel, BorderLayout.SOUTH);
		setResizable(false);
		setLocationRelativeTo(null);

		setSize(900, 500);
		setTitle("회의 확인 페이지");
		setVisible(true);
		
		//Observer Pattern
		Subscriber page = new Subscriber();
		Observer ob1 = new MeetingObserver();
		page.subscribe(ob1);

		btnMod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 줄 (row)번호 알아내기
				int rowIndex = tableView.getSelectedRow();
				System.out.println(rowIndex);
				UpdateFile(rowIndex, group, tfName, tfMember, tfStartday, tfEndday);
				// 선택 안하고 누를 경우
				if (rowIndex == -1)
					JOptionPane.showMessageDialog(null, "아무것도 선택이 되지 않았습니다.");
				// 선택하고 누를 경우
				else {
					dispose();
					
					//Observer Pattern
					page.renew();
				
					ShowMeeting showMeet=new ShowMeeting();
					showMeet.ShowMeeting(user,group);
				}
			}
		});

		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 줄 (row)번호 알아내기
				int rowIndex = tableView.getSelectedRow();
				// 선택 안하고 누를 경우
				if (rowIndex == -1)
					dispose();
				model.removeRow(rowIndex);
				deleteFile(rowIndex, group, tfName, tfMember, tfStartday, tfEndday);
				
			}
		});

		// 뒤로가기
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void UpdateFile(int rowIndex, Group group, JTextField tfName, JTextField tfMember, JTextField tfStartday,
			JTextField tfEndday) {
		String task = tfName.getText();
		String members = tfMember.getText();
		String startDate = tfStartday.getText();
		String endDate = tfEndday.getText();
		String inputData = group.getName() + "/" + task + "/" + startDate + "/" + endDate + "/" + members + "/!end!"
				+ "\r\n";
		String outputData = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("task.txt"));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {

				if (count == rowIndex) {
					outputData += inputData + "\n";
				} else {
					outputData += line + "\n";

				}
				
				count++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
		System.out.println("output: " + outputData);
		try {
			File file = new File("task.txt");
			BufferedWriter fw = new BufferedWriter(new FileWriter(file));
			fw.write(outputData);
			fw.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}

	}
	
	private void deleteFile(int rowIndex, Group group, JTextField tfName, JTextField tfMember, JTextField tfStartday,
			JTextField tfEndday) {
		String outputData = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("task.txt"));
			int count = 0;
			String line = "";
			while ((line = br.readLine()) != null) {

				if (count == rowIndex) {
					count++;
					continue;
				} else {
					outputData += line + "\n";
				}
				
				count++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
		System.out.println("output: " + outputData);
		try {
			File file = new File("task.txt");
			BufferedWriter fw = new BufferedWriter(new FileWriter(file));
			fw.write(outputData);
			fw.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}

	}


}
