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
		Label l1 = new Label("�׷��");
		Label l2 = new Label("�׷� ����");
		Label l3 = new Label("��� ���� ( �� ���� ���� �� '>' ��ư�� �����ּ���! )");
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
		
		JButton j3 = new JButton("�ߺ�Ȯ��");
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
				      copyJList.setListData( userJList.getSelectedValues() ); //copyJList�� ���õ� ���� �ѱ�.
				     }   
				   	}
				   }	
			);
		JScrollPane copyScroll = new JScrollPane(copyJList); 
		//copyScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(copyScroll);
		copyScroll.setBounds(270, 160, 140, 100);
		
		JButton j1 = new JButton("���");
		JButton j2 = new JButton("���");
		add(j1);
		add(j2);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30);
		add(panel);
		setSize(500,400);
		setTitle("�׷� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		j1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isNameUnique) 
					JOptionPane.showMessageDialog(null, "�׷� �ߺ� Ȯ���� ���ּ���.");
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
								JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �׷���Դϴ�.");
							    duplicate = true;
							    break;
							}else {
								continue;
							}
			    		}
			    		if(!duplicate) {
			    			isNameUnique = true;
			    			JOptionPane.showMessageDialog(null, "��밡���� �׷���Դϴ�.");
			    		}
					}
					catch (IOException E10) {
			    		E10.printStackTrace();
			    	}
			}
		});
	}
	
}
