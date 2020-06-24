package group.management.oodp;

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

import factory.design.pattern.oodp.*;
import group.management.oodp.*;

public class MakeGroup extends JFrame{
	boolean isNameUnique=false;
	int i=0;
	public void make(String name) throws HeadlessException {
		JPanel panel = new JPanel();
		Label l1 = new Label("그룹명");
		Label l2 = new Label("그룹 종류");
		Label l3 = new Label("멤버 선택 ( 한 번에 선택 후 '>' 버튼을 눌러주세요! )");
		JRadioButton radio[] = new JRadioButton[3];
		String group_type[] = {"School", "Company", "Other"}; 
		ButtonGroup group = new ButtonGroup();
		for(int i=0; i<3; i++) {
			radio[i]= new JRadioButton(group_type[i]);
			radio[i].setActionCommand(i+"");
			group.add(radio[i]);
			add(radio[i]);
			radio[i].setBounds(100+i*100, 50, 100, 30);
		}
		
		JButton j3 = new JButton("중복확인");
		j3.setBounds(350, 10, 80, 35);
		add(j3);
		add(l1);
		add(l2);
		add(l3);
		l1.setBounds(40, 10, 60, 30);
		l2.setBounds(40, 50, 100, 30);
		l3.setBounds(40, 80, 350, 30);
		TextField t1 = new TextField();
		add(t1);
		t1.setBounds(120, 10, 200, 30);
		
		
		BufferedReader logbuff = null;
		try {
			logbuff = new BufferedReader(new FileReader("user.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str;
		String[] array;
		String userNames[] = new String[100];
		try {
			while((str=logbuff.readLine())!=null){
				
				array=str.split("/");	
				if(!array[0].equals(name)) {
					userNames[i]=array[0];
					i++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		JList userJList;
		JList copyJList = new JList();
		JButton copyJButton;
		userJList = new JList( userNames );
		userJList.setVisibleRowCount( 10 );
		userJList.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		JScrollPane userScroll = new JScrollPane(userJList);
		add(userScroll);
		userScroll.setBounds(100, 110, 120, 200);
		copyJButton = new JButton( ">");
		add(copyJButton);
		copyJButton.setBounds(240, 200, 20, 20);

	  copyJList.setVisibleRowCount( 10 );
	  copyJList.setFixedCellWidth( 120 );
	  copyJList.setFixedCellHeight( 15 );
	  copyJList.setSelectionMode
	   (
	    ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		copyJButton.addActionListener(
				new ActionListener(){
				   public void actionPerformed(ActionEvent e) {
				    {
				      copyJList.setListData( userJList.getSelectedValues() ); //copyJList에 선택된 값을 넘김.
				     }   
				   	}
				   }	
			);
		JScrollPane copyScroll = new JScrollPane(copyJList); 
		//copyScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(copyScroll);
		copyScroll.setBounds(270, 160, 140, 100);
		
		JButton j1 = new JButton("등록");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30);
		add(panel);
		setSize(500,400);
		setTitle("그룹 생성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isNameUnique) 
					JOptionPane.showMessageDialog(null, "그룹 중복 확인을 해주세요.");
				else {
					GroupFactory factory;
					if(group.getSelection().getActionCommand().equals("0"))
						factory = new SchoolGroupFactory();
					else if (group.getSelection().getActionCommand().equals("1"))
						factory = new CompanyGroupFactory();
					else 
						factory = new OtherGroupFactory();
					String saveMem[] = new String[copyJList.getModel().getSize()];
				    for(int j=0; j<copyJList.getModel().getSize(); j++) {
				    	saveMem[j] = (String) copyJList.getModel().getElementAt(j);
				    }
					Group newG = factory.create(t1.getText(), copyJList.getModel().getSize()+1,name, saveMem);
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
			    		BufferedReader logbuff = new BufferedReader(new FileReader("group.txt"));
			    		boolean duplicate = false;
			    		while((str=logbuff.readLine())!=null){
							array=str.split("/");
							if(t1.getText().equals(array[0])) {
								JOptionPane.showMessageDialog(null, "이미 존재하는 그룹명입니다.");
							    duplicate = true;
							    break;
							}else {
								continue;
							}
			    		}
			    		if(!duplicate) {
			    			isNameUnique = true;
			    			JOptionPane.showMessageDialog(null, "사용가능한 그룹명입니다.");
			    		}
					}
					catch (IOException E10) {
			    		E10.printStackTrace();
			    	}
			}
		});
	}
	
}
